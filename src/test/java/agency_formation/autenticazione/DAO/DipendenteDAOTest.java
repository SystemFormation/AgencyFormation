package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class DipendenteDAOTest {
    private DipendenteDAO dao = new DipendenteDAO();

    @Test
    public void doSaveEmployeeFail() throws SQLException {
        Dipendente dip = null;
        assertFalse(dao.doSaveEmploye(dip));
    }

    @Test
    public void doSaveEmployeeOk() throws SQLException {
        Dipendente dip = new Dipendente(4, 1, 2000, "Fisciano", "118", true);
        assertTrue(dao.doSaveEmploye(dip));
    }

    @Test
    public void doRetrieveByIdLessZero() throws SQLException {
        int id = -1;
        assertNull(dao.doRetrieveById(id));
    }

    @Test
    public void doRetrieveByIdFail() throws SQLException {
        int id = 4845684; //queso id non esiste
        assertNull(dao.doRetrieveById(id));
    }


    @Test
    public void doRetrieveByIdPass() throws SQLException {
        int id = 4;
        Dipendente dip = dao.doRetrieveById(id);
        assertNotNull(dip);
    }

    @Test
    public void doRetrieveAllSizeLessOne() throws SQLException {
        assertNull(dao.doRetrieveAll());
    }

    @Test
    public void doRetrieveAllPass() throws SQLException {
        assertNotNull(dao.doRetrieveAll().size());
    }

    @Test
    public void updateStateFailIdLessOne() throws SQLException {
        int id = -1;
        assertFalse(dao.updateState(id, true));
    }


    @Test
    public void updateStatePass() throws SQLException {
        int id = 2;
        boolean ver = true;  //stato=1
        assertTrue(dao.updateState(id, ver));
    }

    //test per dim array minore di uno
    @Test
    public void doRetrieveByStateSizeLessOne() throws SQLException {
        assertNull(dao.doRetrieveByState(false));
    }

    @Test
    public void doRetrieveByStateSizeMoreZero() throws SQLException {
        assertNotNull(dao.doRetrieveByState(true));
    }

    // testa il retreve dei dipendenti con stato true
    @Test
    public void doRetrieveByStateTrueNotPass() throws SQLException {
        assertNull(dao.doRetrieveByState(true));


    }

    @Test
    public void doRetrieveByStateTruePass() throws SQLException {
        assertNotNull(dao.doRetrieveByState(true));
    }

    @Test
    public void doRetrieveByStateFalseNotPass() throws SQLException {
        assertNull(dao.doRetrieveByState(false));
    }

    @Test
    public void doRetrieveByStateFalsePass() throws SQLException {
        assertNotNull(dao.doRetrieveByState(false));
    }
    @Test
    public void updateDipTeamAndStateFail(){
        //TODO
    }
    @Test
    public void updateDipTeamAndStatePass(){
        //TODO
    }

}