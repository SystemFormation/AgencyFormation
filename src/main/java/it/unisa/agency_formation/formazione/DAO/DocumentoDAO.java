package it.unisa.agency_formation.formazione.DAO;

import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentoDAO {
    private static final String TABLE_DOCUMENTO = "Documenti";

    /**
     * Questa funzionalità permette di salvare un documento
     *
     * @param doc
     * @throws SQLException doc!=null
     */
    public static boolean doSaveDocument(Documento doc) throws SQLException {
        if(doc == null){
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement save = null;
        String query = "INSERT INTO " + TABLE_DOCUMENTO + "(MaterialeDiFormazione, IdUtente, IdTeam) " +
                "VALUES(?,?,?)";
        try {
            save = connection.prepareStatement(query);
            save.setString(1, doc.getMaterialeDiFormazione());
            save.setInt(2, doc.getIdUtente());
            save.setInt(3, doc.getIdTeam());
            int result=save.executeUpdate();
            if(result!=-1){
                return true;
            }else{
                return false;
            }
        } finally {
            try {
                if (save != null)
                    save.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
    }

    /**
     * Questa funzionalità permette di eliminare un documento
     *
     * @param materialeFormazione
     * @throws SQLException
     * @pre matForm!=null
     */
    public static boolean doRemoveDocument(String materialeFormazione) throws SQLException {
        if(materialeFormazione==null){
            return false;
        }
        ResultSet result;
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "DELETE FROM " + TABLE_DOCUMENTO + "WHERE MaterialeDiFormazione=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, materialeFormazione);
            result=stmt.executeQuery();
            if(result.next()){
                return true;
            }else {
                return false;
            }
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
    }

    /**
     * Questa funzionalità permette di modificare il materiale di formazione di un team
     *
     * @param idUtente
     * @param materiale
     * @param idTeam
     * @throws SQLException
     * @pre idDocument != null && ma != null && idTeam!=null
     */
    public static boolean updateDocument(int idUtente, String materiale, int idTeam) throws SQLException {
        if(idUtente <1 || materiale==null || idTeam<1){
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        String query = "UPDATE" + TABLE_DOCUMENTO + " SET MaterialeDiFormazione=? WHERE IdUtente=? and IdTeam=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, materiale);
            stmt.setInt(2, idUtente);
            stmt.setInt(3, idTeam);
            int result= stmt.executeUpdate();
            if(result!=-1){
                return true;
            }else{
                return false;
            }
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
    }

    /**
     * Questa funzionalità permette di recuperare del materiale attraverso il team
     *
     * @param idTeam
     * @return
     * @throws SQLException
     * @pre idTeam!=null
     */
    public static String doRetrieveByTeam(int idTeam) throws SQLException {
        if(idTeam<1){
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet result;
        String path = null;
        String query = "SELECT FROM " + TABLE_DOCUMENTO + "WHERE IdTeam=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            result = stmt.executeQuery();
            path = result.getString("MaterialeDiFormazione");
            if(path!=null){
                return path;
            }else{
                return null;
            }
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
    }
}
