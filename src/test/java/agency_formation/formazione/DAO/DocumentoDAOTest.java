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

    }
    @AfterAll
    public static void finish() throws SQLException {
        //String query  = "Insert into documenti (IdDocumento, MaterialeDiFormazione, IdHR, IdTeam) values (1, '/', 4, 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        //PreparedStatement statement = connection.prepareStatement(query);
        //statement.executeUpdate(query);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test //not pass document == null
    @Order(1)
    public void saveDocumentFail() throws SQLException {
        Documento doc= null;
        assertFalse(DocumentoDAO.salvaDocumento(doc));
    }
    @Test //pass
    @Order(2)
    public void saveDocumentPass() throws SQLException {
        Documento doc= new Documento();
        doc.setIdDocumento(2);
        doc.setMaterialeDiFormazione("test");
        doc.setIdHR(4);
        doc.setIdTeam(1);
        assertTrue(DocumentoDAO.salvaDocumento(doc));
    }
    @Test//not pass path == null//
    @Order(3)
    public void removeDocumentFail() throws SQLException {
        String path= null;
        assertFalse(DocumentoDAO.rimuoviDocumento(path));
    }
    @Test // execute query deve essere execute Update
    @Order(4)
    public void removeDocumentPass() throws SQLException {
        String path= "";
        assertTrue(DocumentoDAO.rimuoviDocumento(path));
    }

    @Test//not pass, idHR<1
    @Order(5)
    public void updateDocumentHRFail() throws SQLException {
        int idHR=-1;
        String materiale="null";
        int idTeam=2;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }

   /* @Test//not pass, idHR doesn't exists
    public void updateDocumentNotHRFail() throws SQLException {
        int idHR=6432;
        String materiale="null";
        int idTeam=1;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }*/
    @Test//not pass, materiale == null
    @Order(6)
    public void updateDocumentMaterialFail() throws SQLException {
        int idHR=4;
        String materiale= null;
        int idTeam=2;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));

    }

    @Test//not pass, idTM<1
    @Order(7)
    public void updateDocumentIdTMFail() throws SQLException {
        int idHR=4;
        String materiale="null";
        int idTeam=0;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));

    }
    @Test//pass
    @Order(8)
    public void updateDocumentPass() throws SQLException {
        int idHR=4;
        String materiale="null";
        int idTeam=2;
        assertTrue(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }
    @Test//not pass, idTM<1
    @Order(9)
    public void doRetrieveByTeamFail() throws SQLException {
        int idTeam=-1;
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }

    @Test//pass
    @Order(10)
    public void doRetrieveByTeamPass() throws SQLException {
        //riempire il DB per testarlo
        int idTeam=2;
        assertNotNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }




}
