package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

/*
 * Questa classe racchiude tutti i casi di test riguardante UtenteDAO
 */
public class UtenteDAOTest {








    @Test
    public void saveUserFail() throws SQLException {
        Utente user = null;
        assertFalse(UtenteDAO.salvaUtente(user));
    }

    @Test
    public void saveUserOK() throws SQLException {
        Utente user = new Utente("Gennaro", "Cecco", "genny@libero.it", "lol", RuoliUtenti.CANDIDATO);
        assertTrue(UtenteDAO.salvaUtente(user));
    }

    @Test
    public void loginEmailNull() throws SQLException {
        String email = null;
        String pwd = "lol";
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    public void loginPwdNull() throws SQLException {
        String email = "genny@libero.it";
        String pwd = null;
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    //da rivedere
    public void loginPass() throws SQLException {
        String email = "genny@libero.it";
        String pwd = "lol";
        assertNotNull(UtenteDAO.login(email,pwd));
    }

    @Test
    public void loginFail() throws SQLException {
        String email = "genny158@gmail.it";
        String pwd = "lol";
        assertNull(UtenteDAO.login(email, pwd));
    }

    @Test
    public void doRetrieveByIDLessZero() throws SQLException {
        int id = -1;
        assertNull(UtenteDAO.doRetrieveUtenteByID(id));
    }

    @Test
    public void doRetrieveByIDPass() throws SQLException {
        int id = 5;
        assertNotNull(UtenteDAO.doRetrieveUtenteByID(id));
    }
/*
    @Test
    public void retrieveByRuoloLessZero() throws SQLException {
        int ruolo = -1;
        assertNull(UtenteDAO.doRetrieveUserByRuolo(ruolo));
    }

    @Test
    public void retrieveByRuoloMoreFour() throws SQLException {
        int ruolo = 5;
        assertNull(UtenteDAO.doRetrieveUserByRuolo(ruolo));
    }
*/
    //non ci sarà errore perché l'Enum Ruoli non può errare
    @Test
    public void retrieveByRuoloSizeLessOne() throws SQLException {
        assertNull(UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.HR));
    }

    @Test
    public void retrieveByRuoloPass() throws SQLException {
        assertNotNull(UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.HR));

    }

    //Test doRetrieveCandidatoConCandidatura()
}
