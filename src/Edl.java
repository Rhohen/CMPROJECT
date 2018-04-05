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
    
    // Permet de stocker les noms des unites pour trouver plus tard le fichier obj
    static String[] nomUnites = new String[MAXMOD+1];
    
	static int[] transDon = new int[MAXMOD+1]; // Tableau transDon
	static int[] transCode = new int[MAXMOD+1]; // Tableau transCode
	static int[][] adFinale = new int[MAXMOD+1][MAXDEF+1]; // Tableau adFinale
	static Descripteur.EltDef[] dicoDef = new Descripteur.EltDef[(MAXMOD+1)*MAXDEF]; // Tableau dicoDef
	static int nbDef = 0; // Nombre de definitions
	
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
		for (int unite = 0; unite <= nMod; unite++) {
			 // On ouvre le fichier unite
			InputStream uniteFichier = Lecture.ouvrir(nomUnites[unite] + ".obj");
			
			// On sort si il y a un probleme lors de l ouverture du fichier unite
			if (uniteFichier == null)
				erreur(FATALE, nomUnites[unite] + ".obj illisible");
			
			int[] vecteurTranslation = new int[MAXOBJ + 1];
			for (int j = 1; j <= MAXOBJ; j++)
				vecteurTranslation[j] = -1;
			
			 // On recupere sa liste de translations
			for (int j = 0; j < tabDesc[unite].getNbTransExt(); j++) {
				int at = Lecture.lireInt(uniteFichier) + transCode[unite];
				vecteurTranslation[at] = Lecture.lireIntln(uniteFichier);
			}

			// Concatenation du code actuel au code general
			for (int j = 0; j < tabDesc[unite].getTailleCode(); j++) {
				po[ipo] = Lecture.lireIntln(uniteFichier);
				// Pour chaque translation, son code associé
				switch (vecteurTranslation[ipo]) { 
				case TRANSDON:
						po[ipo] += transDon[unite];
					break;
				case TRANSCODE:
						po[ipo] += transCode[unite];
					break;
				case REFEXT:
						po[ipo] = adFinale[unite][po[ipo]];
					break;
				}
				ipo++;
			}
			Lecture.fermer(uniteFichier);
		}
		
		// Fin de la concatenation
		ipo--;
		 // On met a jour RESERVER X par le nombre de variable globales totales
		po[2] = transDon[nMod] + tabDesc[nMod].getTailleGlobaux();
		
		// On ecrit dans le fichier
		for (int i = 1; i <= ipo; i++)
			Ecriture.ecrireStringln(f2, "" + po[i]);
		
		Ecriture.fermer(f2);
		// Creation du fichier en mnemonique correspondant
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
		for (int unite = 1; unite <= nMod; unite++) { // On remplit transDon, transCode et dicoDef pour chaque unite
			transDon[unite] = transDon[unite - 1] + tabDesc[unite - 1].getTailleGlobaux();
			transCode[unite] = transCode[unite - 1] + tabDesc[unite - 1].getTailleCode();
			for (int i = 1; i <= tabDesc[unite].getNbDef(); i++) { // Regroupement des tabDef dans dicoDef
				for (int k = 0; k < nbDef; k++) // On parcourt les def ajoutees pour verifier si elle n'a pas ete deja rajoutee
					if (dicoDef[k].nomProc.equals(tabDesc[unite].getDefNomProc(i)))
						erreur(NONFATALE, tabDesc[unite].getDefNomProc(i) + " a deja ete definie.");
				// Ajout de la def au dicoDef
				dicoDef[nbDef] = desc.new EltDef(tabDesc[unite].getDefNomProc(i),
						tabDesc[unite].getDefAdPo(i) + transCode[unite], tabDesc[unite].getDefNbParam(i));
				nbDef++;
			}
		}
		
		String messageErreur = "";
		int erreurType = FATALE, j;
		// On remplit adFinale
		for (int unite = 0; unite <= nMod; unite++) { 
			for (int i = 1; i <= tabDesc[unite].getNbRef(); i++) {
				j = 0;
				// On cherche la ref dans dicoDef
				for (j = 0; j < nbDef; j++)
					if (!dicoDef[j].nomProc.equals(tabDesc[unite].getRefNomProc(i))) {
						messageErreur = "La reference suivant est introuvable : " + tabDesc[unite].getRefNomProc(i);
						erreurType = NONFATALE;
					} else if (!(dicoDef[j].nbParam == tabDesc[unite].getRefNbParam(i))) {
						messageErreur = "Le nombre de params est incorrect pour : " + tabDesc[unite].getRefNomProc(i);
						erreurType = FATALE;
					} else {
						adFinale[unite][i] = dicoDef[j].adPo;
						break; // On quitte la boucle quand on a ajoute correctement la valeur a adFinale
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
