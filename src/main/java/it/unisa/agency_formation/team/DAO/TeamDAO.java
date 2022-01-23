package it.unisa.agency_formation.team.DAO;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.team.domain.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeamDAO {
    boolean salvaTeam(Team team, int idUtente) throws SQLException;
    boolean rimuoviTeam(int idTeam) throws SQLException;
    Team recuperaTeamById(int idTeam) throws SQLException;
    boolean rimuoviDipendente(int idDipendente) throws SQLException;
    ArrayList<Team> recuperaTuttiTeam() throws SQLException;
    ArrayList<Team> recuperaTeamDiUnTM(int idUtente) throws SQLException;
    boolean specificaCompetenze(String competence, int idTeam) throws SQLException;
    int recuperaIdUltimoTeamCreato() throws SQLException;
    ArrayList<Integer> recuperaIdTeamMemberFromTeam(int idTeam) throws SQLException;
    boolean updateDipStateDissolution(int idDip) throws SQLException;
    ArrayList<Dipendente> recuperaDipendentiDelTeam() throws SQLException;
}
