import java.io.*;

public class Edl {
	
	// nombre max de modules, taille max d'un code objet d'une unite
	static final int MAXMOD = 5, MAXOBJ = 1000;
	// nombres max de references externes (REF) et de points d'entree (DEF)
	// pour une unite
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
				tabDesc[nMod] = new Descripteur();
				tabDesc[nMod].lireDesc(s);
				if (!tabDesc[nMod].getUnite().equals("module"))
					erreur(FATALE, "module attendu");
			}
		}
	}

	
	static void constMap() {
		// f2 = fichier ex�cutable .map construit
		OutputStream f2 = Ecriture.ouvrir(nomProg + ".map");
		if (f2 == null)
			erreur(FATALE, "creation du fichier " + nomProg
					+ ".map impossible");
		// pour construire le code concatene de toutes les unites
		int[] po = new int[(nMod + 1) * MAXOBJ + 1];

		
		
		Ecriture.fermer(f2);
		// cr�ation du fichier en mnemonique correspondant
		Mnemo.creerFichier(ipo, po, nomProg + ".ima");
	}

	static void printTables() {

		System.out.println("\n Table TransDon:");
		for (int i = 0; i <= nMod; i++)
			System.out.println("[" + i + "]" + " => " + transDon[i]);

		System.out.println("\n Table TransCode:");
		for (int i = 0; i <= nMod; i++)
			System.out.println("[" + i + "]" + " => " + transCode[i]);

		System.out.println("\n Table DicoDef:");
		for (int i = 1; i <= flagDicoDef; i++)
			System.out.println("[" + i + "]" + " => (" + dicoDef[i].nomProc + ", " + dicoDef[i].adPo + ", " + dicoDef[i].nbParam + ")");

		System.out.println("\n Table AdFinale:");
		for (int y = 0; y <= nMod; y++) {
			System.out.print("[" + y + "]" + " => ");

			for (int x = 1; x <= tabDesc[y].getNbRef(); x++)
				System.out.print("[" + adFinale[y][x] + "] ");

			if (tabDesc[y].getNbRef() == 0)
				System.out.print("Pas de reference");

			System.out.println("");
		}
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
		for (int i = 1; i <= nMod; i++) { // Remplissage de transDon, transCode et dicoDef
			transDon[i] = transDon[i - 1] + tabDesc[i - 1].getTailleGlobaux();
			transCode[i] = transCode[i - 1] + tabDesc[i - 1].getTailleCode();
			for (int j = 1; j <= tabDesc[i].getNbDef(); j++) { // Union des tabDef
				for (int k = 0; k < nbDef; k++) // On parcourt les def déjà ajoutées
					if (dicoDef[k].nomProc.equals(tabDesc[i].getDefNomProc(j)))
						erreur(FATALE, tabDesc[i].getDefNomProc(j) + " est déjà définie.");
				tabDesc[i].modifDefAdPo(j, transCode[i]);
				dicoDef[nbDef] = desc.new EltDef(tabDesc[i].getDefNomProc(j), tabDesc[i].getDefAdPo(j), tabDesc[i].getDefNbParam(j));
				nbDef++;
			}
		}
		
		System.out.println("\n Table TransDon:");
		for (int i = 0; i <= nMod; i++)
			System.out.println("[" + i + "]" + " => " + transDon[i]);

		System.out.println("\n Table TransCode:");
		for (int i = 0; i <= nMod; i++)
			System.out.println("[" + i + "]" + " => " + transCode[i]);

		System.out.println("\n Table DicoDef:");
		for (int i = 0; i < nbDef; i++)
			System.out.println("[" + i + "]" + " => (" + dicoDef[i].nomProc + ", " + dicoDef[i].adPo + ", " + dicoDef[i].nbParam + ")");

		
        for (int i=0; i<=nMod; i++) { // Remplissage de adFinale
            for (int j=1; j<=tabDesc[i].getNbRef(); j++) {
            	int k = 0;
                for (k=0; k<nbDef; k++) // On cherche la ref dans dicoDef (même nom et nb de paramètres)
                    if (dicoDef[k].nomProc.equals(tabDesc[i].getRefNomProc(j)) && dicoDef[k].nbParam == tabDesc[i].getRefNbParam(j)) {
                        adFinale[i][j] = dicoDef[k].adPo;
                        break;
                    }
                if (k >= nbDef) erreur(FATALE, "référence introuvable : " + tabDesc[i].getRefNomProc(j));
            }
        }
		
		
		if (nbErr > 0) {
			System.out.println("programme ex�cutable non produit");
			System.exit(1);
		}
		
		// Phase 2 de l'edition de liens
		// -----------------------------
		//printTables();
		
		constMap();				// a completer
		System.out.println("Edition de liens terminee");
	}
}
