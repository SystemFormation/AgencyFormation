package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class DipendenteDAOTest {
    @Test
    public void doSaveEmployeeFail() throws SQLException {
        Dipendente dip = null;
        assertFalse(DipendenteDAO.doSaveEmploye(dip));
    }

    @Test
    public void doSaveEmployeeOk() throws SQLException {
        Dipendente dip = new Dipendente(4, 1, 2000, "Fisciano", "118", true);
        assertTrue(DipendenteDAO.doSaveEmploye(dip));
    }

    @Test
    public void doRetrieveByIdLessZero() throws SQLException {
        int id = -1;
        assertNull(DipendenteDAO.doRetrieveById(id));
    }

    @Test
    public void doRetrieveByIdFail() throws SQLException {
        int id = 4845684; //queso id non esiste
        assertNull(DipendenteDAO.doRetrieveById(id));
    }


    @Test
    public void doRetrieveByIdPass() throws SQLException {
        int id = 4;
        Dipendente dip = DipendenteDAO.doRetrieveById(id);
        assertNotNull(dip);
    }

    @Test
    public void doRetrieveAllSizeLessOne() throws SQLException {
        assertNull(DipendenteDAO.doRetrieveAll());
    }

    @Test
    public void doRetrieveAllPass() throws SQLException {
        assertNotNull(DipendenteDAO.doRetrieveAll().size());
    }

    @Test
    public void updateStateFailIdLessOne() throws SQLException {
        int id = -1;
        assertFalse(DipendenteDAO.updateState(id, true));
    }


    @Test
    public void updateStatePass() throws SQLException {
        int id = 2;
        boolean ver = true;  //stato=1
        assertTrue(DipendenteDAO.updateState(id, ver));
    }

    //test per dim array minore di uno
    @Test
    public void doRetrieveByStateSizeLessOne() throws SQLException {
        assertNull(DipendenteDAO.doRetrieveByState(false));
    }

    @Test
    public void doRetrieveByStateSizeMoreZero() throws SQLException {
        assertNotNull(DipendenteDAO.doRetrieveByState(true));
    }

    // testa il retreve dei dipendenti con stato true
    @Test
    public void doRetrieveByStateTrueNotPass() throws SQLException {
        assertNull(DipendenteDAO.doRetrieveByState(true));


    }

    @Test
    public void doRetrieveByStateTruePass() throws SQLException {
        assertNotNull(DipendenteDAO.doRetrieveByState(true));
    }

    @Test
    public void doRetrieveByStateFalseNotPass() throws SQLException {
        assertNull(DipendenteDAO.doRetrieveByState(false));
    }

    @Test
    public void doRetrieveByStateFalsePass() throws SQLException {
        assertNotNull(DipendenteDAO.doRetrieveByState(false));
    }

    @Test
    public void updateDipTeamAndStateFail() {
        //TODO
    }

    @Test
    public void updateDipTeamAndStatePass() {
        //TODO
    }

}