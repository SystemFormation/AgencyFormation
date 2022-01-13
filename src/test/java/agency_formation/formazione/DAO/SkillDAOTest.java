package agency_formation.formazione.DAO;


import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Questa classe racchiude tutti i casi di test riguardante SkillDAO
 */
public class SkillDAOTest {

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;

    }

    @AfterAll
    public static void finish() throws SQLException {
        //String query  = "Insert into documenti (IdDocumento, MaterialeDiFormazione, IdHR, IdTeam) values (1, '/', 4, 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        //PreparedStatement statement = connection.prepareStatement(query);
        //statement.executeUpdate(query);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @BeforeEach
    public void clearSkillDB() throws SQLException {
        String query1 = "DELETE FROM Skill";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query1);
    }

    @BeforeEach
    public void ripopulateSkillDB() throws SQLException {
        String query1 = "INSERT INTO skill (`NomeSkill`, `DescrizioneSkill`) VALUES ( 'HTML', 'Conoscenze generali di HTML')";
        String query2 = "INSERT INTO skill (`NomeSkill`, `DescrizioneSkill`) VALUES ('CSS', 'Conoscenze basilari di CSS')";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query1);
        PreparedStatement stmt = connection.prepareStatement(query2);
        stmt.executeUpdate(query2);
    }
    @BeforeEach
    public void clearSkillDipDB() throws SQLException{
        String query1 = "DELETE FROM skillsdipendenti WHERE idDipendente=2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query1);
    }
    @Test
    @Order(1)
    public void saveSkillFail() throws SQLException {
        Skill skill = null;
        assertFalse(SkillDAO.salvaSkill(skill));
    }

    @Test
    @Order(2)
    public void saveSkillOK() throws SQLException {
        Skill skill = new Skill("C","Conoscenze base");
        assertTrue(SkillDAO.salvaSkill(skill));
    }

    @Test
    @Order(3)
    public void removeSkillFail() throws SQLException {
        assertFalse(SkillDAO.rimuoviSkill(0));
    }

    @Test
    @Order(4)
    public void removeSkillOK() throws SQLException {
        assertTrue(SkillDAO.rimuoviSkill(2));
    }


    @Test // Funziona con il db vuoto
    @Order(5)
    public void doRetrieveAllFail() throws SQLException {
        clearSkillDB();
        assertNull(SkillDAO.recuperaSkills());
    }

    @Test // Funziona con il db popolato
    @Order(6)
    public void doRetrieveAllPass() throws SQLException {
        ripopulateSkillDB();
        assertNotNull(SkillDAO.recuperaSkills());
    }

    @Test //not pass nomeSkill=null
    @Order(7)
    public void doRetrieveByNameNull() throws SQLException {
        String nomeSkill = null;
        assertNull(SkillDAO.recuperaSkillByNome(nomeSkill));
    }

    @Test //pass
    @Order(8)
    public void doRetrieveByNamePass() throws SQLException {
        ripopulateSkillDB();
        String nomeSkill = "HTML";
        assertNotNull(SkillDAO.recuperaSkillByNome(nomeSkill));
    }

    @Test //not pass idSkill<1
    @Order(9)
    public void saveSkillDipIdSkillFail() throws SQLException {
        assertFalse(SkillDAO.salvaSkillDipendente(0, 1, 4));
    }

    @Test //not pass idDip < 1
    @Order(10)
    public void saveSkillDipIdDipNull() throws SQLException {
        assertFalse(SkillDAO.salvaSkillDipendente(1, 0, 4));
    }

    @Test //not pass idDip < 1 && idSkill < 1
    @Order(11)
    public void saveSkillDipNullAndFail() throws SQLException {
        assertFalse(SkillDAO.salvaSkillDipendente(0, 0, 4));
    }

//NON FUNZIONA
    @Test //pass
    @Order(12)
    public void saveSkillDipPass() throws SQLException {
        clearSkillDipDB();
        assertTrue(SkillDAO.salvaSkillDipendente(1, 2, 4));
    }

    @Test
    @Order(13)
    public void RetrieveLastIdPass() throws SQLException {
        assertNotNull(SkillDAO.recuperaUltimaSkill());
    }

    @Test //there aren't skill
    @Order(14)
    public void RetrieveLastIdFail() throws SQLException {
        assertNotNull(SkillDAO.recuperaUltimaSkill());
    }
}
