/*********************************************************************************
 * VARIABLES ET METHODES FOURNIES PAR LA CLASSE UtilLex (cf libclass)            *
<<<<<<< HEAD
 *       complement à l'ANALYSEUR LEXICAL produit par ANTLR                      *
=======
 *       complement Ã  l'ANALYSEUR LEXICAL produit par ANTLR                      *
>>>>>>> 58def3bff02d4c2b7e6662a390fbad37c3af6638
 *                                                                               *
 *                                                                               *
 *   nom du programme compile, sans suffixe : String UtilLex.nomSource           *
 *   ------------------------                                                    *
 *                                                                               *
 *   attributs lexicaux (selon items figurant dans la grammaire):                *
 *   ------------------                                                          *
 *     int UtilLex.valNb = valeur du dernier nombre entier lu (item nbentier)    *
 *     int UtilLex.numId = code du dernier identificateur lu (item ident)        *
 *                                                                               *
 *                                                                               *
 *   methodes utiles :                                                           *
 *   ---------------                                                             *
 *     void UtilLex.messErr(String m)  affichage de m et arret compilation       *
 *     String UtilLex.repId(int nId) delivre l'ident de codage nId               *
 *     void afftabSymb()  affiche la table des symboles                          *
 *********************************************************************************/


import java.io.*;

// classe de mise en oeuvre du compilateur
// =======================================
// (verifications semantiques + production du code objet)

public class PtGen {
    

    // constantes manipulees par le compilateur
    // ----------------------------------------

	private static final int 
	
	// taille max de la table des symboles
	MAXSYMB=300,

	// codes MAPILE :
	RESERVER=1,EMPILER=2,CONTENUG=3,AFFECTERG=4,OU=5,ET=6,NON=7,INF=8,
	INFEG=9,SUP=10,SUPEG=11,EG=12,DIFF=13,ADD=14,SOUS=15,MUL=16,DIV=17,
	BSIFAUX=18,BINCOND=19,LIRENT=20,LIREBOOL=21,ECRENT=22,ECRBOOL=23,
	ARRET=24,EMPILERADG=25,EMPILERADL=26,CONTENUL=27,AFFECTERL=28,
	APPEL=29,RETOUR=30,

	// codes des valeurs vrai/faux
	VRAI=1, FAUX=0,

    // types permis :
	ENT=1,BOOL=2,NEUTRE=3,

	// categories possibles des identificateurs :
	CONSTANTE=1,VARGLOBALE=2,VARLOCALE=3,PARAMFIXE=4,PARAMMOD=5,PROC=6,
	DEF=7,REF=8,PRIVEE=9,

    //valeurs possible du vecteur de translation 
    TRANSDON=1,TRANSCODE=2,REFEXT=3;


    // utilitaires de controle de type
    // -------------------------------
    
	private static void verifEnt() {
		if (tCour != ENT)
			UtilLex.messErr("expression entiere attendue");
	}

	private static void verifBool() {
		if (tCour != BOOL)
			UtilLex.messErr("expression booleenne attendue");
	}

    // pile pour gerer les chaines de reprise et les branchements en avant
    // -------------------------------------------------------------------

    private static TPileRep pileRep;  


    // production du code objet en memoire
    // -----------------------------------

    private static ProgObjet po;
    
    
    // COMPILATION SEPAREE 
    // -------------------
    //
    // modification du vecteur de translation associe au code produit 
    // + incrementation attribut nbTransExt du descripteur
    // NB: effectue uniquement si c'est une reference externe ou si on compile un module
    private static void modifVecteurTrans(int valeur) {
		if (valeur == REFEXT || desc.getUnite().equals("module")) {
			po.vecteurTrans(valeur);
			desc.incrNbTansExt();
		}
	}
    
    // descripteur associe a un programme objet
    private static Descripteur desc;

     
    // autres variables fournies
    // -------------------------
    public static String trinome="CroqGiraultJouin"; // MERCI de renseigner ici un nom pour le trinome, constitue de exclusivement de lettres
    
    private static int tCour; // type de l'expression compilee
    private static int vCour; // valeur de l'expression compilee le cas echeant
  
    private static int nbVarGlb; //iterateur pour remplir tabSymb avec les var globales
    
    // Definition de la table des symboles
    private static EltTabSymb[] tabSymb = new EltTabSymb[MAXSYMB + 1];
    
    // it = indice de remplissage de tabSymb
    // bc = bloc courant (=1 si le bloc courant est le programme principal)
	private static int it, bc;
	
	// utilitaire de recherche de l'ident courant (ayant pour code UtilLex.numId) dans tabSymb
	// rend en resultat l'indice de cet ident dans tabSymb (O si absence)
	private static int presentIdent(int binf) {
		int i = it;
		while (i >= binf && tabSymb[i].code != UtilLex.numId)
			i--;
		if (i >= binf)
			return i;
		else
			return 0;
	}

	// utilitaire de placement des caracteristiques d'un nouvel ident dans tabSymb
	//
	private static void placeIdent(int c, int cat, int t, int v) {
		if (it == MAXSYMB)
			UtilLex.messErr("debordement de la table des symboles");
		it = it + 1;
		tabSymb[it] = new EltTabSymb(c, cat, t, v);
	}

	// utilitaire d'affichage de la table des symboles
	//
	private static void afftabSymb() { 
		System.out.println("       code           categorie      type    info");
		System.out.println("      |--------------|--------------|-------|----");
		for (int i = 1; i <= it; i++) {
			if (i == bc) {
				System.out.print("bc=");
				Ecriture.ecrireInt(i, 3);
			} else if (i == it) {
				System.out.print("it=");
				Ecriture.ecrireInt(i, 3);
			} else
				Ecriture.ecrireInt(i, 6);
			if (tabSymb[i] == null)
				System.out.println(" reference NULL");
			else
				System.out.println(" " + tabSymb[i]);
		}
		System.out.println();
	}
    

	// initialisations A COMPLETER SI BESOIN
	// -------------------------------------

	public static void initialisations() {
	
		// indices de gestion de la table des symboles
		it = 0;
		bc = 1;
		
		// pile des reprises pour compilation des branchements en avant
		pileRep = new TPileRep(); 
		// programme objet = code Mapile de l'unite en cours de compilation
		po = new ProgObjet();
		// COMPILATION SEPAREE: desripteur de l'unite en cours de compilation
		desc = new Descripteur();
		
		// initialisation necessaire aux attributs lexicaux
		UtilLex.initialisation();
	
		// initialisation du type de l'expression courante
		tCour = NEUTRE;

	} // initialisations

	// code des points de generation A COMPLETER
	// -----------------------------------------
	public static void pt(int numGen) {
		//	Uniquement pour simplifier le debuggage 
		System.out.println("numGen: " + numGen + "\n");

		switch (numGen) {
		case 0:
			initialisations();
			break;
		case 1: //ajout d'une constante
			placeIdent(UtilLex.numId, CONSTANTE, tCour, vCour);
			break;
		case 2: //declaration d'une variable
			placeIdent(UtilLex.numId, VARGLOBALE, tCour, nbVarGlb);
			nbVarGlb++;
			break;
		case 3: //reservation d'une variable
			po.produire(RESERVER);
			po.produire(nbVarGlb);
			break;
		case 4: //valeur d'un nb entier positif
			tCour = ENT;
			vCour = UtilLex.valNb;
			break;
		case 5: //valeur d'un nb entier negatif
			tCour = ENT;
			vCour = - UtilLex.valNb;
			break;
		case 6: //valeur d'un bool true
			tCour = BOOL;
			vCour = VRAI;
			break;
		case 7: //ajout d'un bool false
			tCour = BOOL;
			vCour = FAUX;
			break;
		case 8: //gestion du type ent
			tCour = ENT;
			break;
		case 9: //gestion du type bool
			tCour = BOOL;
			break;
			
		/****************_ EXPRESSIONS _****************/	
		case 19: // Verifie que l'expression attendue est booleenne 
			verifBool();
			break;
		case 20: // Verifie que l'expression attendue est entiere
			verifEnt();
			break;
		case 21: // Produit l'operation OU
			po.produire(OU);
			break;
		case 22: // Produit l'operation ET
			po.produire(ET);
			break;
		case 23: // Produit l'operation NON
			po.produire(NON);
			break;
		case 24: // Produit l'operation =
			po.produire(EG);
			tCour = BOOL;
			break;
		case 25: // Produit l'operation <>
			po.produire(DIFF);
			tCour = BOOL;
			break;
		case 26: // Produit l'operation >
			po.produire(SUP);
			tCour = BOOL;
			break;
		case 27: // Produit l'operation >=
			po.produire(SUPEG);
			tCour = BOOL;
			break;
		case 28: // Produit l'operation <
			po.produire(INF);
			tCour = BOOL;
			break;
		case 29: // Produit l'operation <=
			po.produire(INFEG);
			tCour = BOOL;
			break;
		case 30: // Produit l'operation +
			po.produire(ADD);
			break;
		case 31: // Produit l'operation -
			po.produire(SOUS);
			break;
		case 32: // Produit l'operation *
			po.produire(MUL);
			break;
		case 33: // Produit l'operation /
			po.produire(DIV);
			break;
		case 34: // Empile une valeur entiere
			po.produire(EMPILER);
			po.produire(vCour);
			break;
		case 35: // Gestion de l'identifiant
			int id = presentIdent(1);
			if (id == 0) System.out.println("L'identifiant " + UtilLex.numId + " n'existe pas.");

			tCour = tabSymb[id].type;
			
			switch (tabSymb[id].categorie) {
				case CONSTANTE:
					po.produire(EMPILER);
					po.produire(tabSymb[id].info);
					break;
	
				case VARGLOBALE:
					po.produire(CONTENUG);
					po.produire(tabSymb[id].info);
					break;
					
				default:
					System.out.println("Categorie de l'ident non repertoriée.");
					break;
			}
			break;
		case 50:
			System.out.println("Test");
			break;
		case 200:
			po.produire(ARRET);
			po.constGen();
			po.constObj();
			afftabSymb();
			break;
		default:
			System.out.println("Point de generation non prevu dans votre liste");
			break;
		}
	}
}