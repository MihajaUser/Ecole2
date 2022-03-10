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