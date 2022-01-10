package agency_formation.reclutamento.DAO;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CandidaturaDAOTest {

    @Test //cand = null
    public void saveCandidatureNoDocumentFail() throws SQLException {
        Candidatura cand = null;
        assertFalse(CandidaturaDAO.salvaCandidaturaSenzaDocumenti(cand));
    }

    @Test //salva il candidatura
    public void saveCandidatureNoDocumentOk() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(StatiCandidatura.NonRevisionato);
        assertTrue(CandidaturaDAO.salvaCandidaturaSenzaDocumenti(cand));
    }

    @Test //document = null
    public void addDocumentFail1() throws SQLException {
        String document = null;
        int idUtente = 1;
        assertFalse(CandidaturaDAO.aggiungiDocumentiAggiuntivi(document, idUtente));
    }

    @Test //id = -1
    public void addDocumentFail2() throws SQLException {
        String document = "Test";
        int idUtente = -1;
        assertFalse(CandidaturaDAO.aggiungiDocumentiAggiuntivi(document, idUtente));
    }

    @Test //aggiunge il document Test con id 1
    public void addDocumentOk() throws SQLException {
        String document = "Test";
        int idUtente = 1;
        assertTrue(CandidaturaDAO.aggiungiDocumentiAggiuntivi(document, idUtente));
    }

    @Test //id candidato = -1
    public void doRetrieveByIdFail() throws SQLException {
        int idCandidato = -1;
        assertNull(CandidaturaDAO.doRetrieveCandidaturaById(idCandidato));
    }

    @Test //id candidato = 1
    public void doRetrieveByIdOk() throws SQLException {
        int idCandidato = 1;
        assertNotNull(CandidaturaDAO.doRetrieveCandidaturaById(idCandidato));
    }


    @Test   /*non ci sono candidature con quello
            * stato(cancella tutte le candidatue
             con lo stato accettata dal db)*/
    public void doRetrieveByStateFail2() throws SQLException {
        assertNull(CandidaturaDAO.recuperaCandidatureByStato(StatiCandidatura.Accettata));
    }

    @Test //assicurati di avere candidature con stato non revisionato
    public void doRetrieveByStateOk() throws SQLException {
        assertNotNull(CandidaturaDAO.recuperaCandidatureByStato(StatiCandidatura.NonRevisionato));
    }

    @Test//non ci devono essere candidature
    public void doRetrieveAllFail() throws SQLException {
        assertNotNull(CandidaturaDAO.recuperaCandidature());
    }
    @Test//RIEMPIRE IL DB ci devono essere candidature con stato non revisionato
    public void doRetrieveAllOk() throws SQLException {
        assertNull(CandidaturaDAO.recuperaCandidature());
    }

    @Test //id candidatura = -1
    public void updateStateFail1() throws SQLException {
        int idCandidatura = -1;
        assertFalse(CandidaturaDAO.modificaStatoCandidatura(idCandidatura, StatiCandidatura.Accettata));
    }

    @Test //id candidatura = 2(questo id esiste)
    public void updateStateOk() throws SQLException {
        int idCandidatura = 2;
        assertTrue(CandidaturaDAO.modificaStatoCandidatura(idCandidatura, StatiCandidatura.Rifiutata));
    }

    @Test //idCandidatura = -1
    public void doRemoveCandidaturaFail() throws SQLException {
        int idCandidatura = -1;
        assertFalse(CandidaturaDAO.rimuoviCandidatura(idCandidatura));
    }

    @Test //idCandidatura = 2
    public void doRemoveCandidaturaOk() throws SQLException {
        int idCandidatura = 2;
        assertTrue(CandidaturaDAO.rimuoviCandidatura(idCandidatura));
    }
    @Test //idCandidatura<1
    public void doRejectFail1() throws SQLException {
        int idCandidatura=-1;
        int idHR=4;
        assertFalse(CandidaturaDAO.rifiutaCandidatura(idCandidatura,idHR));
    }
    @Test //idCandidatura non esiste NON FUNZIONA
    public void doRejectFail2() throws SQLException {
        int idCandidatura=432873892;
        int idHR=4;
        assertFalse(CandidaturaDAO.rifiutaCandidatura(idCandidatura,idHR));

    }
    @Test //idHR<1
    public void doRejectFail3() throws SQLException {
        int idCandidatura=1;
        int idHR=-4;
        assertFalse(CandidaturaDAO.rifiutaCandidatura(idCandidatura,idHR));

    }
    @Test //idHR non esiste NON FUNZIONA..MANCA CONTROLLO
    public void doRejectFail4() throws SQLException {
        int idCandidatura=1;
        int idHR=4326;
        assertFalse(CandidaturaDAO.rifiutaCandidatura(idCandidatura,idHR));
    }
    @Test //pass
    public void doRejectPass() throws SQLException {
        int idCandidatura=1;
        int idHR=4;
        assertTrue(CandidaturaDAO.rifiutaCandidatura(idCandidatura,idHR));
    }
/*----------------ERRORE CON LA DATA. CONTROLLARE COME PRENDERLA----
    @Test //idCandidatura<1
    public void accpetCandidaturaFail1() throws SQLException {
        int idCandidatura=-1;
        int idHR=4;
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        assertFalse(CandidaturaDAO.accettaCandidatura(idCandidatura,idHR,data));

    }
    @Test //idHR<1
    public void accpetCandidaturaFail2() throws SQLException {
        int idCandidatura=1;
        int idHR=-1;
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        assertFalse(CandidaturaDAO.accettaCandidatura(idCandidatura,idHR,data));

    }

    @Test //pass
    public void accpetCandidaturaPass(){
        int idCandidatura=1;
        int idHR=4;
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        assertFalse(CandidaturaDAO.accettaCandidatura(idCandidatura,idHR,(Timestamp) data));

    }

 */
}
