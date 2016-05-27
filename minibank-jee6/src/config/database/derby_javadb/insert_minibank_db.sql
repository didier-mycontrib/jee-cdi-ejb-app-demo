

INSERT INTO Adresse (codePostal,rue,ville)  VALUES ('75000','2 rue elle','Paris Derby');
INSERT INTO Client (nom,prenom,dateNaissance,ref_adressePrincipale,password,telephone,email)
              VALUES ('Derby','Didier','1969-07-11',1,'mypwd','0102030405','didier@ici_ou_la');
INSERT INTO Client (nom,prenom,dateNaissance,ref_adressePrincipale,password,telephone,email)
              VALUES ('Derby','Alex','1969-07-12',1,'mypwd','010900909','alex@ici_ou_la');              
INSERT INTO Compte (label,solde) VALUES ('compte derby courant',1800.0);
INSERT INTO Compte (label,solde) VALUES ('compte derby codevi',500.0);   
INSERT INTO Compte (label,solde) VALUES ('compte derby 3',520.0);  
INSERT INTO Operation (dateOp,label,montant,ref_compte)  VALUES ('2011-01-20','achat yy',-50.0,1);
INSERT INTO Operation (dateOp,label,montant,ref_compte)  VALUES ('2011-01-21','achat zz',-30.0,1);
INSERT INTO ClientCompte (numCli,numCpt) VALUES (1,1);
INSERT INTO ClientCompte (numCli,numCpt) VALUES (1,2);
INSERT INTO ClientCompte (numCli,numCpt) VALUES (2,3);

INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('E',1.2,'euro');
INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('Y',0.2,'yen');
INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('D',1.0,'dollar');
INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('L',1.1,'livre');


SELECT * FROM Client;
SELECT * FROM Adresse;
SELECT * FROM Compte;
SELECT * FROM Operation;
SELECT * FROM ClientCompte;

SELECT * FROM Devise;


