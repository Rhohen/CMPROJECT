
/*********************************************************************************
 * VARIABLES ET METHODES FOURNIES PAR LA CLASSE UtilLex (cf libclass)            *
 *       complement Ã  l'ANALYSEUR LEXICAL produit par ANTLR                      *
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
	MAXSYMB = 300,

			// codes MAPILE :
			RESERVER = 1, EMPILER = 2, CONTENUG = 3, AFFECTERG = 4, OU = 5, ET = 6, NON = 7, INF = 8, INFEG = 9,
			SUP = 10, SUPEG = 11, EG = 12, DIFF = 13, ADD = 14, SOUS = 15, MUL = 16, DIV = 17, BSIFAUX = 18,
			BINCOND = 19, LIRENT = 20, LIREBOOL = 21, ECRENT = 22, ECRBOOL = 23, ARRET = 24, EMPILERADG = 25,
			EMPILERADL = 26, CONTENUL = 27, AFFECTERL = 28, APPEL = 29, RETOUR = 30,

			// codes des valeurs vrai/faux
			VRAI = 1, FAUX = 0,

			// types permis :
			ENT = 1, BOOL = 2, NEUTRE = 3,

			// categories possibles des identificateurs :
			CONSTANTE = 1, VARGLOBALE = 2, VARLOCALE = 3, PARAMFIXE = 4, PARAMMOD = 5, PROC = 6, DEF = 7, REF = 8,
			PRIVEE = 9,

			// valeurs possible du vecteur de translation
			TRANSDON = 1, TRANSCODE = 2, REFEXT = 3;

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
	// NB: effectue uniquement si c'est une reference externe ou si on compile
	// un module
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
	public static String trinome = "CroqGiraultJouin"; // MERCI de renseigner
														// ici un nom pour le
														// trinome, constitue de
														// exclusivement de
														// lettres

	private static int tCour; // type de l'expression compilee
	private static int vCour; // valeur de l'expression compilee le cas echeant

	// Definition de la table des symboles
	//
	private static EltTabSymb[] tabSymb = new EltTabSymb[MAXSYMB + 1];

	// it = indice de remplissage de tabSymb
	// bc = bloc courant (=1 si le bloc courant est le programme principal)
	private static int it, bc;

	// utilitaire de recherche de l'ident courant (ayant pour code
	// UtilLex.numId) dans tabSymb
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

	// utilitaire de placement des caracteristiques d'un nouvel ident dans
	// tabSymb
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
	private static int ident; // Identifiant
	private static int typeVarPrec; // Type de la variable precedente
	private static int catVarPrec; // Categorie de la variable precedente
	private static int identVarPrec; // Categorie de la variable precedente
	private static int bsifaux;
	private static int bincond;
	private static int indVar; // iterateur pour remplir tabSymb avec les var
								// locales et globales
	private static int nbParamProc; // nombre de parametre de la procedure
									// actuelle
	private static int identParamMod;
	private static int nbParamRef;
	private static int idProcDef;

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
		// Uniquement pour simplifier le debuggage
		System.out.println("numGen: " + numGen + "\n");

		switch (numGen) {
		case 0:
			initialisations();
			indVar = 0;
			break;
		case 1: // ajout d'une constante
			if (presentIdent(1) == 0)
				placeIdent(UtilLex.numId, CONSTANTE, tCour, vCour);
			else
				UtilLex.messErr("La constante " + UtilLex.repId(UtilLex.numId) + " a deja ete declaree.");
			break;
		case 2: // déclaration d'une variable
			if (presentIdent(bc) == 0) {
				if (bc > 1)
					placeIdent(UtilLex.numId, VARLOCALE, tCour, indVar);
				else
					placeIdent(UtilLex.numId, VARGLOBALE, tCour, indVar);
				indVar++;
			} else
				UtilLex.messErr("La variable " + UtilLex.repId(UtilLex.numId) + " a deja ete declaree.");
			break;
		case 3: // reservation des variables globales et locales
			po.produire(RESERVER);
			if (bc > 1)
				po.produire(indVar - (nbParamProc + 2));
			else
				po.produire(indVar);
			break;
		case 4: // valeur d'un nb entier positif
			tCour = ENT;
			vCour = UtilLex.valNb;
			break;
		case 5: // valeur d'un nb entier negatif
			tCour = ENT;
			vCour = -UtilLex.valNb;
			break;
		case 6: // valeur d'un bool true
			tCour = BOOL;
			vCour = VRAI;
			break;
		case 7: // ajout d'un bool false
			tCour = BOOL;
			vCour = FAUX;
			break;
		case 8: // gestion du type ent
			tCour = ENT;
			break;
		case 9: // gestion du type bool
			tCour = BOOL;
			break;

		/**************** _ EXPRESSIONS _ ****************/
		case 19: // Vérifie que l'expression attendue est booleenne
			verifBool();
			break;
		case 20: // Vérifie que l'expression attendue est entière
			verifEnt();
			break;
		case 21: // Produit l'opération OU
			po.produire(OU);
			break;
		case 22: // Produit l'opération ET
			po.produire(ET);
			break;
		case 23: // Produit l'opération NON
			po.produire(NON);
			break;
		case 24: // Produit l'opération =
			po.produire(EG);
			tCour = BOOL;
			break;
		case 25: // Produit l'opération <>
			po.produire(DIFF);
			tCour = BOOL;
			break;
		case 26: // Produit l'opération >
			po.produire(SUP);
			tCour = BOOL;
			break;
		case 27: // Produit l'opération >=
			po.produire(SUPEG);
			tCour = BOOL;
			break;
		case 28: // Produit l'opération <
			po.produire(INF);
			tCour = BOOL;
			break;
		case 29: // Produit l'opération <=
			po.produire(INFEG);
			tCour = BOOL;
			break;
		case 30: // Produit l'opération +
			po.produire(ADD);
			break;
		case 31: // Produit l'opération -
			po.produire(SOUS);
			break;
		case 32: // Produit l'opération *
			po.produire(MUL);
			break;
		case 33: // Produit l'opération /
			po.produire(DIV);
			break;
		case 34: // Empile une valeur entière
			po.produire(EMPILER);
			po.produire(vCour);
			break;
		case 35: // Gestion de l'identifiant/
			if ((ident = presentIdent(1)) == 0)
				UtilLex.messErr("La variable/constante " + UtilLex.repId(UtilLex.numId) + " n'existe pas.");

			tCour = tabSymb[ident].type;

			switch (tabSymb[ident].categorie) {

			case CONSTANTE:
				po.produire(EMPILER);
				po.produire(tabSymb[ident].info);
				break;

			case VARGLOBALE:
				po.produire(CONTENUG);
				po.produire(tabSymb[ident].info);
				break;

			case VARLOCALE:
				po.produire(CONTENUL);
				po.produire(tabSymb[ident].info);
				po.produire(0);
				break;

			case PARAMMOD:
				po.produire(CONTENUL);
				po.produire(tabSymb[ident].info);
				po.produire(1);
				break;

			case PARAMFIXE:
				po.produire(CONTENUL);
				po.produire(tabSymb[ident].info);
				po.produire(0);
				break;

			default:
				System.out.println("Catégorie de l'ident non répertoriée.");
				break;
			}
			break;

		/**************** _ CONDITIONS/ECRITURE/LECTURE _ ****************/
		case 45: // Ecriture
			if (tCour == ENT)
				po.produire(ECRENT);
			else
				po.produire(ECRBOOL);
			break;

		case 46: // Lecture
			if ((ident = presentIdent(1)) == 0)
				UtilLex.messErr("La variable/constante " + UtilLex.repId(UtilLex.numId) + " n'existe pas.");

			tCour = tabSymb[ident].type;

			if (tCour == ENT)
				po.produire(LIRENT);
			else
				po.produire(LIREBOOL);

			switch (tabSymb[ident].categorie) {

			case VARGLOBALE:
				po.produire(AFFECTERG);
				po.produire(tabSymb[ident].info);
				break;

			case VARLOCALE:
				po.produire(AFFECTERL);
				po.produire(tabSymb[ident].info);
				po.produire(0);
				break;

			case PARAMMOD:
				po.produire(AFFECTERL);
				po.produire(tabSymb[ident].info);
				po.produire(1);
				break;

			default:
				System.out.println("Catégorie de l'ident non répertoriée.");
				break;
			}
			break;

		case 47: // Affectation, sauvegarde des informations de la variable
					// recevant la valeur
			if ((identVarPrec = presentIdent(1)) == 0)
				UtilLex.messErr("La variable/constante " + UtilLex.repId(UtilLex.numId) + " n'existe pas.");
			if (tabSymb[identVarPrec].categorie == CONSTANTE)
				UtilLex.messErr("La constante " + UtilLex.repId(UtilLex.numId) + " ne peut pas etre modifiee.");

			tCour = tabSymb[identVarPrec].type;
			typeVarPrec = tabSymb[identVarPrec].type;
			catVarPrec = tabSymb[identVarPrec].categorie;
			break;

		case 48: // Affectation des valeurs a la variable precedemment
					// enregistree
			if (typeVarPrec == ENT)
				verifEnt();
			else
				verifBool();

			switch (catVarPrec) {

			case VARGLOBALE:
				po.produire(AFFECTERG);
				po.produire(tabSymb[identVarPrec].info);
				break;

			case VARLOCALE:
				po.produire(AFFECTERL);
				po.produire(tabSymb[identVarPrec].info);
				po.produire(0);
				break;

			case PARAMMOD:
				po.produire(AFFECTERL);
				po.produire(tabSymb[identVarPrec].info);
				po.produire(1);
				break;

			default:
				System.out.println("Catégorie de l'ident non répertoriée.");
				break;
			}
			break;

		case 49: // Instruction "si"
			po.produire(BSIFAUX);
			po.produire(0);
			pileRep.empiler(po.getIpo());
			break;

		case 50: // Instruction "si", aussi utilisee dans "cond"
			po.produire(BINCOND);
			po.produire(0);
			po.modifier(pileRep.depiler(), po.getIpo() + 1);
			pileRep.empiler(po.getIpo());
			break;

		case 51: // Instruction "si", aussi utilisee dans la maj du bincond de
					// proc
			po.modifier(pileRep.depiler(), po.getIpo() + 1);
			break;

		case 52: // Instruction "ttq"
			pileRep.empiler(po.getIpo());
			break;

		case 53: // Instruction "ttq", aussi utilisee dans "cond"
			verifBool();
			po.produire(BSIFAUX);
			po.produire(0);
			pileRep.empiler(po.getIpo());
			break;

		case 54: // Instruction "ttq"
			bsifaux = pileRep.depiler();
			bincond = pileRep.depiler();
			po.produire(BINCOND);
			po.produire(bincond + 1);
			po.modifier(bsifaux, po.getIpo() + 1);
			break;

		case 55: // Instruction "cond", permet de delimiter la fin du case dans
					// pileRep
			pileRep.empiler(0);
			break;

		case 56: // Instruction "cond"
			bsifaux = pileRep.depiler();
			po.produire(BINCOND);
			po.produire(0);
			po.modifier(bsifaux, po.getIpo() + 1);
			pileRep.empiler(po.getIpo());
			break;

		case 57: // Instruction "cond"
			while ((bincond = pileRep.depiler()) != 0)
				po.modifier(bincond, po.getIpo() + 1);
			break;

		/**************** _ PROCEDURE _ ****************/
		case 65: //
			if (presentIdent(1) == 0) {
				placeIdent(UtilLex.numId, PROC, NEUTRE, po.getIpo() + 1);
				if (desc.presentDef(UtilLex.repId(UtilLex.numId)) != 0) {
					placeIdent(-1, DEF, NEUTRE, 0);
					desc.modifDefAdPo(desc.presentDef(UtilLex.repId(UtilLex.numId)), po.getIpo());
				} else {
					placeIdent(-1, PRIVEE, NEUTRE, 0);
				}
				bc = it + 1;
				nbParamProc = 0;
				idProcDef = UtilLex.numId;
			} else
				UtilLex.messErr("La procedure " + UtilLex.repId(UtilLex.numId) + " a deja ete declaree.");
			break;

		case 66: //
			if (presentIdent(bc) == 0) {
				placeIdent(UtilLex.numId, PARAMFIXE, tCour, nbParamProc);
				nbParamProc++;
				if (desc.presentDef(UtilLex.repId(idProcDef)) != 0) {
					desc.modifDefNbParam(desc.presentDef(UtilLex.repId(idProcDef)), nbParamProc);
				}
			} else
				UtilLex.messErr("Le parametre fixe " + UtilLex.repId(UtilLex.numId) + " a deja ete declare.");
			break;

		case 67: //
			if (presentIdent(bc) == 0) {
				placeIdent(UtilLex.numId, PARAMMOD, tCour, nbParamProc);
				nbParamProc++;
				if (desc.presentDef(UtilLex.repId(idProcDef)) != 0) {
					desc.modifDefNbParam(desc.presentDef(UtilLex.repId(idProcDef)), nbParamProc);
				}
			} else
				UtilLex.messErr("Le parametre modifiable " + UtilLex.repId(UtilLex.numId) + " a deja ete declare.");
			break;

		case 68: //
			tabSymb[bc - 1].info = nbParamProc;
			indVar = nbParamProc + 2;
			break;

		case 69: //
			po.produire(RETOUR);
			po.produire(nbParamProc);

			// Supprime les var locales
			it = bc + nbParamProc - 1;

			// On met a -1 les codes des parametres de la procedure
			for (int i = it; i >= bc; i--)
				tabSymb[i].code = -1;
			break;

		case 70: //
			po.produire(BINCOND);
			po.produire(0);
			pileRep.empiler(po.getIpo());
			break;

		case 71: //
			if ((identParamMod = presentIdent(1)) == 0)
				UtilLex.messErr("Le parametre modifiable " + UtilLex.repId(UtilLex.numId) + " n'existe pas.");

			switch (tabSymb[identParamMod].categorie) {

			case VARGLOBALE:
				po.produire(EMPILERADG);
				po.produire(tabSymb[identParamMod].info);
				break;

			case VARLOCALE:
				po.produire(EMPILERADL);
				po.produire(tabSymb[identParamMod].info);
				po.produire(0);
				break;

			case PARAMMOD:
				po.produire(EMPILERADL);
				po.produire(tabSymb[identParamMod].info);
				po.produire(1);
				break;

			default:
				System.out.println("Catégorie de l'ident non répertoriée.");
				break;
			}
			break;

		case 72:
			po.produire(APPEL);
			po.produire(tabSymb[identVarPrec].info);
			po.produire(tabSymb[identVarPrec + 1].info);
			break;

		/**************** _ PROGRAMMATION SEPAREE _ ****************/
		// Informe que l unite est de type programme
		case 82:
			desc.setUnite("programme");
			break;

		// Informe que l unite est de type module
		case 83:
			desc.setUnite("module");
			break;

		case 84:
			desc = new Descripteur();
			break;

		case 85:
			desc.setTailleCode(po.getIpo());
			// tailleGlobaux peu etre différent selon le module, faire
			// desc.set... = indVar apres var ?
			// l'increm de Def et ref sont faites automatiquement
			// nbTransExt, utiliser modifVecteurTrans( TRANSDON=1 | TRANSCODE=2
			// | REFEXT=3 );, mais moi pas comprendre comment
			/*
			 * TRANSDON : appel a une variable globale de l unite actuelle (pas
			 * sur) TRANSCODE : pour les bsifaux/bincond REFEXT : appel d une
			 * fonction ou variable globale exterieure à l unite actuelle
			 */
			// tabDef a l'air bien chiante
			// tabRef aussi

			// Generation du .desc
			desc.ecrireDesc(UtilLex.nomSource);
			System.out.println(desc);
			break;

		case 86: // ajout a la tableDef
			if (desc.presentDef(UtilLex.repId(UtilLex.numId)) == 0) {
				desc.ajoutDef(UtilLex.repId(UtilLex.numId));
			} else
				UtilLex.messErr("Le def " + UtilLex.repId(UtilLex.numId) + " a deja ete declaree");
			break;

		case 87: // ajout a la table ref
			if (presentIdent(1) == 0) {
				placeIdent(UtilLex.numId, REF, tCour, vCour);
				desc.ajoutRef(UtilLex.repId(UtilLex.numId));
				nbParamRef = 0;
			}
			break;

		case 88:
			nbParamRef++;
			desc.modifRefNbParam(desc.presentRef(UtilLex.repId(UtilLex.numId)), nbParamRef);
			placeIdent(-1, PARAMFIXE, tCour, nbParamRef);
			break;

		case 89:
			nbParamRef++;
			desc.modifRefNbParam(desc.presentRef(UtilLex.repId(UtilLex.numId)), nbParamRef);
			placeIdent(-1, PARAMMOD, tCour, nbParamRef);
			break;

		case 90:

			break;

		case 91:

			break;

		case 92:

			break;

		case 255:
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
