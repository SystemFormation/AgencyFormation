package it.unisa.agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;

import java.sql.SQLException;

public interface AutenticazioneManager {
    boolean registration(Utente user) throws SQLException;

    Utente login(String email, String password) throws SQLException;

    Utente getAllData(int idUser) throws SQLException;

    Dipendente getAllDataDip(int idUser) throws SQLException; //aggiunto questo
}