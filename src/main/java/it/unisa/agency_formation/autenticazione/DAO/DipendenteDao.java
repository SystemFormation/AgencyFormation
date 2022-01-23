package it.unisa.agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DipendenteDao {
    boolean salvaDipendente(Dipendente dipendente) throws SQLException;

    boolean modificaRuoloUtente(int id) throws SQLException;

    Dipendente recuperoDipendenteById(int id) throws SQLException;

    ArrayList<Dipendente> recuperaDipendenti() throws SQLException;

    boolean setIdTeamDipendente(int idDip, int idTeam) throws SQLException;
}
