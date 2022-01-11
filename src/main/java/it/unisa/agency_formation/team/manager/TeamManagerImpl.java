package it.unisa.agency_formation.team.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeamManagerImpl implements TeamManager {

    @Override
    public boolean creaTeam(Team team, int idUtente) throws SQLException {
       return TeamDAO.salvaTeam(team, idUtente);
    }

    @Override
    public ArrayList<Dipendente> getDipendenti(StatiDipendenti state) throws SQLException {
        return DipendenteDAO.recuperaByStato(state);
    }

  /*  @Override
    public boolean addDipendente(int idTeam, Dipendente dip) throws SQLException {
       return TeamDAO.aggiungiDipendente(idTeam, dip.getIdDipendente());
    } Non viene utilizzato */

    @Override
    public boolean rimuoviDipendente(int idDip) throws SQLException {
        return TeamDAO.rimuoviDipendente(idDip);
    }

    @Override
    public ArrayList<Team> visualizzaTeams(int idUtente) throws SQLException {
        return TeamDAO.recuperaTeamDiUnTM(idUtente);
    }

    @Override
    public ArrayList<Team> visualizzaTuttiTeams() throws SQLException {
        return TeamDAO.recuperaTuttiTeam();
    }
/*
    @Override
    public boolean sciogliTeam(int idTeam) throws SQLException {
        return TeamDAO.rimuoviTeam(idTeam);
    }
*/
    @Override
    public int viewLastIdTeam() throws SQLException {
        return TeamDAO.recuperaIdUltimoTeamCreato();
    }

    @Override
    public boolean updateDipOnTeam(int idDip, int idTeam) throws SQLException {
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
       return autenticazioneManager.setTeamDipendente(idDip, idTeam);
    }

    @Override
    public ArrayList<Integer> recuperaIdDipendentiDelTeam(int idTeam) throws SQLException {
        return TeamDAO.recuperaIdTeamMemberFromTeam(idTeam);
    }

    @Override
    public boolean updateDipsDisso(int idDip) throws SQLException {
       return TeamDAO.updateDipStateDissolution(idDip);

    }

    @Override
    public boolean sciogliTeam(int idTeam) throws SQLException {
        return TeamDAO.rimuoviTeam(idTeam);

    }

    @Override
    public ArrayList<Dipendente> recuperaDipendentiDelTeam() throws SQLException {
        return TeamDAO.recuperaDipendentiDelTeam();

    }

    @Override
    public Team getTeamById(int idTeam) throws SQLException {
        return TeamDAO.recuperaTeamById(idTeam);
    }
}
