package agency_formation.reclutamento.DAO;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAOImpl;
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

import static org.junit.jupiter.api.Assertions.*;

public class CandidaturaDAOTest {
    private CandidaturaDAO dao = new CandidaturaDAOImpl();

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "Insert into candidature (IdCandidatura,Curriculum,Stato,DataCandidatura,IdCandidato) values(1,'test','NonRevisionato','2022-01-10', 1);";
        String query2 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(2,'test','test','Accettata','2022-01-10', 5);";
        String query3 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(3,'test','test','Rifiutata','2022-01-10', 6)";
        String query4 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(4,'test','test','Assunzione','2022-01-10', 7)";
        String query5 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(5,'TestNome','TestCognome','lol','test1@gmail.com', 1)";
        String query6 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(6,'TestNome','TestCognome','lol','test2@gmail.com', 1)";
        String query7 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(7,'TestNome','TestCognome','lol','test3@gmail.com', 1)";
        String query8 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(8,'TestNome','TestCognome','lol','test4@gmail.com', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query5);
        statement.executeUpdate(query5);
        statement.executeUpdate(query6);
        statement.executeUpdate(query7);
        statement.executeUpdate(query8);
        statement.executeUpdate(query1);
        statement.executeUpdate(query2);
        statement.executeUpdate(query3);
        statement.executeUpdate(query4);
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
        assertFalse(dao.salvaCandidaturaSenzaDocumenti(cand));
    }


    @Test
    @Order(2) //salva il candidatura
    public void salvaCandidaturaSenzaDocumentiOk3() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(8);
        cand.setCurriculum("test");
        cand.setStato(StatiCandidatura.NonRevisionato);
        assertTrue(dao.salvaCandidaturaSenzaDocumenti(cand));
    }

    @Test
    @Order(3) //document = null
    public void aggiungiDocumentiAggiuntiviFail1() throws SQLException {
        String document = null;
        int idUtente = 8;
        assertFalse(dao.aggiungiDocumentiAggiuntivi(document, idUtente));
    }

    @Test
    @Order(4) //id = -1
    public void aggiungiDocumentiAggiuntiviFail2() throws SQLException {
        String document = "Test";
        int idUtente = -1;
        assertFalse(dao.aggiungiDocumentiAggiuntivi(document, idUtente));
    }

    @Test
    @Order(5) //aggiunge il document Test con id 1
    public void aggiungiDocumentiAggiuntiviOk() throws SQLException {
        String document = "Test";
        int idUtente = 8;
        assertTrue(dao.aggiungiDocumentiAggiuntivi(document, idUtente));
    }

    @Test
    @Order(6) //id candidato = -1
    public void retrieveCandidaturaByIdFail() throws SQLException {
        int idCandidato = -1;
        assertNull(dao.doRetrieveCandidaturaById(idCandidato));
    }

    @Test
    @Order(7) //id candidato = 1
    public void retrieveCandidaturaByIdOk1NonRev() throws SQLException {
        int idCandidato = 1;
        assertNotNull(dao.doRetrieveCandidaturaById(idCandidato));
    }

    @Test
    @Order(8) //id candidato = 1
    public void retrieveCandidaturaByIdOk2Accet() throws SQLException {
        int idCandidato = 5;
        assertNotNull(dao.doRetrieveCandidaturaById(idCandidato));
    }

    @Test
    @Order(9) //id candidato = 1
    public void retrieveCandidaturaByIdOk3Rif() throws SQLException {
        int idCandidato = 6;
        assertNotNull(dao.doRetrieveCandidaturaById(idCandidato));
    }

    @Test
    @Order(10) //id candidato = 1
    public void retrieveCandidaturaByIdOk3Ass() throws SQLException {
        int idCandidato = 7;
        assertNotNull(dao.doRetrieveCandidaturaById(idCandidato));
    }

    @Test
    @Order(11) //id candidatura = -1
    public void modificaStatoCandidaturaFail() throws SQLException {
        int idCandidatura = -1;
        assertFalse(dao.modificaStatoCandidatura(idCandidatura, StatiCandidatura.Accettata));
    }

    @Test
    @Order(12) //id candidatura = 2
    public void modificaStatoCandidaturaOk1() throws SQLException {
        int idCandidatura = 2;
        assertTrue(dao.modificaStatoCandidatura(idCandidatura, StatiCandidatura.Rifiutata));
    }

    @Test
    @Order(13) //id candidatura = 2
    public void modificaStatoCandidaturaOk2() throws SQLException {
        int idCandidatura = 2;
        assertTrue(dao.modificaStatoCandidatura(idCandidatura, StatiCandidatura.NonRevisionato));
    }

    @Test
    @Order(14) //id candidatura = 2
    public void modificaStatoCandidaturaOk3() throws SQLException {
        int idCandidatura = 2;
        assertTrue(dao.modificaStatoCandidatura(idCandidatura, StatiCandidatura.Assunzione));
    }

    @Test
    @Order(15) //idCandidatura = -1
    public void rimuoviCandidaturaFail() throws SQLException {
        int idCandidato = -1;
        assertFalse(dao.rimuoviCandidatura(idCandidato));
    }

    @Test
    @Order(16) //idCandidatura = 2
    public void rimuoviCandidaturaOk() throws SQLException {
        int idCandidato = 5;
        assertTrue(dao.rimuoviCandidatura(idCandidato));
    }

    @Test
    @Order(17) //idCandidatura non esiste
    public void rifiutaCandidaturaFail1() throws SQLException {
        int idCandidatura = -1;
        int idHR = 4;
        assertFalse(dao.rifiutaCandidatura(idCandidatura, idHR));
    }

    @Test
    @Order(18) //idHR<1
    public void rifiutaCandidaturaFail2() throws SQLException {
        int idCandidatura = 1;
        int idHR = -4;
        assertFalse(dao.rifiutaCandidatura(idCandidatura, idHR));

    }

    @Test
    @Order(19) //Rifiuta con successo la candidatura
    public void rifiutaCandidaturaPass() throws SQLException {
        int idCandidatura = 1;
        int idHR = 4;
        assertTrue(dao.rifiutaCandidatura(idCandidatura, idHR));
    }

    @Test
    @Order(20) //idCandidatura<1
    public void accettaCandidaturaFail1() throws SQLException, ParseException {
        int idCandidatura = -1;
        int idHR = 4;
        String data = ("2022-04-03");
        String tempo = ("17:30");
        String dataOra = data + " " + tempo;
        Date data1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataOra);
        Timestamp timestamp = new Timestamp(data1.getTime());
        assertFalse(dao.accettaCandidatura(idCandidatura, idHR, timestamp));

    }

    @Test
    @Order(21) //idHR<1
    public void accettaCandidaturaFail2() throws SQLException, ParseException {
        int idCandidatura = 1;
        int idHR = -1;
        String data = ("2022-04-03");
        String tempo = ("17:30");
        String dataOra = data + " " + tempo;
        Date data1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataOra);
        Timestamp timestamp = new Timestamp(data1.getTime());
        assertFalse(dao.accettaCandidatura(idCandidatura, idHR, timestamp));

    }

    @Test
    @Order(22) //Accetta con successo la candidatura
    public void accettaCandidaturaPass() throws SQLException, ParseException {
        int idCandidatura = 1;
        int idHR = 4;
        String data = ("2022-04-03");
        String tempo = ("17:30");
        String dataOra = data + " " + tempo;
        Date data1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataOra);
        Timestamp timestamp = new Timestamp(data1.getTime());
        assertTrue(dao.accettaCandidatura(idCandidatura, idHR, timestamp));
    }

    @Test
    @Order(23) //Recupera tutte le candidature
    public void recuperaCandidatureOk() throws SQLException {
        String query1 = "Insert into candidature (IdCandidatura,Curriculum,Stato,DataCandidatura,IdCandidato) values(50,'test','NonRevisionato','2022-01-10', 15);";
        String query8 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(15,'TestNome','TestCognome','lol','test5@gmail.com', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query8);
        statement.executeUpdate(query8);
        statement.executeUpdate(query1);
        assertNotNull(dao.recuperaCandidature());
    }

    @Test
    @Order(24) //Nessuna candidatura
    public void recuperaCandidatureFail() throws SQLException {
        String delete = "Delete from candidature where IdCandidatura>=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.executeUpdate(delete);
        assertNull(dao.recuperaCandidature());
    }
}
