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
        String query_per_rimozione_delete ="delete from team where idTeam = 2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_per_rimozione_delete);
        statement.executeUpdate(query_per_rimozione_delete);
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
    @Test
    @Order(5)
    public void doRetrieveTeamByIdFail() throws SQLException {
        int idTeam = -1;
        assertNull(TeamDAO.recuperaTeamById(idTeam));
    }

    @Test
    @Order(6)
    public void doRetrieveTeamByIdOk() throws SQLException {
        int idTeam = 1;
        assertNotNull(TeamDAO.recuperaTeamById(idTeam));
    }


    @Test
    @Order(7)
    public void retrieveAllTeamOk() throws SQLException   {
        assertNotNull(TeamDAO.recuperaTuttiTeam());

    }
    @Test
    @Order(8)
    public void retrieveTMTeamFail() throws SQLException{
        int idUtente = -1;
        assertNull(TeamDAO.recuperaTeamDiUnTM(idUtente));
    }
    @Test
    @Order(9)
    public void retrieveTMTeamOk() throws SQLException{
        int idUtente = 3;
        assertNotNull(TeamDAO.recuperaTeamDiUnTM(idUtente));

    }
    @Test
    @Order(10)
    public void retrieveCompetenceOk() throws SQLException {
        int idTeam = 1;
        assertNotNull(TeamDAO.recuperaCompetenza(idTeam));
    }
    @Test
    @Order(11)
    public void retrieveCompetenceFail() throws SQLException {
        int idTeam = -1;
        assertNull(TeamDAO.recuperaCompetenza(idTeam));

    }
    @Test
    @Order(12)
    public void recoverEmployeesOK() throws SQLException{
        assertNotNull(TeamDAO.recuperaDipendentiDelTeam());
    }
    @Test
    @Order(13)
    public void removeEmployeeFail() throws SQLException {
        int idDipendente = -1;
        assertFalse(TeamDAO.rimuoviDipendente(idDipendente));
    }

    @Test
    @Order(14)
    public void removeEmployeeOk() throws SQLException {
        int idDipendente = 2;
        assertTrue(TeamDAO.rimuoviDipendente(idDipendente));
    }
    @Test
    @Order(15)
    public void updateCompetenceFail() throws SQLException{
        String competence = null;
        int idTeam = 1;
        assertFalse(TeamDAO.modificaCompetenze(competence, idTeam));

    }
    // TODO
    @Test
    @Order(16)
    public void updateCompetenceOk() throws SQLException{
        String competence = "HTML";
        int idTeam = 1;
        assertTrue(TeamDAO.modificaCompetenze(competence, idTeam));

    }

    @Test
    @Order(17)
    public void retrieveAllIdEmployeesfromTeamFail() throws SQLException{
        int idTeam =  -1;
        assertNull(TeamDAO.recuperaIdTeamMemberFromTeam(idTeam));
    }
    @Test
    @Order(18)
    public void retrieveAllIdEmployeesfromTeamOK() throws SQLException{
        int idTeam =  1;
        assertNotNull(TeamDAO.recuperaIdTeamMemberFromTeam(idTeam));
    }
    @Test
    @Order(19)
    public void updateEmployeesStateWhenTeamDissolutionFail() throws SQLException{
        int idDip = -1;
        assertFalse(TeamDAO.updateDipStateDissolution(idDip));
    }
    @Test
    @Order(20)
    public void updateEmployeesStateWhenTeamDissolutionOK() throws SQLException{
        int idDip = 2;
        assertTrue(TeamDAO.updateDipStateDissolution(idDip));
    }

    // Nessun Dipendente per farlo funzionare
    @Test
    @Order(21)
    public void recoverEmployeesFail() throws SQLException{
        String query = "delete * from dipendente where idDipendente = 1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        assertNull(TeamDAO.recuperaDipendentiDelTeam());
    }

    @Test
    @Order(21) //il database deve essere vuoto per farlo funzionare
    public void retrieveAllTeamFail() throws SQLException   {
        String query = "delete from team where idTM = 3";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        assertNull(TeamDAO.recuperaTuttiTeam());
    }





}
