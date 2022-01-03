package agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.SQLException;

//Questa classe testa i metodi della classe AutenticazioneManager Impl

public class AutenticazioneManagerTest {
    AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl();

    @Test
    public void registrationFail() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", 1);

        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.doSaveUser(user)).thenReturn(false);
        }
        assertFalse(aut.registration(user));
    }

    @Test
    public void registrationPass() throws SQLException {
        Utente user = new Utente("Francescoz", "Ceccoz", "frza@gmail.com", "lol", 1);
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.doSaveUser(user)).thenReturn(true);
        }

        assertTrue(aut.registration(user));
    }

    @Test
    public void loginFail() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", 1);
        String email = "manuel@gmail.com";
        String pwd = "lol";
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.login(email,pwd)).thenReturn(user);
        }
        assertNull(aut.login(email, pwd));
    }

    @Test
    public void loginPass() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", 1);
        String email = "fra@gmail.com";
        String pwd = "lol";
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.login(email,pwd)).thenReturn(user);
        }
        assertNotNull(aut.login(email, pwd));
    }

    @Test
    public void getAllDataFail() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", 1);
        int id = -1;
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.doRetrieveByID(id)).thenReturn(user);
        }
        assertNull(aut.getAllData(id));
    }

    @Test
    public void getAllDataPass() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", 1);
        int id = 5;
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.doRetrieveByID(id)).thenReturn(user);
        }
        assertNotNull(aut.getAllData(id));
    }

}
