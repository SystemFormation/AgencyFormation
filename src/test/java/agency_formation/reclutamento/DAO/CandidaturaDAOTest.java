package agency_formation.reclutamento.DAO;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO.accettaCandidatura;
import static org.junit.jupiter.api.Assertions.*;

public class CandidaturaDAOTest {

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(1,'test','test','NonRevisionato','2022-01-10', 1);";
        String query2 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(2,'test','test','Accettata','2022-01-10', 1);";
        String query3 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(3,'test','test','Rifiutata','2022-01-10', 1)";
        String query4 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(4,'test','test','Assunzione','2022-01-10', 1)";
        String query5 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(5,'test','test','test','test', 1)";
        String query6 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(6,'test','test','test','test', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query6);
        statement.executeUpdate(query5);
        statement.executeUpdate(query4);
        statement.executeUpdate(query3);
        statement.executeUpdate(query2);
        statement.executeUpdate(query1);
    }

    @AfterAll
    public static void finish() throws SQLException {
        String delete1 = "Delete from candidature where IdCandidatura>=1";
        String delete2 = "Delete from utenti where IdUtente>4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete1);
        statement.executeUpdate(delete1);
        statement.executeUpdate(delete2);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    @Order(1) //cand = null
    public void salvaCandidaturaSenzaDocumentiFail() throws SQLException {
        Candidatura cand = null;
        assertFalse(CandidaturaDAO.salvaCandidaturaSenzaDocumenti(cand));
    }

    @Test
    @Order(2) //salva il candidatura
    public void salvaCandidaturaSenzaDocumentiOk1() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(StatiCandidatura.Accettata);
        assertTrue(CandidaturaDAO.salvaCandidaturaSenzaDocumenti(cand));
    }

    @Test
    @Order(3) //salva il candidatura
    public void salvaCandidaturaSenzaDocumentiOk2() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(StatiCandidatura.Rifiutata);
        assertTrue(CandidaturaDAO.salvaCandidaturaSenzaDocumenti(cand));
    }

    @Test
    @Order(4) //salva il candidatura
    public void salvaCandidaturaSenzaDocumentiOk3() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(StatiCandidatura.NonRevisionato);
        assertTrue(CandidaturaDAO.salvaCandidaturaSenzaDocumenti(cand));
    }

    @Test
    @Order(5) //salva il candidatura
    public void salvaCandidaturaSenzaDocumentiOk4() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(1);
        cand.setCurriculum("test");
        cand.setStato(StatiCandidatura.Assunzione);
        assertTrue(CandidaturaDAO.salvaCandidaturaSenzaDocumenti(cand));
    }

    @Test
    @Order(6) //document = null
    public void aggiungiDocumentiAggiuntiviFail1() throws SQLException {
        String document = null;
        int idUtente = 1;
        assertFalse(CandidaturaDAO.aggiungiDocumentiAggiuntivi(document, idUtente));
    }

    @Test
    @Order(7) //id = -1
    public void aggiungiDocumentiAggiuntiviFail2() throws SQLException {
        String document = "Test";
        int idUtente = -1;
        assertFalse(CandidaturaDAO.aggiungiDocumentiAggiuntivi(document, idUtente));
    }

    @Test
    @Order(8) //aggiunge il document Test con id 1
    public void aggiungiDocumentiAggiuntiviOk() throws SQLException {
        String document = "Test";
        int idUtente = 1;
        assertTrue(CandidaturaDAO.aggiungiDocumentiAggiuntivi(document, idUtente));
    }

    @Test
    @Order(9) //id candidato = -1
    public void retrieveCandidaturaByIdFail() throws SQLException {
        int idCandidato = -1;
        assertNull(CandidaturaDAO.doRetrieveCandidaturaById(idCandidato));
    }

    @Test
    @Order(10) //id candidato = 1
    public void retrieveCandidaturaByIdOk1() throws SQLException {
        int idCandidato = 1;
        assertNotNull(CandidaturaDAO.doRetrieveCandidaturaById(idCandidato));
    }

    @Test
    @Order(11) //id candidato = 1
    public void retrieveCandidaturaByIdOk2() throws SQLException {
        String query1 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(5,'test','test','Rifiutata','2022-01-10', 5);";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query1);
        int idCandidato = 5;
        assertNotNull(CandidaturaDAO.doRetrieveCandidaturaById(idCandidato));
    }

    @Test
    @Order(12) //id candidato = 1
    public void retrieveCandidaturaByIdOk3() throws SQLException {
        String query1 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(6,'test','test','Assunzione','2022-01-10', 6);";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query1);
        int idCandidato = 6;
        assertNotNull(CandidaturaDAO.doRetrieveCandidaturaById(idCandidato));
    }

    @Test
    @Order(13)
    public void recuperaCandidatureByStatoFail1() throws SQLException {
        StatiCandidatura stato = StatiCandidatura.Accettata;
        assertNotNull(CandidaturaDAO.recuperaCandidatureByStato(stato));
    }

    @Test
    @Order(14)
    public void recuperaCandidatureByStatoFail2() throws SQLException {
        StatiCandidatura stato = StatiCandidatura.Rifiutata;
        assertNotNull(CandidaturaDAO.recuperaCandidatureByStato(stato));
    }

    @Test
    @Order(15)
    public void recuperaCandidatureByStatoFail3() throws SQLException {
        StatiCandidatura stato = StatiCandidatura.NonRevisionato;
        assertNotNull(CandidaturaDAO.recuperaCandidatureByStato(stato));
    }

    @Test
    @Order(16)
    public void recuperaCandidatureByStatoFail4() throws SQLException {
        StatiCandidatura stato = StatiCandidatura.Assunzione;
        assertNotNull(CandidaturaDAO.recuperaCandidatureByStato(stato));
    }

    @Test
    @Order(17) //assicurati di avere candidature con stato non revisionato
    public void recuperaCandidatureByStatoFail6() throws SQLException {
        StatiCandidatura stato = null;
        assertNull(CandidaturaDAO.recuperaCandidatureByStato(stato));
    }

    @Test
    @Order(18) //id candidatura = -1
    public void modificaStatoCandidaturaFail() throws SQLException {
        int idCandidatura = -1;
        assertFalse(CandidaturaDAO.modificaStatoCandidatura(idCandidatura, StatiCandidatura.Accettata));
    }

    @Test
    @Order(19) //id candidatura = 2
    public void modificaStatoCandidaturaOk1() throws SQLException {
        int idCandidatura = 2;
        assertTrue(CandidaturaDAO.modificaStatoCandidatura(idCandidatura, StatiCandidatura.Rifiutata));
    }

    @Test
    @Order(20) //id candidatura = 2
    public void modificaStatoCandidaturaOk2() throws SQLException {
        int idCandidatura = 2;
        assertTrue(CandidaturaDAO.modificaStatoCandidatura(idCandidatura, StatiCandidatura.NonRevisionato));
    }

    @Test
    @Order(21) //id candidatura = 2
    public void modificaStatoCandidaturaOk3() throws SQLException {
        int idCandidatura = 2;
        assertTrue(CandidaturaDAO.modificaStatoCandidatura(idCandidatura, StatiCandidatura.Assunzione));
    }

    @Test
    @Order(22) //idCandidatura = -1
    public void rimuoviCandidaturaFail() throws SQLException {
        int idCandidatura = -1;
        assertFalse(CandidaturaDAO.rimuoviCandidatura(idCandidatura));
    }

    @Test
    @Order(23) //idCandidatura = 2
    public void rimuoviCandidaturaOk() throws SQLException {
        int idCandidatura = 2;
        assertTrue(CandidaturaDAO.rimuoviCandidatura(idCandidatura));
    }

    @Test
    @Order(24) //idCandidatura non esiste
    public void rifiutaCandidaturaFail1() throws SQLException {
        int idCandidatura = -1;
        int idHR = 4;
        assertFalse(CandidaturaDAO.rifiutaCandidatura(idCandidatura, idHR));
    }

    @Test
    @Order(25) //idHR<1
    public void rifiutaCandidaturaFail2() throws SQLException {
        int idCandidatura = 1;
        int idHR = -4;
        assertFalse(CandidaturaDAO.rifiutaCandidatura(idCandidatura, idHR));

    }

    @Test
    @Order(26) //Rifiuta con successo la candidatura
    public void rifiutaCandidaturaPass() throws SQLException {
        int idCandidatura = 1;
        int idHR = 4;
        assertTrue(CandidaturaDAO.rifiutaCandidatura(idCandidatura, idHR));
    }

    @Test
    @Order(27)//idCandidatura<1
    public void accettaCandidaturaFail1() throws SQLException, ParseException {
        int idCandidatura = -1;
        int idHR = 4;
        String data = ("2022-04-03");
        String tempo = ("17:30");
        String dataOra = data + " " + tempo;
        Date data1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataOra);
        Timestamp timestamp = new Timestamp(data1.getTime());
        assertFalse(accettaCandidatura(idCandidatura, idHR, timestamp));

    }

    @Test
    @Order(28) //idHR<1
    public void accettaCandidaturaFail2() throws SQLException, ParseException {
        int idCandidatura = 1;
        int idHR = -1;
        String data = ("2022-04-03");
        String tempo = ("17:30");
        String dataOra = data + " " + tempo;
        Date data1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataOra);
        Timestamp timestamp = new Timestamp(data1.getTime());
        assertFalse(accettaCandidatura(idCandidatura, idHR, timestamp));

    }

    @Test
    @Order(29) //Accetta con successo la candidatura
    public void accettaCandidaturaPass() throws SQLException, ParseException {
        int idCandidatura = 1;
        int idHR = 4;
        String data = ("2022-04-03");
        String tempo = ("17:30");
        String dataOra = data + " " + tempo;
        Date data1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataOra);
        Timestamp timestamp = new Timestamp(data1.getTime());
        System.out.println(timestamp);
        assertTrue(CandidaturaDAO.accettaCandidatura(idCandidatura, idHR, timestamp));
    }

    @Test
    @Order(30) //Recupera tutte le candidature
    public void recuperaCandidatureOk() throws SQLException {
        String query = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(5,'test','test','NonRevisionato','2022-01-10', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNotNull(CandidaturaDAO.recuperaCandidature());
    }

    @Test
    @Order(31) //Nessuna candidatura
    public void recuperaCandidatureFail() throws SQLException {
        String delete = "Delete from candidature where IdCandidatura>=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.executeUpdate(delete);
        assertNull(CandidaturaDAO.recuperaCandidature());
    }
}
