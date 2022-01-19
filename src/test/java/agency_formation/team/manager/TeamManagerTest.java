package agency_formation.team.manager;


import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

//Questa classe testa i metodi della classe TeamManagerImpl

public class TeamManagerTest {
    TeamManagerImpl aut = new TeamManagerImpl();

    @Test
    public void createTeamPass() throws SQLException {
        int id = 3;
        Team team = new Team("Agency Formation", 4, "The system errors", "i membri sono dei geni", null, 3);
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.salvaTeam(team, id)).thenReturn(true);
            assertTrue(aut.creaTeam(team, id));

        }
    }

    @Test
    public void createTeamFail() throws SQLException {
        int id = -3;
        Team team = null;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.salvaTeam(team, id)).thenReturn(false);
            assertFalse(aut.creaTeam(team, id));

        }

    }

    @Test
    public void rimuoviDipendetePass() throws SQLException {
        int idDip = 1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.rimuoviDipendente(idDip)).thenReturn(true);
            assertTrue(aut.rimuoviDipendente(idDip));

        }

    }

    @Test
    public void rimuoviDipendenteFail() throws SQLException {
        int idDip = -1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.rimuoviDipendente(idDip)).thenReturn(false);
            assertFalse(aut.rimuoviDipendente(idDip));

        }
    }

    @Test
    public void visualizzaTeamsPass() throws SQLException {
        ArrayList<Team> teams = new ArrayList<Team>();
        Team team = new Team("Agency Formation", 4, "The system errors", "i membri sono dei geni", null, 3);
        Team team2 = new Team("System Formation", 3, "Pinguini tattici nucleari", "i membri sono dei bravi ragazzi", null, 2);
        teams.add(team);
        teams.add(team2);
        int idUtente = 1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaTeamDiUnTM(idUtente)).thenReturn(teams);
            assertNotNull(aut.visualizzaTeams(idUtente));

        }
    }


    @Test
    public void visualizzaTeamsFail() throws SQLException {
        ArrayList<Team> teams = null;
        int idUtente = -1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaTeamDiUnTM(idUtente)).thenReturn(teams);
            assertNull(aut.visualizzaTeams(idUtente));

        }
    }

    @Test
    public void visualizzaTuttiTeamsPass() throws SQLException {
        ArrayList<Team> teams = new ArrayList<Team>();
        Team team = new Team("Agency Formation", 4, "The system errors", "i membri sono dei geni", null, 3);
        Team team2 = new Team("System Formation", 3, "Pinguini tattici nucleari", "i membri sono dei bravi ragazzi", null, 2);
        teams.add(team);
        teams.add(team2);
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaTuttiTeam()).thenReturn(teams);
            assertNotNull(aut.visualizzaTuttiTeams());

        }
    }

    @Test
    public void visualizzaTuttiTeamsFail() throws SQLException {
        ArrayList<Team> teams = null;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaTuttiTeam()).thenReturn(teams);
            assertNull(aut.visualizzaTuttiTeams());

        }
    }

    @Test
    public void viewLastIdTeamPass() throws SQLException {
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaIdUltimoTeamCreato()).thenReturn(1);
            assertTrue(aut.viewLastIdTeam() > 0);

        }
    }

    @Test
    public void viewLastIdTeamPassFail() throws SQLException {
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaIdUltimoTeamCreato()).thenReturn(-1);
            assertTrue(aut.viewLastIdTeam() < 0);

        }
    }

    @Test
    public void recuperaIdDipendenteDelTeamPass() throws SQLException {
        ArrayList<Integer> dipendente = null;
        int idTeam = 1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaIdTeamMemberFromTeam(idTeam)).thenReturn(dipendente);
                    assertNull(aut.recuperaIdDipendentiDelTeam(idTeam));

        }

    }

    @Test
    public void recuperaIdDipendenteDelTeamFail() throws SQLException {
        ArrayList<Integer> dipendente = null;
        int idTeam = -1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaIdTeamMemberFromTeam(idTeam)).thenReturn(dipendente);
            assertNull(aut.recuperaIdDipendentiDelTeam(idTeam));

        }
    }

    @Test
    public void updateDipsDissoPass() throws SQLException {
        int idDip = 2;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.updateDipStateDissolution(idDip)).thenReturn(true);
            assertTrue(aut.updateDipsDisso(idDip));
        }


    }

    @Test
    public void updateDipsDissoFail() throws SQLException {
        int idDip = 1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.updateDipStateDissolution(idDip)).thenReturn(false);
            assertFalse(aut.updateDipsDisso(idDip));

        }

    }

    @Test
    public void sciogliTeamPass() throws SQLException {
        int idTeam = 1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.rimuoviTeam(idTeam)).thenReturn(true);
            assertTrue(aut.sciogliTeam(idTeam));

        }
    }

    @Test
    public void sciogliTeamFail() throws SQLException {
        int idTeam = -1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.rimuoviTeam(idTeam)).thenReturn(false);
            assertFalse(aut.sciogliTeam(idTeam));

        }
    }

    @Test
    public void recuperaDipendentiDelTeamPass() throws SQLException {
        ArrayList<Dipendente> dipendenti = new ArrayList<>();

        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaDipendentiDelTeam()).thenReturn(dipendenti);
            assertNotNull(aut.recuperaDipendentiDelTeam());

        }

    }

    @Test
     public void recuperaDipendentiDelTeamFail() throws SQLException{
         ArrayList<Dipendente> dipendenti = null;
         try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
             mocked.when(() -> TeamDAO.recuperaDipendentiDelTeam()).thenReturn(dipendenti);
                      assertNull(aut.recuperaDipendentiDelTeam());

         }

     }
    @Test
    public void getTeamByIdFail1() throws SQLException {
        Team team = null;
        int idTeam = 1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaTeamById(idTeam)).thenReturn(team);
            assertNull(aut.getTeamById(idTeam));

        }
    }

    @Test
    public void getTeamByIdFail2() throws SQLException {
        Team team = null;
        int idTeam = -1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaTeamById(idTeam)).thenReturn(team);
            assertNull(aut.getTeamById(idTeam));

        }
    }
    @Test
    public void getTeamByIdPass() throws SQLException {
        Team team = new Team();
        int idTeam = 1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.recuperaTeamById(idTeam)).thenReturn(team);
            assertNotNull(aut.getTeamById(idTeam));

        }
    }

    @Test // dovrebbe tornare true ma ritorna false, se ritorna false funziona!
    public void modificaLeCompetenzePass() throws SQLException {
        String competence = "HTML";
        int idTeam = 2;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.specificaCompetenze(competence, idTeam)).thenReturn(true);
                    assertTrue(aut.specificaLeCompetenze(competence, idTeam));

        }
    }

    @Test
    public void specificaLeCompetenzeFail() throws SQLException {
        String compentence = "HTML";
        int idTeam = -1;
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.specificaCompetenze(compentence, idTeam)).thenReturn(false);
            assertFalse(aut.specificaLeCompetenze(compentence, idTeam));
        }
    }

}



