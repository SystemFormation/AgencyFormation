package agency_formation.team.DAO;

import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeamDAOTest {

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query_per_rimozione="insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(2, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_per_rimozione);
        statement.executeUpdate(query_per_rimozione);

    }
    @AfterAll
    public static void finish() throws SQLException {
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }
    @Test
    @Order(1)
    public void saveTeamFail() throws SQLException {
        Team team = null;
        int idUtente = -1;
        assertFalse(TeamDAO.salvaTeam(team, idUtente));
    }
    @Test
    @Order(2)
    public void saveTeamOk() throws SQLException {
        Team team = new Team("TestNomeProgetto", 8, "TestNomeTeam", "Esempio di una descrizione", null, 3);
        int idUtente = 3;
        assertTrue(TeamDAO.salvaTeam(team, idUtente));
    }
    @Test
    @Order(3)
    public void removeTeamFail() throws SQLException {
        int idTeam = -1;
        assertFalse(TeamDAO.rimuoviTeam(idTeam));

    }
    @Test
    @Order(4)
    public void removeTeamOk() throws SQLException {
        int idTeam = 2;
        assertTrue(TeamDAO.rimuoviTeam(idTeam));
    }
    /*@Test
    public void addEmployeeFail() throws SQLException{
        int idTeam = -1;
        int idDipendente = -1;
        assertFalse(TeamDAO.aggiungiDipendente(idTeam, idDipendente));

    }

    @Test
    public void addEmployeeOk() throws SQLException {
        int idTeam = 1;
        int idDipendente = 2;
        assertTrue(TeamDAO.aggiungiDipendente(idTeam, idDipendente));
    }*/

    @Test
    public void doRetrieveTeamByIdFail() throws SQLException {
        int idTeam = -1;
        assertNull(TeamDAO.recuperaTeamById(idTeam));
    }

    @Test
    public void doRetrieveTeamByIdOk() throws SQLException {
        int idTeam = 1;
        assertNotNull(TeamDAO.recuperaTeamById(idTeam));
    }

    @Test
    public void removeEmployeeFail() throws SQLException {
        int idDipendente = -1;
    }

    @Test
    public void removeEmployeeOk() throws SQLException {
        int idTeam = 1;
        int idDipendente = 1;

    }
    @Test // fa questo o quello sotto (non entrambi)
    public void retrieveAllTeamFail() throws SQLException   {
        assertNull(TeamDAO.recuperaTuttiTeam());
//non può funzionare perchè non può azzerare l'arraylist di team
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
        assertNull(TeamDAO.recuperaTuttiTeamMember(idTeam));
    }
    @Test
    public void retrieveAllTMemberOk () throws SQLException {
        int idTeam = 3;
        assertNotNull(TeamDAO.recuperaTuttiTeamMember(idTeam));
    }
    @Test
    public void retrieveNTMemberFail() throws SQLException {
        int idTeam = -1;
        assertFalse(TeamDAO.rimuoviTeam(idTeam));

    }
    @Test
    public void retrieveNTMemberOk() throws SQLException {
        int idTeam = 3;
        assertTrue(TeamDAO.rimuoviTeam(idTeam));

    }

}
