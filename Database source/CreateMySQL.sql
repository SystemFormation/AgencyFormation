DROP DATABASE IF EXISTS af_db;
CREATE DATABASE af_db;
use af_db;

create table Utenti(
IdUtente int primary key not null auto_increment, 
Nome varchar(32) not null, 
Cognome varchar(32) not null,
Pwd varchar(16) not null,
Mail varchar(32) not null,
Ruolo int not null
);

use af_db;

create table Team(
IdTeam int primary key not null, 
NomeProgetto varchar(32) not null, 
NumeroDipendenti int not null,
NomeTeam varchar(32) not null,
Descrizione varchar(128) not null,
Competenza varchar(512) null,
IdUtente int not null,
foreign key(IdUtente) references Utenti(IdUtente)
on update cascade
on delete cascade
);

use af_db;

create table Dipendenti(
IdUtente int primary key not null, 
Residenza varchar(128) null, 
Telefono varchar(20) null,
Stato boolean not null,
AnnoDiNascita int not null,
IdTeam int null,
foreign key(IdTeam) references Team(IdTeam)
on update cascade
on delete cascade,
foreign key(IdUtente) references Utenti(IdUtente)
on update cascade
on delete cascade

);

use af_db;

create table Skill(
IdSkill int primary key not null, 
NomeSkill varchar(64) not null, 
DescrizioneSkill varchar(512) not null
);

use af_db;

create table SkillsDipendenti(
IdUtente int not null, 
IdSkill int not null, 
Livello int not null,
primary key(IdUtente,IdSkill),
foreign key(IdUtente) references dipendenti(IdUtente)
on update cascade
on delete cascade,
foreign key(IdSkill) references Skill(IdSkill)
on update cascade
on delete cascade
);

use af_db;

create table Documenti(
IdDocumento int primary key not null, 
MaterialeDiFormazione varchar(32) not null, 
IdUtente int not null,
IdTeam int not null,
foreign key(IdUtente) references dipendenti(IdUtente)
on update cascade
on delete cascade,
foreign key(IdTeam) references team(IdTeam)
on update cascade
on delete cascade
);

use af_db;

create table Candidature(
IdCandidatura int primary key not null, 
Cv varchar(32) not null, 
Attestati varchar(32) null,
Certificazioni varchar(32) null,
Stato varchar(32) not null,
DataCandidatura datetime not null,
DataOraColloquio datetime null,
IdCandidato int not null,
IdHR int null,
foreign key(IdCandidato) references utenti(IdUtente)
on update cascade
on delete cascade,
foreign key(IdHR) references utenti(IdUtente)
on update cascade
on delete cascade
);