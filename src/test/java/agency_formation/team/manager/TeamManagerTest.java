package agency_formation.team.manager;


import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.DAO.TeamDAOImpl;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Questa classe testa i metodi della classe TeamManagerImpl

public class TeamManagerTest {
    private TeamManagerImpl teamManager = new TeamManagerImpl();
    private TeamDAO daoTeam = mock(TeamDAOImpl.class);

    public void init(){
        TeamManagerImpl.daoTeam=daoTeam;
    }


    @Test
    public void createTeamPass() throws SQLException {
        int id = 3;
        Team team = new Team("Agency Formation", 4, "The system errors", "i membri sono dei geni", null, 3);
        when(daoTeam.salvaTeam(team, id)).thenReturn(true);
        init();
        assertTrue(teamManager.creaTeam(team, id));
    }

    @Test
    public void createTeamFail() throws SQLException {
        int id = -3;
        Team team = null;
        when(daoTeam.salvaTeam(team, id)).thenReturn(false);
        init();
        assertFalse(teamManager.creaTeam(team, id));
    }

    @Test
    public void rimuoviDipendetePass() throws SQLException {
        int idDip = 1;
        when(daoTeam.rimuoviDipendente(idDip)).thenReturn(true);
        init();
        assertTrue(teamManager.rimuoviDipendente(idDip));
    }

    @Test
    public void rimuoviDipendenteFail() throws SQLException {
        int idDip = -1;
        when(daoTeam.rimuoviDipendente(idDip)).thenReturn(false);
        init();
        assertFalse(teamManager.rimuoviDipendente(idDip));
    }

    @Test
    public void visualizzaTeamsPass() throws SQLException {
        ArrayList<Team> teams = new ArrayList<Team>();
        Team team = new Team("Agency Formation", 4, "The system errors", "i membri sono dei geni", null, 3);
        Team team2 = new Team("System Formation", 3, "Pinguini tattici nucleari", "i membri sono dei bravi ragazzi", null, 2);
        teams.add(team);
        teams.add(team2);
        int idUtente = 1;
        when(daoTeam.recuperaTeamDiUnTM(idUtente)).thenReturn(teams);
        init();
        assertNotNull(teamManager.visualizzaTeams(idUtente));
    }


    @Test
    public void visualizzaTeamsFail() throws SQLException {
        ArrayList<Team> teams = null;
        int idUtente = -1;
        when(daoTeam.recuperaTeamDiUnTM(idUtente)).thenReturn(teams);
        init();
        assertNull(teamManager.visualizzaTeams(idUtente));
    }

    @Test
    public void visualizzaTuttiTeamsPass() throws SQLException {
        ArrayList<Team> teams = new ArrayList<Team>();
        Team team = new Team("Agency Formation", 4, "The system errors", "i membri sono dei geni", null, 3);
        Team team2 = new Team("System Formation", 3, "Pinguini tattici nucleari", "i membri sono dei bravi ragazzi", null, 2);
        teams.add(team);
        teams.add(team2);
        when(daoTeam.recuperaTuttiTeam()).thenReturn(teams);
        init();
        assertNotNull(teamManager.visualizzaTuttiTeams());
    }

    @Test
    public void visualizzaTuttiTeamsFail() throws SQLException {
        ArrayList<Team> teams = null;
        when(daoTeam.recuperaTuttiTeam()).thenReturn(teams);
        init();
        assertNull(teamManager.visualizzaTuttiTeams());
    }

    @Test
    public void viewLastIdTeamPass() throws SQLException {
        when(daoTeam.recuperaIdUltimoTeamCreato()).thenReturn(1);
        init();
        assertTrue(teamManager.viewLastIdTeam() > 0);
    }

    @Test
    public void viewLastIdTeamPassFail() throws SQLException {
        when(daoTeam.recuperaIdUltimoTeamCreato()).thenReturn(-1);
        init();
        assertTrue(teamManager.viewLastIdTeam() < 0);
    }

    @Test
    public void recuperaIdDipendenteDelTeamPass() throws SQLException {
        ArrayList<Integer> dipendente = null;
        int idTeam = 1;
        when(daoTeam.recuperaIdTeamMemberFromTeam(idTeam)).thenReturn(dipendente);
        init();
        assertNull(teamManager.recuperaIdDipendentiDelTeam(idTeam));
    }

    @Test
    public void recuperaIdDipendenteDelTeamFail() throws SQLException {
        ArrayList<Integer> dipendente = null;
        int idTeam = -1;
        when(daoTeam.recuperaIdTeamMemberFromTeam(idTeam)).thenReturn(dipendente);
        init();
        assertNull(teamManager.recuperaIdDipendentiDelTeam(idTeam));
    }

    @Test
    public void updateDipsDissoPass() throws SQLException {
        int idDip = 2;
        when(daoTeam.updateDipStateDissolution(idDip)).thenReturn(true);
        init();
        assertTrue(teamManager.updateDipsDisso(idDip));
    }

    @Test
    public void updateDipsDissoFail() throws SQLException {
        int idDip = 1;
        when(daoTeam.updateDipStateDissolution(idDip)).thenReturn(false);
        init();
        assertFalse(teamManager.updateDipsDisso(idDip));
    }

    @Test
    public void sciogliTeamPass() throws SQLException {
        int idTeam = 1;
        when(daoTeam.rimuoviTeam(idTeam)).thenReturn(true);
        init();
        assertTrue(teamManager.sciogliTeam(idTeam));
    }

    @Test
    public void sciogliTeamFail() throws SQLException {
        int idTeam = -1;
        when(daoTeam.rimuoviTeam(idTeam)).thenReturn(false);
        init();
        assertFalse(teamManager.sciogliTeam(idTeam));
    }

    @Test
    public void recuperaDipendentiDelTeamPass() throws SQLException {
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        when(daoTeam.recuperaDipendentiDelTeam()).thenReturn(dipendenti);
        init();
        assertNotNull(teamManager.recuperaDipendentiDelTeam());
    }

    @Test
     public void recuperaDipendentiDelTeamFail() throws SQLException{
         ArrayList<Dipendente> dipendenti = null;
        when(daoTeam.recuperaDipendentiDelTeam()).thenReturn(dipendenti);
        init();
        assertNull(teamManager.recuperaDipendentiDelTeam());
     }
    @Test
    public void getTeamByIdFail1() throws SQLException {
        Team team = null;
        int idTeam = 1;
        when(daoTeam.recuperaTeamById(idTeam)).thenReturn(team);
        init();
        assertNull(teamManager.getTeamById(idTeam));
    }

    @Test
    public void getTeamByIdFail2() throws SQLException {
        Team team = null;
        int idTeam = -1;
        when(daoTeam.recuperaTeamById(idTeam)).thenReturn(team);
        init();
        assertNull(teamManager.getTeamById(idTeam));
    }
    @Test
    public void getTeamByIdPass() throws SQLException {
        Team team = new Team();
        int idTeam = 1;
        when(daoTeam.recuperaTeamById(idTeam)).thenReturn(team);
        init();
        assertNotNull(teamManager.getTeamById(idTeam));
    }

    @Test // dovrebbe tornare true ma ritorna false, se ritorna false funziona!
    public void modificaLeCompetenzePass() throws SQLException {
        String competence = "HTML";
        int idTeam = 2;
        when(daoTeam.specificaCompetenze(competence, idTeam)).thenReturn(true);
        init();
        assertTrue(teamManager.specificaLeCompetenze(competence, idTeam));
    }

    @Test
    public void specificaLeCompetenzeFail() throws SQLException {
        String compentence = "HTML";
        int idTeam = -1;
        when(daoTeam.specificaCompetenze(compentence, idTeam)).thenReturn(false);
        init();
        assertFalse(teamManager.specificaLeCompetenze(compentence, idTeam));
    }

}



