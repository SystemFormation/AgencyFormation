package agency_formation.formazione.DAO;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentoDAOTest {
    @BeforeAll
    public static void init () throws SQLException{
        Const.nomeDB = Const.NOME_DB_TEST;
        String query  = "Insert into documenti (IdDocumento, MaterialeDiFormazione, IdHR, IdTeam) values (2, '\', 4, 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);

    }
    @AfterAll
    public static void finish() throws SQLException {
        String delete= "Delete from documenti where IdDocumento>1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.executeUpdate(delete);
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
        Documento doc= new Documento();
        doc.setIdDocumento(2);
        doc.setMaterialeDiFormazione("test");
        doc.setIdHR(4);
        doc.setIdTeam(1);
        assertTrue(DocumentoDAO.salvaDocumento(doc));
    }

@Test//non rimuovi il documento
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

    @Test//non aggiunge il documento
    @Order(6)
    public void modificaDocumentoFail1() throws SQLException {
        int idHR=-1;
        String materiale=null;
        int idTeam=-2;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }

    @Test//aggiunge il documento
    @Order(6)
    public void modificaDocumentoOk() throws SQLException {
        String materiale= null;
        int idHR=4;
        int idTeam=2;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }

    @Test//aggiunge il documento
    @Order(6)
    public void modificaDocumentoFail2() throws SQLException {
        String materiale= null;
        int idHR=4;
        int idTeam=3;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));

    }

    @Test//Il team non esiste
    @Order(3)
    public void recuperaDocumentoByTeamFail1() throws SQLException {
        int idTeam=-1;
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }

    @Test//Prende le informazioni del documento
    @Order(4)
    public void recuperaDocumentoByTeamOk1() throws SQLException {
        int idTeam=1;
        assertNotNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }

    @Test//Documento esiste
    @Order(5)
    public void recuperaDocumentoByTeamOk2() throws SQLException {
        int idTeam=1;
        assertNotNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }

    @Test//Documento non esiste
    @Order(6)
    public void recuperaDocumentoByTeamFail2() throws SQLException {
        int IdTeam=2;
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(IdTeam));
    }

}
