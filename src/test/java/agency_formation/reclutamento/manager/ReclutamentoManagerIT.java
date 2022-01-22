package agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ReclutamentoManagerIT {
    public static ReclutamentoManager reclutamento = new ReclutamentoManagerImpl();

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "Insert into candidature (IdCandidatura,Curriculum,Stato,DataCandidatura,IdCandidato) values(1,'test','NonRevisionato','2022-01-10', 504);";
        String query2 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(2,'test','test','Accettata','2022-01-10', 500);";
        String query3 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(3,'test','test','Rifiutata','2022-01-10', 501)";
        String query4 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(4,'test','test','Assunzione','2022-01-10', 502)";
        String query5 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(500,'TestNome','TestCognome','lol','test1@gmail.com', 1)";
        String query6 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(501,'TestNome1','TestCognome1','lol','test2@gmail.com', 1)";
        String query7 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(502,'TestNome2','TestCognome2','lol','test3@gmail.com', 1)";
        String query8 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(503,'TestNome3','TestCognome3','lol','test4@gmail.com', 1)";
        String query9 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(504,'TestNome4','TestCognome4','lol','test5@gmail.com', 1)";
        String query10 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(505,'TestNome5','TestCognome5','lol','test6@gmail.com', 1)";

        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query5);
        statement.executeUpdate(query5);
        statement.executeUpdate(query6);
        statement.executeUpdate(query7);
        statement.executeUpdate(query8);
        statement.executeUpdate(query9);
        statement.executeUpdate(query10);
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

    @Test //candidatura = null not pass
    public void caricaCandidaturaFail() throws SQLException {
        Candidatura cand = null;
        assertFalse(reclutamento.caricaCandidatura(cand));
    }

    @Test //upload pass
    public void caricaCandidaturaNoAggiuntiPass() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(505);
        cand.setCurriculum("test");
        assertTrue(reclutamento.caricaCandidatura(cand));
    }

    @Test //candidatura  già esistente
    public void caricaCandidaturaEsistenteFail() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(500);
        cand.setCurriculum("test");
        cand.setDocumentiAggiuntivi("test");
        cand.setStato(StatiCandidatura.NonRevisionato);
        assertTrue(reclutamento.caricaCandidatura(cand));
    }

    @Test
    public void caricaCandidaturaConAggiuntiviPass() throws SQLException {
        Candidatura cand = new Candidatura();
        java.util.Date date = new java.util.Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setDataCandidatura(data);
        cand.setIdCandidato(503);
        cand.setDocumentiAggiuntivi("testing");
        assertTrue(reclutamento.caricaCandidatura(cand));
    }



    @Test
    public void modificaStatoCandidaturaFail() throws SQLException {
        assertFalse(reclutamento.modificaStatoCandidatura(-1,StatiCandidatura.Accettata));
    }

    @Test
    public void modificaStatoCandidaturaPass() throws SQLException {
        assertTrue(reclutamento.modificaStatoCandidatura(1,StatiCandidatura.Accettata));
    }

    @Test //id<1
    public void getCandidaturaByIdFail() throws SQLException {
       assertNull(reclutamento.getCandidaturaById(-1));
    }

    @Test //id di un candidato che non esiste
    public void getCandidaturaByIdFail1() throws SQLException {
        assertNull(reclutamento.getCandidaturaById(850));
    }

    @Test
    public void getCandidaturaByIdPass() throws SQLException {
        assertNull(reclutamento.getCandidaturaById(501));
    }

    @Test //Candidato inesistente
    public void accettaCandidaturaFail() throws SQLException {
        java.sql.Timestamp time = new java.sql.Timestamp(2022, 3, 8, 10, 30, 00,0);
        assertFalse(reclutamento.accettaCandidatura(10,4,time));
    }

    @Test //not pass , Candidato inesistente
    public void rifiutaCandidaturaFail() throws SQLException {
        assertFalse(reclutamento.rifiutaCandidatura(0,4));
    }

    @Test //pass
    public void rifiutaCandidaturaPass() throws SQLException {
        assertFalse(reclutamento.rifiutaCandidatura(500,4));
    }

    @Test // ci devono essere candidature
    public void getTutteCandidaturePass() throws SQLException {
        String query1 = "Insert into candidature (IdCandidatura,Curriculum,Stato,DataCandidatura,IdCandidato) values(50,'test','NonRevisionato','2022-01-10', 15);";
        String query8 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(15,'TestNome','TestCognome','lol','test5@gmail.com', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query8);
        statement.executeUpdate(query8);
        statement.executeUpdate(query1);
        assertNotNull(reclutamento.getTutteCandidature());
    }

    @Test //non ci devono essere candidature
    public void getTutteCandidatureFail() throws SQLException {
        String delete = "Delete from candidature where IdCandidatura>=1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.executeUpdate(delete);
        assertNull(reclutamento.getTutteCandidature());
    }



}
