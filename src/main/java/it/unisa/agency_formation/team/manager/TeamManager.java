package it.unisa.agency_formation.team.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.team.domain.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeamManager {
    boolean createTeam(Team team, int idUser) throws SQLException;

    ArrayList<Dipendente> getEmployee(boolean state) throws SQLException;

    boolean addEmployee(int idTeam, Dipendente dip) throws SQLException;

    boolean removeEmployee(int idTeam, int idDip) throws SQLException;

    ArrayList<Team> viewTeams(int idUtente) throws SQLException;

    ArrayList<Team> viewAllTeams() throws SQLException;

    boolean disbandTeam(int idTeam) throws SQLException;
    int viewLastIdTeam() throws SQLException;
    public boolean updateDipOnTeam(int idDip, int idTeam) throws SQLException;
}
