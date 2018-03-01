// $ANTLR 3.5.2 projet.g 2018-03-01 10:15:51
           
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class projetParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMENT", "ID", "INT", "LIGNE", 
		"ML_COMMENT", "WS", "'('", "')'", "'*'", "'+'", "','", "'-'", "':'", "':='", 
		"';'", "'<'", "'<='", "'<>'", "'='", "'>'", "'>='", "'alors'", "'aut'", 
		"'bool'", "'cond'", "'const'", "'debut'", "'def'", "'div'", "'ecrire'", 
		"'ent'", "'et'", "'faire'", "'fait'", "'faux'", "'fcond'", "'fin'", "'fixe'", 
		"'fsi'", "'lire'", "'mod'", "'module'", "'non'", "'ou'", "'proc'", "'programme'", 
		"'ref'", "'si'", "'sinon'", "'ttq'", "'var'", "'vrai'"
	};
	public static final int EOF=-1;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int COMMENT=4;
	public static final int ID=5;
	public static final int INT=6;
	public static final int LIGNE=7;
	public static final int ML_COMMENT=8;
	public static final int WS=9;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public projetParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public projetParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return projetParser.tokenNames; }
	@Override public String getGrammarFileName() { return "projet.g"; }


	 
	// variables globales et methodes utiles a placer ici
	  



	// $ANTLR start "unite"
	// projet.g:26:1: unite : ( unitprog EOF | unitmodule EOF );
	public final void unite() throws RecognitionException {
		try {
			// projet.g:26:8: ( unitprog EOF | unitmodule EOF )
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==49) ) {
				alt1=1;
			}
			else if ( (LA1_0==45) ) {
				alt1=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// projet.g:26:12: unitprog EOF
					{
					pushFollow(FOLLOW_unitprog_in_unite54);
					unitprog();
					state._fsp--;

					match(input,EOF,FOLLOW_EOF_in_unite57); 
					}
					break;
				case 2 :
					// projet.g:27:12: unitmodule EOF
					{
					pushFollow(FOLLOW_unitmodule_in_unite70);
					unitmodule();
					state._fsp--;

					match(input,EOF,FOLLOW_EOF_in_unite73); 
					}
					break;

			}
		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "unite"



	// $ANTLR start "unitprog"
	// projet.g:30:1: unitprog : 'programme' ident ':' declarations corps ;
	public final void unitprog() throws RecognitionException {
		try {
			// projet.g:31:3: ( 'programme' ident ':' declarations corps )
			// projet.g:31:5: 'programme' ident ':' declarations corps
			{
			match(input,49,FOLLOW_49_in_unitprog88); 
			pushFollow(FOLLOW_ident_in_unitprog90);
			ident();
			state._fsp--;

			match(input,16,FOLLOW_16_in_unitprog92); 
			pushFollow(FOLLOW_declarations_in_unitprog101);
			declarations();
			state._fsp--;

			pushFollow(FOLLOW_corps_in_unitprog110);
			corps();
			state._fsp--;

			 System.out.println("succes, arret de la compilation "); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "unitprog"



	// $ANTLR start "unitmodule"
	// projet.g:36:1: unitmodule : 'module' ident ':' declarations ;
	public final void unitmodule() throws RecognitionException {
		try {
			// projet.g:37:3: ( 'module' ident ':' declarations )
			// projet.g:37:5: 'module' ident ':' declarations
			{
			match(input,45,FOLLOW_45_in_unitmodule127); 
			pushFollow(FOLLOW_ident_in_unitmodule129);
			ident();
			state._fsp--;

			match(input,16,FOLLOW_16_in_unitmodule131); 
			pushFollow(FOLLOW_declarations_in_unitmodule139);
			declarations();
			state._fsp--;

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "unitmodule"



	// $ANTLR start "declarations"
	// projet.g:41:1: declarations : ( partiedef )? ( partieref )? ( consts )? ( vars )? ( decprocs )? ;
	public final void declarations() throws RecognitionException {
		try {
			// projet.g:42:3: ( ( partiedef )? ( partieref )? ( consts )? ( vars )? ( decprocs )? )
			// projet.g:42:5: ( partiedef )? ( partieref )? ( consts )? ( vars )? ( decprocs )?
			{
			// projet.g:42:5: ( partiedef )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==31) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// projet.g:42:5: partiedef
					{
					pushFollow(FOLLOW_partiedef_in_declarations157);
					partiedef();
					state._fsp--;

					}
					break;

			}

			// projet.g:42:16: ( partieref )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==50) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// projet.g:42:16: partieref
					{
					pushFollow(FOLLOW_partieref_in_declarations160);
					partieref();
					state._fsp--;

					}
					break;

			}

			// projet.g:42:27: ( consts )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==29) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// projet.g:42:27: consts
					{
					pushFollow(FOLLOW_consts_in_declarations163);
					consts();
					state._fsp--;

					}
					break;

			}

			// projet.g:42:35: ( vars )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==54) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// projet.g:42:35: vars
					{
					pushFollow(FOLLOW_vars_in_declarations166);
					vars();
					state._fsp--;

					}
					break;

			}

			// projet.g:42:41: ( decprocs )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==48) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// projet.g:42:41: decprocs
					{
					pushFollow(FOLLOW_decprocs_in_declarations169);
					decprocs();
					state._fsp--;

					}
					break;

			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "declarations"



	// $ANTLR start "partiedef"
	// projet.g:45:1: partiedef : 'def' ident ( ',' ident )* ptvg ;
	public final void partiedef() throws RecognitionException {
		try {
			// projet.g:46:3: ( 'def' ident ( ',' ident )* ptvg )
			// projet.g:46:5: 'def' ident ( ',' ident )* ptvg
			{
			match(input,31,FOLLOW_31_in_partiedef186); 
			pushFollow(FOLLOW_ident_in_partiedef188);
			ident();
			state._fsp--;

			// projet.g:46:18: ( ',' ident )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==14) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// projet.g:46:19: ',' ident
					{
					match(input,14,FOLLOW_14_in_partiedef192); 
					pushFollow(FOLLOW_ident_in_partiedef194);
					ident();
					state._fsp--;

					}
					break;

				default :
					break loop7;
				}
			}

			pushFollow(FOLLOW_ptvg_in_partiedef199);
			ptvg();
			state._fsp--;

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "partiedef"



	// $ANTLR start "partieref"
	// projet.g:49:1: partieref : 'ref' specif ( ',' specif )* ptvg ;
	public final void partieref() throws RecognitionException {
		try {
			// projet.g:49:10: ( 'ref' specif ( ',' specif )* ptvg )
			// projet.g:49:12: 'ref' specif ( ',' specif )* ptvg
			{
			match(input,50,FOLLOW_50_in_partieref211); 
			pushFollow(FOLLOW_specif_in_partieref214);
			specif();
			state._fsp--;

			// projet.g:49:26: ( ',' specif )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==14) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// projet.g:49:27: ',' specif
					{
					match(input,14,FOLLOW_14_in_partieref217); 
					pushFollow(FOLLOW_specif_in_partieref219);
					specif();
					state._fsp--;

					}
					break;

				default :
					break loop8;
				}
			}

			pushFollow(FOLLOW_ptvg_in_partieref223);
			ptvg();
			state._fsp--;

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "partieref"



	// $ANTLR start "specif"
	// projet.g:52:1: specif : ident ( 'fixe' '(' type ( ',' type )* ')' )? ( 'mod' '(' type ( ',' type )* ')' )? ;
	public final void specif() throws RecognitionException {
		try {
			// projet.g:52:9: ( ident ( 'fixe' '(' type ( ',' type )* ')' )? ( 'mod' '(' type ( ',' type )* ')' )? )
			// projet.g:52:11: ident ( 'fixe' '(' type ( ',' type )* ')' )? ( 'mod' '(' type ( ',' type )* ')' )?
			{
			pushFollow(FOLLOW_ident_in_specif237);
			ident();
			state._fsp--;

			// projet.g:52:18: ( 'fixe' '(' type ( ',' type )* ')' )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==41) ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// projet.g:52:20: 'fixe' '(' type ( ',' type )* ')'
					{
					match(input,41,FOLLOW_41_in_specif242); 
					match(input,10,FOLLOW_10_in_specif244); 
					pushFollow(FOLLOW_type_in_specif246);
					type();
					state._fsp--;

					// projet.g:52:37: ( ',' type )*
					loop9:
					while (true) {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0==14) ) {
							alt9=1;
						}

						switch (alt9) {
						case 1 :
							// projet.g:52:39: ',' type
							{
							match(input,14,FOLLOW_14_in_specif251); 
							pushFollow(FOLLOW_type_in_specif253);
							type();
							state._fsp--;

							}
							break;

						default :
							break loop9;
						}
					}

					match(input,11,FOLLOW_11_in_specif259); 
					}
					break;

			}

			// projet.g:53:18: ( 'mod' '(' type ( ',' type )* ')' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==44) ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// projet.g:53:20: 'mod' '(' type ( ',' type )* ')'
					{
					match(input,44,FOLLOW_44_in_specif284); 
					match(input,10,FOLLOW_10_in_specif287); 
					pushFollow(FOLLOW_type_in_specif289);
					type();
					state._fsp--;

					// projet.g:53:37: ( ',' type )*
					loop11:
					while (true) {
						int alt11=2;
						int LA11_0 = input.LA(1);
						if ( (LA11_0==14) ) {
							alt11=1;
						}

						switch (alt11) {
						case 1 :
							// projet.g:53:39: ',' type
							{
							match(input,14,FOLLOW_14_in_specif294); 
							pushFollow(FOLLOW_type_in_specif296);
							type();
							state._fsp--;

							}
							break;

						default :
							break loop11;
						}
					}

					match(input,11,FOLLOW_11_in_specif302); 
					}
					break;

			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "specif"



	// $ANTLR start "consts"
	// projet.g:56:1: consts : 'const' ( ident '=' valeur ptvg )+ ;
	public final void consts() throws RecognitionException {
		try {
			// projet.g:56:9: ( 'const' ( ident '=' valeur ptvg )+ )
			// projet.g:56:11: 'const' ( ident '=' valeur ptvg )+
			{
			match(input,29,FOLLOW_29_in_consts320); 
			// projet.g:56:19: ( ident '=' valeur ptvg )+
			int cnt13=0;
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==ID) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// projet.g:56:21: ident '=' valeur ptvg
					{
					pushFollow(FOLLOW_ident_in_consts324);
					ident();
					state._fsp--;

					match(input,22,FOLLOW_22_in_consts326); 
					pushFollow(FOLLOW_valeur_in_consts328);
					valeur();
					state._fsp--;

					pushFollow(FOLLOW_ptvg_in_consts331);
					ptvg();
					state._fsp--;

					PtGen.pt(1);
					}
					break;

				default :
					if ( cnt13 >= 1 ) break loop13;
					EarlyExitException eee = new EarlyExitException(13, input);
					throw eee;
				}
				cnt13++;
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "consts"



	// $ANTLR start "vars"
	// projet.g:59:1: vars : 'var' ( type ident ( ',' ident )* ptvg )+ ;
	public final void vars() throws RecognitionException {
		try {
			// projet.g:59:7: ( 'var' ( type ident ( ',' ident )* ptvg )+ )
			// projet.g:59:9: 'var' ( type ident ( ',' ident )* ptvg )+
			{
			match(input,54,FOLLOW_54_in_vars351); 
			// projet.g:59:15: ( type ident ( ',' ident )* ptvg )+
			int cnt15=0;
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==27||LA15_0==34) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// projet.g:59:17: type ident ( ',' ident )* ptvg
					{
					pushFollow(FOLLOW_type_in_vars355);
					type();
					state._fsp--;

					pushFollow(FOLLOW_ident_in_vars358);
					ident();
					state._fsp--;

					PtGen.pt(2);
					// projet.g:59:44: ( ',' ident )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==14) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// projet.g:59:46: ',' ident
							{
							match(input,14,FOLLOW_14_in_vars364); 
							pushFollow(FOLLOW_ident_in_vars367);
							ident();
							state._fsp--;

							PtGen.pt(2);
							}
							break;

						default :
							break loop14;
						}
					}

					pushFollow(FOLLOW_ptvg_in_vars374);
					ptvg();
					state._fsp--;

					}
					break;

				default :
					if ( cnt15 >= 1 ) break loop15;
					EarlyExitException eee = new EarlyExitException(15, input);
					throw eee;
				}
				cnt15++;
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "vars"



	// $ANTLR start "type"
	// projet.g:62:1: type : ( 'ent' | 'bool' );
	public final void type() throws RecognitionException {
		try {
			// projet.g:62:7: ( 'ent' | 'bool' )
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==34) ) {
				alt16=1;
			}
			else if ( (LA16_0==27) ) {
				alt16=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// projet.g:62:9: 'ent'
					{
					match(input,34,FOLLOW_34_in_type391); 
					PtGen.pt(8);
					}
					break;
				case 2 :
					// projet.g:63:9: 'bool'
					{
					match(input,27,FOLLOW_27_in_type404); 
					PtGen.pt(9);
					}
					break;

			}
		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "type"



	// $ANTLR start "decprocs"
	// projet.g:66:1: decprocs : ( decproc ptvg )+ ;
	public final void decprocs() throws RecognitionException {
		try {
			// projet.g:66:9: ( ( decproc ptvg )+ )
			// projet.g:66:11: ( decproc ptvg )+
			{
			// projet.g:66:11: ( decproc ptvg )+
			int cnt17=0;
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( (LA17_0==48) ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// projet.g:66:12: decproc ptvg
					{
					pushFollow(FOLLOW_decproc_in_decprocs419);
					decproc();
					state._fsp--;

					pushFollow(FOLLOW_ptvg_in_decprocs421);
					ptvg();
					state._fsp--;

					}
					break;

				default :
					if ( cnt17 >= 1 ) break loop17;
					EarlyExitException eee = new EarlyExitException(17, input);
					throw eee;
				}
				cnt17++;
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decprocs"



	// $ANTLR start "decproc"
	// projet.g:69:1: decproc : 'proc' ident ( parfixe )? ( parmod )? ( consts )? ( vars )? corps ;
	public final void decproc() throws RecognitionException {
		try {
			// projet.g:69:9: ( 'proc' ident ( parfixe )? ( parmod )? ( consts )? ( vars )? corps )
			// projet.g:69:12: 'proc' ident ( parfixe )? ( parmod )? ( consts )? ( vars )? corps
			{
			match(input,48,FOLLOW_48_in_decproc437); 
			pushFollow(FOLLOW_ident_in_decproc440);
			ident();
			state._fsp--;

			// projet.g:69:27: ( parfixe )?
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==41) ) {
				alt18=1;
			}
			switch (alt18) {
				case 1 :
					// projet.g:69:27: parfixe
					{
					pushFollow(FOLLOW_parfixe_in_decproc443);
					parfixe();
					state._fsp--;

					}
					break;

			}

			// projet.g:69:36: ( parmod )?
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==44) ) {
				alt19=1;
			}
			switch (alt19) {
				case 1 :
					// projet.g:69:36: parmod
					{
					pushFollow(FOLLOW_parmod_in_decproc446);
					parmod();
					state._fsp--;

					}
					break;

			}

			// projet.g:69:44: ( consts )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==29) ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// projet.g:69:44: consts
					{
					pushFollow(FOLLOW_consts_in_decproc449);
					consts();
					state._fsp--;

					}
					break;

			}

			// projet.g:69:52: ( vars )?
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==54) ) {
				alt21=1;
			}
			switch (alt21) {
				case 1 :
					// projet.g:69:52: vars
					{
					pushFollow(FOLLOW_vars_in_decproc452);
					vars();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_corps_in_decproc455);
			corps();
			state._fsp--;

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decproc"



	// $ANTLR start "ptvg"
	// projet.g:72:1: ptvg : ( ';' |);
	public final void ptvg() throws RecognitionException {
		try {
			// projet.g:72:7: ( ';' |)
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==18) ) {
				alt22=1;
			}
			else if ( (LA22_0==EOF||LA22_0==ID||LA22_0==27||(LA22_0 >= 29 && LA22_0 <= 30)||LA22_0==34||LA22_0==48||LA22_0==50||LA22_0==54) ) {
				alt22=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// projet.g:72:9: ';'
					{
					match(input,18,FOLLOW_18_in_ptvg470); 
					}
					break;
				case 2 :
					// projet.g:74:3: 
					{
					}
					break;

			}
		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ptvg"



	// $ANTLR start "corps"
	// projet.g:76:1: corps : 'debut' instructions 'fin' ;
	public final void corps() throws RecognitionException {
		try {
			// projet.g:76:7: ( 'debut' instructions 'fin' )
			// projet.g:76:9: 'debut' instructions 'fin'
			{
			PtGen.pt(3);
			match(input,30,FOLLOW_30_in_corps490); 
			pushFollow(FOLLOW_instructions_in_corps492);
			instructions();
			state._fsp--;

			match(input,40,FOLLOW_40_in_corps494); 
			PtGen.pt(255);
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "corps"



	// $ANTLR start "parfixe"
	// projet.g:79:1: parfixe : 'fixe' '(' pf ( ';' pf )* ')' ;
	public final void parfixe() throws RecognitionException {
		try {
			// projet.g:79:8: ( 'fixe' '(' pf ( ';' pf )* ')' )
			// projet.g:79:10: 'fixe' '(' pf ( ';' pf )* ')'
			{
			match(input,41,FOLLOW_41_in_parfixe508); 
			match(input,10,FOLLOW_10_in_parfixe510); 
			pushFollow(FOLLOW_pf_in_parfixe512);
			pf();
			state._fsp--;

			// projet.g:79:24: ( ';' pf )*
			loop23:
			while (true) {
				int alt23=2;
				int LA23_0 = input.LA(1);
				if ( (LA23_0==18) ) {
					alt23=1;
				}

				switch (alt23) {
				case 1 :
					// projet.g:79:26: ';' pf
					{
					match(input,18,FOLLOW_18_in_parfixe516); 
					pushFollow(FOLLOW_pf_in_parfixe518);
					pf();
					state._fsp--;

					}
					break;

				default :
					break loop23;
				}
			}

			match(input,11,FOLLOW_11_in_parfixe522); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "parfixe"



	// $ANTLR start "pf"
	// projet.g:82:1: pf : type ident ( ',' ident )* ;
	public final void pf() throws RecognitionException {
		try {
			// projet.g:82:5: ( type ident ( ',' ident )* )
			// projet.g:82:7: type ident ( ',' ident )*
			{
			pushFollow(FOLLOW_type_in_pf536);
			type();
			state._fsp--;

			pushFollow(FOLLOW_ident_in_pf538);
			ident();
			state._fsp--;

			// projet.g:82:19: ( ',' ident )*
			loop24:
			while (true) {
				int alt24=2;
				int LA24_0 = input.LA(1);
				if ( (LA24_0==14) ) {
					alt24=1;
				}

				switch (alt24) {
				case 1 :
					// projet.g:82:21: ',' ident
					{
					match(input,14,FOLLOW_14_in_pf543); 
					pushFollow(FOLLOW_ident_in_pf545);
					ident();
					state._fsp--;

					}
					break;

				default :
					break loop24;
				}
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "pf"



	// $ANTLR start "parmod"
	// projet.g:84:1: parmod : 'mod' '(' pm ( ';' pm )* ')' ;
	public final void parmod() throws RecognitionException {
		try {
			// projet.g:84:9: ( 'mod' '(' pm ( ';' pm )* ')' )
			// projet.g:84:11: 'mod' '(' pm ( ';' pm )* ')'
			{
			match(input,44,FOLLOW_44_in_parmod562); 
			match(input,10,FOLLOW_10_in_parmod564); 
			pushFollow(FOLLOW_pm_in_parmod566);
			pm();
			state._fsp--;

			// projet.g:84:24: ( ';' pm )*
			loop25:
			while (true) {
				int alt25=2;
				int LA25_0 = input.LA(1);
				if ( (LA25_0==18) ) {
					alt25=1;
				}

				switch (alt25) {
				case 1 :
					// projet.g:84:26: ';' pm
					{
					match(input,18,FOLLOW_18_in_parmod570); 
					pushFollow(FOLLOW_pm_in_parmod572);
					pm();
					state._fsp--;

					}
					break;

				default :
					break loop25;
				}
			}

			match(input,11,FOLLOW_11_in_parmod576); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "parmod"



	// $ANTLR start "pm"
	// projet.g:87:1: pm : type ident ( ',' ident )* ;
	public final void pm() throws RecognitionException {
		try {
			// projet.g:87:5: ( type ident ( ',' ident )* )
			// projet.g:87:7: type ident ( ',' ident )*
			{
			pushFollow(FOLLOW_type_in_pm590);
			type();
			state._fsp--;

			pushFollow(FOLLOW_ident_in_pm592);
			ident();
			state._fsp--;

			// projet.g:87:19: ( ',' ident )*
			loop26:
			while (true) {
				int alt26=2;
				int LA26_0 = input.LA(1);
				if ( (LA26_0==14) ) {
					alt26=1;
				}

				switch (alt26) {
				case 1 :
					// projet.g:87:21: ',' ident
					{
					match(input,14,FOLLOW_14_in_pm597); 
					pushFollow(FOLLOW_ident_in_pm599);
					ident();
					state._fsp--;

					}
					break;

				default :
					break loop26;
				}
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "pm"



	// $ANTLR start "instructions"
	// projet.g:90:1: instructions : instruction ( ';' instruction )* ;
	public final void instructions() throws RecognitionException {
		try {
			// projet.g:91:3: ( instruction ( ';' instruction )* )
			// projet.g:91:5: instruction ( ';' instruction )*
			{
			pushFollow(FOLLOW_instruction_in_instructions618);
			instruction();
			state._fsp--;

			// projet.g:91:17: ( ';' instruction )*
			loop27:
			while (true) {
				int alt27=2;
				int LA27_0 = input.LA(1);
				if ( (LA27_0==18) ) {
					alt27=1;
				}

				switch (alt27) {
				case 1 :
					// projet.g:91:19: ';' instruction
					{
					match(input,18,FOLLOW_18_in_instructions622); 
					pushFollow(FOLLOW_instruction_in_instructions624);
					instruction();
					state._fsp--;

					}
					break;

				default :
					break loop27;
				}
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "instructions"



	// $ANTLR start "instruction"
	// projet.g:94:1: instruction : ( inssi | inscond | boucle | lecture | ecriture | affouappel |);
	public final void instruction() throws RecognitionException {
		try {
			// projet.g:95:3: ( inssi | inscond | boucle | lecture | ecriture | affouappel |)
			int alt28=7;
			switch ( input.LA(1) ) {
			case 51:
				{
				alt28=1;
				}
				break;
			case 28:
				{
				alt28=2;
				}
				break;
			case 53:
				{
				alt28=3;
				}
				break;
			case 43:
				{
				alt28=4;
				}
				break;
			case 33:
				{
				alt28=5;
				}
				break;
			case ID:
				{
				alt28=6;
				}
				break;
			case 14:
			case 18:
			case 26:
			case 37:
			case 39:
			case 40:
			case 42:
			case 52:
				{
				alt28=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}
			switch (alt28) {
				case 1 :
					// projet.g:95:5: inssi
					{
					pushFollow(FOLLOW_inssi_in_instruction641);
					inssi();
					state._fsp--;

					}
					break;
				case 2 :
					// projet.g:96:5: inscond
					{
					pushFollow(FOLLOW_inscond_in_instruction648);
					inscond();
					state._fsp--;

					}
					break;
				case 3 :
					// projet.g:97:5: boucle
					{
					pushFollow(FOLLOW_boucle_in_instruction654);
					boucle();
					state._fsp--;

					}
					break;
				case 4 :
					// projet.g:98:5: lecture
					{
					pushFollow(FOLLOW_lecture_in_instruction660);
					lecture();
					state._fsp--;

					}
					break;
				case 5 :
					// projet.g:99:5: ecriture
					{
					pushFollow(FOLLOW_ecriture_in_instruction666);
					ecriture();
					state._fsp--;

					}
					break;
				case 6 :
					// projet.g:100:5: affouappel
					{
					pushFollow(FOLLOW_affouappel_in_instruction672);
					affouappel();
					state._fsp--;

					}
					break;
				case 7 :
					// projet.g:102:3: 
					{
					}
					break;

			}
		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "instruction"



	// $ANTLR start "inssi"
	// projet.g:104:1: inssi : 'si' expression 'alors' instructions ( 'sinon' instructions )? 'fsi' ;
	public final void inssi() throws RecognitionException {
		try {
			// projet.g:104:7: ( 'si' expression 'alors' instructions ( 'sinon' instructions )? 'fsi' )
			// projet.g:104:9: 'si' expression 'alors' instructions ( 'sinon' instructions )? 'fsi'
			{
			match(input,51,FOLLOW_51_in_inssi689); 
			pushFollow(FOLLOW_expression_in_inssi691);
			expression();
			state._fsp--;

			PtGen.pt(49);
			match(input,25,FOLLOW_25_in_inssi695); 
			pushFollow(FOLLOW_instructions_in_inssi697);
			instructions();
			state._fsp--;

			// projet.g:104:62: ( 'sinon' instructions )?
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==52) ) {
				alt29=1;
			}
			switch (alt29) {
				case 1 :
					// projet.g:104:64: 'sinon' instructions
					{
					match(input,52,FOLLOW_52_in_inssi701); 
					PtGen.pt(50);
					pushFollow(FOLLOW_instructions_in_inssi705);
					instructions();
					state._fsp--;

					}
					break;

			}

			PtGen.pt(51);
			match(input,42,FOLLOW_42_in_inssi711); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "inssi"



	// $ANTLR start "inscond"
	// projet.g:107:1: inscond : 'cond' expression ':' instructions ( ',' expression ':' instructions )* ( 'aut' instructions |) 'fcond' ;
	public final void inscond() throws RecognitionException {
		try {
			// projet.g:107:9: ( 'cond' expression ':' instructions ( ',' expression ':' instructions )* ( 'aut' instructions |) 'fcond' )
			// projet.g:107:11: 'cond' expression ':' instructions ( ',' expression ':' instructions )* ( 'aut' instructions |) 'fcond'
			{
			match(input,28,FOLLOW_28_in_inscond725); 
			PtGen.pt(55);
			pushFollow(FOLLOW_expression_in_inscond729);
			expression();
			state._fsp--;

			PtGen.pt(53);
			match(input,16,FOLLOW_16_in_inscond733); 
			pushFollow(FOLLOW_instructions_in_inscond735);
			instructions();
			state._fsp--;

			// projet.g:108:11: ( ',' expression ':' instructions )*
			loop30:
			while (true) {
				int alt30=2;
				int LA30_0 = input.LA(1);
				if ( (LA30_0==14) ) {
					alt30=1;
				}

				switch (alt30) {
				case 1 :
					// projet.g:108:12: ',' expression ':' instructions
					{
					match(input,14,FOLLOW_14_in_inscond749); 
					PtGen.pt(50);
					pushFollow(FOLLOW_expression_in_inscond753);
					expression();
					state._fsp--;

					PtGen.pt(53);
					match(input,16,FOLLOW_16_in_inscond757); 
					pushFollow(FOLLOW_instructions_in_inscond759);
					instructions();
					state._fsp--;

					}
					break;

				default :
					break loop30;
				}
			}

			// projet.g:109:11: ( 'aut' instructions |)
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==26) ) {
				alt31=1;
			}
			else if ( (LA31_0==39) ) {
				alt31=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}

			switch (alt31) {
				case 1 :
					// projet.g:109:12: 'aut' instructions
					{
					match(input,26,FOLLOW_26_in_inscond776); 
					PtGen.pt(56);
					pushFollow(FOLLOW_instructions_in_inscond780);
					instructions();
					state._fsp--;

					}
					break;
				case 2 :
					// projet.g:109:50: 
					{
					}
					break;

			}

			PtGen.pt(57);
			match(input,39,FOLLOW_39_in_inscond800); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "inscond"



	// $ANTLR start "boucle"
	// projet.g:113:1: boucle : 'ttq' expression 'faire' instructions 'fait' ;
	public final void boucle() throws RecognitionException {
		try {
			// projet.g:113:9: ( 'ttq' expression 'faire' instructions 'fait' )
			// projet.g:113:11: 'ttq' expression 'faire' instructions 'fait'
			{
			match(input,53,FOLLOW_53_in_boucle815); 
			PtGen.pt(52);
			pushFollow(FOLLOW_expression_in_boucle819);
			expression();
			state._fsp--;

			PtGen.pt(53);
			match(input,36,FOLLOW_36_in_boucle823); 
			pushFollow(FOLLOW_instructions_in_boucle825);
			instructions();
			state._fsp--;

			PtGen.pt(54);
			match(input,37,FOLLOW_37_in_boucle829); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "boucle"



	// $ANTLR start "lecture"
	// projet.g:116:1: lecture : 'lire' '(' ident ( ',' ident )* ')' ;
	public final void lecture() throws RecognitionException {
		try {
			// projet.g:116:8: ( 'lire' '(' ident ( ',' ident )* ')' )
			// projet.g:116:10: 'lire' '(' ident ( ',' ident )* ')'
			{
			match(input,43,FOLLOW_43_in_lecture841); 
			match(input,10,FOLLOW_10_in_lecture843); 
			pushFollow(FOLLOW_ident_in_lecture845);
			ident();
			state._fsp--;

			PtGen.pt(46);
			// projet.g:116:43: ( ',' ident )*
			loop32:
			while (true) {
				int alt32=2;
				int LA32_0 = input.LA(1);
				if ( (LA32_0==14) ) {
					alt32=1;
				}

				switch (alt32) {
				case 1 :
					// projet.g:116:45: ',' ident
					{
					match(input,14,FOLLOW_14_in_lecture851); 
					pushFollow(FOLLOW_ident_in_lecture853);
					ident();
					state._fsp--;

					PtGen.pt(46);
					}
					break;

				default :
					break loop32;
				}
			}

			match(input,11,FOLLOW_11_in_lecture860); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "lecture"



	// $ANTLR start "ecriture"
	// projet.g:119:1: ecriture : 'ecrire' '(' expression ( ',' expression )* ')' ;
	public final void ecriture() throws RecognitionException {
		try {
			// projet.g:119:9: ( 'ecrire' '(' expression ( ',' expression )* ')' )
			// projet.g:119:11: 'ecrire' '(' expression ( ',' expression )* ')'
			{
			match(input,33,FOLLOW_33_in_ecriture873); 
			match(input,10,FOLLOW_10_in_ecriture875); 
			pushFollow(FOLLOW_expression_in_ecriture877);
			expression();
			state._fsp--;

			PtGen.pt(45);
			// projet.g:119:52: ( ',' expression )*
			loop33:
			while (true) {
				int alt33=2;
				int LA33_0 = input.LA(1);
				if ( (LA33_0==14) ) {
					alt33=1;
				}

				switch (alt33) {
				case 1 :
					// projet.g:119:54: ',' expression
					{
					match(input,14,FOLLOW_14_in_ecriture884); 
					pushFollow(FOLLOW_expression_in_ecriture886);
					expression();
					state._fsp--;

					PtGen.pt(45);
					}
					break;

				default :
					break loop33;
				}
			}

			match(input,11,FOLLOW_11_in_ecriture894); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ecriture"



	// $ANTLR start "affouappel"
	// projet.g:122:1: affouappel : ident ( ':=' expression | ( effixes ( effmods )? )? ) ;
	public final void affouappel() throws RecognitionException {
		try {
			// projet.g:123:3: ( ident ( ':=' expression | ( effixes ( effmods )? )? ) )
			// projet.g:123:5: ident ( ':=' expression | ( effixes ( effmods )? )? )
			{
			pushFollow(FOLLOW_ident_in_affouappel910);
			ident();
			state._fsp--;

			PtGen.pt(47);
			// projet.g:123:27: ( ':=' expression | ( effixes ( effmods )? )? )
			int alt36=2;
			int LA36_0 = input.LA(1);
			if ( (LA36_0==17) ) {
				alt36=1;
			}
			else if ( (LA36_0==10||LA36_0==14||LA36_0==18||LA36_0==26||LA36_0==37||(LA36_0 >= 39 && LA36_0 <= 40)||LA36_0==42||LA36_0==52) ) {
				alt36=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}

			switch (alt36) {
				case 1 :
					// projet.g:123:29: ':=' expression
					{
					match(input,17,FOLLOW_17_in_affouappel916); 
					pushFollow(FOLLOW_expression_in_affouappel919);
					expression();
					state._fsp--;

					PtGen.pt(48);
					}
					break;
				case 2 :
					// projet.g:124:17: ( effixes ( effmods )? )?
					{
					// projet.g:124:17: ( effixes ( effmods )? )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==10) ) {
						alt35=1;
					}
					switch (alt35) {
						case 1 :
							// projet.g:124:18: effixes ( effmods )?
							{
							pushFollow(FOLLOW_effixes_in_affouappel940);
							effixes();
							state._fsp--;

							// projet.g:124:26: ( effmods )?
							int alt34=2;
							int LA34_0 = input.LA(1);
							if ( (LA34_0==10) ) {
								alt34=1;
							}
							switch (alt34) {
								case 1 :
									// projet.g:124:27: effmods
									{
									pushFollow(FOLLOW_effmods_in_affouappel943);
									effmods();
									state._fsp--;

									}
									break;

							}

							}
							break;

					}

					}
					break;

			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "affouappel"



	// $ANTLR start "effixes"
	// projet.g:128:1: effixes : '(' ( expression ( ',' expression )* )? ')' ;
	public final void effixes() throws RecognitionException {
		try {
			// projet.g:128:9: ( '(' ( expression ( ',' expression )* )? ')' )
			// projet.g:128:11: '(' ( expression ( ',' expression )* )? ')'
			{
			match(input,10,FOLLOW_10_in_effixes975); 
			// projet.g:128:15: ( expression ( ',' expression )* )?
			int alt38=2;
			int LA38_0 = input.LA(1);
			if ( ((LA38_0 >= ID && LA38_0 <= INT)||LA38_0==10||LA38_0==13||LA38_0==15||LA38_0==38||LA38_0==46||LA38_0==55) ) {
				alt38=1;
			}
			switch (alt38) {
				case 1 :
					// projet.g:128:16: expression ( ',' expression )*
					{
					pushFollow(FOLLOW_expression_in_effixes978);
					expression();
					state._fsp--;

					// projet.g:128:28: ( ',' expression )*
					loop37:
					while (true) {
						int alt37=2;
						int LA37_0 = input.LA(1);
						if ( (LA37_0==14) ) {
							alt37=1;
						}

						switch (alt37) {
						case 1 :
							// projet.g:128:29: ',' expression
							{
							match(input,14,FOLLOW_14_in_effixes982); 
							pushFollow(FOLLOW_expression_in_effixes984);
							expression();
							state._fsp--;

							}
							break;

						default :
							break loop37;
						}
					}

					}
					break;

			}

			match(input,11,FOLLOW_11_in_effixes992); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "effixes"



	// $ANTLR start "effmods"
	// projet.g:131:1: effmods : '(' ( ident ( ',' ident )* )? ')' ;
	public final void effmods() throws RecognitionException {
		try {
			// projet.g:131:9: ( '(' ( ident ( ',' ident )* )? ')' )
			// projet.g:131:10: '(' ( ident ( ',' ident )* )? ')'
			{
			match(input,10,FOLLOW_10_in_effmods1004); 
			// projet.g:131:14: ( ident ( ',' ident )* )?
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==ID) ) {
				alt40=1;
			}
			switch (alt40) {
				case 1 :
					// projet.g:131:15: ident ( ',' ident )*
					{
					pushFollow(FOLLOW_ident_in_effmods1007);
					ident();
					state._fsp--;

					// projet.g:131:22: ( ',' ident )*
					loop39:
					while (true) {
						int alt39=2;
						int LA39_0 = input.LA(1);
						if ( (LA39_0==14) ) {
							alt39=1;
						}

						switch (alt39) {
						case 1 :
							// projet.g:131:23: ',' ident
							{
							match(input,14,FOLLOW_14_in_effmods1011); 
							pushFollow(FOLLOW_ident_in_effmods1013);
							ident();
							state._fsp--;

							}
							break;

						default :
							break loop39;
						}
					}

					}
					break;

			}

			match(input,11,FOLLOW_11_in_effmods1021); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "effmods"



	// $ANTLR start "expression"
	// projet.g:134:1: expression : ( exp1 ) ( 'ou' exp1 )* ;
	public final void expression() throws RecognitionException {
		try {
			// projet.g:134:11: ( ( exp1 ) ( 'ou' exp1 )* )
			// projet.g:134:13: ( exp1 ) ( 'ou' exp1 )*
			{
			// projet.g:134:13: ( exp1 )
			// projet.g:134:14: exp1
			{
			pushFollow(FOLLOW_exp1_in_expression1035);
			exp1();
			state._fsp--;

			}

			// projet.g:134:20: ( 'ou' exp1 )*
			loop41:
			while (true) {
				int alt41=2;
				int LA41_0 = input.LA(1);
				if ( (LA41_0==47) ) {
					alt41=1;
				}

				switch (alt41) {
				case 1 :
					// projet.g:134:21: 'ou' exp1
					{
					match(input,47,FOLLOW_47_in_expression1039); 
					PtGen.pt(19);
					pushFollow(FOLLOW_exp1_in_expression1043);
					exp1();
					state._fsp--;

					PtGen.pt(19); PtGen.pt(21);
					}
					break;

				default :
					break loop41;
				}
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "expression"



	// $ANTLR start "exp1"
	// projet.g:137:1: exp1 : exp2 ( 'et' exp2 )* ;
	public final void exp1() throws RecognitionException {
		try {
			// projet.g:137:7: ( exp2 ( 'et' exp2 )* )
			// projet.g:137:9: exp2 ( 'et' exp2 )*
			{
			pushFollow(FOLLOW_exp2_in_exp11062);
			exp2();
			state._fsp--;

			// projet.g:137:14: ( 'et' exp2 )*
			loop42:
			while (true) {
				int alt42=2;
				int LA42_0 = input.LA(1);
				if ( (LA42_0==35) ) {
					alt42=1;
				}

				switch (alt42) {
				case 1 :
					// projet.g:137:15: 'et' exp2
					{
					match(input,35,FOLLOW_35_in_exp11065); 
					PtGen.pt(19);
					pushFollow(FOLLOW_exp2_in_exp11069);
					exp2();
					state._fsp--;

					PtGen.pt(19); PtGen.pt(22);
					}
					break;

				default :
					break loop42;
				}
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "exp1"



	// $ANTLR start "exp2"
	// projet.g:140:1: exp2 : ( 'non' exp2 | exp3 );
	public final void exp2() throws RecognitionException {
		try {
			// projet.g:140:7: ( 'non' exp2 | exp3 )
			int alt43=2;
			int LA43_0 = input.LA(1);
			if ( (LA43_0==46) ) {
				alt43=1;
			}
			else if ( ((LA43_0 >= ID && LA43_0 <= INT)||LA43_0==10||LA43_0==13||LA43_0==15||LA43_0==38||LA43_0==55) ) {
				alt43=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}

			switch (alt43) {
				case 1 :
					// projet.g:140:9: 'non' exp2
					{
					match(input,46,FOLLOW_46_in_exp21088); 
					pushFollow(FOLLOW_exp2_in_exp21090);
					exp2();
					state._fsp--;

					PtGen.pt(19); PtGen.pt(23);
					}
					break;
				case 2 :
					// projet.g:141:5: exp3
					{
					pushFollow(FOLLOW_exp3_in_exp21098);
					exp3();
					state._fsp--;

					}
					break;

			}
		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "exp2"



	// $ANTLR start "exp3"
	// projet.g:144:1: exp3 : exp4 ( '=' exp4 | '<>' exp4 | '>' exp4 | '>=' exp4 | '<' exp4 | '<=' exp4 )? ;
	public final void exp3() throws RecognitionException {
		try {
			// projet.g:144:7: ( exp4 ( '=' exp4 | '<>' exp4 | '>' exp4 | '>=' exp4 | '<' exp4 | '<=' exp4 )? )
			// projet.g:144:9: exp4 ( '=' exp4 | '<>' exp4 | '>' exp4 | '>=' exp4 | '<' exp4 | '<=' exp4 )?
			{
			pushFollow(FOLLOW_exp4_in_exp31114);
			exp4();
			state._fsp--;

			// projet.g:145:3: ( '=' exp4 | '<>' exp4 | '>' exp4 | '>=' exp4 | '<' exp4 | '<=' exp4 )?
			int alt44=7;
			switch ( input.LA(1) ) {
				case 22:
					{
					alt44=1;
					}
					break;
				case 21:
					{
					alt44=2;
					}
					break;
				case 23:
					{
					alt44=3;
					}
					break;
				case 24:
					{
					alt44=4;
					}
					break;
				case 19:
					{
					alt44=5;
					}
					break;
				case 20:
					{
					alt44=6;
					}
					break;
			}
			switch (alt44) {
				case 1 :
					// projet.g:145:5: '=' exp4
					{
					match(input,22,FOLLOW_22_in_exp31121); 
					PtGen.pt(20);
					pushFollow(FOLLOW_exp4_in_exp31126);
					exp4();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(24);
					}
					break;
				case 2 :
					// projet.g:146:5: '<>' exp4
					{
					match(input,21,FOLLOW_21_in_exp31134); 
					PtGen.pt(20);
					pushFollow(FOLLOW_exp4_in_exp31138);
					exp4();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(25);
					}
					break;
				case 3 :
					// projet.g:147:5: '>' exp4
					{
					match(input,23,FOLLOW_23_in_exp31146); 
					PtGen.pt(20);
					pushFollow(FOLLOW_exp4_in_exp31151);
					exp4();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(26);
					}
					break;
				case 4 :
					// projet.g:148:5: '>=' exp4
					{
					match(input,24,FOLLOW_24_in_exp31159); 
					PtGen.pt(20);
					pushFollow(FOLLOW_exp4_in_exp31163);
					exp4();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(27);
					}
					break;
				case 5 :
					// projet.g:149:5: '<' exp4
					{
					match(input,19,FOLLOW_19_in_exp31171); 
					PtGen.pt(20);
					pushFollow(FOLLOW_exp4_in_exp31176);
					exp4();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(28);
					}
					break;
				case 6 :
					// projet.g:150:5: '<=' exp4
					{
					match(input,20,FOLLOW_20_in_exp31184); 
					PtGen.pt(20);
					pushFollow(FOLLOW_exp4_in_exp31188);
					exp4();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(29);
					}
					break;

			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "exp3"



	// $ANTLR start "exp4"
	// projet.g:154:1: exp4 : exp5 ( '+' exp5 | '-' exp5 )* ;
	public final void exp4() throws RecognitionException {
		try {
			// projet.g:154:7: ( exp5 ( '+' exp5 | '-' exp5 )* )
			// projet.g:154:9: exp5 ( '+' exp5 | '-' exp5 )*
			{
			pushFollow(FOLLOW_exp5_in_exp41210);
			exp5();
			state._fsp--;

			// projet.g:155:9: ( '+' exp5 | '-' exp5 )*
			loop45:
			while (true) {
				int alt45=3;
				int LA45_0 = input.LA(1);
				if ( (LA45_0==13) ) {
					alt45=1;
				}
				else if ( (LA45_0==15) ) {
					alt45=2;
				}

				switch (alt45) {
				case 1 :
					// projet.g:155:10: '+' exp5
					{
					match(input,13,FOLLOW_13_in_exp41222); 
					PtGen.pt(20);
					pushFollow(FOLLOW_exp5_in_exp41226);
					exp5();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(30);
					}
					break;
				case 2 :
					// projet.g:156:10: '-' exp5
					{
					match(input,15,FOLLOW_15_in_exp41239); 
					PtGen.pt(20);
					pushFollow(FOLLOW_exp5_in_exp41243);
					exp5();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(31);
					}
					break;

				default :
					break loop45;
				}
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "exp4"



	// $ANTLR start "exp5"
	// projet.g:160:1: exp5 : primaire ( '*' primaire | 'div' primaire )* ;
	public final void exp5() throws RecognitionException {
		try {
			// projet.g:160:7: ( primaire ( '*' primaire | 'div' primaire )* )
			// projet.g:160:9: primaire ( '*' primaire | 'div' primaire )*
			{
			pushFollow(FOLLOW_primaire_in_exp51270);
			primaire();
			state._fsp--;

			// projet.g:161:9: ( '*' primaire | 'div' primaire )*
			loop46:
			while (true) {
				int alt46=3;
				int LA46_0 = input.LA(1);
				if ( (LA46_0==12) ) {
					alt46=1;
				}
				else if ( (LA46_0==32) ) {
					alt46=2;
				}

				switch (alt46) {
				case 1 :
					// projet.g:161:14: '*' primaire
					{
					match(input,12,FOLLOW_12_in_exp51286); 
					PtGen.pt(20);
					pushFollow(FOLLOW_primaire_in_exp51291);
					primaire();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(32);
					}
					break;
				case 2 :
					// projet.g:162:13: 'div' primaire
					{
					match(input,32,FOLLOW_32_in_exp51307); 
					PtGen.pt(20);
					pushFollow(FOLLOW_primaire_in_exp51311);
					primaire();
					state._fsp--;

					PtGen.pt(20); PtGen.pt(33);
					}
					break;

				default :
					break loop46;
				}
			}

			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "exp5"



	// $ANTLR start "primaire"
	// projet.g:166:1: primaire : ( valeur | ident | '(' expression ')' );
	public final void primaire() throws RecognitionException {
		try {
			// projet.g:166:9: ( valeur | ident | '(' expression ')' )
			int alt47=3;
			switch ( input.LA(1) ) {
			case INT:
			case 13:
			case 15:
			case 38:
			case 55:
				{
				alt47=1;
				}
				break;
			case ID:
				{
				alt47=2;
				}
				break;
			case 10:
				{
				alt47=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 47, 0, input);
				throw nvae;
			}
			switch (alt47) {
				case 1 :
					// projet.g:166:11: valeur
					{
					pushFollow(FOLLOW_valeur_in_primaire1336);
					valeur();
					state._fsp--;

					PtGen.pt(34);
					}
					break;
				case 2 :
					// projet.g:167:5: ident
					{
					pushFollow(FOLLOW_ident_in_primaire1344);
					ident();
					state._fsp--;

					PtGen.pt(35);
					}
					break;
				case 3 :
					// projet.g:168:5: '(' expression ')'
					{
					match(input,10,FOLLOW_10_in_primaire1353); 
					pushFollow(FOLLOW_expression_in_primaire1355);
					expression();
					state._fsp--;

					match(input,11,FOLLOW_11_in_primaire1357); 
					}
					break;

			}
		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "primaire"



	// $ANTLR start "valeur"
	// projet.g:171:1: valeur : ( nbentier | '+' nbentier | '-' nbentier | 'vrai' | 'faux' );
	public final void valeur() throws RecognitionException {
		try {
			// projet.g:171:9: ( nbentier | '+' nbentier | '-' nbentier | 'vrai' | 'faux' )
			int alt48=5;
			switch ( input.LA(1) ) {
			case INT:
				{
				alt48=1;
				}
				break;
			case 13:
				{
				alt48=2;
				}
				break;
			case 15:
				{
				alt48=3;
				}
				break;
			case 55:
				{
				alt48=4;
				}
				break;
			case 38:
				{
				alt48=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}
			switch (alt48) {
				case 1 :
					// projet.g:171:11: nbentier
					{
					pushFollow(FOLLOW_nbentier_in_valeur1371);
					nbentier();
					state._fsp--;

					PtGen.pt(4);
					}
					break;
				case 2 :
					// projet.g:172:5: '+' nbentier
					{
					match(input,13,FOLLOW_13_in_valeur1379); 
					pushFollow(FOLLOW_nbentier_in_valeur1381);
					nbentier();
					state._fsp--;

					PtGen.pt(4);
					}
					break;
				case 3 :
					// projet.g:173:5: '-' nbentier
					{
					match(input,15,FOLLOW_15_in_valeur1389); 
					pushFollow(FOLLOW_nbentier_in_valeur1391);
					nbentier();
					state._fsp--;

					PtGen.pt(5);
					}
					break;
				case 4 :
					// projet.g:174:5: 'vrai'
					{
					match(input,55,FOLLOW_55_in_valeur1399); 
					PtGen.pt(6);
					}
					break;
				case 5 :
					// projet.g:175:5: 'faux'
					{
					match(input,38,FOLLOW_38_in_valeur1407); 
					PtGen.pt(7);
					}
					break;

			}
		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "valeur"



	// $ANTLR start "nbentier"
	// projet.g:184:1: nbentier : INT ;
	public final void nbentier() throws RecognitionException {
		Token INT1=null;

		try {
			// projet.g:184:11: ( INT )
			// projet.g:184:15: INT
			{
			INT1=(Token)match(input,INT,FOLLOW_INT_in_nbentier1436); 
			 UtilLex.valNb = Integer.parseInt((INT1!=null?INT1.getText():null));
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "nbentier"



	// $ANTLR start "ident"
	// projet.g:185:1: ident : ID ;
	public final void ident() throws RecognitionException {
		Token ID2=null;

		try {
			// projet.g:185:7: ( ID )
			// projet.g:185:9: ID
			{
			ID2=(Token)match(input,ID,FOLLOW_ID_in_ident1446); 
			 UtilLex.traiterId((ID2!=null?ID2.getText():null)); 
			}

		}

		catch (RecognitionException e) {reportError (e) ; throw e ; }
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ident"

	// Delegated rules



	public static final BitSet FOLLOW_unitprog_in_unite54 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_unite57 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unitmodule_in_unite70 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_unite73 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_49_in_unitprog88 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_unitprog90 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_unitprog92 = new BitSet(new long[]{0x00450000E0000000L});
	public static final BitSet FOLLOW_declarations_in_unitprog101 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_corps_in_unitprog110 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_45_in_unitmodule127 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_unitmodule129 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_unitmodule131 = new BitSet(new long[]{0x00450000A0000000L});
	public static final BitSet FOLLOW_declarations_in_unitmodule139 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_partiedef_in_declarations157 = new BitSet(new long[]{0x0045000020000002L});
	public static final BitSet FOLLOW_partieref_in_declarations160 = new BitSet(new long[]{0x0041000020000002L});
	public static final BitSet FOLLOW_consts_in_declarations163 = new BitSet(new long[]{0x0041000000000002L});
	public static final BitSet FOLLOW_vars_in_declarations166 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_decprocs_in_declarations169 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_31_in_partiedef186 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_partiedef188 = new BitSet(new long[]{0x0000000000044000L});
	public static final BitSet FOLLOW_14_in_partiedef192 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_partiedef194 = new BitSet(new long[]{0x0000000000044000L});
	public static final BitSet FOLLOW_ptvg_in_partiedef199 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_partieref211 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_specif_in_partieref214 = new BitSet(new long[]{0x0000000000044000L});
	public static final BitSet FOLLOW_14_in_partieref217 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_specif_in_partieref219 = new BitSet(new long[]{0x0000000000044000L});
	public static final BitSet FOLLOW_ptvg_in_partieref223 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ident_in_specif237 = new BitSet(new long[]{0x0000120000000002L});
	public static final BitSet FOLLOW_41_in_specif242 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_specif244 = new BitSet(new long[]{0x0000000408000000L});
	public static final BitSet FOLLOW_type_in_specif246 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_14_in_specif251 = new BitSet(new long[]{0x0000000408000000L});
	public static final BitSet FOLLOW_type_in_specif253 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_11_in_specif259 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_44_in_specif284 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_specif287 = new BitSet(new long[]{0x0000000408000000L});
	public static final BitSet FOLLOW_type_in_specif289 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_14_in_specif294 = new BitSet(new long[]{0x0000000408000000L});
	public static final BitSet FOLLOW_type_in_specif296 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_11_in_specif302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_29_in_consts320 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_consts324 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_consts326 = new BitSet(new long[]{0x008000400000A040L});
	public static final BitSet FOLLOW_valeur_in_consts328 = new BitSet(new long[]{0x0000000000040020L});
	public static final BitSet FOLLOW_ptvg_in_consts331 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_54_in_vars351 = new BitSet(new long[]{0x0000000408000000L});
	public static final BitSet FOLLOW_type_in_vars355 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_vars358 = new BitSet(new long[]{0x0000000408044000L});
	public static final BitSet FOLLOW_14_in_vars364 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_vars367 = new BitSet(new long[]{0x0000000408044000L});
	public static final BitSet FOLLOW_ptvg_in_vars374 = new BitSet(new long[]{0x0000000408000002L});
	public static final BitSet FOLLOW_34_in_type391 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_type404 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_decproc_in_decprocs419 = new BitSet(new long[]{0x0001000000040000L});
	public static final BitSet FOLLOW_ptvg_in_decprocs421 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_48_in_decproc437 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_decproc440 = new BitSet(new long[]{0x0040120060000000L});
	public static final BitSet FOLLOW_parfixe_in_decproc443 = new BitSet(new long[]{0x0040100060000000L});
	public static final BitSet FOLLOW_parmod_in_decproc446 = new BitSet(new long[]{0x0040000060000000L});
	public static final BitSet FOLLOW_consts_in_decproc449 = new BitSet(new long[]{0x0040000040000000L});
	public static final BitSet FOLLOW_vars_in_decproc452 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_corps_in_decproc455 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_18_in_ptvg470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_corps490 = new BitSet(new long[]{0x0028080210040020L});
	public static final BitSet FOLLOW_instructions_in_corps492 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_40_in_corps494 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_parfixe508 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_parfixe510 = new BitSet(new long[]{0x0000000408000000L});
	public static final BitSet FOLLOW_pf_in_parfixe512 = new BitSet(new long[]{0x0000000000040800L});
	public static final BitSet FOLLOW_18_in_parfixe516 = new BitSet(new long[]{0x0000000408000000L});
	public static final BitSet FOLLOW_pf_in_parfixe518 = new BitSet(new long[]{0x0000000000040800L});
	public static final BitSet FOLLOW_11_in_parfixe522 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_in_pf536 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_pf538 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_14_in_pf543 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_pf545 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_44_in_parmod562 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_parmod564 = new BitSet(new long[]{0x0000000408000000L});
	public static final BitSet FOLLOW_pm_in_parmod566 = new BitSet(new long[]{0x0000000000040800L});
	public static final BitSet FOLLOW_18_in_parmod570 = new BitSet(new long[]{0x0000000408000000L});
	public static final BitSet FOLLOW_pm_in_parmod572 = new BitSet(new long[]{0x0000000000040800L});
	public static final BitSet FOLLOW_11_in_parmod576 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_in_pm590 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_pm592 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_14_in_pm597 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_pm599 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_instruction_in_instructions618 = new BitSet(new long[]{0x0000000000040002L});
	public static final BitSet FOLLOW_18_in_instructions622 = new BitSet(new long[]{0x0028080210040020L});
	public static final BitSet FOLLOW_instruction_in_instructions624 = new BitSet(new long[]{0x0000000000040002L});
	public static final BitSet FOLLOW_inssi_in_instruction641 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inscond_in_instruction648 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_boucle_in_instruction654 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_lecture_in_instruction660 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ecriture_in_instruction666 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_affouappel_in_instruction672 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_51_in_inssi689 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_expression_in_inssi691 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_inssi695 = new BitSet(new long[]{0x0028080210040020L});
	public static final BitSet FOLLOW_instructions_in_inssi697 = new BitSet(new long[]{0x0010040000000000L});
	public static final BitSet FOLLOW_52_in_inssi701 = new BitSet(new long[]{0x0028080210040020L});
	public static final BitSet FOLLOW_instructions_in_inssi705 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_42_in_inssi711 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_inscond725 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_expression_in_inscond729 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_inscond733 = new BitSet(new long[]{0x0028080210040020L});
	public static final BitSet FOLLOW_instructions_in_inscond735 = new BitSet(new long[]{0x0000008004004000L});
	public static final BitSet FOLLOW_14_in_inscond749 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_expression_in_inscond753 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_inscond757 = new BitSet(new long[]{0x0028080210040020L});
	public static final BitSet FOLLOW_instructions_in_inscond759 = new BitSet(new long[]{0x0000008004004000L});
	public static final BitSet FOLLOW_26_in_inscond776 = new BitSet(new long[]{0x0028080210040020L});
	public static final BitSet FOLLOW_instructions_in_inscond780 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_39_in_inscond800 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_53_in_boucle815 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_expression_in_boucle819 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_36_in_boucle823 = new BitSet(new long[]{0x0028080210040020L});
	public static final BitSet FOLLOW_instructions_in_boucle825 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_37_in_boucle829 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_43_in_lecture841 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_lecture843 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_lecture845 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_14_in_lecture851 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_lecture853 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_11_in_lecture860 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_33_in_ecriture873 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_ecriture875 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_expression_in_ecriture877 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_14_in_ecriture884 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_expression_in_ecriture886 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_11_in_ecriture894 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ident_in_affouappel910 = new BitSet(new long[]{0x0000000000020402L});
	public static final BitSet FOLLOW_17_in_affouappel916 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_expression_in_affouappel919 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_effixes_in_affouappel940 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_effmods_in_affouappel943 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_effixes975 = new BitSet(new long[]{0x008040400000AC60L});
	public static final BitSet FOLLOW_expression_in_effixes978 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_14_in_effixes982 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_expression_in_effixes984 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_11_in_effixes992 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_effmods1004 = new BitSet(new long[]{0x0000000000000820L});
	public static final BitSet FOLLOW_ident_in_effmods1007 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_14_in_effmods1011 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ident_in_effmods1013 = new BitSet(new long[]{0x0000000000004800L});
	public static final BitSet FOLLOW_11_in_effmods1021 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_exp1_in_expression1035 = new BitSet(new long[]{0x0000800000000002L});
	public static final BitSet FOLLOW_47_in_expression1039 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_exp1_in_expression1043 = new BitSet(new long[]{0x0000800000000002L});
	public static final BitSet FOLLOW_exp2_in_exp11062 = new BitSet(new long[]{0x0000000800000002L});
	public static final BitSet FOLLOW_35_in_exp11065 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_exp2_in_exp11069 = new BitSet(new long[]{0x0000000800000002L});
	public static final BitSet FOLLOW_46_in_exp21088 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_exp2_in_exp21090 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_exp3_in_exp21098 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_exp4_in_exp31114 = new BitSet(new long[]{0x0000000001F80002L});
	public static final BitSet FOLLOW_22_in_exp31121 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_exp4_in_exp31126 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_21_in_exp31134 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_exp4_in_exp31138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_23_in_exp31146 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_exp4_in_exp31151 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_24_in_exp31159 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_exp4_in_exp31163 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_19_in_exp31171 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_exp4_in_exp31176 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_20_in_exp31184 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_exp4_in_exp31188 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_exp5_in_exp41210 = new BitSet(new long[]{0x000000000000A002L});
	public static final BitSet FOLLOW_13_in_exp41222 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_exp5_in_exp41226 = new BitSet(new long[]{0x000000000000A002L});
	public static final BitSet FOLLOW_15_in_exp41239 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_exp5_in_exp41243 = new BitSet(new long[]{0x000000000000A002L});
	public static final BitSet FOLLOW_primaire_in_exp51270 = new BitSet(new long[]{0x0000000100001002L});
	public static final BitSet FOLLOW_12_in_exp51286 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_primaire_in_exp51291 = new BitSet(new long[]{0x0000000100001002L});
	public static final BitSet FOLLOW_32_in_exp51307 = new BitSet(new long[]{0x008000400000A460L});
	public static final BitSet FOLLOW_primaire_in_exp51311 = new BitSet(new long[]{0x0000000100001002L});
	public static final BitSet FOLLOW_valeur_in_primaire1336 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ident_in_primaire1344 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_primaire1353 = new BitSet(new long[]{0x008040400000A460L});
	public static final BitSet FOLLOW_expression_in_primaire1355 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_primaire1357 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nbentier_in_valeur1371 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_13_in_valeur1379 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_nbentier_in_valeur1381 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_15_in_valeur1389 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_nbentier_in_valeur1391 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_55_in_valeur1399 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_38_in_valeur1407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_nbentier1436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_ident1446 = new BitSet(new long[]{0x0000000000000002L});
}
