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
        String insertDocumento = "insert into documenti (IdDocumento,MaterialeDiFormazione,IdHR,IdTeam) values (2,'//test',4,2)";
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(2, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        String insertUtente = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(201,'test','test','test','test', 1)";
        String insertDipendente = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(201, 'Fisciano','77777',1,2000,2)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(insertDocumento);
        PreparedStatement statement2 = connection.prepareStatement(insertTeam);
        PreparedStatement statement3 = connection.prepareStatement(insertUtente);
        PreparedStatement statement4 = connection.prepareStatement(insertDipendente);
        statement1.executeUpdate(insertTeam);
        statement2.executeUpdate(insertDocumento);
        statement3.executeUpdate(insertUtente);
        statement4.executeUpdate(insertDipendente);
    }

    @AfterAll
    public static void finish() throws SQLException {
        String deleteUtente = "delete from Utenti where IdUtente=201";
        String deleteDocumento = "delete from documenti where IdDocumento>=1";
        String deleteTeam = "delete from team where idTeam>=1";
        String deleteDipendente = "delete from dipendenti where idDipendente > 2";
        String insertTeamDefault = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(1, 'TechAll', '8', 'NuoveTecnologie', 'Ricerchiamo nuove tecnologie', 'Basi di Ingegneria Meccanica', 3)";
        String insertDipendenteDefault = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(2,'Fisciano', 118, 0, 2000, 1)";
        String insertSkills1 = "insert into skillsdipendenti(IdDipendente,IdSkill,Livello) values(2,1,5)";
        String insertSkills2 = "insert into skillsdipendenti(IdDipendente,IdSkill,Livello) values(2,2,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(deleteDocumento);
        PreparedStatement statement2 = connection.prepareStatement(deleteTeam);
        PreparedStatement statement3 = connection.prepareStatement(deleteDipendente);
        PreparedStatement statement4 = connection.prepareStatement(deleteUtente);
        PreparedStatement statement5 = connection.prepareStatement(insertTeamDefault);
        PreparedStatement statement6 = connection.prepareStatement(insertDipendenteDefault);
        PreparedStatement statement8 = connection.prepareStatement(insertDipendenteDefault);
        statement1.executeUpdate(deleteDocumento);
        statement2.executeUpdate(deleteTeam);
        statement3.executeUpdate(deleteDipendente);
        statement4.executeUpdate(deleteUtente);
        statement5.executeUpdate(insertTeamDefault);
        statement6.executeUpdate(insertDipendenteDefault);
        statement8.executeUpdate(insertSkills1);
        statement8.executeUpdate(insertSkills2);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    @Order(1)
    public void saveTeamFail() throws SQLException {
        Team team = null;
        int idUtente = 2;
        assertFalse(TeamDAO.salvaTeam(team, idUtente));
    }

    @Test
    @Order(2)
    public void saveTeamFail2() throws SQLException {
        Team team = new Team("TestNomeProgetto", 8, "TestNomeTeam", "Esempio di una descrizione", null, 3);
        int idUtente = -1;
        assertFalse(TeamDAO.salvaTeam(team, idUtente));
    }

    @Test
    @Order(3)
    public void saveTeamOk1() throws SQLException {
        Team team = new Team("TestNomeProgetto", 8, "TestNomeTeam", "Esempio di una descrizione", null, 3);
        int idUtente = 3;
        assertTrue(TeamDAO.salvaTeam(team, idUtente));
    }

    @Test
    @Order(4)
    public void retrieveLastIdTeamPass() throws SQLException {
        assertNotNull(TeamDAO.recuperaIdUltimoTeamCreato());
    }

    @Test
    @Order(5) //rimossione fallita
    public void removeTeamFail() throws SQLException {
        int idTeam = -1;
        assertFalse(TeamDAO.rimuoviTeam(idTeam));

    }

    @Test
    @Order(6) //rimosso team 2
    public void removeTeamOk() throws SQLException {
        int idTeam = 2;
        assertTrue(TeamDAO.rimuoviTeam(idTeam));
    }

    @Test
    @Order(7)
    public void doRetrieveTeamByIdFail1() throws SQLException {
        int idTeam = -1;
        assertNull(TeamDAO.recuperaTeamById(idTeam));
    }

    @Test
    @Order(8)
    public void doRetrieveTeamByIdOk2() throws SQLException {
        int idTeam = 2;
        assertNotNull(TeamDAO.recuperaTeamById(idTeam));
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
        String deleteDipendente = "delete from dipendenti where IdDipendente=2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(deleteDipendente);
        statement1.executeUpdate(deleteDipendente);
        String insertDipendente = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(2,'Fisciano', 118, 0, 2000, 1)";
        PreparedStatement statement2 = connection.prepareStatement(insertDipendente);
        statement2.executeUpdate(insertDipendente);
        int idDipendente = 2;
        assertTrue(TeamDAO.rimuoviDipendente(idDipendente));
        String deleteDipendente2 = "delete from dipendenti where IdDipendente=2";
        PreparedStatement statement3 = connection.prepareStatement(deleteDipendente2);
        statement3.executeUpdate(deleteDipendente2);
    }

    @Test
    @Order(12)
    public void retrieveAllTeamOk() throws SQLException {
        assertNotNull(TeamDAO.recuperaTuttiTeam());

    }

    @Test
    @Order(13)
    public void retrieveAllTeamFail() throws SQLException {
        String query = "delete from team where idTeam >=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(query);
        statement1.executeUpdate(query);
        assertNull(TeamDAO.recuperaTuttiTeam());
        String insertTeamDefault = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(1, 'Fitdiary', '8', 'Bastoncini Fitnuss', 'Vendiamo bastoncini di pesce', 'HTML', 3)";
        PreparedStatement statement2 = connection.prepareStatement(insertTeamDefault);
        statement2.executeUpdate(insertTeamDefault);
    }

    @Test
    @Order(14)
    public void retrieveTMTeamFail() throws SQLException {
        int idUtente = -1;
        assertNull(TeamDAO.recuperaTeamDiUnTM(idUtente));
    }

    @Test
    @Order(15)
    public void retrieveTMTeamOk1() throws SQLException {
        String query_team_order14 = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(4, 'RTresd',8,'PolloFlitto','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_team_order14);
        statement.executeUpdate(query_team_order14);
        int idUtente = 3;
        assertNotNull(TeamDAO.recuperaTeamDiUnTM(idUtente));
        String query_delete = "delete from team where idTeam = 4";
        PreparedStatement statement2 = connection.prepareStatement(query_delete);
        statement2.executeUpdate(query_delete);
    }

    @Test
    @Order(16)
    public void retrieveTMTeamOk2() throws SQLException {
        String query_team_order14 = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(4, 'RTresd',8,'PolloFlitto','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_team_order14);
        statement.executeUpdate(query_team_order14);
        int idUtente = 3;
        assertNotNull(TeamDAO.recuperaTeamDiUnTM(idUtente));
        String query_delete = "delete from team where idTeam = 4";
        PreparedStatement statement2 = connection.prepareStatement(query_delete);
        statement2.executeUpdate(query_delete);
    }

    @Test
    @Order(17)
    public void updateCompetenceFail1() throws SQLException {
        String query_team_order = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(5, 'RTresd',8,'PolloFlitto','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_team_order);
        statement.executeUpdate(query_team_order);
        String competence = null;
        int idTeam = 0;
        assertFalse(TeamDAO.specificaCompetenze(competence, idTeam));
        String query_delete = "delete from team where idTeam = 5";
        PreparedStatement statement2 = connection.prepareStatement(query_delete);
        statement2.executeUpdate(query_delete);
    }

    @Test
    @Order(18)
    public void updateCompetenceFail2() throws SQLException {
        String competence = null;
        int idTeam = -1;
        assertFalse(TeamDAO.specificaCompetenze(competence, idTeam));
    }

    @Test
    @Order(19)
    public void updateCompetenceOk() throws SQLException {
        String query_team_order = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(5, 'RTresd',8,'PolloFlitto','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_team_order);
        statement.executeUpdate(query_team_order);
        String competence = "HTML";
        int idTeam = 5;
        assertTrue(TeamDAO.specificaCompetenze(competence, idTeam));
        String query_delete = "delete from team where idTeam = 5";
        PreparedStatement statement2 = connection.prepareStatement(query_delete);
        statement2.executeUpdate(query_delete);
    }

    @Test
    @Order(20)
    public void retrieveAllIdEmployeesfromTeamFail() throws SQLException {
        int idTeam = -1;
        assertNull(TeamDAO.recuperaIdTeamMemberFromTeam(idTeam));
    }

    @Test
    @Order(21)
    public void retrieveAllIdEmployeesfromTeamOK() throws SQLException {
        String query_team_order = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(5, 'RTresd',8,'PolloFlitto','Non siamo eroi','HTML',3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_team_order);
        statement.executeUpdate(query_team_order);
        String query_safe0 = "SET SQL_SAFE_UPDATES=0";
        String query_set_dipendente_team = "update dipendenti set Stato = 0, IdTeam = 5 where IdDipendente = 201";
        String query_safe1 = "SET SQL_SAFE_UPDATES=1";
        PreparedStatement statement1 = connection.prepareStatement(query_safe0);
        PreparedStatement statement2 = connection.prepareStatement(query_set_dipendente_team);
        PreparedStatement statement3 = connection.prepareStatement(query_safe1);
        statement1.executeUpdate(query_safe0);
        statement2.executeUpdate(query_set_dipendente_team);
        statement3.executeUpdate(query_safe1);
        int idTeam = 5;
        assertNotNull(TeamDAO.recuperaIdTeamMemberFromTeam(idTeam));
        String query_delete = "delete from team where idTeam = 5";
        PreparedStatement statement4 = connection.prepareStatement(query_delete);
        statement4.executeUpdate(query_delete);
    }

    @Test
    @Order(22)
    public void recoverEmployeesOK() throws SQLException {
        String deleteTeam = "delete from team where idTeam =1";
        String insertTeamDefault = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(1, 'Fitdiary', '8', 'Bastoncini Fitnuss', 'Vendiamo bastoncini di pesce', 'HTML', 3)";
        String insertDipendenteDefault = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(2,'Fisciano', 118, 0, 2000, 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(insertTeamDefault);
        PreparedStatement statement2 = connection.prepareStatement(insertDipendenteDefault);
        PreparedStatement statement3 = connection.prepareStatement(deleteTeam);
        statement1.executeUpdate(deleteTeam);
        statement2.executeUpdate(insertTeamDefault);
        statement3.executeUpdate(insertDipendenteDefault);
        assertNotNull(TeamDAO.recuperaDipendentiDelTeam());
    }

    @Test
    @Order(23)
    public void updateEmployeesStateWhenTeamDissolutionFail() throws SQLException {
        int idDip = -1;
        assertFalse(TeamDAO.updateDipStateDissolution(idDip));
    }

    @Test
    @Order(24)
    public void updateEmployeesStateWhenTeamDissolutionOK() throws SQLException {
        String query_team_order = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(100, 'RTresd',8,'PolloFlitto','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query_team_order);
        statement.executeUpdate(query_team_order);
        int idDip = 2;
        assertTrue(TeamDAO.updateDipStateDissolution(idDip));
        String query_delete = "delete from team where idTeam = 100";
        PreparedStatement statement2 = connection.prepareStatement(query_delete);
        statement2.executeUpdate(query_delete);
    }

    @Test
    @Order(25)
    public void recoverEmployeesState0() throws SQLException {
        String query = "update dipendenti set Stato = 0 where idDipendente = 2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        assertNotNull(TeamDAO.recuperaDipendentiDelTeam());
    }

    @Test
    @Order(26)
    public void recoverEmployeesState1() throws SQLException {
        String query = "update dipendenti set Stato = 1 where idDipendente = 2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        assertNotNull(TeamDAO.recuperaDipendentiDelTeam());
    }

    @Test
    @Order(27)
    public void recoverEmployeesFail() throws SQLException {
        String deleteDipendente = "delete from dipendenti where idDipendente >1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteDipendente);
        statement.executeUpdate(deleteDipendente);
        assertNull(TeamDAO.recuperaDipendentiDelTeam());
    }
}
