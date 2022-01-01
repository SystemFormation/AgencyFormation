package agency_formation.reclutamento.DAO;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CandidaturaDAOTest {
    private CandidaturaDAO dao = new CandidaturaDAO();


    @Test //cand == null
    public void saveCand1() throws SQLException {
        Candidatura cand = null;
        assertFalse(dao.doSaveCandidaturaWithoutDocument(cand));
    }
    @Test
    public void savecand2() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCv("test");
        cand.setStato("test");
        assertTrue(dao.doSaveCandidaturaWithoutDocument(cand));
    }
    @Test//document == null
    public void addDocument1() throws SQLException {
        String document = null;
        int id = 1;
        assertFalse(dao.addDocument(document,id));
    }
    @Test//id == -1
    public void addDocument2() throws SQLException {
        String document = "Test";
        int id = -1;
        assertFalse(dao.addDocument(document,id));
    }
    @Test
    public void addDocument3() throws SQLException {
        String document = "Test";
        int id = 1;
        assertTrue(dao.addDocument(document,id));
    }
}
