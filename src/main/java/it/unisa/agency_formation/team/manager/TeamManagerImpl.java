package it.unisa.agency_formation.team.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeamManagerImpl implements TeamManager {

    @Override
    public boolean createTeam(Team team, int idUtente) throws SQLException {
        boolean result = TeamDAO.doSaveTeam(team, idUtente);
        if(result){
            return result;
        }
        else{
            return false;
        }
    }

    @Override
    public ArrayList<Dipendente> getEmployee(StatiDipendenti state) throws SQLException {
        ArrayList<Dipendente> result = DipendenteDAO.doRetrieveByState(state);
        if(result != null){
            return result;
        }else{
            return null;
        }
    }

    @Override
    public boolean addEmployee(int idTeam, Dipendente dip) throws SQLException {
        boolean result = TeamDAO.addEmployee(idTeam, dip.getIdDipendente());
        if(result){
            return result;
        }else{
            return false;
        }
    }

    @Override
    public boolean removeEmployee(int idTeam, int idDip) throws SQLException {
        boolean result = TeamDAO.removeEmployee(idTeam, idDip);
        if(result){
            return result;
        }
        else{
            return false;
        }
    }

    @Override
    public ArrayList<Team> viewTeams(int idUtente) throws SQLException {
        ArrayList<Team> result = TeamDAO.doRetrieveTMTeam(idUtente);
        if(result != null){
            return result;
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Team> viewAllTeams() throws SQLException {
        ArrayList<Team> result = TeamDAO.doRetrieveAllTeam();
        if(result != null){
            return result;
        }else{
            return null;
        }
    }

    @Override
    public boolean disbandTeam(int idTeam) throws SQLException {
        boolean result = TeamDAO.doRemoveTeam(idTeam);
        if(result){
            return result;
        }
        else{
            return false;
        }
    }

    @Override
    public int viewLastIdTeam() throws SQLException {
        int result = TeamDAO.doRetrieveLastIDTeam();
        if(result == 0){
            return result;
        }else{
            return 0;
        }
    }

    @Override
    public boolean updateDipOnTeam(int idDip, int idTeam) throws SQLException {
        boolean result = DipendenteDAO.updateDipTeamAndState(idDip, idTeam);
        if(result){
            return result;
        }
        else{
            return false;
        }
    }

    @Override
    public ArrayList<Integer> retriveDips(int idTeam) throws SQLException {
        ArrayList<Integer> listaIdDips = TeamDAO.doRetrieveIdEmployees(idTeam);
        if(listaIdDips != null){
            return listaIdDips;
        }else{
            return null;
        }
    }

    @Override
    public boolean updateDipsDisso(int idDip) throws SQLException {
        boolean result = TeamDAO.updateDipStateDissolution(idDip);
        if(result){
            return result;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean deleteTeam(int idTeam) throws SQLException {
        boolean result = TeamDAO.doRemoveTeam(idTeam);
        if (result) {
            return result;
        } else {
            return false;
        }
    }

}
