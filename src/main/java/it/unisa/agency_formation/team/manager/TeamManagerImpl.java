package it.unisa.agency_formation.team.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeamManagerImpl implements TeamManager {
    private TeamDAO tdao;
    private DipendenteDAO dipdao;

    public TeamManagerImpl(TeamDAO tdao) {
        this.tdao = tdao;
    }

    public TeamManagerImpl(DipendenteDAO dipdao) {
        this.dipdao = dipdao;
    }

    @Override
    public boolean createTeam(Team team, int idUtente) throws SQLException {
        return tdao.doSaveTeam(team, idUtente);
    }

    @Override
    public ArrayList<Dipendente> getEmployee(boolean state) throws SQLException {
        return dipdao.doRetrieveByState(state);
    }

    @Override
    public boolean addEmployee(int idTeam, Dipendente dip) throws SQLException {
        return tdao.addEmployee(idTeam, dip.getIdDipendente());
    }

    @Override
    public boolean removeEmployee(int idTeam, int idDip) throws SQLException {
        return tdao.removeEmployee(idTeam, idDip);
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
    public boolean disbandTeam(int idTeam) throws SQLException {
        return tdao.doRemoveTeam(idTeam);
    }

    @Override
    public int viewLastIdTeam() throws SQLException {
        return tdao.doRetrieveLastIDTeam();
    }

    @Override
    public boolean updateDipOnTeam(int idDip, int idTeam) throws SQLException {
            return dipdao.updateDipTeamAndState(idDip, idTeam);
    }

}
