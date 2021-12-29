package agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

//Questa classe testa i metodi della classe AutenticazioneManager Impl
public class AutenticazioneManagerTest {
    UtenteDAO dao = Mockito.mock(UtenteDAO.class);
    AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl(dao);

    @Test
    public void registrationFail() throws SQLException {
        Utente user = new Utente("Francesco","Cecco","fra@gmail.com","lol",1);
        Mockito.when(dao.doSaveUser(user)).thenReturn(false);
        assertFalse(aut.registration(user));
    }
    @Test
    public void registrationPass() throws SQLException {
        Utente user = new Utente("Francesco","Cecco","fra@gmail.com","lol",1);
        Mockito.when(dao.doSaveUser(user)).thenReturn(true);
        assertTrue(aut.registration(user));
    }
    @Test
    public void loginFail() throws SQLException {
        String email = "manuel@gmail.com";
        String pwd = "lol";
        Mockito.when(dao.login(email,pwd)).thenReturn(null);
        assertNull(aut.login(email,pwd));
    }
    @Test
    public void loginPass() throws SQLException{
        Utente user = new Utente("Francesco","Cecco","fra@gmail.com","lol",1);
        String email = "fra@gmail.com";
        String pwd = "lol";
        Mockito.when(dao.login(email,pwd)).thenReturn(user);
        assertNotNull(aut.login("fra@gmail.com","lol"));
    }
    @Test
    public void getAllDataFail() throws SQLException{
        Utente user = new Utente("Francesco","Cecco","fra@gmail.com","lol",1);
        int id = 3;
        Mockito.when(dao.doRetrieveByID(id)).thenReturn(null);
        assertNull(aut.getAllData(id));
    }
    @Test
    public void getAllDataPass() throws SQLException{
        Utente user = new Utente("Francesco","Cecco","fra@gmail.com","lol",1);
        int id = 3;
        Mockito.when(dao.doRetrieveByID(3)).thenReturn(user);
        assertNotNull(aut.getAllData(id));
    }

}
