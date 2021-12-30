package it.unisa.agency_formation.reclutamento.DAO;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;

public class CandidaturaDAO {
    private final static String TABLE_CANDIDATURA = "Candidature";

    /**
     * Questa funzionalità permette di salvare una candidatura
     *
     * @param candidatura
     * @throws SQLException
     * @pre canididatura!=null
     */
    public boolean doSaveCandidatura(Candidatura candidatura) throws SQLException {
        if (candidatura == null) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement save = null;
        String query = "insert into " + TABLE_CANDIDATURA +
                "(Curriculum,DocumentiAggiuntivi, Stato, DataCandidatura,IdCandidato) "
                + "VALUES(?,?,?,?,?)";
        try {
            save = connection.prepareStatement(query);
            save.setString(1, candidatura.getCv());
            save.setString(2, candidatura.getDocumentiAggiuntivi());
            save.setString(3, candidatura.getStato());
            save.setDate(4, (Date) candidatura.getDataCandidatura());
            save.setInt(5, candidatura.getIdCandidato());
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
     * Questa funzionalità permette di recuperare una candidatura attraverso l’id del candidato
     *
     * @param idCandidato
     * @return Candidatura
     * @throws SQLException
     * @pre idCandidato >0
     */
    public Candidatura doRetrieveById(int idCandidato) throws SQLException {
        if (idCandidato < 1) {
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
                System.out.println("dentro if dao");
                cand.setIdCandidatura(result.getInt("IdCandidatura"));
                cand.setCv(result.getString("Curriculum"));
                cand.setDocumentiAggiuntivi(result.getString("DocumentiAggiuntivi"));
                cand.setStato(result.getString("Stato"));
                cand.setDataCandidatura(result.getDate("DataCandidatura"));
                cand.setDataOraColloquio(result.getTimestamp("DataOraColloquio"));
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
     * Questa funzionalità permette di recuperare tutte le candidature
     *
     * @return arrraylist di candidature
     * @throws SQLException
     */
    public ArrayList<Candidatura> doRetrieveAll() throws SQLException {
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
                cand.setIdCandidatura(result.getInt("IdCandidatura"));
                cand.setCv(result.getString("Curriculum"));
                cand.setDocumentiAggiuntivi(result.getString("DocumentiAggiuntivi"));
                cand.setStato(result.getString("Stato"));
                cand.setDataCandidatura(result.getDate("DataCandidatura"));
                cand.setDataOraColloquio(result.getTimestamp("DataOraColloquio"));
                cand.setIdCandidato(result.getInt("IdCandidato"));
                cand.setIdHR(result.getInt("IdHR"));
                candidature.add(cand);
            }
            if (candidature.size() > 0) {
                return candidature;
            } else {
                return null;
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
    public ArrayList<Candidatura> doRetrieveByState(String stato) throws SQLException {
        if (stato == null) {
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_CANDIDATURA + " Where Stato=?";
        ArrayList<Candidatura> candidature = new ArrayList<Candidatura>();
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setString(1, stato);
            result = retrieve.executeQuery();
            while (result.next()) {
                Candidatura cand = new Candidatura();
                cand.setIdCandidatura(result.getInt("IdCandidatura"));
                cand.setCv(result.getString("Curriculum"));
                cand.setDocumentiAggiuntivi(result.getString("DocumentiAggiuntivi"));
                cand.setStato(result.getString("Stato"));
                cand.setDataCandidatura(result.getDate("DataCandidatura"));
                cand.setDataOraColloquio(result.getTimestamp("DataOraColloquio"));
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
     * Questa funzionalità permette di modificare lo stato della candidatura
     *
     * @param stato
     * @param idCandidatura
     * @throws SQLException
     * @pre stato!=null and idCandidatura>0
     */
    public boolean updateState(int idCandidatura, String stato) throws SQLException {
        if (idCandidatura < 1 || stato == null) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement retrieve = null;
        String query = "update " + TABLE_CANDIDATURA + " set Stato= ? where IdCandidatura=?";
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setString(1, stato);
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
    public boolean updateCertificazioni(String certificazione, int idCandidatura) throws SQLException {
        if (idCandidatura < 1 || certificazione == null) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement retrieve = null;
        String query = "update " + TABLE_CANDIDATURA + " set Stato= ? where IdCandidatura=?";
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setString(1, certificazione);
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
     * Questa funzionalità permette di modificare gli attestati di una candidatura
     *
     * @param attestati
     * @throws SQLException
     * @pre attestati!=null and idCandidatura>0
     */
    public boolean updateAttestati(String attestati, int idCandidatura) throws SQLException {
        if (idCandidatura < 1 || attestati == null) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement retrieve = null;
        String query = "update " + TABLE_CANDIDATURA + " set Stato= ? where IdCandidatura=?";
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setString(1, attestati);
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
     * Questa funzionalità permette di rimuovere una candidatura
     *
     * @param idCandidatura
     * @throws SQLException
     * @pre idCandidatura>0
     */
    public boolean doRemoveCandidatura(int idCandidatura) throws SQLException {
        if (idCandidatura < 1) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "DELETE FROM " + TABLE_CANDIDATURA + "WHERE idCandidatura=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idCandidatura);
            int result = stmt.executeUpdate();
            if (result != -1) {
                return true;
            } else {
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
