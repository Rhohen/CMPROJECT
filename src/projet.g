// Grammaire du langage PROJET
// COMP L3  
// Anne Grazon, Veronique Masson
// il convient d'y inserer les appels a {PtGen.pt(k);}
// relancer Antlr apres chaque modification et raffraichir le projet Eclipse le cas echeant

// attention l'analyse est poursuivie apres erreur si l'on supprime la clause rulecatch

grammar projet;

options {
  language=Java; k=1;
 }

@header {           
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;
} 


// partie syntaxique :  description de la grammaire //
// les non-terminaux doivent commencer par une minuscule


@members {

 
// variables globales et methodes utiles a placer ici
  
}
// la directive rulecatch permet d'interrompre l'analyse a la premiere erreur de syntaxe
@rulecatch {
catch (RecognitionException e) {reportError (e) ; throw e ; }}


unite  :   unitprog  EOF  
      |    unitmodule  EOF
  ;
  
unitprog
  : 'programme' ident ':'  
     declarations  
     corps {PtGen.pt(255);} { System.out.println("succes, arret de la compilation "); }
  ;
  
unitmodule
  : 'module' ident ':' 
     declarations   
  ;
  
declarations
  : partiedef? partieref? consts? vars? decprocs? 
  ;
  
partiedef
  : 'def' ident  (',' ident )* ptvg
  ;
  
partieref: 'ref'  specif (',' specif)* ptvg
  ;
  
specif  : ident  ( 'fixe' '(' type  ( ',' type  )* ')' )? 
                 ( 'mod'  '(' type  ( ',' type  )* ')' )? 
  ;
  
consts  : 'const' ( ident '=' valeur  ptvg {PtGen.pt(1);} )+ 
  ;
  
vars  : 'var' ( type  ident {PtGen.pt(2);} ( ','  ident {PtGen.pt(2);} )* ptvg )+ {PtGen.pt(3);}
  ;
  
type  : 'ent' {PtGen.pt(8);} 
  |     'bool' {PtGen.pt(9);}
  ;
  
decprocs: {PtGen.pt(70);} (decproc ptvg)+ {PtGen.pt(51);}
  ;
  
decproc :  'proc'  ident {PtGen.pt(65);} parfixe? parmod? {PtGen.pt(68);} consts? vars?  corps {PtGen.pt(69);}
  ;
  
ptvg  : ';'
  | 
  ;
  
corps : 'debut' instructions 'fin'
  ;
  
parfixe: 'fixe' '(' pf ( ';' pf)* ')'
  ;
  
pf  : type ident {PtGen.pt(66);} ( ',' ident {PtGen.pt(66);} )*  
  ;

parmod  : 'mod' '(' pm ( ';' pm)* ')'
  ;
  
pm  : type ident {PtGen.pt(67);} ( ',' ident {PtGen.pt(67);} )*
  ;
  
instructions
  : instruction ( ';' instruction)*
  ;
  
instruction
  : inssi 
  | inscond
  | boucle
  | lecture
  | ecriture
  | affouappel
  |
  ;
  
inssi : 'si' expression {PtGen.pt(49);} 'alors' instructions ( 'sinon' {PtGen.pt(50);} instructions)? {PtGen.pt(51);} 'fsi' 
  ;
  
inscond : 'cond' {PtGen.pt(55);} expression {PtGen.pt(53);} ':' instructions 
          (',' {PtGen.pt(50);} expression {PtGen.pt(53);} ':' instructions )* 
          ('aut' {PtGen.pt(56);} instructions |  ) 
          {PtGen.pt(57);} 'fcond' 
  ;
  
boucle  : 'ttq' {PtGen.pt(52);} expression {PtGen.pt(53);} 'faire' instructions {PtGen.pt(54);} 'fait'
  ;
  
lecture: 'lire' '(' ident {PtGen.pt(46);} ( ',' ident {PtGen.pt(46);} )* ')' 
  ;
  
ecriture: 'ecrire' '(' expression  {PtGen.pt(45);} ( ',' expression  {PtGen.pt(45);} )* ')'
   ;
  
affouappel
  : ident {PtGen.pt(47);} ( ':='  expression {PtGen.pt(48);}
            |   (effixes (effmods)?)? {PtGen.pt(72);}
           )
  ;
  
effixes : '(' (expression  (',' expression  )*)? ')'
  ;
  
effmods :'(' (ident {PtGen.pt(71);} (',' ident {PtGen.pt(71);} )*)? ')'
  ; 
  
expression: (exp1) ('ou' {PtGen.pt(19);} exp1 {PtGen.pt(19); PtGen.pt(21);} )*
  ;
  
exp1  : exp2 ('et' {PtGen.pt(19);} exp2 {PtGen.pt(19); PtGen.pt(22);} )*
  ;
  
exp2  : 'non' exp2 {PtGen.pt(19); PtGen.pt(23);}
  | exp3  
  ;
  
exp3  : exp4 
  ( '='  {PtGen.pt(20);} exp4 {PtGen.pt(20); PtGen.pt(24);}
  | '<>' {PtGen.pt(20);} exp4 {PtGen.pt(20); PtGen.pt(25);}
  | '>'  {PtGen.pt(20);} exp4 {PtGen.pt(20); PtGen.pt(26);}
  | '>=' {PtGen.pt(20);} exp4 {PtGen.pt(20); PtGen.pt(27);}
  | '<'  {PtGen.pt(20);} exp4 {PtGen.pt(20); PtGen.pt(28);}
  | '<=' {PtGen.pt(20);} exp4 {PtGen.pt(20); PtGen.pt(29);}
  ) ?
  ;
  
exp4  : exp5 
        ('+' {PtGen.pt(20);} exp5 {PtGen.pt(20); PtGen.pt(30);}
        |'-' {PtGen.pt(20);} exp5 {PtGen.pt(20); PtGen.pt(31);}
        )*
  ;
  
exp5  : primaire 
        (    '*'  {PtGen.pt(20);} primaire {PtGen.pt(20); PtGen.pt(32);}
          | 'div' {PtGen.pt(20);} primaire {PtGen.pt(20); PtGen.pt(33);}
        )*
  ;
  
primaire: valeur {PtGen.pt(34);}
  | ident  {PtGen.pt(35);}
  | '(' expression ')'
  ;
  
valeur  : nbentier {PtGen.pt(4);}
  | '+' nbentier {PtGen.pt(4);}
  | '-' nbentier {PtGen.pt(5);}
  | 'vrai' {PtGen.pt(6);}
  | 'faux' {PtGen.pt(7);}
  ;

// partie lexicale  : cette partie ne doit pas etre modifie  //
// les unites lexicales de ANTLR doivent commencer par une majuscule
// attention : ANTLR n'autorise pas certains traitements sur les unites lexicales, 
// il est alors ncessaire de passer par un non-terminal intermediaire 
// exemple : pour l'unit lexicale INT, le non-terminal nbentier a du etre introduit
 
      
nbentier  :   INT { UtilLex.valNb = Integer.parseInt($INT.text);}; // mise a jour de valNb

ident : ID  { UtilLex.traiterId($ID.text); } ; // mise a jour de numId
     // tous les identificateurs seront places dans la table des identificateurs, y compris le nom du programme ou module
     // la table des symboles n'est pas geree au niveau lexical
        
  
ID  :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ; 
     
// zone purement lexicale //

INT :   '0'..'9'+ ;
WS  :   (' '|'\t' |'\r')+ {skip();} ; // definition des "espaces"
LIGNE :   '\n' {UtilLex.incrementeLigne();skip();};

COMMENT
  :  '\{' (.)* '\}' {skip();}   // toute suite de caracteres entouree d'accolades est un commentaire
  |  '#' ~( '\r' | '\n' )* {skip();}  // tout ce qui suit un caractere diese sur une ligne est un commentaire
  ;

// commentaires sur plusieurs lignes
ML_COMMENT    :   '/*' (options {greedy=false;} : .)* '*/' {$channel=HIDDEN;}
    ;	   
