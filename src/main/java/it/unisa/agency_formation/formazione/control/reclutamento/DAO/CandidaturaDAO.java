package it.unisa.agency_formation.formazione.control.reclutamento.DAO;

import it.unisa.agency_formation.formazione.control.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;

public class CandidaturaDAO {
    private final static String TABLE_CANDIDATURA="Candidature";

    /**
     *Questa funzionalità permette di salvare una candidatura
     * @param candidatura
     * @throws SQLException
     * @pre canididatura!=null
     */
    public static void doSaveCandidatura(Candidatura candidatura) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        if(candidatura!=null){
            PreparedStatement save = null;
            String query="insert into " + TABLE_CANDIDATURA + "(Cv, Attestati, Certificazioni, Stato, DataCandidatura, DataOraColloquio, IdCandidato, IdHR) "
                    + "VALUES(?,?,?,?,?,?,?,?)";
            try{
                save = connection.prepareStatement(query);
                save.setString(1, candidatura.getCv());
                save.setString(2, candidatura.getAttestati());
                save.setString(3, candidatura.getCertificazioni());
                save.setString(4, candidatura.getStato());
                save.setDate(5, (Date) candidatura.getDataCandidatura());
                save.setDate(6, (Date) candidatura.getDataOraColloquio());
                save.setInt(7,candidatura.getIdCandidato());
                save.setInt(8,candidatura.getIdHR());
                save.executeUpdate();
            }finally {
                try {
                    if (save != null)
                        save.close();
                } finally {
                    if (connection!= null)
                        connection.close();
                }
            }
        }
    }

    /**
     *Questa funzionalità permette di recuperare una candidatura attraverso l’id del candidato
     * @param idCandidato
     * @return Candidatura
     * @throws SQLException
     * @pre idCandidato !=null
     */
    public static Candidatura doRetrieveById(int idCandidato)throws SQLException {
        if (idCandidato <= 0) {
            return null;
        }
        ResultSet result;
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_CANDIDATURA + " where IdCandidato=?";
        Candidatura cand = new Candidatura();
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setInt(1, idCandidato);
            result = retrieve.executeQuery();
            if (result.next()) {
                cand.setCv(result.getString("CV"));
                cand.setAttestati(result.getString("Attestati"));
                cand.setCertificazioni(result.getString("Certificazioni"));
                cand.setStato(result.getString("Stato"));
                cand.setDataCandidatura(result.getDate("DataCandidatura"));
                cand.setDataOraColloquio(result.getDate("DataOraColloquio"));
                cand.setIdCandidato(result.getInt("IdCandidato"));
                cand.setIdHR(result.getInt("IdHR"));
                return cand;
            }
        } finally {
            if (retrieve != null) {
                retrieve.close();
            }
        }
        return null;
    }



    /**
     *Questa funzionalità permette di recuperare tutte le candidature
     * @return arrraylist di candidature
     * @throws SQLException
     */
    public static ArrayList<Candidatura> doRetrieveAll()throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_CANDIDATURA;
        ArrayList<Candidatura> candidature = new ArrayList<Candidatura>();
        try {
            retrieve = connection.prepareStatement(query);
            result = retrieve.executeQuery();
            while (result.next()) {
                Candidatura cand = new Candidatura();
                cand.setCv(result.getString("CV"));
                cand.setAttestati(result.getString("Attestati"));
                cand.setCertificazioni(result.getString("Certificazioni"));
                cand.setStato(result.getString("Stato"));
                cand.setDataCandidatura(result.getDate("DataCandidatura"));
                cand.setDataOraColloquio(result.getDate("DataOraColloquio"));
                cand.setIdCandidato(result.getInt("IdCandidato"));
                cand.setIdHR(result.getInt("IdHR"));
                candidature.add(cand);
            }
            if (candidature.size() > 0) return candidature;
            else return null;
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
     * @param stato
     * @return arraylist di candiature
     * @throws SQLException
     * @pre stato!=null
     */
    public static ArrayList<Candidatura> doRetrieveByState(String stato)throws SQLException{
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "Select * from "+ TABLE_CANDIDATURA +" Where Stato=?";
        ArrayList<Candidatura> candidature= new ArrayList<Candidatura>();
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setString(1, stato);
            result = retrieve.executeQuery();
            while (result.next()) {
                Candidatura cand = new Candidatura();
                cand.setCv(result.getString("CV"));
                cand.setAttestati(result.getString("Attestati"));
                cand.setCertificazioni(result.getString("Certificazioni"));
                cand.setStato(result.getString("Stato"));
                cand.setDataCandidatura(result.getDate("DataCandidatura"));
                cand.setDataOraColloquio(result.getDate("DataOraColloquio"));
                cand.setIdCandidato(result.getInt("IdCandidato"));
                cand.setIdHR(result.getInt("IdHR"));
                candidature.add(cand);
            }
            if (candidature.size() > 0) return candidature;
            else return null;
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
     *Questa funzionalità permette di modificare lo stato della candidatura
     * @param stato
     * @throws SQLException
     * @pre stato!=null
     */
    public static void updateState(int idCandidatura, String stato)throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "update " + TABLE_CANDIDATURA + " set Stato= ? where IdCandidatura=?";
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setString(1,stato);
            retrieve.setInt(2,idCandidatura);
            retrieve.executeUpdate();
        } finally {
            try {
                if (retrieve != null) {
                    retrieve.close();
                }
            } finally {
                if (connection!= null) {
                    connection.close();
                }
            }
        }
    }

    /**
     *Questa funzionalità permette di modificare le certificazioni di una candidatura
     * @param certificazione
     * @throws SQLException
     * @pre certificazione!=null
     */
    public static void updateCertificazioni(String certificazione)throws SQLException{
        //
    }

    /**
     *Questa funzionalità permette di modificare gli attestati di una candidatura
     * @param attestati
     * @throws SQLException
     * @pre attestati!=null
     */
    public static void updateAttestati(String attestati)throws SQLException{
        //
    }

    /**
     *Questa funzionalità permette di rimuovere una candidatura
     * @param idCandidatura
     * @throws SQLException
     * @pre idCandidatura!=null
     */
    public static void doRemoveCandidatura(int idCandidatura)throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query= "DELETE FROM " + TABLE_CANDIDATURA + "WHERE idCandidatura=?";
        PreparedStatement  stmt = null;
        try{
            stmt= connection.prepareStatement(query);
            stmt.setInt(1,idCandidatura);
            stmt.executeQuery();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } finally {
                if (connection!= null)
                    connection.close();
            }
        }
    }
}
