package agency_formation.formazione.DAO;

import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DocumentoDAOTest {
    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "INSERT INTO utenti (IdUtente,Nome, Cognome, Pwd, Mail, Ruolo) VALUES (6,'Manuel', 'Noce', 'lol', 'm.noce@live.it', '3')";
        String query2 = "INSERT INTO team (IdTeam,NomeProgetto, NumeroDipendenti, NomeTeam, Descrizione, Competenza, IdTM) VALUES (4,'Fitdiary', '8', 'Bastoncini Fitnuss', 'Vendiamo bastoncini di pesce', 'HTML', 6)";
        String query3 = "INSERT INTO documenti (IdDocumento, MaterialeDiFormazione, IdHR, IdTeam) values (3, '//', 4, 4)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        PreparedStatement statement2 = connection.prepareStatement(query2);
        PreparedStatement statement3 = connection.prepareStatement(query3);
        statement.executeUpdate(query1);
        statement2.executeUpdate(query2);
        statement3.executeUpdate(query3);

    }

    @AfterAll
    public static void finish() throws SQLException {
        String delete = "DELETE FROM documenti WHERE IdDocumento>1";
        String delete2 = "DELETE From utenti where IdUtente = 6";
        String delete3 = "DELETE From team where IdTeam = 4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        PreparedStatement statement2 = connection.prepareStatement(delete2);
        PreparedStatement statement3 = connection.prepareStatement(delete3);
        statement.executeUpdate(delete);
        statement2.executeUpdate(delete2);
        statement3.executeUpdate(delete3);

        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test //non è presente nessun documento
    @Order(1)
    public void salvaDocumentoFail1() throws SQLException {
        Documento doc = null;
        assertFalse(DocumentoDAO.salvaDocumento(doc));
    }

    @Test //salva il documento
    @Order(2)
    public void salvaDocumentoOk() throws SQLException {
        Documento doc = new Documento(2,"/",4,4);
        assertTrue(DocumentoDAO.salvaDocumento(doc));
    }

    @Test //non rimuovi il documento
    @Order(3)
    public void rimuoviDocumentoFail() throws SQLException {
        String path= null;
        assertFalse(DocumentoDAO.rimuoviDocumento(path));
    }

    @Test // rimuovi il documento
    @Order(4)
    public void rimuoviDocumentoOk() throws SQLException {
        String path= "/";
        assertTrue(DocumentoDAO.rimuoviDocumento(path));
    }

    /*@Test //non aggiunge il documento
    @Order(5)
    public void modificaDocumentoFail1() throws SQLException {
        int idHR=4;
        String materiale=null;
        int idTeam=-2;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }

    @Test //aggiunge il documento
    @Order(6)
    public void modificaDocumentofail2() throws SQLException {
        int idHR=-1;
        String materiale= "/";
        int idTeam=-2;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }

    @Test //aggiunge il documento
    @Order(7)
    public void modificaDocumentoFail3() throws SQLException {
        int idHR=-1;
        String materiale= null;
        int idTeam=4;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));

    }
    @Test //aggiunge il documento
    @Order(8)
    public void modificaDocumentoPass() throws SQLException {
        int idHR=4;
        String materiale= "//";
        int idTeam=4;
        assertTrue(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));

    }*/

    /*@Test //Il team non esiste
    @Order(9)
    public void recuperaDocumentoByTeamFail1() throws SQLException {
        int idTeam=-1;
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }

    @Test //Prende le informazioni del documento
    @Order(10)
    public void recuperaDocumentoByTeamOk1() throws SQLException {
        int idTeam=1;
        assertNotNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }*/

    @Test //Documento esiste
    @Order(5)
    public void recuperaDocumentoByTeamOk() throws SQLException {
        assertNotNull(DocumentoDAO.recuperaDocumentoByTeam(4));
    }

    @Test//Documento non esiste
    @Order(4)
    public void recuperaDocumentoByTeamFail() throws SQLException {
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(8));
    }

}
