package agency_formation.team.DAO;

import it.unisa.agency_formation.formazione.domain.Documento;
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
        String query_documento = "insert into documenti (IdDocumento,MaterialeDiFormazione,IdHR,IdTeam) values (2,'//test',4,2)";
        String query_per_rimozione="insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(2, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_per_rimozione);
        PreparedStatement statement2 = connection.prepareStatement(query_documento);

        statement.executeUpdate(query_per_rimozione);
        statement2.executeUpdate(query_documento);


    }
    @AfterAll
    public static void finish() throws SQLException {
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }
    @Test
    @Order(2)
    public void saveTeamFail() throws SQLException {
        Team team = null;
        int idUtente = 1;
        assertFalse(TeamDAO.salvaTeam(team, idUtente));
    }
    @Test
    @Order(3)
    public void saveTeamFail2() throws SQLException {
        Team team = new Team("TestNomeProgetto", 8, "TestNomeTeam", "Esempio di una descrizione", null, 3);
        int idUtente = -1;
        assertFalse(TeamDAO.salvaTeam(team, idUtente));
    }
    @Test
    @Order(4)
    public void saveTeamOk1() throws SQLException {
        Team team = new Team("TestNomeProgetto", 8, "TestNomeTeam", "Esempio di una descrizione", null, 3);
        int idUtente = 3;
        assertTrue(TeamDAO.salvaTeam(team, idUtente));
    }
    @Test
    @Order(5)
    public void retrieveLastIdTeamPass() throws  SQLException {
        assertNotNull(TeamDAO.recuperaIdUltimoTeamCreato());
    }

    @Test
    @Order(6) //rimossione fallita
    public void removeTeamFail() throws SQLException {
        int idTeam = -1;
        assertFalse(TeamDAO.rimuoviTeam(idTeam));

    }
    @Test
    @Order(7) //rimosso team 2
    public void removeTeamOk() throws SQLException {
        int idTeam = 2;
        assertTrue(TeamDAO.rimuoviTeam(idTeam));
    }

    @Test
    @Order(8)
    public void doRetrieveTeamByIdFail() throws SQLException {
        int idTeam = -1;
        assertNull(TeamDAO.recuperaTeamById(idTeam));
    }

    @Test
    @Order(9)
    public void doRetrieveTeamByIdOk() throws SQLException {
        int idTeam = 1;
        assertNotNull(TeamDAO.recuperaTeamById(idTeam));
    }
    @Test
    @Order(10)
    public void removeEmployeeFail() throws SQLException {
        int idDipendente = -1;
        assertFalse(TeamDAO.rimuoviDipendente(idDipendente));
    }

    @Test
    @Order(11)
    public void removeEmployeeOk() throws SQLException {
        int idDipendente = 2;
        assertTrue(TeamDAO.rimuoviDipendente(idDipendente));
    }
    @Test
    @Order(12)
    public void retrieveAllTeamOk() throws SQLException   {
        assertNotNull(TeamDAO.recuperaTuttiTeam());

    }
    @Test
    @Order(13)
    public void retrieveAllTeamFail()throws SQLException{
        String query = "delete from team where idTM = 3";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        assertNull(TeamDAO.recuperaTuttiTeam());
    }

    @Test
    @Order(14)
    public void retrieveTMTeamFail() throws SQLException{
        int idUtente = -1;
        assertNull(TeamDAO.recuperaTeamDiUnTM(idUtente));
    }
    @Test
    @Order(15)
    public void retrieveTMTeamOk() throws SQLException{
        String query_team_order14 ="insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(4, 'RTresd',8,'PolloFlitto','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_team_order14);
        statement.executeUpdate(query_team_order14);
        int idUtente = 3;
        assertNotNull(TeamDAO.recuperaTeamDiUnTM(idUtente));

        String query_delete ="delete from team where idTM = 3";
        PreparedStatement statement2 = connection.prepareStatement(query_delete);
        statement2.executeUpdate(query_delete);



    }
    @Test
    @Order(16)
    public void updateCompetenceFail() throws SQLException{
        String query_team_order14 ="insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(5, 'RTresd',8,'PolloFlitto','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_team_order14);
        statement.executeUpdate(query_team_order14);
        String competence = null;
        int idTeam = 0;
        assertFalse(TeamDAO.modificaCompetenze(competence, idTeam));
    }

    @Test
    @Order(17)
    public void updateCompetenceOk() throws SQLException{
        String competence = "HTML";
        int idTeam = 5;
        assertTrue(TeamDAO.modificaCompetenze(competence, idTeam));

    }

    @Test
    @Order(18)
    public void retrieveCompetenceFail() throws SQLException {
        int idTeam = -1;
        assertNull(TeamDAO.recuperaCompetenza(idTeam));

    }
    @Test
    @Order(19)
    public void retrieveCompetenceOk() throws SQLException {
        int idTeam = 5;
        assertNotNull(TeamDAO.recuperaCompetenza(5));

    }

    @Test
    @Order(20)
    public void retrieveAllIdEmployeesfromTeamFail() throws SQLException{
        int idTeam =  -1;
        assertNull(TeamDAO.recuperaIdTeamMemberFromTeam(idTeam));
    }

    @Test
    @Order(21)
    public void retrieveAllIdEmployeesfromTeamOK() throws SQLException{
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query_safe0 = "SET SQL_SAFE_UPDATES=0";
        String query_set_dipendente_team = "update dipendenti set Stato = 0, IdTeam = 5 where IdDipendente = 2";
        String query_safe1 = "SET SQL_SAFE_UPDATES=1";
        PreparedStatement statement1 = connection.prepareStatement(query_safe0);
        PreparedStatement statement2 = connection.prepareStatement(query_set_dipendente_team);
        PreparedStatement statement3 = connection.prepareStatement(query_safe1);
        statement1.executeUpdate(query_safe0);
        statement2.executeUpdate(query_set_dipendente_team);
        statement3.executeUpdate(query_safe1);
        int idTeam =  5;
        assertNotNull(TeamDAO.recuperaIdTeamMemberFromTeam(idTeam));
       // String query_delete ="delete from team where idTM = 3";
       //PreparedStatement statement4 = connection.prepareStatement(query_delete);
       // statement4.executeUpdate(query_delete);
    }

    /*
    @Test
    @Order(15)
    public void recoverEmployeesOK() throws SQLException{
        assertNotNull(TeamDAO.recuperaDipendentiDelTeam());
    }
    */

    @Test
    @Order(22)
    public void updateEmployeesStateWhenTeamDissolutionFail() throws SQLException{
        int idDip = -1;
        assertFalse(TeamDAO.updateDipStateDissolution(idDip));
    }
    @Test
    @Order(23)
    public void updateEmployeesStateWhenTeamDissolutionOK() throws SQLException{
        int idDip = 2;
        assertTrue(TeamDAO.updateDipStateDissolution(idDip));
    }
    /*
    @Test
    @Order(24)
    public void recoverEmployeesState0() throws SQLException{
        String query = "update dipendenti set Stato = 0 where idDipendente = 2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        assertNotNull(TeamDAO.recuperaDipendentiDelTeam());
    }
    @Test
    @Order(25)
    public void recoverEmployeesState1() throws SQLException{
        String query = "update dipendenti set Stato = 1 where idDipendente = 2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        assertNotNull(TeamDAO.recuperaDipendentiDelTeam());
    }
   /* @Test
    @Order(25)
    public void recoverEmployeesFail() throws SQLException{
        String query = "delete from dipendenti where idDipendente = 2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        assertNull(TeamDAO.recuperaDipendentiDelTeam());
    }*/
    /*
    @Test
    @Order(26) //il database deve essere vuoto per farlo funzionare
    public void retrieveAllTeamFail() throws SQLException   {
        String query = "delete from team where idTM = 3";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        assertNull(TeamDAO.recuperaTuttiTeam());
    }*/
}
