package agency_formation.formazione.DAO;

import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentoDAOTest {


    @Test//not pass document == null
    public void saveDocumentFail() throws SQLException {
        Documento doc= null;
        assertFalse(DocumentoDAO.salvaDocumento(doc));
    }
    @Test//pass
    public void saveDocumentPass() throws SQLException {
        Documento doc=new Documento();
        assertTrue(DocumentoDAO.salvaDocumento(doc));
    }
    @Test//not pass path == null
    public void removeDocumentFail() throws SQLException {
        String path= null;
        assertFalse(DocumentoDAO.rimuoviDocumento(path));
    }
    @Test//inserire un documento in insertDB per provare
    public void removeDocumentPass() throws SQLException {
        String path= "";
        assertTrue(DocumentoDAO.rimuoviDocumento(path));
    }

    @Test//not pass, idHR<1
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
    public void updateDocumentMaterialFail() throws SQLException {
        int idHR=4;
        String materiale= null;
        int idTeam=2;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));

    }

    @Test//not pass, idTM<1
    public void updateDocumentIdTMFail() throws SQLException {
        int idHR=4;
        String materiale="null";
        int idTeam=0;
        assertFalse(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));

    }
    @Test//pass
    public void updateDocumentPass() throws SQLException {
        int idHR=4;
        String materiale="null";
        int idTeam=2;
        assertTrue(DocumentoDAO.modificaDocumento(idHR,materiale,idTeam));
    }
    @Test//not pass, idTM<1
    public void doRetrieveByTeamFail() throws SQLException {
        int idTeam=-1;
        assertNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }

    @Test//pass
    public void doRetrieveByTeamPass() throws SQLException {
        //riempire il DB per testarlo
        int idTeam=2;
        assertNotNull(DocumentoDAO.recuperaDocumentoByTeam(idTeam));
    }




}
