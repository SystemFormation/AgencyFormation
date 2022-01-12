package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;

import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Questa classe racchiude tutti i casi di test riguardante UtenteDAO
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteDAOTest {

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query= "Delete from utenti where IdUtente=4";
        String candidatura="Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(1,'test','test','NonRevisionato','2022-01-10', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        statement.executeUpdate(candidatura);
    }
    @AfterAll
    public static void finish() throws SQLException {
        String query= "Insert into utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(4,'Domenico','Pagliuca','lol','d.pagliuca@studenti.unisa.it',4)";
        String delete = "Delete from utenti where IdUtente>4";
        String deleteCand = "Delete from candidature where IdCandidatura>=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.executeUpdate(query);
        statement.executeUpdate(delete);
        statement.executeUpdate(deleteCand);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    @Order(1)
    public void salvataggioFail() throws SQLException {
        Utente user = null;
        assertFalse(UtenteDAO.salvaUtente(user));
    }


    @Test
    @Order(2)
    public void salvataggioOK1() throws SQLException {
        Utente user = new Utente("Gennaro", "Cecco", "genny@libero.it", "lol", RuoliUtenti.CANDIDATO);
        assertTrue(UtenteDAO.salvaUtente(user));
    }

    @Test
    @Order(3)
    public void salvataggioOK2() throws SQLException {
        Utente user = new Utente("Gennaro", "Cecco", "genny@libero.it", "lol", RuoliUtenti.DIPENDENTE);
        assertTrue(UtenteDAO.salvaUtente(user));
    }

    @Test
    @Order(4)
    public void salvataggioOK3() throws SQLException {
        Utente user = new Utente("Gennaro", "Cecco", "genny@libero.it", "lol", RuoliUtenti.HR);
        assertTrue(UtenteDAO.salvaUtente(user));
    }

    @Test
    @Order(5)
    public void salvataggioOK4() throws SQLException {
        Utente user = new Utente("Gennaro", "Cecco", "genny@libero.it", "lol", RuoliUtenti.TM);
        assertTrue(UtenteDAO.salvaUtente(user));
    }

    @Test
    @Order(6)
    public void loginEmailNull() throws SQLException {
        String email = null;
        String pwd = "lol";
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    @Order(7)
    public void loginPwdNull() throws SQLException {
        String email = "genny@libero.it";
        String pwd = null;
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    @Order(7)
    public void loginEmailPwdNull() throws SQLException {
        String email = null;
        String pwd = null;
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    @Order(8)
    public void loginPass() throws SQLException {
        String email = "genny@libero.it";
        String pwd = "lol";
        assertNotNull(UtenteDAO.login(email,pwd));
    }

    @Test
    @Order(9)
    public void loginFail() throws SQLException {
        String email = "genny158@gmail.it";
        String pwd = "lol";
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    @Order(10)
    public void doRetrieveByIDLessZero() throws SQLException {
        int id = -1;
        assertNull(UtenteDAO.doRetrieveUtenteByID(id));
    }

    @Test
    @Order(11)
    public void doRetrieveByIDPass1() throws SQLException {
        int id = 3;
        assertNotNull(UtenteDAO.doRetrieveUtenteByID(id));
    }
    @Test
    @Order(12)
    public void doRetrieveByIDPass2() throws SQLException {
        int id = 1;
        assertNotNull(UtenteDAO.doRetrieveUtenteByID(id));
    }
    @Test
    @Order(13)
    public void doRetrieveByIDPass3() throws SQLException {
        int id = 2;
        assertNotNull(UtenteDAO.doRetrieveUtenteByID(id));
    }
    @Test
    @Order(14)
    public void doRetrieveByIDPass4() throws SQLException {
        String query= "Insert into utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(4,'Domenico','Pagliuca','lol','d.pagliuca@studenti.unisa.it',4)";
        String delete = "Delete from utenti where IdUtente=4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        int id = 4;
        assertNotNull(UtenteDAO.doRetrieveUtenteByID(id));
        Connection connection1 = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection1.prepareStatement(delete);
        statement1.executeUpdate();
    }
/*
    @Test
    @Order(15)
    public void retrieveByRuoloSizeLessOne1() throws SQLException {
        String query = "Delete from utenti where Ruolo=4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.HR));
    }
    @Test
    @Order(15)
    public void retrieveByRuoloSizeLessOne2() throws SQLException {
        String query = "Delete from utenti where Ruolo=4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.));
    }

    @Test
    @Order(16)
    public void retrieveByRuoloSizeLessOne3() throws SQLException {
        assertNull(UtenteDAO.doRetrieveUtenteByRuolo(null));
    }

    @Test
    @Order(17)
    public void retrieveByRuoloPass() throws SQLException {
        assertNotNull(UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.CANDIDATO));

    }
*/
    @Test
    @Order(18) //pass
    public void retrieveCandidatiConCandidatura1() throws SQLException {
        assertNotNull(UtenteDAO.doRetrieveCandidatoConCandidatura());
    }


    @Test
    @Order(19)//not pass
    public void recuperoCandidatiColloquio1() throws SQLException {
        assertNull(UtenteDAO.recuperoCandidatiColloquio());
    }

    @Test
    @Order(20)//pass
    public void recuperoCandidatiColloquio2() throws SQLException {
        String query = "update candidature set Stato='Accettata' where idCandidatura=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNotNull(UtenteDAO.recuperoCandidatiColloquio());
    }

    @Test
    @Order(21) //fail
    public void retrieveCandidatiConCandidatura2() throws SQLException{
        String query = "Delete from candidature where IdCandidatura>=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(UtenteDAO.doRetrieveCandidatoConCandidatura());
    }


}
