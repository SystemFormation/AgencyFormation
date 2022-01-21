package agency_formation.team.manager;

import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeamManagerIT {
    TeamManagerImpl aut = new TeamManagerImpl();
    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(13, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        String insertUtente = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(20,'test','test','test','test', 2)";
        String insertDipendente = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(20, 'Fisciano','77777',0,2000,13)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(insertTeam);
        PreparedStatement statement2 = connection.prepareStatement(insertUtente);
        PreparedStatement statement3 = connection.prepareStatement(insertDipendente);
        statement1.executeUpdate();
        statement2.executeUpdate();
        statement3.executeUpdate();
    }
    @AfterAll
    public static void finish() throws SQLException {
        String deleteTeam = "delete from team where idTeam>=1";
        String deleteUtente = "delete from Utenti where IdUtente>4";
        String deleteDipendente = "delete from dipendenti where idDipendente > 2";
        String insertTeamDefault = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(1, 'TechAll', '8', 'NuoveTecnologie', 'Ricerchiamo nuove tecnologie', 'Basi di Ingegneria Meccanica', 3)";
        String insertDipendenteDefault = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(2,'Fisciano', 118, 0, 2000, 1)";

        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(deleteTeam);
        PreparedStatement statement2 = connection.prepareStatement(deleteUtente);
        PreparedStatement statement3 = connection.prepareStatement(deleteDipendente);
        PreparedStatement statement4 = connection.prepareStatement(insertTeamDefault);
        PreparedStatement statement5 = connection.prepareStatement(insertDipendenteDefault);
        statement1.executeUpdate();
        statement2.executeUpdate();
        statement3.executeUpdate();
        statement4.executeUpdate();
        statement5.executeUpdate();
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    @Order(1)
    public void createTeamPass() throws SQLException{
        Team team = new Team("TestNomeProgetto", 8, "TestNomeTeam", "Esempio di una descrizione", null, 3);
        int idUtente = 3;
        assertTrue(aut.creaTeam(team,idUtente));
    }
    @Test
    @Order(2)
    public void createTeamFail() throws SQLException{
        int idUtente = -3;
        Team team = null;
        assertFalse(aut.creaTeam(team, idUtente));
    }
    @Test
    @Order(3)
    public void rimuoviDipendenteFail() throws SQLException{
        int idDip = -1;
        assertFalse(aut.rimuoviDipendente(idDip));
    }
    @Test
    @Order(4)
    public void rimuoviDipendentePass() throws SQLException{
        int idDip = 20;
        assertTrue(aut.rimuoviDipendente(idDip));
    }
    @Test
    @Order(5)
    public void visualizzaTeamsPass() throws SQLException {
        int idUtente = 3;
        assertNotNull(aut.visualizzaTeams(idUtente));
    }
    @Test
    @Order(6)
    public void visualizzaTeamsFail() throws SQLException {
        int idUtente = -1;
        assertNull(aut.visualizzaTeams(idUtente));
    }
    @Test
    @Order(7)
    public void visualizzaTuttiTeamsPass() throws SQLException{
        assertNotNull(aut.visualizzaTuttiTeams());
    }
    @Test
    @Order(8)
    public void visualizzaTuttiTeamsFail() throws SQLException{
        String deleteTeams = "delete from team where idTeam>=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(deleteTeams);
        statement1.executeUpdate();
        assertNull(aut.visualizzaTuttiTeams());
    }
    @Test
    @Order(9)
    public void viewLastIdTeamPass() throws SQLException{
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(13, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(insertTeam);
        statement1.executeUpdate();
        assertTrue(aut.viewLastIdTeam() > 0);
    }
    @Test
    @Order(10)
    public void viewLastIdTeamFail() throws SQLException{
        String deleteTeams = "delete from team where idTeam>=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(deleteTeams);
        statement1.executeUpdate();
        assertFalse(aut.viewLastIdTeam() < 0);
    }
    @Test
    @Order(11)
    public void recuperaIdDipendenteDelTeamFail() throws SQLException{
        int idTeam = -1;
        assertNull(aut.recuperaIdDipendentiDelTeam(idTeam));

    }
    /*@Test
    @Order(12)
    public void recuperaIdDipendenteDelTeamPass() throws SQLException{
        String insertTeamDefault = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(1, 'TechAll', '8', 'NuoveTecnologie', 'Ricerchiamo nuove tecnologie', 'Basi di Ingegneria Meccanica', 3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(insertTeamDefault);
        statement1.executeUpdate();
        int idTeam = 1;
        assertNotNull(aut.recuperaIdDipendentiDelTeam(idTeam));
    }*/
    @Test
    @Order(13)
    public void updateDipsDissoFail() throws SQLException {
        int idDip = 1;
        assertFalse(aut.updateDipsDisso(idDip));

    }
    /*@Test
    @Order(14)
    public void updateDipsDissoPass() throws SQLException {
        int idDip = 2;
        assertTrue(aut.updateDipsDisso(idDip));
    }*/

    @Test
    @Order(15)
    public void sciogliTeamPass() throws SQLException {
        int idTeam = 13;
        assertTrue(aut.sciogliTeam(idTeam));
    }
    @Test
    @Order(16)
    public void sciogliTeamFail() throws SQLException {
        int idTeam = -1;
        assertFalse(aut.sciogliTeam(idTeam));
    }
    /* Questo funziona
    @Test
    @Order(17)
    public void recuperaDipendentiDelTeamFail() throws SQLException{
        String deleteUtente = "Delete from utenti where IdUtente = 2" ;
        String deleteDipendente ="Delete from dipendenti where IdDipendente = 2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(deleteUtente);
        PreparedStatement statement2 = connection.prepareStatement(deleteDipendente);
        statement1.executeUpdate();
        statement2.executeUpdate();
    }*/
    /*@Test Constraint fails
    @Order(18)
    public void recuperaDipendentiDelTeamPass() throws SQLException {
       /String insertUtente = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(2,'Pasquale', 'Severino', 'lol', 'p.severino@studenti.unisa.it', 2)";
        String insertDipendente = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(2,'Fisciano', 118, 0, 2000, 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(insertUtente);
        PreparedStatement statement2 = connection.prepareStatement(insertDipendente);
        statement1.executeUpdate();
        statement2.executeUpdate();
        assertNotNull(aut.recuperaDipendentiDelTeam());
    }*/

    @Test
    @Order(19)
    public void getTeamByIdFail() throws SQLException{
        int idTeam = -1;
        assertNull(aut.getTeamById(idTeam));
    }
    @Test
    @Order(20)
    public void getTeamByIdPass() throws SQLException{
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(22, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(insertTeam);
        statement1.executeUpdate();
        int idTeam = 22;
        assertNotNull(aut.getTeamById(idTeam));
    }
    @Test
    @Order(21)
    public void specificaLeCompetenzeFail() throws SQLException{
        String competence = "HTML";
        int idTeam = -1;
        assertFalse(aut.specificaLeCompetenze(competence,idTeam));
    }
    @Test
    @Order(22)
    public void specificaLeCompetenzePass() throws SQLException{
        String competence = "HTML";
        int idTeam = 22;
        assertTrue(aut.specificaLeCompetenze(competence,idTeam));
    }

}