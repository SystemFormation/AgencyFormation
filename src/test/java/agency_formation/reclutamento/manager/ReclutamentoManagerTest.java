package agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
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
        cand.setCurriculum("test");
        cand.setStato(StatiCandidatura.NonRevisionato);
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doSaveCandidaturaWithoutDocument(cand)).thenReturn(true);
        }
        assertTrue(reclutamento.uploadCandidature(cand));

    }

    @Test //candidatura = null not pass
    public void uploadCandidatura2() throws SQLException {
        Candidatura cand = null;
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doSaveCandidaturaWithoutDocument(cand)).thenReturn(false);
        }
        assertFalse(reclutamento.uploadCandidature(cand));
    }

    @Test //candidatura  alreadyLoaded non caricata
    public void uploadCandidatura3() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(StatiCandidatura.NonRevisionato);
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doRetrieveById(cand.getIdCandidato())).thenReturn(null);
        }
        assertTrue(reclutamento.uploadCandidature(cand));

    }
    @Test //candidatura  alreadyLoaded
    public void uploadCandidatura4() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(StatiCandidatura.NonRevisionato);
        try (MockedStatic mocked = mockStatic(CandidaturaDAO.class)) {
            mocked.when(() -> CandidaturaDAO.doRetrieveById(cand.getIdCandidato())).thenReturn(cand);
        }
        assertFalse(reclutamento.uploadCandidature(cand));
    }

    @Test//document = null
    public void uploadDocument1() throws SQLException{

    }

    @Test//pass Attenzione(crea la candidatura con i documenti aggiuntivi)
    public void uloadDocument3() throws SQLException{

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
    @Test //pass
    public void getCandidaturaById3() throws SQLException{

    }

    @Test //non ci devono essere candidature
    public void getAll1() throws SQLException{

    }
    @Test //pass
    public void getAll2() throws SQLException{

    }

    @Test //not pass , mi aspetto false
    public void acceptCandidatura1(){

    }
    @Test //pass
    public void acceptCandidatura2(){

    }

    @Test//not pass mi aspetto false
    public void rejectCandidatura1(){

    }

    @Test//pass
    public void rejectCandidatura2(){

    }



}
