programme b:

const min=7; max=+77; marq=-1; oui=vrai; nenni=faux;
var ent i,j;
    bool b1,b2;

debut

	i:= (max-min) div 2;
	si i>70 alors
		b1:= oui et nenni;
		j:= (i+5)*10;
	sinon
		j:= (i-5)*2;
		b1:= (i<=j) ou (i<>10);
	fsi;
	ecrire(i,j);
	ecrire(b1);
fin
