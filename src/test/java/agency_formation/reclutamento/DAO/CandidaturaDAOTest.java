package agency_formation.reclutamento.DAO;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CandidaturaDAOTest {

    @Test //cand = null
    public void saveCandFail() throws SQLException {
        Candidatura cand = null;
        assertFalse(CandidaturaDAO.doSaveCandidaturaWithoutDocument(cand));
    }

    @Test //salva il candidato
    public void savecandOk() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCv("test");
        cand.setStato("test");
        assertTrue(CandidaturaDAO.doSaveCandidaturaWithoutDocument(cand));
    }

    @Test //document = null
    public void addDocumentFail1() throws SQLException {
        String document = null;
        int idUtente = 1;
        assertFalse(CandidaturaDAO.addDocument(document, idUtente));
    }

    @Test //id = -1
    public void addDocumentFail2() throws SQLException {
        String document = "Test";
        int idUtente = -1;
        assertFalse(CandidaturaDAO.addDocument(document, idUtente));
    }

    @Test //aggiunge il document Test con id 1
    public void addDocumentOk() throws SQLException {
        String document = "Test";
        int idUtente = 1;
        assertTrue(CandidaturaDAO.addDocument(document, idUtente));
    }

    @Test //id candidato = -1
    public void doRetrieveByIdFail() throws SQLException {
        int idCandidato = -1;
        assertNull(CandidaturaDAO.doRetrieveById(idCandidato));
    }

    @Test //id candidato = 1
    public void doRetrieveByIdOk() throws SQLException {
        int idCandidato = 1;
        assertNotNull(CandidaturaDAO.doRetrieveById(idCandidato));
    }

    @Test //stato = null
    public void doRetrieveByStateFail1() throws SQLException {
        String stato = null;
        assertNull(CandidaturaDAO.doRetrieveByState(stato));
    }

    @Test //stato = prova(non esiste)
    public void doRetrieveByStateFail2() throws SQLException {
        String stato = "prova";
        assertNull(CandidaturaDAO.doRetrieveByState(stato));
    }

    @Test //stato = test(presente nel DB)
    public void doRetrieveByStateOk() throws SQLException {
        String stato = "test";
        assertNotNull(CandidaturaDAO.doRetrieveByState(stato));
    }

    @Test //id candidatura = -1 stato = null
    public void updateStateFail1() throws SQLException {
        int idCandidatura = -1;
        String stato = null;
        assertFalse(CandidaturaDAO.updateState(idCandidatura, stato));
    }

    @Test //id candidatura =  stato = null
    public void updateStateFail2() throws SQLException {
        int idCandidatura = 1;
        String stato = null;
        assertFalse(CandidaturaDAO.updateState(idCandidatura, stato));
    }

    @Test //id candidatura = 2(questo id esiste) stato = disponibile
    public void updateStateOk() throws SQLException {
        int idCandidatura = 2;
        String stato = "disponibile";
        assertTrue(CandidaturaDAO.updateState(idCandidatura, stato));
    }

    @Test //idCandidatura = -1
    public void doRemoveCandidaturaFail() throws SQLException {
        int idCandidatura = -1;
        assertFalse(CandidaturaDAO.doRemoveCandidatura(idCandidatura));
    }

    @Test //idCandidatura = 2
    public void doRemoveCandidaturaOk() throws SQLException {
        int idCandidatura = 2;
        assertTrue(CandidaturaDAO.doRemoveCandidatura(idCandidatura));
    }
}
