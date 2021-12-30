package it.unisa.agency_formation.team.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeamManagerImpl implements TeamManager {
    private TeamDAO tdao;
    private DipendenteDAO dipdao;

    @Override
    public boolean createTeam(String name, String projectName, String description, int maxEmpl, String competence, int idTM) throws SQLException {
        return tdao.doSaveTeam(new Team(projectName, maxEmpl, name, description, competence, idTM));
    }

    @Override
    public ArrayList<Dipendente> getEmployee(boolean state) throws SQLException {
        return dipdao.doRetrieveByState(state);
    }

    @Override
    public void addEmployee(int idTeam, Dipendente dip) throws SQLException {
        tdao.addEmployee(idTeam, dip.getIdDipendente());
    }

    @Override
    public void removeEmployee(int idTeam, int idDip) throws SQLException {
        tdao.removeEmployee(idTeam, idDip);
    }

    @Override
    public ArrayList<Team> viewTeams(int idUtente) throws SQLException {
        return tdao.doRetrieveTMTeam(idUtente);
    }

    @Override
    public ArrayList<Team> viewAllTeams() throws SQLException {
        return tdao.doRetrieveAllTeam();
    }

    @Override
    public void disbandTeam(int idTeam) throws SQLException {
        tdao.doRemoveTeam(idTeam);
    }
}
