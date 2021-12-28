package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;


public class DipendenteDAOTest {
    private DipendenteDAO dao = new DipendenteDAO();

    @Test
    public void doSaveEmployeeFail() throws SQLException {
        Dipendente dip = null;
        Assert.assertFalse(dao.doSaveEmploye(dip));
    }

    @Test
    public void doSaveEmployeeOk() throws SQLException {
        Dipendente dip = new Dipendente(6, 1, 2000, "Fisciano", "118", true);
        Assert.assertTrue(dao.doSaveEmploye(dip));
    }

    @Test
    public void doRetrieveByIdLessZero() throws SQLException {
        int id = -1;
        Assert.assertNull(dao.doRetrieveById(id));
    }

    @Test
    public void doRetrieveByIdFail() throws SQLException {
        int id=4845684; //queso id non esiste
        Assert.assertNull(dao.doRetrieveById(id));
    }


    @Test
    public void doRetrieveByIdPass() throws SQLException {
        int id = 4;
        Dipendente dip = dao.doRetrieveById(id);
        Assert.assertNotNull(dip);
    }

    @Test
    public void doRetrieveAllSizeLessOne() throws SQLException {
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        DipendenteDAO dao = Mockito.mock(DipendenteDAO.class);
        Mockito.when(dao.doRetrieveAll()).thenReturn(dipendenti);
        Assert.assertEquals(0,dao.doRetrieveAll().size());
    }
    @Test
    public void doRetrieveAllPass() throws SQLException {
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        Dipendente dip1 = new Dipendente(1,1,2000,"Via Armando Diaz","0817776625",false);
        Dipendente dip2 = new Dipendente(2,1,2000,"Via Armando Diaz","0817776625",false);
        Dipendente dip3 = new Dipendente(4,1,2000,"Via Armando Diaz","0817776625",false);
        dipendenti.add(dip1);
        dipendenti.add(dip2);
        dipendenti.add(dip3);
        DipendenteDAO dao = Mockito.mock(DipendenteDAO.class);
        Mockito.when(dao.doRetrieveAll()).thenReturn(dipendenti);
        Assert.assertEquals(3,dao.doRetrieveAll().size());
    }
    @Test
    public void updateStateFailIdLessOne() throws SQLException {
        int id = -1;
        Assert.assertFalse(dao.updateState(id,true));
    }


    @Test
    public void updateStatePass() throws SQLException {
        int id = 4;
        boolean ver = false;
        Assert.assertTrue(dao.updateState(id,ver));
    }
    //test per dim array minore di uno
    @Test
    public void doRetrieveByStateSizeLessOne()throws SQLException{
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        DipendenteDAO dao = Mockito.mock(DipendenteDAO.class);
        Mockito.when(dao.doRetrieveByState(true)).thenReturn(dipendenti);
        Assert.assertEquals(0,dao.doRetrieveByState(true).size());
    }
    @Test
    public void doRetrieveByStateSizeMoreOne()throws SQLException{
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        Dipendente dip1 = new Dipendente(1,1,2000,"Via Armando Diaz","0817776625",false);
        Dipendente dip2 = new Dipendente(2,1,2000,"Via Armando Diaz","0817776625",false);
        Dipendente dip3 = new Dipendente(4,1,2000,"Via Armando Diaz","0817776625",false);
        dipendenti.add(dip1);
        dipendenti.add(dip2);
        dipendenti.add(dip3);
        DipendenteDAO dao = Mockito.mock(DipendenteDAO.class);
        Mockito.when(dao.doRetrieveByState(true)).thenReturn(dipendenti);
        Assert.assertEquals(3,dao.doRetrieveByState(true).size());
    }
    // testa il retreve dei dipendenti con stato true
    @Test
    public void doRetrieveByStateTrueNotPass()throws SQLException{
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        DipendenteDAO dao = Mockito.mock(DipendenteDAO.class);
        Mockito.when(dao.doRetrieveByState(false)).thenReturn(dipendenti);
        Assert.assertEquals(0,dao.doRetrieveByState(false).size());


    }
    @Test
    public void doRetrieveByStateTruePass()throws SQLException{
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        Dipendente dip1 = new Dipendente(1,1,2000,"Via Armando Diaz","0817776625",true);
        Dipendente dip2 = new Dipendente(2,1,2000,"Via Armando Diaz","0817776625",true);
        Dipendente dip3 = new Dipendente(4,1,2000,"Via Armando Diaz","0817776625",true);
        dipendenti.add(dip1);
        dipendenti.add(dip2);
        dipendenti.add(dip3);
        DipendenteDAO dao = Mockito.mock(DipendenteDAO.class);
        Mockito.when(dao.doRetrieveByState(true)).thenReturn(dipendenti);
        Assert.assertEquals(3,dao.doRetrieveByState(true).size());
    }
    @Test
    public void doRetrieveByStateFalseNotPass()throws SQLException{
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        DipendenteDAO dao = Mockito.mock(DipendenteDAO.class);
        Mockito.when(dao.doRetrieveByState(true)).thenReturn(dipendenti);
        Assert.assertEquals(0,dao.doRetrieveByState(true).size());
    }
    @Test
    public void doRetrieveByStateFalsePass()throws SQLException{
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        Dipendente dip1 = new Dipendente(1,1,2000,"Via Armando Diaz","0817776625",false);
        Dipendente dip2 = new Dipendente(2,1,2000,"Via Armando Diaz","0817776625",false);
        Dipendente dip3 = new Dipendente(4,1,2000,"Via Armando Diaz","0817776625",false);
        dipendenti.add(dip1);
        dipendenti.add(dip2);
        dipendenti.add(dip3);
        DipendenteDAO dao = Mockito.mock(DipendenteDAO.class);
        Mockito.when(dao.doRetrieveByState(true)).thenReturn(dipendenti);
        Assert.assertEquals(3,dao.doRetrieveByState(true).size());
    }

}