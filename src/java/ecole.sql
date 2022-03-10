create database ecole;
create role ecole login password 'ecole';
alter database ecole owner to ecole;

create table classes(
	IdClasses serial PRIMARY KEY,
	nom varchar(20)
);

create table hsitoriqueEcolage(
	IdhistoriqueEcolage serial PRIMARY KEY,
	IdClasses int,
	ecolage decimal,
	anneeDebut date,
	anneeFin date,
	foreign key (IdClasses) references classes(IdClasses)
);

create table personnes(
	IdPersonne serial PRIMARY KEY,
	nom varchar(20),
	prenom varchar(20),
	naissance date,
	sexe varchar(10),
	adresse varchar(30)
);

create table eleves(
	IdEleve serial PRIMARY KEY,
	IdPersonne int,
	IdClasses int,
	statut varchar(30),
	cree_le date,
	foreign key (IdClasses) references classes(IdClasses),
	foreign key (IdClasses) references classes(IdClasses)
);

create table professeurs(
	IdProfesseur serial PRIMARY KEY,
	IdPersonne int,
	statut varchar(30),
	foreign key(IdPersonne) references personnes(IdPersonne)
);

create table ClasseProf(
	IdClasseProf serial PRIMARY KEY,
	IdClasses int,
	IdProfesseur int,
	foreign key(IdClasses) references classes(IdClasses),
	foreign key(IdProfesseur) references professeurs(IdProfesseur)
);

create table historiqueClasse(
	IdHistoriqueClasse serial PRIMARY KEY,
	IdEleve int,
	IdClasses int,
	debut date,
	fin date,
	foreign key(IdEleve) references eleves(IdEleve),
	foreign key(IdClasses) references classes(IdClasses)
);

create table paimentEcolage(
	IdPaimentEcolage serial PRIMARY KEY,
	IdEleve int,
	somme decimal,
	duMois date,
	paye_le date,
	foreign key(IdEleve) references eleves(IdEleve)
);

create table matieres(
	Idmatiere serial PRIMARY KEY,
	nom varchar(20),
	coefficient int
);

create table inscriptionMatieres(
	IdInscriptionmatiere serial PRIMARY KEY,
	IdEleve int,
	Idmatiere int,
	foreign key(IdEleve) references eleves(IdEleve),
	foreign key(Idmatiere) references matieres(Idmatiere)
);

create table note(
	IdNote serial PRIMARY KEY,
	IdEleve int,
	Idmatiere int,
	poimt float,
	dateExamen date,
	validation boolean,
	foreign key(IdEleve) references eleves(IdEleve),
	foreign key(Idmatiere) references matieres(Idmatiere)
);

create table examens(
	IdExamen serial PRIMARY KEY,
	IdClasses int,
	debut date,
	fin date,
	foreign key(IdClasses) references classes(IdClasses)
);

create table Directeur(
    idDirecteur serial primary key,
    idPersonne int not null,
    statut varchar(15),
    foreign key (idPersonne) references personnes (idPersonne)
);

select eleves.ideleve as idEleve,personnes.nom as nom,personnes.naissance as dateNaissance,personnes.sexe as sexe,
personnes.adresse as adresse,eleves.statut as statut,eleves.cree_le as cree_le 
from eleves join personnes on eleves.idPersonne = personnes.idpersonne;

VIEW ELEVE
create or replace view Eleve as
select eleves.ideleve as idEleve,personnes.nom as nom,classes.idclasses as idClasse,classes.nom as classeNom,personnes.naissance 
as dateNaissance,personnes.sexe as sexe,personnes.adresse as adresse,eleves.statut as statut,eleves.cree_le as cree_le 
from eleves join classes on eleves.idclasses = classes.idclasses join personnes on eleves.idPersonne = personnes.idpersonne;


select personnes.nom as nom,personnes.naissance as dateNaissance,personnes.sexe as sexe,
personnes.adresse as adresse,classes.nom as classeNom,hsitoriqueEcolage.ecolage as classeEcolage,paimentEcolage.somme 
as sommePaye,paimentEcolage.dumois as duMois,paimentEcolage.paye_le as payeLe
from eleves join personnes on eleves.idpersonne = personnes.idpersonne join historiqueClasse
on eleves.ideleve = historiqueClasse.ideleve join classes on classes.idClasses = historiqueClasse.idClasses 
join hsitoriqueEcolage on classes.idClasses = hsitoriqueEcolage.idClasses join paimentEcolage 
on eleves.ideleve = paimentEcolage.ideleve;

VIEW PAIMENTECOLAGE
create or replace view PaimentEcolages as 
select personnes.nom as nom,personnes.naissance as dateNaissance,personnes.sexe as sexe,
personnes.adresse as adresse,classes.nom as classeNom,hsitoriqueEcolage.ecolage as classeEcolage,paimentEcolage.somme 
as sommePaye,paimentEcolage.dumois as duMois,paimentEcolage.paye_le as payeLe
from eleves join personnes on eleves.idpersonne = personnes.idpersonne join historiqueClasse
on eleves.ideleve = historiqueClasse.ideleve join classes on classes.idClasses = historiqueClasse.idClasses 
join hsitoriqueEcolage on classes.idClasses = hsitoriqueEcolage.idClasses join paimentEcolage 
on eleves.ideleve = paimentEcolage.ideleve;



select classes.idclasses as idClasse,classes.nom as classeNom,hsitoriqueEcolage.ecolage as ecolage,
historiqueclasse.debut as anneDebut,historiqueclasse.fin as anneFin from classes join hsitoriqueecolage 
on classes.idclasses = hsitoriqueEcolage.idclasses join historiqueclasse on classes.idclasses = historiqueclasse.idclasses;

VIEW ECOLAGENIVEAU
create or replace view EcolageNiveau as 
select classes.idclasses as idClasse,classes.nom as classeNom,hsitoriqueEcolage.ecolage as ecolage,
hsitoriqueEcolage.anneedebut as anneDebut,hsitoriqueEcolage.anneefin as anneFin from classes join hsitoriqueecolage 
on classes.idclasses = hsitoriqueEcolage.idclasses;


select eleves.ideleve as idEleve,personnes.nom as eleveNom,classes.nom as eleveClasse,
inscriptionmatieres.idMatiere as idMatiere,matieres.nom as matiereNom,matieres.coefficient as matiereCoefficient,
note.poimt as notePoint,note.dateexamen as noteDateExamen 
from eleves join personnes on eleves.idPersonne = personnes.idPersonne 
join classes on eleves.idClasses = classes.idClasses join inscriptionmatieres 
on eleves.idEleve = inscriptionmatieres.idEleve join matieres on inscriptionmatieres.idMatiere = matieres.idMatiere
join note on matieres.idMatiere = note.idMatiere;

VIEW NOTE
create or replace view Notes as 
select eleves.ideleve as idEleve,personnes.nom as eleveNom,classes.nom as eleveClasse,
inscriptionmatieres.idMatiere as idMatiere,matieres.nom as matiereNom,matieres.coefficient as matiereCoefficient,
note.poimt as notePoint,note.dateexamen as noteDateExamen 
from eleves join personnes on eleves.idPersonne = personnes.idPersonne 
join classes on eleves.idClasses = classes.idClasses join inscriptionmatieres 
on eleves.idEleve = inscriptionmatieres.idEleve join matieres on inscriptionmatieres.idMatiere = matieres.idMatiere
join note on matieres.idMatiere = note.idMatiere;


VIEW RESULTATCLASSE
select classes.nom as classeNom,AVG(note) as moyenne,historiqueclasse.debut as anneDebut,historiqueclasse.fin as anneFin
from NoteClasse join eleves on note.idEleve = eleves.idEleve join classes on eleves.idClasses = classes.idClasses
join historiqueclasse on historiqueclasse.idclasses = classes.idclasses;


CLASSE
insert into classes (nom) values ('2nd A');
insert into classes (nom) values ('2nd B');
insert into classes (nom) values ('1ere L');
insert into classes (nom) values ('1ere S');
insert into classes (nom) values ('Terminal L');
insert into classes (nom) values ('Terminal S');

PERSONNE
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rakoto','Vao','1999-03-21',false,'B642');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Kanto','Niaina','2002-02-18',true,'Itaosy cite');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rivo','Lala','1995-06-30',false,'C331');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rajao','Maria','1990-05-12',false,'Ivato aeroport');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Iaro','Liantsoa','2003-05-12',true,'b225 Itaosy');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Ratsimba','Zafy','1985-10-11',true,'B202');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rabenanahary','Rojo','2000-05-25',false,'Ivandry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rija','Rakoto','2001-09-01',false,'Isotry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Fanja','Nofy','2001-09-01',true,'Ankadimbahoka');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Haja','Rakoto','2001-09-01',false,'Tanjombato');

insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Razafindrabe','Sylvio','1999-03-21',false,'B642');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Falinarivoniaina','Samantha','2002-02-18',true,'Itaosy cite');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rakotobe','Hery','1995-06-30',false,'C331');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Andriananahary','Kendjy','1990-05-12',false,'Ivato aeroport');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Andriamalanto','Laria','2003-05-12',true,'b225 Itaosy');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Andriamanantena','Prisca','1985-03-03',true,'B202');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Mamisoa','Anjara','2000-05-25',false,'Ivandry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Andriamirindra','Rolland','2001-09-01',false,'Isotry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Nomenjanahary','Fifalina','2001-09-01',true,'Ankadimbahoka');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Hajanarivo','Marcello','2001-09-01',false,'Tanjombato');

insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rakotondraibe','Emilio','1999-03-21',false,'B642');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Solotafika','Muriel','2002-02-18',true,'itaost cite');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Sebastien','Orlando','1995-06-30',false,'C331');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rajaonarivony','Clark','1990-05-12',false,'Ivato aeroport');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rojotina','Lara','2003-05-12',true,'b225 Itaosy');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Mamitiana','Diamondra','1985-07-06',true,'B202');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rakotonirina','Angelo','2000-05-25',false,'Ivandry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Herinirina','Cedric','2001-09-01',false,'Isotry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Diamondraniaina','Koloina','2001-09-01',true,'Ankadimbahoka');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Falinarivo','Henintsoa','2001-09-01',false,'Tanjombato');

insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Ramanampamonjy','Joro','1999-03-21',false,'B642');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Kantoniaina','Mialy','2002-02-18',true,'itaost cite');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rivolalaina','Julio','1995-06-30',false,'C331');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Ramanandraibe','Quentin','1990-05-12',false,'Ivato aeroport');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Manantenasoa','Koloina','2003-05-12',true,'b225 Itaosy');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Lantonirina','Gianah','1985-04-23',true,'B202');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Manantsoa','Henry','2000-05-25',false,'Ivandry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Randrianantenaina','Tonio','2001-09-01',false,'Isotry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Andrialalaina','Francine','2001-09-01',true,'Ankadimbahoka');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Hajanirina','Rivo','2001-09-01',false,'Tanjombato');

insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Randrianarivony','Lalaina','1999-03-21',false,'B642');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Kantosoa','Celia','2002-02-18',true,'itaost cite');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Faniriniaina','Brandon','1995-06-30',false,'C331');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Rijaniaina','Francisco','1990-05-12',false,'Ivato aeroport');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Iaromalala','Gracia','2003-05-12',true,'b225 Itaosy');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Barijaona','Malanto','1985-10-08',true,'B202');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Lalanirina','Dereck','2000-05-25',false,'Ivandry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Andriamampionona','Gabriel','2001-09-01',false,'Isotry');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Fanjanirina','Maria','2001-09-01',true,'Ankadimbahoka');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Nantenainarivo','Larry','2001-09-01',false,'Tanjombato');

insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Ralantonirina','Olivia','1968-09-01',true,'Tanjombato');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Ralay','Emile','1983-07-06',false,'67Ha');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Andrianjatovo','Naina','1970-09-05',false,'Faravohitra');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Annah','Cecile','1990-10-07',true,'Andoharanofotsy');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Nantenaina','Rijatina','1976-12-25',false,'Ambondrona');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Andrialala','Mino','2001-09-01',true,'Imerintsiatosika');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Andriatina','Charlin','1987-08-24',false,'Ankazomanga');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Mamy','Honore','1979-04-15',false,'Manakambahiny');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Emiliano','Franco','1986-09-26',false,'Iavoloha');
insert into personnes (nom,prenom,naissance,sexe,adresse) values ('Marie','Natalia','1988-08-30',true,'Ambanidia');

MPIANATRA
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('1','1','Eleve','20222-03-02');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('2','1','Eleve','20222-03-02');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('3','1','Eleve','20222-03-02');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('4','1','Eleve','20222-03-02');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('5','1','Eleve','20222-03-02');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('6','1','Eleve','20222-03-02');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('7','1','Eleve','20222-03-02');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('8','1','Eleve','20222-03-02');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('9','1','Eleve','20222-03-02');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('10','1','Eleve','20222-03-02');

insert into eleves (idPersonne,idClasses,statut,cree_le) values ('11','2','Eleve','20222-03-03');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('12','2','Eleve','20222-03-03');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('13','2','Eleve','20222-03-03');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('14','2','Eleve','20222-03-03');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('15','2','Eleve','20222-03-03');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('16','2','Eleve','20222-03-03');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('17','2','Eleve','20222-03-03');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('18','2','Eleve','20222-03-03');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('19','2','Eleve','20222-03-03');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('20','2','Eleve','20222-03-03');

insert into eleves (idPersonne,idClasses,statut,cree_le) values ('21','3','Eleve','20222-03-04');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('22','3','Eleve','20222-03-04');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('23','3','Eleve','20222-03-04');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('24','3','Eleve','20222-03-04');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('25','3','Eleve','20222-03-04');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('26','3','Eleve','20222-03-04');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('27','3','Eleve','20222-03-04');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('28','3','Eleve','20222-03-04');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('29','3','Eleve','20222-03-04');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('30','3','Eleve','20222-03-04');
 
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('31','4','Eleve','20222-03-05');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('32','4','Eleve','20222-03-05');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('33','4','Eleve','20222-03-05');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('34','4','Eleve','20222-03-05');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('35','4','Eleve','20222-03-05');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('36','4','Eleve','20222-03-05');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('37','4','Eleve','20222-03-05');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('38','4','Eleve','20222-03-05');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('39','4','Eleve','20222-03-05');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('40','4','Eleve','20222-03-05');

insert into eleves (idPersonne,idClasses,statut,cree_le) values ('41','5','Eleve','20222-03-06');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('42','5','Eleve','20222-03-06');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('43','5','Eleve','20222-03-06');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('44','5','Eleve','20222-03-06');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('45','5','Eleve','20222-03-06');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('46','5','Eleve','20222-03-06');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('47','5','Eleve','20222-03-06');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('48','5','Eleve','20222-03-06');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('49','5','Eleve','20222-03-06');
insert into eleves (idPersonne,idClasses,statut,cree_le) values ('50','5','Eleve','20222-03-06');


PROFESSEURS
insert into professeurs (idPersonne,statut) values('51','Professeur');
insert into professeurs (idPersonne,statut) values('52','Professeur');
insert into professeurs (idPersonne,statut) values('53','Professeur');
insert into professeurs (idPersonne,statut) values('54','Professeur');
insert into professeurs (idPersonne,statut) values('55','Professeur');
insert into professeurs (idPersonne,statut) values('56','Professeur');
insert into professeurs (idPersonne,statut) values('57','Professeur');
insert into professeurs (idPersonne,statut) values('58','Professeur');
insert into professeurs (idPersonne,statut) values('59','Professeur');
insert into professeurs (idPersonne,statut) values('60','Professeur');

MATIERE
insert into matieres (nom,coefficient) values('Mathematiques','5');
insert into matieres (nom,coefficient) values('Physique','5');
insert into matieres (nom,coefficient) values('Science','4');
insert into matieres (nom,coefficient) values('Histo-Geo','4');
insert into matieres (nom,coefficient) values('Malagasy','4');
insert into matieres (nom,coefficient) values('Francais','3');
insert into matieres (nom,coefficient) values('Anglais','2');
insert into matieres (nom,coefficient) values('Phylosophie','2');

INSCRIPTIONMATIERE
insert into inscriptionMatieres values('1','1');

EXAMENS
insert into examens values('1','2022-03-07','2022-03-10');

HISTORIQUECLASSE
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('1','1','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('2','1','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('3','1','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('4','1','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('5','1','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('6','1','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('7','1','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('8','1','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('9','1','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('10','1','2022-03-14','2023-01-07');

insert into historiqueClasse (ideleve,idclasses,debut,fin) values('11','2','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('12','2','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('13','2','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('14','2','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('15','2','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('16','2','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('17','2','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('18','2','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('19','2','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('20','2','2022-03-14','2023-01-07');

insert into historiqueClasse (ideleve,idclasses,debut,fin) values('21','3','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('22','3','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('23','3','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('24','3','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('25','3','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('26','3','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('27','3','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('28','3','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('29','3','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('30','3','2022-03-14','2023-01-07');

insert into historiqueClasse (ideleve,idclasses,debut,fin) values('31','4','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('32','4','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('33','4','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('34','4','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('35','4','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('36','4','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('37','4','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('38','4','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('39','4','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('40','4','2022-03-14','2023-01-07');

insert into historiqueClasse (ideleve,idclasses,debut,fin) values('41','5','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('42','5','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('43','5','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('44','5','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('45','5','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('46','5','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('47','5','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('48','5','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('49','5','2022-03-14','2023-01-07');
insert into historiqueClasse (ideleve,idclasses,debut,fin) values('50','5','2022-03-14','2023-01-07');

CLASSEPROFESSEUR
insert into ClasseProf values(default,'1','1');

PAIMENTECOLAGE
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('1','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('2','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('3','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('4','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('5','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('6','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('7','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('8','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('9','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('10','250000','2022-03-14','2023-01-07');

insert into paimentecolage (ideleve,somme,dumois,paye_le) values('11','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('12','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('13','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('14','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('15','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('16','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('17','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('18','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('19','250000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('20','250000','2022-03-14','2023-01-07');

insert into paimentecolage (ideleve,somme,dumois,paye_le) values('21','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('22','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('23','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('24','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('25','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('26','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('27','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('28','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('29','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('30','450000','2022-03-14','2023-01-07');

insert into paimentecolage (ideleve,somme,dumois,paye_le) values('31','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('32','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('33','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('34','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('35','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('36','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('37','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('38','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('39','450000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('40','450000','2022-03-14','2023-01-07');

insert into paimentecolage (ideleve,somme,dumois,paye_le) values('41','500000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('42','500000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('43','500000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('44','500000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('45','500000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('46','500000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('47','500000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('48','500000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('49','500000','2022-03-14','2023-01-07');
insert into paimentecolage (ideleve,somme,dumois,paye_le) values('50','500000','2022-03-14','2023-01-07');

HISTORIQUEECOLAGE
insert into hsitoriqueecolage (idclasses,ecolage,anneedebut,anneefin) values('1','250000','2022-03-14','2023-01-07');
insert into hsitoriqueecolage (idclasses,ecolage,anneedebut,anneefin) values('2','250000','2022-03-14','2023-01-07');
insert into hsitoriqueecolage (idclasses,ecolage,anneedebut,anneefin) values('3','450000','2022-03-14','2023-01-07');
insert into hsitoriqueecolage (idclasses,ecolage,anneedebut,anneefin) values('4','450000','2022-03-14','2023-01-07');
insert into hsitoriqueecolage (idclasses,ecolage,anneedebut,anneefin) values('5','450000','2022-03-14','2023-01-07');
insert into hsitoriqueecolage (idclasses,ecolage,anneedebut,anneefin) values('6','500000','2022-03-14','2023-01-07');

NOTE
insert into note values(default,'','','','','');