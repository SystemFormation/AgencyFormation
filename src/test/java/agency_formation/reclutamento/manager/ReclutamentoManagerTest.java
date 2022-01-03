package agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class ReclutamentoManagerTest {
private ReclutamentoManager reclutamento = new ReclutamentoManagerImpl();

    @Test //upload pass
    public void uploadCandidatura1() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCv("test");
        cand.setStato("test");
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doSaveCandidaturaWithoutDocument(cand)).thenReturn(true);
        }
        assertTrue(reclutamento.uploadCandidatureWithoutDocument(cand));

    }

    @Test //candidatura = null not pass
    public void uploadCandidatura2() throws SQLException {
        Candidatura cand = null;
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doSaveCandidaturaWithoutDocument(cand)).thenReturn(false);
        }
        assertFalse(reclutamento.uploadCandidatureWithoutDocument(cand));
    }

    @Test //candidatura  alreadyLoaded
    public void uploadCandidatura3() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCv("test");
        cand.setStato("test");
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doRetrieveById(cand.getIdCandidato())).thenReturn(null);
        }
        assertFalse(reclutamento.uploadCandidatureWithoutDocument(cand));

    }
    @Test //candidatura  alreadyLoaded
    public void uploadCandidatura4() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCv("test");
        cand.setStato("test");
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doRetrieveById(cand.getIdCandidato())).thenReturn(cand);
        }
        assertFalse(reclutamento.uploadCandidatureWithoutDocument(cand));
    }

    @Test//document = null
    public void uloadDocument1() throws SQLException{
        String document=null;
        int id = 1;
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.addDocument(document,id)).thenReturn(false);
        }
        assertFalse(reclutamento.uploadDocument(document,id));
    }
    @Test//id < 1
    public void uloadDocument2() throws SQLException{
        String document="test";
        int id = 0;
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.addDocument(document,id)).thenReturn(false);
        }
        assertFalse(reclutamento.uploadDocument(document,id));
    }
    @Test//pass
    public void uloadDocument3() throws SQLException{
        String document="test";
        int id = 1;
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.addDocument(document,id)).thenReturn(true);
        }
        assertTrue(reclutamento.uploadDocument(document,id));
    }

    @Test //id<1
    public void getCandidaturaById1() throws SQLException{
        int id = 0;
        Candidatura cand = null;
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doRetrieveById(id)).thenReturn(cand);
        }
        assertNull(reclutamento.getCandidaturaById(id));
    }
    @Test //id di un candidato che non esiste
    public void getCandidaturaById2() throws SQLException{
        int id = 150;
        Candidatura cand = null;
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doRetrieveById(id)).thenReturn(cand);
        }
        assertNull(reclutamento.getCandidaturaById(id));
    }
    @Test //da controllare non va
    public void getCandidaturaById3() throws SQLException{
        int id = 1;
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCv("test");
        cand.setStato("test");
        cand.setIdCandidatura(1);
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doRetrieveById(id)).thenReturn(cand);
        }
        assertNotNull(reclutamento.getCandidaturaById(id));
    }

}
