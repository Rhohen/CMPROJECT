:: commandes d'execution de antlr sur une grammaire contenue dans un fichier de suffixe .g
:: appel par g2java nom-de-votre-grammaire-suffixe-par-g

:: Commande si antlr-3.5.2-complete.jar est celui du share
java -cp  H:\workspace\CMPROJECT\antlr\antlr-3.5.2-complete.jar org.antlr.Tool %*

:: Commande si antlr-3.5.2-complete.jar EST COPIE SOUS VOTRE REPERTOIRE
::    -> pensez alors a indiquer le chemin correct
:: java -cp H:\...\antlr-3.5.2-complete.jar org.antlr.Tool %*


