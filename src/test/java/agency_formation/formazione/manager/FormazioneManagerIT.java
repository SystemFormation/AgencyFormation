package agency_formation.formazione.manager;

import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManager;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormazioneManagerIT {

    private FormazioneManager formazioneManager = new FormazioneManagerImpl();


    @BeforeAll
    public static void init()throws SQLException{
        Const.nomeDB = Const.NOME_DB_TEST;
        String query2 = "INSERT INTO team (IdTeam,NomeProgetto, NumeroDipendenti, NomeTeam, Descrizione, Competenza, IdTM) VALUES (400,'Alo', '8', 'GameProjectTest', 'GameTest', 'unityTest', 3)";
        String query3 = "INSERT INTO team (IdTeam,NomeProgetto, NumeroDipendenti, NomeTeam, Descrizione, Competenza, IdTM) VALUES (401,'AloDue', '8', 'GameProjectDue', 'GameDue', 'unityDue', 3)";
        String query4 = "INSERT INTO documenti (IdDocumento, MaterialeDiFormazione, IdHR, IdTeam) values (300, '//', 4, 401)";
        String insertUtente="INSERT INTO utenti (IdUtente,Nome, Cognome, Pwd, Mail, Ruolo) VALUES (600,'Giovanni', 'Rossi', 'lol', 'giovanni@gmail.com', 2)";
        String insertDip="INSERT INTO dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita) VALUES (600,'Mosca', '852', true, 2000)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query2);
        statement.executeUpdate(query2);
        statement.executeUpdate(query3);
        statement.executeUpdate(query4);
        statement.executeUpdate(insertUtente);
        statement.executeUpdate(insertDip);
    }

    @AfterAll
    public static void finish()throws SQLException{
        String delete1 = "DELETE FROM documenti WHERE IdDocumento>=1";
        String delete2 = "DELETE From team where IdTeam > 1";
        String deleteSkill = "Delete from skill where IdSkill>2";
        String deleteDip = "Delete from dipendenti where IdDipendente>2";
        String deleteUtente = "Delete from utenti where IdUtente>4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete1);
        statement.executeUpdate(delete1);
        statement.executeUpdate(delete2);
        statement.executeUpdate(deleteSkill);
        statement.executeUpdate(deleteDip);
        statement.executeUpdate(deleteUtente);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }


    @Test
    @Order(1)
    public void salvaDocumento1() throws SQLException {
        Documento documento = null;
        assertFalse(formazioneManager.salvaDocumento(documento));
    }
    @Test
    @Order(2)
    public void salvaDocumento2() throws SQLException {
        Documento documento = new Documento();
        documento.setMaterialeDiFormazione("fakePath");
        documento.setIdTeam(400);
        documento.setIdHR(4);
        assertTrue(formazioneManager.salvaDocumento(documento));
    }
    @Test
    @Order(3)
    public void salvaSkill1() throws SQLException {
        Skill skill = null;
        assertFalse(formazioneManager.aggiungiSkill(skill));
    }

    @Test
    @Order(4)
    public void salvaSkill2() throws SQLException {
        Skill skill = new Skill();
        skill.setNomeSkill("TesNomeSkill");
        skill.setDescrizioneSkill("TestDescrizione");
        assertTrue(formazioneManager.aggiungiSkill(skill));
    }
    @Test
    @Order(5)
    public void addSkillDipendente1() throws SQLException {
       int idSkill = -1;
       int idDip = 2;
       int skillLivello = 4;
        assertFalse(formazioneManager.addSkillDipendente(idSkill,idDip,skillLivello));
    }

    @Test
    @Order(6)
    public void addSkillDipendente2() throws SQLException {
        int idSkill = 100;
        int idDip = -2;
        int skillLivello = 4;
        assertFalse(formazioneManager.addSkillDipendente(idSkill,idDip,skillLivello));
    }

    @Test
    @Order(7)
    public void addSkillDipendente3() throws SQLException {
        int idSkill = 100;
        int idDip = 2;
        int skillLivello = 6;
        assertFalse(formazioneManager.addSkillDipendente(idSkill,idDip,skillLivello));
    }

    @Test
    @Order(8)
    public void addSkillDipendente4() throws SQLException {
        int idSkill = formazioneManager.getUltimaSkill();
        int idDip = 2;
        int skillLivello = 4;
        assertTrue(formazioneManager.addSkillDipendente(idSkill,idDip,skillLivello));
    }

    @Test
    @Order(9)
    public void getMaterialeByIdTeam1() throws SQLException {
        int idTeam = -2;
        assertNull(formazioneManager.getMaterialeByIdTeam(idTeam));
    }

    @Test
    @Order(10)
    public void getMaterialeByIdTeam2() throws SQLException {
        int idTeam = 2;
        assertNull(formazioneManager.getMaterialeByIdTeam(idTeam));
    }

    @Test
    @Order(11)
    public void getMaterialeByIdTeam3() throws SQLException {
        int idTeam = 401;
        assertNotNull(formazioneManager.getMaterialeByIdTeam(idTeam));
    }

    @Test
    @Order(12)
    public void recuperoSkillDipendente1() throws SQLException {
        int idDip = 2;
        assertNotNull(formazioneManager.recuperoSkillConIdDipendente(idDip));
    }
    @Test
    @Order(13)
    public void recuperoSkillDipendente2() throws SQLException {
        int idDip = 600;
        assertNull(formazioneManager.recuperoSkillConIdDipendente(idDip));
    }



}
