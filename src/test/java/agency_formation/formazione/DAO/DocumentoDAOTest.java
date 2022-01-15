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

public class DocumentoDAOTest {
    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "INSERT INTO utenti (Nome, Cognome, Pwd, Mail, Ruolo) VALUES (6,'Manuel', 'Noce', 'lol', 'm.noce@live.it', '3')";
        String query2 = "INSERT INTO team (IdTeam,NomeProgetto, NumeroDipendenti, NomeTeam, Descrizione, Competenza, IdTM) VALUES (4,'Fitdiary', '8', 'Bastoncini Fitnuss', 'Vendiamo bastoncini di pesce', 'HTML', 6)";
        String query3 = "INSERT INTO documenti (IdDocumento, MaterialeDiFormazione, IdHR, IdTeam) values (2, '\', 4, 4)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query1);
        statement.executeUpdate(query2);
        statement.executeUpdate(query3);

    }

    @AfterAll
    public static void finish() throws SQLException {
        String delete = "DELETE FROM documenti WHERE IdDocumento>1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.executeUpdate(delete);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @BeforeEach
    public static void clearDocumentDB() throws SQLException {
        String query = "DELETE FROM documenti WHERE IdDocumento=4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement2 = connection.prepareStatement(query);
        statement2.executeUpdate(query);
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
        Team team = new Team();
        Documento doc = new Documento();
        doc.setIdDocumento(2);
        doc.setMaterialeDiFormazione("test");
        doc.setIdHR(4);
        doc.setIdTeam(team.getIdTeam());
        assertTrue(DocumentoDAO.salvaDocumento(doc));
    }

    @Test //non rimuovi il documento
    @Order(4)
    public void rimuoviDocumentoFail() throws SQLException {
        String path= null;
        assertFalse(DocumentoDAO.rimuoviDocumento(path));
    }

    @Test // rimuovi il documento
    @Order(5)
    public void rimuoviDocumentoOk() throws SQLException {
        String path= "\\";
        assertTrue(DocumentoDAO.rimuoviDocumento(path));
    }

    @Test //non aggiunge il documento
    @Order(6)
    public void modificaDocumentoFail1() throws SQLException {
        int idHR=-1;
        String materiale=null;
        int idTeam=-2;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }

    @Test //aggiunge il documento
    @Order(6)
    public void modificaDocumentoOk() throws SQLException {
        String materiale= null;
        int idHR=4;
        int idTeam=2;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }

    @Test //aggiunge il documento
    @Order(6)
    public void modificaDocumentoFail2() throws SQLException {
        String materiale= null;
        int idHR=4;
        int idTeam=3;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));

    }

    @Test //Il team non esiste
    @Order(3)
    public void recuperaDocumentoByTeamFail1() throws SQLException {
        int idTeam=-1;
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }

    @Test //Prende le informazioni del documento
    @Order(4)
    public void recuperaDocumentoByTeamOk1() throws SQLException {
        int idTeam=1;
        assertNotNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }

    @Test //Documento esiste
    @Order(5)
    public void recuperaDocumentoByTeamOk() throws SQLException {
        assertNotNull(DocumentoDAO.recuperaDocumentoByTeam(5));
    }

    @Test//Documento non esiste
    @Order(6)
    public void recuperaDocumentoByTeamFail() throws SQLException {
        clearDocumentDB();
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(5));
    }

}
