
DROP TABLE ClientCompte;
DROP TABLE Operation;
DROP TABLE Compte;
DROP TABLE Client;
DROP TABLE Adresse;

DROP TABLE Devise;


CREATE TABLE Client(
	nom VARCHAR(64),
	prenom VARCHAR(64),
	numClient integer  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	dateNaissance DATE,
	telephone VARCHAR(16),
	email VARCHAR(64),
	ref_adressePrincipale integer,
	password VARCHAR(64),
	PRIMARY KEY(numClient));	 

CREATE TABLE Adresse(
	codePostal VARCHAR(64),
	ville VARCHAR(64),
	rue VARCHAR(64),
	idAdr integer  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	
	PRIMARY KEY(idAdr));	 


CREATE TABLE Compte(
	label VARCHAR(64),
	numCpt integer  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	solde double,
	PRIMARY KEY(numCpt));	 

CREATE TABLE Operation(
	label VARCHAR(64),
	montant double,
	numOp integer  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	dateOp DATE,
	ref_compte integer,
	PRIMARY KEY(numOp));	 
	
CREATE TABLE ClientCompte(
	numCli integer,
	numCpt integer,
	PRIMARY KEY(numCli,numCpt));
	
	
CREATE TABLE Devise(
	codeDevise VARCHAR(8),
	monnaie VARCHAR(64),
	dChange double,
	PRIMARY KEY(codeDevise));	 
	



ALTER TABLE Client ADD CONSTRAINT Client_avec_adressePrincipale_valide 
FOREIGN KEY (ref_adressePrincipale) REFERENCES Adresse(idAdr);



ALTER TABLE ClientCompte ADD CONSTRAINT ClientCompte_avec_client_valide 
FOREIGN KEY (numCli) REFERENCES Client(numClient);
ALTER TABLE ClientCompte ADD CONSTRAINT ClientCompte_avec_compte_valide 
FOREIGN KEY (numCpt) REFERENCES Compte(numCpt);


ALTER TABLE Operation ADD CONSTRAINT Operation_avec_compte_valide 
FOREIGN KEY (ref_compte) REFERENCES Compte(numCpt);





