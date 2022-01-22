package agency_formation.formazione.DAO;

import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
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
        String query2 = "INSERT INTO team (IdTeam,NomeProgetto, NumeroDipendenti, NomeTeam, Descrizione, Competenza, IdTM) VALUES (4,'Alo', '8', 'GameProject', 'Game', 'unity', 6)";
        String query3 = "INSERT INTO documenti (IdDocumento, MaterialeDiFormazione, IdHR, IdTeam) values (3, '//', 4, 4)";
        String query4 = "INSERT INTO team (IdTeam,NomeProgetto, NumeroDipendenti, NomeTeam, Descrizione, Competenza, IdTM) VALUES (5,'TestTeam', '8', 'TestNome', 'TestDescr', 'HTML', 6)";

        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        PreparedStatement statement2 = connection.prepareStatement(query2);
        PreparedStatement statement3 = connection.prepareStatement(query3);
        statement.executeUpdate(query1);
        statement2.executeUpdate(query2);
        statement3.executeUpdate(query3);
        statement3.executeUpdate(query4);

    }

    @AfterAll
    public static void finish() throws SQLException {
        String delete = "DELETE FROM documenti WHERE IdDocumento>=1";
        String delete2 = "DELETE From utenti where IdUtente > 4";
        String delete3 = "DELETE From team where IdTeam > 1";
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

    @Test //Il team non esiste
    @Order(3)
    public void recuperaDocumentoByTeamFail1() throws SQLException {
        int idTeam=-1;
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }

    @Test //Documento esiste
    @Order(4)
    public void recuperaDocumentoByTeamOk() throws SQLException {
        assertNotNull(DocumentoDAO.recuperaDocumentoByTeam(4));
    }

    @Test//Documento non esiste
    @Order(5)
    public void recuperaDocumentoByTeamFail() throws SQLException {
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(5));
    }

}
