package it.unisa.agency_formation.team.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.team.domain.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeamManager {
    boolean createTeam(String name, String projectName, String description, int maxEmpl, String competence, int idUtente) throws SQLException;
    ArrayList<Dipendente> getEmployee(boolean state) throws SQLException;
    void addEmployee(int idTeam, Dipendente dip) throws SQLException;
    void removeEmployee(int idTeam, int idDip) throws SQLException;
    ArrayList<Team> viewTeams(int idUtente) throws SQLException;
    ArrayList<Team> viewAllTeams() throws SQLException;
    void disbandTeam(int idTeam) throws SQLException;
}
