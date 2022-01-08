package it.unisa.agency_formation.reclutamento.DAO;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;

public class CandidaturaDAO {
    private final static String TABLE_CANDIDATURA = "Candidature";


    /**
     * Questa funzionalità permette di salvare una candidatura
     * senza gli attestati e le certificazioni
     *
     * @param candidatura
     * @return
     * @throws SQLException
     * @pre candidatura!=null
     */
    public static boolean salvaCandidaturaSenzaDocumenti(Candidatura candidatura) throws SQLException {
        if (candidatura == null) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement save = null;
        String query = "insert into " + TABLE_CANDIDATURA +
                "(Curriculum, Stato, DataCandidatura,IdCandidato) "
                + "VALUES(?,?,?,?)";
        try {
            save = connection.prepareStatement(query);
            save.setString(1, candidatura.getCurriculum());
            switch (candidatura.getStato()){
                case Accettata:
                    save.setString(2, "Accettata");
                    break;
                case Rifiutata:
                    save.setString(2, "Rifiutata");
                    break;
                case NonRevisionato:
                    save.setString(2, "NonRevisionato");
                    break;
            }

            save.setDate(3, (Date) candidatura.getDataCandidatura());
            save.setInt(4, candidatura.getIdCandidato());
            int result = save.executeUpdate();
            if (result != -1) {
                return true;
            } else {
                return false;
            }
        } finally {
            try {
                if (save != null) {
                    save.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di aggiungere attestati e certificazioni
     *
     * @param document
     * @param idUtente
     * @return
     * @throws SQLException
     * @pre document!=null and idUtente>0
     */

    public static boolean aggiungiDocumentiAggiuntivi(String document, int idUtente) throws SQLException {
        if (document == null || idUtente < 1) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement update = null;
        String query = "update " + TABLE_CANDIDATURA + " set DocumentiAggiuntivi= ? where IdCandidato=?";
        try {
            update = connection.prepareStatement(query);
            update.setString(1, document);
            update.setInt(2, idUtente);

            int result = update.executeUpdate();
            if (result != -1) {
                return true;
            } else {
                return false;
            }

        } finally {
            try {
                if (update != null) {
                    update.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di recuperare una candidatura attraverso l’id del candidato
     *
     * @param idCandidato
     * @return Candidatura
     * @throws SQLException
     * @pre idCandidato >0
     */
    // todo refactor
    public static Candidatura doRetrieveById(int idCandidato) throws SQLException {
        if (idCandidato < 1) {
            return null;
        }
        ResultSet result;
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_CANDIDATURA + " where IdCandidato=?";
        Candidatura cand = null;
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setInt(1, idCandidato);
            result = retrieve.executeQuery();
            if (result.next()) {
                cand = new Candidatura();
                cand.setIdCandidatura(result.getInt("IdCandidatura"));
                cand.setCurriculum(result.getString("Curriculum"));
                cand.setDocumentiAggiuntivi(result.getString("DocumentiAggiuntivi"));
                switch (result.getString("Stato")){
                    case "NonRevisionato":
                        cand.setStato(StatiCandidatura.NonRevisionato);
                        break;
                    case "Accettata":
                        cand.setStato(StatiCandidatura.Accettata);
                        break;
                    case "Rifiutata":
                        cand.setStato(StatiCandidatura.Rifiutata);
                        break;
                }
                cand.setDataCandidatura(result.getDate("DataCandidatura"));
                cand.setDataOraColloquio(result.getTimestamp("DataOraColloquio"));
                cand.setIdCandidato(result.getInt("IdCandidato"));
                cand.setIdHR(result.getInt("IdHR"));
            }
            if (cand != null) {
                return cand;
            } else {
                return null;
            }
        } finally {
            if (retrieve != null) {
                retrieve.close();
            }
        }
    }


    /**
     * Questa funzionalità permette di recuperare tutte le candidature
     *
     * @return arrraylist di candidature
     * @throws SQLException
     * @post candidature.size()>0
     */
    public static ArrayList<Candidatura> recuperaCandidature() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_CANDIDATURA + " where Stato=?";
        ArrayList<Candidatura> candidature = new ArrayList<>();
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setString(1, "NonRevisionato");
            result = retrieve.executeQuery();
            while (result.next()) {
                Candidatura cand = new Candidatura();
                cand.setIdCandidatura(result.getInt("IdCandidatura"));
                cand.setCurriculum(result.getString("Curriculum"));
                cand.setDocumentiAggiuntivi(result.getString("DocumentiAggiuntivi"));
                switch (result.getString("Stato")){
                    case "NonRevisionato":
                        cand.setStato(StatiCandidatura.NonRevisionato);
                        break;
                    case "Accettata":
                        cand.setStato(StatiCandidatura.Accettata);
                        break;
                    case "Rifiutata":
                        cand.setStato(StatiCandidatura.Rifiutata);
                        break;
                }
                cand.setDataCandidatura(result.getDate("DataCandidatura"));
                cand.setDataOraColloquio(result.getTimestamp("DataOraColloquio"));
                cand.setIdCandidato(result.getInt("IdCandidato"));
                cand.setIdHR(result.getInt("IdHR"));
                candidature.add(cand);
            }
            if (candidature.size() > 0) {
                return candidature;
            } else {
                candidature = null;
                return candidature;
            }
        } finally {
            try {
                if (retrieve != null) {
                    retrieve.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di recuperare le candidature in base al loro stato
     *
     * @param stato
     * @return arraylist di candiature
     * @throws SQLException
     * @pre stato!=null
     */
    public static ArrayList<Candidatura> recuperaCandidatureByStato(StatiCandidatura stato) throws SQLException {
        if (stato == null) {
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_CANDIDATURA + " Where Stato=?";
        ArrayList<Candidatura> candidature = new ArrayList<>();
        try {
            retrieve = connection.prepareStatement(query);
            switch (stato){
                case Accettata:
                    retrieve.setString(1, "Accettata");
                    break;
                case Rifiutata:
                    retrieve.setString(1, "Rifiutata");
                    break;
                case NonRevisionato:
                    retrieve.setString(1, "NonRevisionato");
                    break;
            }
            result = retrieve.executeQuery();
            while (result.next()) {
                Candidatura cand = new Candidatura();
                cand.setIdCandidatura(result.getInt("IdCandidatura"));
                cand.setCurriculum(result.getString("Curriculum"));
                cand.setDocumentiAggiuntivi(result.getString("DocumentiAggiuntivi"));
                switch (result.getString("Stato")){
                    case "NonRevisionato":
                        cand.setStato(StatiCandidatura.NonRevisionato);
                        break;
                    case "Accettata":
                        cand.setStato(StatiCandidatura.Accettata);
                        break;
                    case "Rifiutata":
                        cand.setStato(StatiCandidatura.Rifiutata);
                        break;
                }
                cand.setDataCandidatura(result.getDate("DataCandidatura"));
                cand.setDataOraColloquio(result.getTimestamp("DataOraColloquio"));
                cand.setIdCandidato(result.getInt("IdCandidato"));
                cand.setIdHR(result.getInt("IdHR"));
                candidature.add(cand);
            }
            if (candidature.size() > 0) return candidature;
            else{
              candidature = null;
              return candidature;
            }
        } finally {
            try {
                if (retrieve != null) {
                    retrieve.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di modificare lo stato della candidatura
     *
     * @param stato
     * @param idCandidatura
     * @throws SQLException
     * @pre stato!=null and idCandidatura>0
     */
    public static boolean modificaStato(int idCandidatura, StatiCandidatura stato) throws SQLException {
        if (idCandidatura < 1 || stato == null) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement retrieve = null;
        String query = "update " + TABLE_CANDIDATURA + " set Stato= ? where IdCandidatura=?";
        try {
            retrieve = connection.prepareStatement(query);
            switch (stato){
                case Accettata:
                    retrieve.setString(1, "Accettata");
                    break;
                case Rifiutata:
                    retrieve.setString(1, "Rifiutata");
                    break;
                case NonRevisionato:
                    retrieve.setString(1, "NonRevisionato");
                    break;
            }
            retrieve.setInt(2, idCandidatura);
            int result = retrieve.executeUpdate();
            if (result != -1) {
                return true;
            } else {
                return false;
            }
        } finally {
            try {
                if (retrieve != null) {
                    retrieve.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di modificare le certificazioni di una candidatura
     *
     * @param certificazione
     * @param idCandidatura
     * @throws SQLException
     * @pre certificazione!=null and idCandidatura>0
     */


    /**
     * Questa funzionalità permette di rimuovere una candidatura
     *
     * @param idCandidatura
     * @throws SQLException
     * @pre idCandidatura>0
     */
    public static boolean rimuoviCandidatura(int idCandidatura) throws SQLException {
        if (idCandidatura < 1) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "DELETE FROM " + TABLE_CANDIDATURA + " WHERE idCandidatura=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idCandidatura);
            int result = stmt.executeUpdate();
            if(result!=-1) {
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


                                            /*Nuovi metodi*/

    /**
     * Quersta funzionalità permette di rifiutare una candidatura
     * @param idCandidatura
     * @pre idCandidatura>0
     *
     * */
    public static boolean rifiutaCandidatura(int idCandidatura, int idHR) throws SQLException {
        if (idCandidatura < 1 || idHR <1) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        String delete= "deleted";
        modificaStato(idCandidatura,StatiCandidatura.Rifiutata);
        String query = "update " + TABLE_CANDIDATURA +" set Curriculum=?, DocumentiAggiuntivi=?, IdHR=? where IdCandidatura=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1,delete);
            stmt.setString(2,delete);
            stmt.setInt(3, idHR);
            stmt.setInt(4, idCandidatura);
            int result = stmt.executeUpdate();
            if(result!=-1) {
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
    public static boolean accettaCandidatura(int idCandidatura, int idHR, Timestamp data) throws SQLException {
        if (idCandidatura < 1 || idHR<1){
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        modificaStato(idCandidatura,StatiCandidatura.Accettata);
        String query = "update " + TABLE_CANDIDATURA +" set Stato=?, IdHR=?, DataOraColloquio=? where IdCandidatura=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1,"Accettata");
            stmt.setInt(2,idHR);
            stmt.setTimestamp(3,  data);
            stmt.setInt(4, idCandidatura);

            int result = stmt.executeUpdate();
            if(result!=-1) {
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

}
