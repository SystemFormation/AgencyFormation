package it.unisa.agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Utente;

import java.sql.SQLException;

public interface AutenticazioneManager {
    void registration(Utente user) throws SQLException;
    Utente login(String email, String password) throws SQLException;
    Utente getAllData(int idUser) throws SQLException;
}