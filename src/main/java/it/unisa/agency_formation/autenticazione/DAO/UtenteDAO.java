package it.unisa.agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.domain.Utente;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UtenteDAO {
    boolean salvaUtente(Utente user) throws SQLException;

    Utente login(String email, String pwd) throws SQLException;

    ArrayList<Utente> doRetrieveCandidatoConCandidatura() throws SQLException;

    ArrayList<Utente> recuperoCandidatiColloquio() throws SQLException;

    boolean checkEmail(String email)  throws SQLException;

}

