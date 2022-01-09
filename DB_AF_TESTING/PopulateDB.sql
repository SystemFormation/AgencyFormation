use af_db;

/*-------------------  DA POPOLARE INSIEME  -------------------

INSERT INTO utenti ( `Nome`, `Cognome`, `Pwd`, `Mail`, `Ruolo`) VALUES ('Luigi', 'Giacchetti', 'lol', 'l.giacchetti@studenti.unisa.it', '1');	/*Utente*/
INSERT INTO utenti ( `Nome`, `Cognome`, `Pwd`, `Mail`, `Ruolo`) VALUES ('Pasquale', 'Severino', 'lol', 'p.severino@studenti.unisa.it', '2');	/*Dipendente*/
INSERT INTO utenti ( `Nome`, `Cognome`, `Pwd`, `Mail`, `Ruolo`) VALUES ('Manuel', 'Nocerino', 'lol', 'm.nocerino@studenti.unisa.it', '3');		/*TM*/
INSERT INTO utenti ( `Nome`, `Cognome`, `Pwd`, `Mail`, `Ruolo`) VALUES ('Domenico', 'Pagliuca', 'lol', 'd.pagliuca@studenti.unisa.it', '4'); 	/*HR*/
INSERT INTO skill (`NomeSkill`, `DescrizioneSkill`) VALUES ( 'HTML', 'Conoscenze generali di HTML');
INSERT INTO skill (`NomeSkill`, `DescrizioneSkill`) VALUES ('CSS', 'Conoscenze basilari di CSS');
INSERT INTO team (`NomeProgetto`, `NumeroDipendenti`, `NomeTeam`, `Descrizione`, `Competenza`, `IdTM`) VALUES ('Fitdiary', '8', 'Bastoncini Fitnuss', 'Vendiamo bastoncini di pesce', 'HTML', 3);
INSERT INTO dipendenti (`IdDipendente`,`Residenza`, `Telefono`, `Stato`, `AnnoDiNascita`, `IdTeam`) VALUES (2,'Fisciano', '118', '0', '2000', '1');
INSERT INTO skillsdipendenti (`IdDipendente`, `IdSkill`, `Livello`) VALUES ('2', '1', '5');
INSERT INTO skillsdipendenti (`IdDipendente`, `IdSkill`, `Livello`) VALUES ('2', '2', '3');
INSERT INTO documenti (`MaterialeDiFormazione`, `IdHR`, `IdTeam`) VALUES ('\\', '4', '1');
*/