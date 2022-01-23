package agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAOImpl;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static it.unisa.agency_formation.reclutamento.domain.StatiCandidatura.NonRevisionato;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReclutamentoManagerTest {
    private ReclutamentoManager reclutamento = new ReclutamentoManagerImpl();
    private CandidaturaDAO daoCandidatura = mock(CandidaturaDAOImpl.class);
    public void init(){
        ReclutamentoManagerImpl.daoCandidatura=daoCandidatura;
    }


    @Test //candidatura = null not pass
    public void caricaCandidatura2() throws SQLException {
        Candidatura cand = null;
        when(daoCandidatura.salvaCandidaturaSenzaDocumenti(cand)).thenReturn(false);
        init();
        assertFalse(reclutamento.caricaCandidatura(cand));
    }


    @Test //upload pass
    public void caricaCandidatura1() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(NonRevisionato);
        when(daoCandidatura.doRetrieveCandidaturaById(cand.getIdCandidato())).thenReturn(null);
        when(daoCandidatura.salvaCandidaturaSenzaDocumenti(cand)).thenReturn(true);
        init();
        assertTrue(reclutamento.caricaCandidatura(cand));
    }

    @Test
    public void caricaCandidatura3() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(NonRevisionato);
        when(daoCandidatura.doRetrieveCandidaturaById(cand.getIdCandidato())).thenReturn(cand);
        init();
        assertFalse(reclutamento.caricaCandidatura(cand));
    }


    @Test //candidatura  alreadyLoaded
    public void caricaCandidatura4() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setDocumentiAggiuntivi("test");
        cand.setStato(NonRevisionato);
        when(daoCandidatura.aggiungiDocumentiAggiuntivi(cand.getDocumentiAggiuntivi(),cand.getIdCandidato())).thenReturn(true);
        init();
        assertTrue(reclutamento.caricaCandidatura(cand));
    }

    @Test
    public void modificaStatoCandidaturaFail1() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(NonRevisionato);
        when(daoCandidatura.modificaStatoCandidatura(cand.getIdCandidato(), cand.getStato())).thenReturn(true);
        init();
        assertFalse(reclutamento.caricaCandidatura(cand));
    }

    @Test //id<1
    public void getCandidaturaByIdFail1() throws SQLException{
        int id = 0;
        Candidatura cand = null;
        when(daoCandidatura.doRetrieveCandidaturaById(id)).thenReturn(cand);
        init();
        assertNull(reclutamento.getCandidaturaById(id));
    }
    @Test //id di un candidato che non esiste
    public void getCandidaturaByIdFail2() throws SQLException{
        int id = 150;
        Candidatura cand = null;
        when(daoCandidatura.doRetrieveCandidaturaById(id)).thenReturn(cand);
        init();
        assertNull(reclutamento.getCandidaturaById(id));
    }

    @Test //non ci devono essere candidature
    public void getTutteCandidature1() throws SQLException{
        ArrayList<Candidatura> candidature =null;
        when(daoCandidatura.recuperaCandidature()).thenReturn(candidature);
        init();
        assertNull(reclutamento.getTutteCandidature());
    }
    @Test //non ci devono essere candidature
    public void getTutteCandidature2() throws SQLException{
        ArrayList<Candidatura> candidature = new ArrayList<>();
        when(daoCandidatura.recuperaCandidature()).thenReturn(candidature);
        init();
        assertNotNull(reclutamento.getTutteCandidature());
    }

    @Test
    public void ricandidatura() throws SQLException {
        Candidatura candidatura = new Candidatura();
        candidatura.setStato(StatiCandidatura.Rifiutata);
        candidatura.setIdCandidatura(1);
        candidatura.setIdCandidato(1);
        candidatura.setCurriculum("test");
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        candidatura.setDataCandidatura(data);
        when(daoCandidatura.rimuoviCandidatura(candidatura.getIdCandidato())).thenReturn(true);
        init();
        assertTrue(reclutamento.ricandidatura(candidatura.getIdCandidato()));
    }


    @Test
    public void modificaStatoCandidatura() throws SQLException{
        Candidatura cand = new Candidatura();
        cand.setStato(StatiCandidatura.NonRevisionato);
        cand.setIdCandidatura(1);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        when(daoCandidatura.modificaStatoCandidatura(cand.getIdCandidatura(), cand.getStato())).thenReturn(true);
        init();
        assertTrue(reclutamento.modificaStatoCandidatura(cand.getIdCandidatura(), cand.getStato()));
    }

    @Test //not pass , mi aspetto false
    public void accettaCandidatura() throws SQLException {
        int idCandidatura = 1;
        int idHR = 4;
        String data = ("2022-04-03");
        String tempo = ("17:30");
        String dataOra = data + " " + tempo;
        Date data1 = null;
        try {
            data1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataOra);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(data1.getTime());
        when(daoCandidatura.accettaCandidatura(idCandidatura, idHR, timestamp )).thenReturn(true);
        init();
        assertTrue(reclutamento.accettaCandidatura(idCandidatura, idHR, timestamp));
    }

    @Test //not pass , mi aspetto false
    public void rifiutaCandidatura() throws SQLException {
        int idCandidatura = 1;
        int idHR = 4;
        when(daoCandidatura.rifiutaCandidatura(idCandidatura, idHR )).thenReturn(true);
        init();
        assertTrue(reclutamento.rifiutaCandidatura(idCandidatura, idHR));
    }
}
