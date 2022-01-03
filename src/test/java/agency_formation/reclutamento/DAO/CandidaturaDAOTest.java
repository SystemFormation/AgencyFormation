package agency_formation.reclutamento.DAO;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CandidaturaDAOTest {

    @Test //cand = null
    public void saveCand1() throws SQLException {
        Candidatura cand = null;
        assertFalse(CandidaturaDAO.doSaveCandidaturaWithoutDocument(cand));
    }
    @Test //
    public void savecand2() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCv("test");
        cand.setStato("test");
        assertTrue(CandidaturaDAO.doSaveCandidaturaWithoutDocument(cand));
    }

    @Test//document = null
    public void addDocument1() throws SQLException {
        String document = null;
        int id = 1;
        assertFalse(CandidaturaDAO.addDocument(document,id));
    }
    @Test//id = -1
    public void addDocument2() throws SQLException {
        String document = "Test";
        int id = -1;
        assertFalse(CandidaturaDAO.addDocument(document,id));
    }
    @Test
    public void addDocument3() throws SQLException {
        String document = "Test";
        int id = 1;
        assertTrue(CandidaturaDAO.addDocument(document,id));
    }

    @Test //id candidato = -1
    public void doRetrieveById1() throws SQLException {
        int idCandidato = -1;
        assertNull(CandidaturaDAO.doRetrieveById(idCandidato));
    }
    @Test //id candidato = 1
    public void doRetrieveById2() throws SQLException {
        int idCandidato = 1;
        assertNotNull(CandidaturaDAO.doRetrieveById(idCandidato));
    }

    @Test //stato = null
    public void doRetrieveByState1() throws SQLException {
        String stato = null;
        assertNull(CandidaturaDAO.doRetrieveByState(stato));
    }
    @Test //stato = prova(non esiste)
    public void doRetrieveByState2() throws SQLException {
        String stato = "prova";
        assertNull(CandidaturaDAO.doRetrieveByState(stato));
    }

    @Test //id candidatura = -1 stato = null
    public void updateState1() throws SQLException {
        int idCandidatura = -1;
        String stato = null;
        assertFalse(CandidaturaDAO.updateState(idCandidatura, stato));
    }
    @Test //id candidatura =  stato = null
    public void updateState2() throws SQLException {
        int idCandidatura = 1;
        String stato = null;
        assertFalse(CandidaturaDAO.updateState(idCandidatura, stato));
    }
    @Test //id candidatura = 2(questo id esiste) stato = disponibile
    public void updateState3() throws SQLException {
        int idCandidatura = 2;
        String stato = "disponibile";
        assertTrue(CandidaturaDAO.updateState(idCandidatura, stato));
    }

    @Test //idCandidatura = -1
    public void doRemoveCandidatura1() throws SQLException {
        int idCandidatura = -1;
        assertFalse(CandidaturaDAO.doRemoveCandidatura(idCandidatura));
    }
    @Test //idCandidatura = 2
    public void doRemoveCandidatura2() throws SQLException {
        int idCandidatura = 2;
        assertTrue(CandidaturaDAO.doRemoveCandidatura(idCandidatura));
    }
}
