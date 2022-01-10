package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;

import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class DipendenteDAOTest {
    @Test
    public void doSaveEmployeeFail() throws SQLException {
        Dipendente dip = null;
        assertFalse(DipendenteDAO.salvaDipendente(dip));
    }

    @Test
    public void doSaveEmployeeOk() throws SQLException {
        Utente user = UtenteDAO.doRetrieveUtenteByID(2);
        assertNotNull(UtenteDAO.doRetrieveUtenteByID(2));
        Dipendente dip = new Dipendente();
        dip.setIdDipendente(user.getId());
        dip.setStato(StatiDipendenti.DISPONIBILE);
        dip.setResidenza("Fisciano");
        dip.setTelefono("118");
        assertTrue(DipendenteDAO.salvaDipendente(dip));
    }

    @Test
    public void doRetrieveByIdLessZero() throws SQLException {
        int id = -1;
        assertNull(DipendenteDAO.doRetrieveDipendenteById(id));
    }

    @Test
    public void doRetrieveByIdFail() throws SQLException {
        int id = 4845684; //queso id non esiste
        assertNull(DipendenteDAO.doRetrieveDipendenteById(id));
    }


    @Test
    public void doRetrieveByIdPass() throws SQLException {
        int id = 2;
        Dipendente dip = DipendenteDAO.doRetrieveDipendenteById(id);
        assertNotNull(dip);
    }
//non funziona
    @Test
    public void doRetrieveAllSizeLessOne() throws SQLException {
        assertNull(DipendenteDAO.recuperaDipendenti());
    }

    @Test
    public void doRetrieveAllPass() throws SQLException {
        assertNotNull(DipendenteDAO.recuperaDipendenti().size());
    }

    @Test
    public void updateStateFailIdLessOne() throws SQLException {
        int id = -1;
        assertFalse(DipendenteDAO.modificaStatoDipendente(id, StatiDipendenti.DISPONIBILE));
    }


    @Test
    public void updateStatePass() throws SQLException {
        int id = 2;
        assertTrue(DipendenteDAO.modificaStatoDipendente(id, StatiDipendenti.DISPONIBILE));
    }

    //test per dim array minore di uno
    @Test
    public void doRetrieveByStateSizeLessOne() throws SQLException {
        assertNull(DipendenteDAO.recuperaByStato(StatiDipendenti.OCCUPATO));
    }
//non funziona
    @Test
    public void doRetrieveByStateSizeMoreZero() throws SQLException {
        assertNotNull(DipendenteDAO.recuperaByStato(StatiDipendenti.DISPONIBILE));
    }

    // testa il retreve dei dipendenti con stato true
    @Test
    public void doRetrieveByStateTrueNotPass() throws SQLException {
        assertNull(DipendenteDAO.recuperaByStato(StatiDipendenti.DISPONIBILE));

    }
//non funziona- riempire il db
    @Test
    public void doRetrieveByStateTruePass() throws SQLException {
        assertNotNull(DipendenteDAO.recuperaByStato(StatiDipendenti.DISPONIBILE));
    }

    @Test
    public void doRetrieveByStateFalseNotPass() throws SQLException {
        assertNull(DipendenteDAO.recuperaByStato(StatiDipendenti.DISPONIBILE));
    }
//non funziona
    @Test
    public void doRetrieveByStateFalsePass() throws SQLException {
        assertNotNull(DipendenteDAO.recuperaByStato(StatiDipendenti.OCCUPATO));
    }


    @Test//not pass with id<1
    public void updateRole1() {

    }

    @Test//not pass because id doesn't exists
    public void updateRole2() {

    }
    @Test//pass
    public void updateRole3() {

    }

    @Test //not pass because idDip<1
    public void updateDipTeamAndState1() {

    }
    @Test //not pass because idTeam<1
    public void updateDipTeamAndState2() {

    }
    @Test //not pass because idDip doesn't exists
    public void updateDipTeamAndState3() {

    }
    @Test //not pass because idTeam doesn't exists
    public void updateDipTeamAndState4() {

    }
    @Test //pass
    public void updateDipTeamAndState5() {

    }
}