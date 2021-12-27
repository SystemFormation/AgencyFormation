package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
/*
* Questa classe racchiude tutti i casi di test riguardante UtenteDAO
*/
public class UtenteDAOTest {
    private UtenteDAO dao = Mockito.mock(UtenteDAO.class);
    @Test
    public void saveUserFail() throws SQLException {
        Utente user = null;
        Mockito.when(dao.doSaveUser(user)).thenReturn(false);
        Assert.assertFalse(dao.doSaveUser(user));
    }
    @Test
    public void saveUserOK() throws SQLException {
        Utente user = new Utente("Gennaro","Cecco","genny@libero.it","lol",1);
        Mockito.when(dao.doSaveUser(user)).thenReturn(true);
        Assert.assertTrue(dao.doSaveUser(user));
    }

    @Test
    public void loginEmailNull() throws SQLException{
        String email = null;
        String pwd = "lol";
        Mockito.when(dao.login(email,pwd)).thenReturn(null);
        Assert.assertNull(dao.login(email,pwd));
    }
    @Test
    public void loginPwdNull() throws SQLException{
        String email = "genny@libero.it";
        String pwd = null;
        Mockito.when(dao.login(email,pwd)).thenReturn(null);
        Assert.assertNull(dao.login(email,pwd));
    }

    @Test
    public void loginPass() throws SQLException{
        Utente user = new Utente("Gennaro","Cecco","genny@libero.it","lol",1);
        dao.doSaveUser(user);
        String email = "genny@libero.it";
        String pwd = "lol";
        Mockito.when(dao.login(email,pwd)).thenReturn(user);
        Assert.assertNotNull(dao.login(email,pwd));
    }

    @Test
    public void loginFail() throws SQLException{
        String email = "genny@libero.it";
        String pwd = "lol";

        Mockito.when(dao.login(email,pwd)).thenReturn(null);
        Assert.assertNull(dao.login(email,pwd));
    }

    @Test
    public void doRetrieveByIDLessZero()throws SQLException{
        int id = -1;
        Mockito.when(dao.doRetrieveByID(id)).thenReturn(null);
        Assert.assertNull(dao.doRetrieveByID(id));
    }
    @Test
    public void doRetrieveByIDFail()throws SQLException{
        int id = 5;
        Mockito.when(dao.doRetrieveByID(id)).thenReturn(null);
        Assert.assertNull(dao.doRetrieveByID(id));
    }
    @Test
    public void doRetrieveByIDPass()throws SQLException{
        int id = 5;
        Utente user = new Utente("Gennaro","Cecco","genny@libero.it","lol",1);
        dao.doSaveUser(user);
        Mockito.when(dao.doRetrieveByID(id)).thenReturn(user);
        Assert.assertNotNull(dao.doRetrieveByID(id));
    }

}
