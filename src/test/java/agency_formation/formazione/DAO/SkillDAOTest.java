package agency_formation.formazione.DAO;


import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class SkillDAOTest {

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String insertUtente="INSERT INTO utenti (IdUtente,Nome, Cognome, Pwd, Mail, Ruolo) VALUES (100,'Mario', 'Rossi', 'lol', 'mario@gmail.com', 2)";
        String insertDip="INSERT INTO dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita) VALUES (100,'Fisciano', '118', true, 2000)";
        String insertUtente2="INSERT INTO utenti (IdUtente,Nome, Cognome, Pwd, Mail, Ruolo) VALUES (101,'Lola', 'Viola', 'lol', 'lola@gmail.com', 2)";
        String insertDip2="INSERT INTO dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita) VALUES (101,'Arzano', '811', true, 2000)";
        String insertSkill="INSERT INTO skill (IdSkill,NomeSkill, DescrizioneSkill) VALUES (3,'TestNome', 'TestDesc')";

        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertUtente);
        preparedStatement.executeUpdate(insertUtente);
        preparedStatement.executeUpdate(insertDip);
        preparedStatement.executeUpdate(insertSkill);
        preparedStatement.executeUpdate(insertUtente2);
        preparedStatement.executeUpdate(insertDip2);

    }

    @AfterAll
    public static void finish() throws SQLException {
        String deleteUtente = "Delete from utenti where IdUtente>4";
        String deleteDip = "Delete from dipendenti where IdDipendente>2";
        String deleteSkill = "Delete from skill where IdSkill>2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteUtente);
        statement.executeUpdate(deleteUtente);
        statement.executeUpdate(deleteDip);
        statement.executeUpdate(deleteSkill);
        Const.nomeDB = Const.NOME_DB_MANAGER;
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

    @Test //pass
    @Order(3)
    public void doRetrieveByNamePass() throws SQLException {
        String nomeSkill = "HTML";
        assertNotNull(SkillDAO.recuperaSkillByNome(nomeSkill));
    }

    @Test
    @Order(4)
    public void doRetrieveByNameNull() throws SQLException {
        String nomeSkill = null;
        assertNull(SkillDAO.recuperaSkillByNome(nomeSkill));
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

    @Test //pass
    @Order(8)
    public void saveSkillDipPass() throws SQLException {
        assertTrue(SkillDAO.salvaSkillDipendente(1, 100, 4));
    }

    @Test
    @Order(9)
    public void retrieveLastIdPass() throws SQLException {
        assertNotNull(SkillDAO.recuperaUltimaSkill());
    }


    @Test
    @Order(10)//dip non ha skill
    public void doRetrieveSkillByIdDipendenteFail() throws SQLException {
    assertNull(SkillDAO.recuperoSkillsByIdDipendente(101));

    }

    @Test // idDip<1
    @Order(11)
     public void doRetrieveSkillByIdDipendenteFail1() throws SQLException {
        assertNull(SkillDAO.recuperoSkillsByIdDipendente(0));
    }

    @Test
    @Order(12)
    public void doRetrieveSkillByIdDipendentePass() throws SQLException {
        assertNotNull(SkillDAO.recuperoSkillsByIdDipendente(2));
    }
}
