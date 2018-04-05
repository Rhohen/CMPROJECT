import java.io.*;

public class Edl {
	
	// nombre max de modules, taille max d'un code objet d'une unite
	static final int MAXMOD = 5, MAXOBJ = 1000;
	// nombres max de references externes (REF) et de points d'entree (DEF)
	// pour une unite
	@SuppressWarnings("unused")
	private static final int MAXREF = 10, MAXDEF = 10;
	
	// typologie des erreurs
	private static final int FATALE = 0, NONFATALE = 1;
	
	// valeurs possibles du vecteur de translation
	private static final int TRANSDON=1,TRANSCODE=2,REFEXT=3;
	
	// table de tous les descripteurs concernes par l'edl
	static Descripteur[] tabDesc = new Descripteur[MAXMOD + 1];
	
	// declarations de variables A COMPLETER SI BESOIN
	static int ipo, nMod, nbErr;
	static String nomProg;
	
	// descripteur associe a un programme objet
    private static Descripteur desc;
    static String[] nomUnites = new String[MAXMOD+1];
    
	static int[] transDon = new int[MAXMOD+1];
	static int[] transCode = new int[MAXMOD+1];
	static int[][] adFinale = new int[MAXMOD+1][MAXDEF+1];
	static Descripteur.EltDef[] dicoDef = new Descripteur.EltDef[(MAXMOD+1)*MAXDEF];
	static int flagDicoDef = 0;
	static int nbDef = 0;
	
	// utilitaire de traitement des erreurs
	// ------------------------------------
	static void erreur(int te, String m) {
		System.out.println(m);
		if (te == FATALE) {
			System.out.println("ABANDON DE L'EDITION DE LIENS");
			System.exit(1);
		}
		nbErr = nbErr + 1;
	}

	// utilitaire de remplissage de la table des descripteurs tabDesc
	// --------------------------------------------------------------
	static void lireDescripteurs() {
		String s;
		System.out.println("les noms doivent etre fournis sans suffixe");
		System.out.print("nom du programme : ");
		s = Lecture.lireString();
		nomUnites[0]=s;
		tabDesc[0] = new Descripteur();
		tabDesc[0].lireDesc(s);
		if (!tabDesc[0].getUnite().equals("programme"))
			erreur(FATALE, "programme attendu");
		nomProg = s;
		
		nMod = 0;
		while (!s.equals("") && nMod < MAXMOD) {
			System.out.print("nom de module " + (nMod + 1)
					+ " (RC si termine) ");
			s = Lecture.lireString();
			if (!s.equals("")) {
				nMod = nMod + 1;
				nomUnites[nMod] = s;
				tabDesc[nMod] = new Descripteur();
				tabDesc[nMod].lireDesc(s);
				if (!tabDesc[nMod].getUnite().equals("module"))
					erreur(FATALE, "module attendu");
			}
		}
	}

	static void constMap() {
		// f2 = fichier executable .map construit
		OutputStream f2 = Ecriture.ouvrir(nomProg + ".map");
		if (f2 == null)
			erreur(FATALE, "creation du fichier " + nomProg
					+ ".map impossible");
		// pour construire le code concatene de toutes les unites
		int[] po = new int[(nMod + 1) * MAXOBJ + 1];

		ipo = 1;
		for (int i = 0; i <= nMod; i++) {
			InputStream unite = Lecture.ouvrir(nomUnites[i] + ".obj");
			if (unite == null)
				erreur(FATALE, nomUnites[i] + ".obj illisible");
			int[] vecteurTranslation = new int[MAXOBJ + 1];
			for (int j = 1; j <= MAXOBJ; j++)
				vecteurTranslation[j] = -1;

			for (int j = 0; j < tabDesc[i].getNbTransExt(); j++) {
				int at = Lecture.lireInt(unite) + transCode[i];
				vecteurTranslation[at] = Lecture.lireIntln(unite);
			}

			for (int j = 0; j < tabDesc[i].getTailleCode(); j++) {
				po[ipo] = Lecture.lireIntln(unite);
				switch (vecteurTranslation[ipo]) {
				case TRANSDON:
					po[ipo] += transDon[i];
					break;
				case TRANSCODE:
					po[ipo] += transCode[i];
					break;
				case REFEXT:
					po[ipo] = adFinale[i][po[ipo]];
					break;
				}
				ipo++;
			}
			Lecture.fermer(unite);
		}
		ipo--;
		po[2] = transDon[nMod] + tabDesc[nMod].getTailleGlobaux();
		for (int i = 1; i <= ipo; i++)
			Ecriture.ecrireStringln(f2, "" + po[i]);
		
		Ecriture.fermer(f2);
		// cr�ation du fichier en mnemonique correspondant
		Mnemo.creerFichier(ipo, po, nomProg + ".ima");
	}
	
	public static void main(String argv[]) {
		System.out.println("EDITEUR DE LIENS / PROJET LICENCE");
		System.out.println("---------------------------------");
		System.out.println("");
		nbErr = 0;
		ipo = 1;

		// Phase 1 de l'edition de liens
		// -----------------------------
		lireDescripteurs();		// lecture des descripteurs a completer si besoin

		// Construire dicoDef, adFinale, transCode, TransDon
		// TransCode = bsifaux ect
		// TransDon = varGlob
		

		desc = new Descripteur();

		transDon[0] = 0;
		transCode[0] = 0;
		for (int unite = 1; unite <= nMod; unite++) { // Remplissage de transDon, transCode et dicoDef
			transDon[unite] = transDon[unite - 1] + tabDesc[unite - 1].getTailleGlobaux();
			transCode[unite] = transCode[unite - 1] + tabDesc[unite - 1].getTailleCode();
			for (int i = 1; i <= tabDesc[unite].getNbDef(); i++) { // Union des tabDef
				for (int k = 0; k < nbDef; k++) // On parcourt les def déjà ajoutées
					if (dicoDef[k].nomProc.equals(tabDesc[unite].getDefNomProc(i)))
						erreur(NONFATALE, tabDesc[unite].getDefNomProc(i) + " est déjà définie.");
				dicoDef[nbDef] = desc.new EltDef(tabDesc[unite].getDefNomProc(i), tabDesc[unite].getDefAdPo(i) + transCode[unite], tabDesc[unite].getDefNbParam(i));
				nbDef++;
			}
		}
		
		String messageErreur = "";
		int erreurType = FATALE;
		for (int unite = 0; unite <= nMod; unite++) { // Remplissage de adFinale
			for (int i = 1; i <= tabDesc[unite].getNbRef(); i++) {
				int j = 0;
				for (j = 0; j < nbDef; j++) // On cherche la ref dans dicoDef
					if (!dicoDef[j].nomProc.equals(tabDesc[unite].getRefNomProc(i))) {
						messageErreur = "reference introuvable : " + tabDesc[unite].getRefNomProc(i);
						erreurType = NONFATALE;
					} else if (!(dicoDef[j].nbParam == tabDesc[unite].getRefNbParam(i))) {
						messageErreur = "nombre de params incorrect pour : " + tabDesc[unite].getRefNomProc(i);
						erreurType = FATALE;
					} else {
						adFinale[unite][i] = dicoDef[j].adPo;
						break;
					}
				if (j >= nbDef)
					erreur(erreurType, messageErreur);
			}
		}

		if (nbErr > 0) {
			System.out.println("programme executable non produit");
			System.exit(1);
		}
		
		// Phase 2 de l'edition de liens
		// -----------------------------
		
		constMap();
		System.out.println("Edition de liens terminee");
	}
}
