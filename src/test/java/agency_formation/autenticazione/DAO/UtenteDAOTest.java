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
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        statement.executeUpdate(delete);
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
    public void salvataggioOK() throws SQLException {
        Utente user = new Utente("Gennaro", "Cecco", "genny@libero.it", "lol", RuoliUtenti.CANDIDATO);
        assertTrue(UtenteDAO.salvaUtente(user));
    }

    @Test
    @Order(3)
    public void loginEmailNull() throws SQLException {
        String email = null;
        String pwd = "lol";
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    @Order(4)
    public void loginPwdNull() throws SQLException {
        String email = "genny@libero.it";
        String pwd = null;
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    @Order(5)
    public void loginPass() throws SQLException {
        String email = "genny@libero.it";
        String pwd = "lol";
        assertNotNull(UtenteDAO.login(email,pwd));
    }

    @Test
    @Order(6)
    public void loginFail() throws SQLException {
        String email = "genny158@gmail.it";
        String pwd = "lol";
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    @Order(7)
    public void doRetrieveByIDLessZero() throws SQLException {
        int id = -1;
        assertNull(UtenteDAO.doRetrieveUtenteByID(id));
    }

    @Test
    @Order(8)
    public void doRetrieveByIDPass() throws SQLException {
        int id = 3;
        assertNotNull(UtenteDAO.doRetrieveUtenteByID(id));
    }

    @Test
    @Order(9)
    public void retrieveByRuoloSizeLessOne() throws SQLException {
        assertNull(UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.HR));
    }

    @Test
    @Order(10)
    public void retrieveByRuoloPass() throws SQLException {
        assertNotNull(UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.CANDIDATO));

    }

    @Test
    @Order(11) //pass
    public void retrieveCandidatiConCandidatura1() throws SQLException {
        assertNotNull(UtenteDAO.doRetrieveCandidatoConCandidatura());
    }
    @Test
    @Order(12) //fail
    public void retrieveCandidatiConCandidatura2() throws SQLException{
        String query = "Delete from candidature where IdCandidatura=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(UtenteDAO.doRetrieveCandidatoConCandidatura());
    }
}
