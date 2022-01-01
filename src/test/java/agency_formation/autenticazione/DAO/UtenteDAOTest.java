package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

/*
 * Questa classe racchiude tutti i casi di test riguardante UtenteDAO
 */
public class UtenteDAOTest {
    private UtenteDAO dao = new UtenteDAO();

    @Test
    public void saveUserFail() throws SQLException {
        Utente user = null;
        assertFalse(dao.doSaveUser(user));
    }

    @Test
    public void saveUserOK() throws SQLException {
        Utente user = new Utente("Gennaro", "Cecco", "genny@libero.it", "lol", 1);
        assertTrue(dao.doSaveUser(user));
    }

    @Test
    public void loginEmailNull() throws SQLException {
        String email = null;
        String pwd = "lol";
        assertNull(dao.login(email, pwd));
    }

    @Test
    public void loginPwdNull() throws SQLException {
        String email = "genny@libero.it";
        String pwd = null;
        assertNull(dao.login(email, pwd));
    }

    @Test
    //da rivedere
    public void loginPass() throws SQLException {
        String email = "genny@libero.it";
        String pwd = "lol";
        assertNotNull(dao.login(email,pwd));
    }

    @Test
    public void loginFail() throws SQLException {
        String email = "genny@gmail.it";
        String pwd = "lol";
        assertNull(dao.login(email, pwd));
    }

    @Test
    public void doRetrieveByIDLessZero() throws SQLException {
        int id = -1;
        assertNull(dao.doRetrieveByID(id));
    }

    @Test
    public void doRetrieveByIDPass() throws SQLException {
        int id = 5;
        assertNotNull(dao.doRetrieveByID(id));
    }

    @Test
    public void retrieveByRuoloLessZero() throws SQLException {
        int ruolo = -1;
        assertNull(dao.doRetrieveUserByRuolo(ruolo));
    }

    @Test
    public void retrieveByRuoloMoreFour() throws SQLException {
        int ruolo = 5;
        assertNull(dao.doRetrieveUserByRuolo(ruolo));
    }

    @Test
    public void retrieveByRuoloSizeLessOne() throws SQLException {
        assertNull(dao.doRetrieveUserByRuolo(4));

    }

    @Test
    public void retrieveByRuoloPass() throws SQLException {
        int ruolo = 1;
        assertNotNull(dao.doRetrieveUserByRuolo(ruolo));

    }
}
