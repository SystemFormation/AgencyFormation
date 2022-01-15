package agency_formation.formazione.DAO;


import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
        //Connection connection = DatabaseManager.getInstance().getConnection();
        //PreparedStatement statement = connection.prepareStatement(query);
        //statement.executeUpdate(query);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }
    @BeforeEach
    public void ripopulateSkillDipDB() throws SQLException{
        String query4 = "INSERT INTO skill (IdSkill, NomeSkill, DescrizioneSkill) VALUES (23, 'Pyton', 'Conoscenze basilari di Pyton')";
        String query2 = "INSERT INTO utenti (Nome, Cognome, Pwd, Mail, Ruolo) VALUES ('Alex', 'Fissa', 'lol', 'a.fissa@studenti.unisa.it', 2)";
        String query3 = "INSERT INTO dipendenti (IdDipendente, Residenza, Telefono, Stato, AnnoDiNascita) VALUES (5,'Fisciano', 112, 0, 2000)";
        String query1 = "INSERT INTO skillsdipendenti(IdSkill, IdDipendente, Livello) " +
                    " VALUES (3,2,3)";
            Connection connection = DatabaseManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.executeUpdate(query2);
        PreparedStatement statement1 = connection.prepareStatement(query3);
        statement1.executeUpdate(query3);
        PreparedStatement statement2 = connection.prepareStatement(query4);
        statement2.executeUpdate(query4);
        PreparedStatement statement3 = connection.prepareStatement(query1);
        statement3.executeUpdate(query1);
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
    }/*
    @AfterEach
    public void ripopulateSkillDipDB() throws SQLException {
        String query1 = "INSERT INTO skillsdipendenti(IdSkill, IdDipendente, Livello) " +
                " VALUES (1,2,3), (2,2,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query1);
    }*/
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
    @Order(16)
    public void removeSkillOK() throws SQLException {
        assertTrue(SkillDAO.rimuoviSkill(2));
    }


    @Test // Funziona con il db vuoto
    @Order(17)
    public void doRetrieveAllFail() throws SQLException {
        clearSkillDB();
        assertNull(SkillDAO.recuperaSkills());
    }

    @Test // Funziona con il db popolato
    @Order(10)
    public void doRetrieveAllPass() throws SQLException {
        ripopulateSkillDB();
        assertNotNull(SkillDAO.recuperaSkills());
    }

    @Test //not pass nomeSkill=null
    @Order(4)
    public void doRetrieveByNameNull() throws SQLException {
        String nomeSkill = null;
        assertNull(SkillDAO.recuperaSkillByNome(nomeSkill));
    }

    @Test //pass
    @Order(13)
    public void doRetrieveByNamePass() throws SQLException {
        ripopulateSkillDB();
        String nomeSkill = "HTML";
        assertNotNull(SkillDAO.recuperaSkillByNome(nomeSkill));
    }

    @Test //not pass idSkill<1
    @Order(5)
    public void saveSkillDipIdSkillFail() throws SQLException {
        assertFalse(SkillDAO.salvaSkillDipendente(0, 1, 4));
    }

    @Test //not pass idDip < 1
    @Order(6)
    public void saveSkillDipIdDipNull() throws SQLException {
        assertFalse(SkillDAO.salvaSkillDipendente(1, 0, 4));
    }

    @Test //not pass idDip < 1 && idSkill < 1
    @Order(7)
    public void saveSkillDipNullAndFail() throws SQLException {
        assertFalse(SkillDAO.salvaSkillDipendente(0, 0, 4));
    }

//NON FUNZIONA
    @Test //pass
    @Order(14)
    public void saveSkillDipPass() throws SQLException {
        clearSkillDipDB();
        assertTrue(SkillDAO.salvaSkillDipendente(1, 2, 4));
    }

    @Test
    @Order(8)
    public void RetrieveLastIdPass() throws SQLException {
        assertNotNull(SkillDAO.recuperaUltimaSkill());
    }

    @Test //there aren't skill
    @Order(9)
    public void RetrieveLastIdFail() throws SQLException {
        assertNotNull(SkillDAO.recuperaUltimaSkill());
    }
    @Test
    @Order(15)
    public void doRetrieveSkillByIdDipendenteFail() throws SQLException {
    clearSkillDipDB();
    assertNull(SkillDAO.recuperoSkillsByIdDipendente(2));

    }

    @Test // dipendente insesistente
    @Order(11)
     public void doRetrieveSkillByIdDipendenteFail1() throws SQLException {
        assertNull(SkillDAO.recuperoSkillsByIdDipendente(0));
    }

    @Test
    @Order(12)
    public void doRetrieveSkillByIdDipendentePass() throws SQLException {
    /*Skill skill = new Skill();
    skill.setIdSkill(10);
    skill.setNomeSkill("C++");
    skill.setDescrizioneSkill("Linguaggio C++");
    ArrayList<Skill> list = new ArrayList<>();
    list.add(skill);
    Dipendente dip = new Dipendente();
        dip.setSkills(list);
        dip.setIdDipendente(15);
        System.out.println(dip.getSkills());*/
        ripopulateSkillDB();
        ripopulateSkillDipDB();
        assertNotNull(SkillDAO.recuperoSkillsByIdDipendente(2));


    }

}
