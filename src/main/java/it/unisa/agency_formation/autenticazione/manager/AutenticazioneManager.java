package it.unisa.agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AutenticazioneManager {
    boolean registrazione(Utente user) throws SQLException;
    Utente login(String email, String password) throws SQLException;
    Dipendente getDipendente(int idUser) throws SQLException;
    ArrayList<Utente> getCandidatiConCandidatura() throws SQLException;
    ArrayList<Dipendente> getTuttiDipendenti() throws SQLException;
    boolean assumiCandidato(Dipendente dipendente) throws SQLException;
    ArrayList<Utente> getCandidatiColloquio() throws SQLException;
    boolean setTeamDipendente(int dip, int idTeam) throws SQLException;
}
