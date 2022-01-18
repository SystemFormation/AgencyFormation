package it.unisa.agency_formation.team.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.team.domain.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeamManager {
    boolean creaTeam(Team team, int idUser) throws SQLException;

   // ArrayList<Dipendente> getDipendenti(StatiDipendenti state) throws SQLException;

   // boolean addDipendente(int idTeam, Dipendente dip) throws SQLException;

    boolean rimuoviDipendente(int idDip) throws SQLException;

    ArrayList<Team> visualizzaTeams(int idUtente) throws SQLException;

    ArrayList<Team> visualizzaTuttiTeams() throws SQLException;

    //boolean sciogliTeam(int idTeam) throws SQLException;

    int viewLastIdTeam() throws SQLException;

    ArrayList<Integer> recuperaIdDipendentiDelTeam(int idTeam) throws SQLException;

    boolean updateDipsDisso(int idDip) throws SQLException;

    boolean sciogliTeam(int idTeam) throws SQLException;

    ArrayList<Dipendente> recuperaDipendentiDelTeam() throws SQLException;

    Team getTeamById(int idTeam) throws SQLException;

    boolean modificaLeCompetenze(String competence, int idTeam) throws SQLException;
}
