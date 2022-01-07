package agency_formation.team.DAO;

import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TeamDAOTest {
    TeamDAO team= new TeamDAO();

    @Test // Non avviene la creazione del team perche il team è null
    public void saveTeamFail() throws SQLException {
        Team team = null;
        int idUtente = -1;
        assertFalse(TeamDAO.salvaTeam(team, idUtente));
    }
    @Test
    public void saveTeamOk() throws SQLException {
        Team team = new Team("FitDiary", 8, "Bastoncini Fitnuss","Vendiamo Bastoncini Di Pesce","HTML", 2);
        int idUtente = 1;
        assertTrue(TeamDAO.salvaTeam(team, idUtente));
    }
    @Test
    public void removeTeamFail() throws SQLException {
        int idTeam = -1;
        assertFalse(TeamDAO.rimuoviTeam(idTeam));

    }
    @Test
    public void removeTeamOk() throws SQLException {
        int idTeam = 1;
        assertTrue(TeamDAO.rimuoviTeam(idTeam));
    }
    @Test
    public void addEmployeeFail() throws SQLException{
        int idTeam = -1;
        int idDipendente = -1;
        assertFalse(TeamDAO.aggiungiDipendente(idTeam, idDipendente));

    }
    @Test
    public void addEmployeeOk() throws SQLException{
        int idTeam = 1;
        int idDipendente = 1;
        assertTrue(TeamDAO.aggiungiDipendente(idTeam, idDipendente));
    }
    @Test
    public void removeEmployeeFail() throws SQLException{
        int idTeam = -1;
        int idDipendente = -1;
        assertFalse(TeamDAO.aggiungiDipendente(idTeam, idDipendente));

    }
    @Test
    public void removeEmployeeOk() throws SQLException{
        int idTeam = 1;
        int idDipendente = 1;
        assertTrue(TeamDAO.aggiungiDipendente(idTeam, idDipendente));

    }
    @Test // fa questo o quello sotto (non entrambi)
    public void retrieveAllTeamFail() throws SQLException   {
            assertNull(TeamDAO.recuperaTuttiTeam());

    }
    @Test
    public void retrieveAllTeamOk() throws SQLException   {
        assertNotNull(TeamDAO.recuperaTuttiTeam());

    }
    @Test
    public void retrieveTMTeamFail() throws SQLException{
        int idUtente = -1;
        assertNull(TeamDAO.recuperaTeamDiUnTM(idUtente));


    }
    @Test
    public void retrieveTMTeamOk() throws SQLException{
        int idUtente = 1;
        assertNotNull(TeamDAO.recuperaTeamDiUnTM(idUtente));

    }
    @Test
    public void updateCompetenceFail() throws SQLException{
        String competence = null;
        int idTeam = 1;
        assertFalse(TeamDAO.modificaCompetenze(competence, idTeam));

    }
    @Test
    public void updateCompetenceOk() throws SQLException{
        String competence = "HTML";
        int idTeam = 1;
        assertTrue(TeamDAO.modificaCompetenze(competence, idTeam));

    }
    @Test
    public void retrieveCompetenceFail() throws SQLException {
        int idTeam = -1;
        assertNull(TeamDAO.recuperaCompetenza(idTeam));

    }
    @Test
    public void retrieveCompetenceOk() throws SQLException {
        int idTeam = 1;
        assertNotNull(TeamDAO.recuperaCompetenza(idTeam));

    }
    @Test
    public void retrieveAllTMemberFail () throws SQLException {
        int idTeam = -1;
        assertNull(TeamDAO.recuperaTuttiTMember(idTeam));

    }
    @Test
    public void retrieveAllTMemberOk () throws SQLException {
        int idTeam = 1;
        assertNotNull(TeamDAO.recuperaTuttiTMember(idTeam));

    }
    @Test
    public void retrieveNTMemberFail() throws SQLException {
        int idTeam = -1;
        assertFalse(TeamDAO.rimuoviTeam(idTeam));

    }
    @Test
    public void retrieveNTMemberOk() throws SQLException {
        int idTeam = 1;
        assertTrue(TeamDAO.rimuoviTeam(idTeam));

    }

}
