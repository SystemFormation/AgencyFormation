use af_db;

INSERT INTO utenti ( `Nome`, `Cognome`, `Pwd`, `Mail`, `Ruolo`) VALUES ('Luigi', 'Giacchetti', 'lol', 'l.giacchetti@studenti.unisa.it', '1');	/*Utente*/
INSERT INTO utenti ( `Nome`, `Cognome`, `Pwd`, `Mail`, `Ruolo`) VALUES ('Pasquale', 'Severino', 'lol', 'p.severino@studenti.unisa.it', '2');	/*Dipendente*/
INSERT INTO utenti ( `Nome`, `Cognome`, `Pwd`, `Mail`, `Ruolo`) VALUES ('Manuel', 'Nocerino', 'lol', 'm.nocerino@studenti.unisa.it', '3');		/*TM*/
INSERT INTO utenti ( `Nome`, `Cognome`, `Pwd`, `Mail`, `Ruolo`) VALUES ('Domenico', 'Pagliuca', 'lol', 'd.pagliuca@studenti.unisa.it', '4'); 	/*HR*/
INSERT INTO skill (`IdSkill`, `NomeSkill`, `DescrizioneSkill`) VALUES ('1', 'HTML', 'Conoscenze generali di HTML');
INSERT INTO skill (`IdSkill`, `NomeSkill`, `DescrizioneSkill`) VALUES ('2', 'CSS', 'Conoscenze basilari di CSS');
INSERT INTO team (`IdTeam`, `NomeProgetto`, `NumeroDipendenti`, `NomeTeam`, `Descrizione`, `Competenza`, `IdUtente`) VALUES ('1', 'Fitdiary', '8', 'Bastoncini Fitnuss', 'Vendiamo bastoncini di pesce', 'HTML', '2');
INSERT INTO dipendenti (`IdUtente`, `Residenza`, `Telefono`, `Stato`, `AnnoDiNascita`, `IdTeam`) VALUES ('2', 'Fisciano', '118', '0', '2000', '1');
INSERT INTO skillsdipendenti (`IdUtente`, `IdSkill`, `Livello`) VALUES ('2', '1', '5');
INSERT INTO skillsdipendenti (`IdUtente`, `IdSkill`, `Livello`) VALUES ('2', '2', '3');
INSERT INTO documenti (`IdDocumento`, `MaterialeDiFormazione`, `IdUtente`, `IdTeam`) VALUES ('1', '\\', '2', '1');
INSERT INTO candidature (`IdCandidatura`, `Cv`, `Attestati`, `Certificazioni`, `Stato`, `DataCandidatura`, `DataOraColloquio`, `IdCandidato`, `IdHR`) VALUES ('1', '\\', '\\', '\\', 'Non visionato', '2021-12-21 21:48:30', '2021-12-31 17:30:00', '1', '4');