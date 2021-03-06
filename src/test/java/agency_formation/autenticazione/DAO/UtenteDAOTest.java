package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAOImpl;
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
    private UtenteDAO dao = new UtenteDAOImpl();

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String candidatura="Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(1,'test','test','NonRevisionato','2022-01-10', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(candidatura);
        statement.executeUpdate(candidatura);
    }
    
    @AfterAll
    public static void finish() throws SQLException {
        String query= "Insert into utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(4,'Domenico','Pagliuca','lol','d.pagliuca@studenti.unisa.it',4)";
        String delete = "Delete from utenti where IdUtente>4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.executeUpdate(query);
        statement.executeUpdate(delete);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    @Order(1)
    public void salvataggioFail() throws SQLException {
        Utente user = null;
        assertFalse(dao.salvaUtente(user));
    }


    @Test
    @Order(2)
    public void salvataggioOK1() throws SQLException {
        Utente user = new Utente("Gennaro", "Cecco", "genny@libero.it", "lol", RuoliUtenti.CANDIDATO);
        assertTrue(dao.salvaUtente(user));
    }

    @Test
    @Order(3)
    public void salvataggioOK2() throws SQLException {
        Utente user = new Utente("Mario", "Rossi", "mario@libero.it", "lol", RuoliUtenti.DIPENDENTE);
        assertTrue(dao.salvaUtente(user));
    }

    @Test
    @Order(4)
    public void salvataggioOK3() throws SQLException {
        Utente user = new Utente("Paki", "Espo", "paki@libero.it", "lol", RuoliUtenti.HR);
        assertTrue(dao.salvaUtente(user));
    }

    @Test
    @Order(5)
    public void salvataggioOK4() throws SQLException {
        Utente user = new Utente("Manu", "Sola", "manu@libero.it", "lol", RuoliUtenti.TM);
        assertTrue(dao.salvaUtente(user));
    }

    @Test
    @Order(6)
    public void loginEmailNull() throws SQLException {
        String email = null;
        String pwd = "lol";
        assertNull(dao.login(email, pwd));
    }

    @Test
    @Order(7)
    public void loginPwdNull() throws SQLException {
        String email = "genny@libero.it";
        String pwd = null;
        assertNull(dao.login(email, pwd));
    }

    @Test
    @Order(8)
    public void loginEmailPwdNull() throws SQLException {
        String email = null;
        String pwd = null;
        assertNull(dao.login(email, pwd));
    }

    @Test
    @Order(9)
    public void loginPassCand() throws SQLException {
        String email = "genny@libero.it";
        String pwd = "lol";
        assertNotNull(dao.login(email,pwd));
    }

    @Test
    @Order(10)
    public void loginPassDip() throws SQLException {
        String email = "p.severino@studenti.unisa.it";
        String pwd = "lol";
        assertNotNull(dao.login(email,pwd));
    }

    @Test
    @Order(11)
    public void loginPassTM() throws SQLException {
        String email = "m.nocerino@studenti.unisa.it";
        String pwd = "lol";
        assertNotNull(dao.login(email,pwd));
    }

    @Test
    @Order(12)
    public void loginPassHR() throws SQLException {
        String email = "d.pagliuca@studenti.unisa.it";
        String pwd = "lol";
        assertNotNull(dao.login(email,pwd));
        String query= "Delete from utenti where IdUtente=4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
    }

    @Test
    @Order(13)
    public void loginFail() throws SQLException {
        String email = "genny158@gmail.it";
        String pwd = "lol";
        assertNull(dao.login(email, pwd));
    }

    @Test
    @Order(14) //pass
    public void retrieveCandidatiConCandidatura1() throws SQLException {
        assertNotNull(dao.doRetrieveCandidatoConCandidatura());
    }


    @Test
    @Order(15)
    public void recuperoCandidatiColloquio1() throws SQLException {
        String query = "update candidature set Stato='Rifiutata' where idCandidatura>=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(dao.recuperoCandidatiColloquio());
    }

    @Test
    @Order(16)
    public void recuperoCandidatiColloquio2() throws SQLException {
        String query = "update candidature set Stato='Accettata' where idCandidatura=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNotNull(dao.recuperoCandidatiColloquio());
    }

    @Test
    @Order(17)
    public void retrieveCandidatiConCandidatura2() throws SQLException{
        String query = "Delete from candidature where IdCandidatura>=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(dao.doRetrieveCandidatoConCandidatura());
    }


}
