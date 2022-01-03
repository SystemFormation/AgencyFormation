package it.unisa.agency_formation.team.DAO;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamDAO {
    private static final String TABLE_DIPENDENTE = "dipendenti";
    private static final String TABLE_TEAM = "team";

    /**
     * Questa funzionalità permette di salvare un team
     *
     * @param team
     * @return boolean
     * @throws SQLException
     * @pre team>0
     */
    public static boolean doSaveTeam(Team team, int idUtente) throws SQLException {
        if(team == null || idUtente <1){
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();

        if (team != null) {
            PreparedStatement save = null;
            String query = "insert into " + TABLE_TEAM + "(NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM)"
                    + " values(?,?,?,?,?,?)";
            try {
                save = connection.prepareStatement(query);
                save.setString(1,team.getNomeProgetto());
                save.setInt(2, team.getNumeroDipendenti());
                save.setString(3, team.getNomeTeam());
                save.setString(4, team.getDescrizione());
                save.setString(5, null);
                save.setInt(6, idUtente);
                int result=save.executeUpdate();
                if(result!=-1) return true;
                else return false;
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
        return true;
    }

    /**
     * Questa funzionalità permette di eliminare un dipendente
     *
     * @param idTeam
     * @throws SQLException
     * @pre idTeam>0
     */
    /* ---------DA CONTROLLARE ----------*/
    public static boolean doRemoveTeam(int idTeam) throws SQLException {
        if(idTeam<1) return false;
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "DELETE FROM " + TABLE_TEAM + " WHERE idTeam=?";
        String upd = "UPDATE " + TABLE_DIPENDENTE + " SET Stato=1 WHERE IdTeam=?";
        PreparedStatement stmt = null;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(upd);
            stmt.setInt(1, idTeam);
            int res=stmt.executeUpdate();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            int res2 = stmt.executeUpdate();
            if(res!=-1 && res2!=-1){
                return true;
            }
            else{
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
     * questa funzionalità permette di aggiungere un dipendente in un team
     *
     * @param idTeam
     * @param idDipendente
     * @throws SQLException
     * @pre idTeam>0 && idDipendente>0
     */
    public static boolean addEmployee(int idTeam, int idDipendente) throws SQLException {
        if(idTeam<1 || idDipendente<1){
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        if(idTeam<1 || idDipendente<1) return false;
        String query = "UPDATE " + TABLE_DIPENDENTE + " SET IdTeam=? and Stato=1 WHERE IdDipendente=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            stmt.setInt(2, idDipendente);
            int result=stmt.executeUpdate();
            if(result!=-1) return true;
            else return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * questa funzionalità permette di rimuovere un dipendente da un team
     *
     * @param idTeam
     * @param idDipendente
     * @throws SQLException
     * @pre idTeam>0
     * @pre idDipendente>0
     */
    public static boolean removeEmployee(int idTeam, int idDipendente) throws SQLException {
        if(idTeam<1 || idDipendente<1) return false;
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "UPDATE " + TABLE_DIPENDENTE + " SET IdTeam=? and Stato=1 WHERE IdDipendente=?";
        PreparedStatement stmt = null;
        int result;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            stmt.setInt(2, idDipendente);
            result = stmt.executeUpdate();
            if(result!=-1) return true;
            else return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di recuperare tutti i team presenti nella piattaforma
     *
     * @return arraylist di team
     * @throws SQLException
     * @post teams.size>0
     */
    public static ArrayList<Team> doRetrieveAllTeam() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ArrayList<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TEAM;
        PreparedStatement stmt = null;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                Team team = new Team();
                team.setNomeProgetto(result.getString("NomeProgetto"));
                team.setNumeroDipendenti(result.getInt("NumeroDipendenti"));
                team.setNomeTeam(result.getString("NomeTeam"));
                team.setDescrizione(result.getString("Descrizione"));
                team.setCompetenza(result.getString("Competenza"));
                team.setIdTM(result.getInt("IdTM"));
                teams.add(team);
            }
            if (teams.size() > 0) {
                return teams;
            } else {
                teams = null;
                return teams;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di recuperare la lista dei team di un TM
     *
     * @param idUtente
     * @return arraylist di team
     * @throws SQLException
     * @pre idTM>0
     */
    public static ArrayList<Team> doRetrieveTMTeam(int idUtente) throws SQLException {
        if(idUtente<1){
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        ArrayList<Team> teams = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet result;
        String query = "SELECT * FROM " + TABLE_TEAM + " WHERE IdUtente=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idUtente);
            result = stmt.executeQuery();
            while (result.next()) {
                Team team = new Team();
                team.setNomeProgetto(result.getString("NomeProgetto"));
                team.setNumeroDipendenti(result.getInt("NumeroDIpendenti"));
                team.setNomeTeam(result.getString("NomeTeam"));
                team.setDescrizione(result.getString("Descrizione"));
                team.setCompetenza(result.getString("Competenza"));
                team.setIdTM(result.getInt("IdTM"));
                teams.add(team);
            }
            if (teams.size() > 0) {
                return teams;
            } else {
                teams = null;
                return teams;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di modificare le competenze di un team
     *
     * @param competence
     * @param idTeam
     * @throws SQLException
     * @pre competence!=null && idTeam>0
     */
    public static boolean updateCompetence(String competence, int idTeam) throws SQLException {
        if(competence==null || idTeam<1) return false;
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "UPDATE " + TABLE_TEAM + " SET Competenza=? WHERE IdTeam=?";
        PreparedStatement stmt = null;
        int result;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, competence);
            stmt.setInt(2, idTeam);
            result = stmt.executeUpdate();
            if(result!=-1) return true;
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di recuperare le competenze specificate di un team
     *
     * @param idTeam
     * @return Stringa contenente le competenze specificate
     * @throws SQLException
     * @pre idTeam>0
     */
    public static String doRetrieveCompetence(int idTeam) throws SQLException {
        if(idTeam<1) return null;
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "SELECT Competenza FROM "  + TABLE_TEAM + " WHERE IdTeam=?";
        PreparedStatement stmt = null;
        String path=null;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            result = (stmt.executeQuery());
            path=result.getString(query);
            if(path!=null){
                return path;
            }else{
                return null;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di recuperare la lista di tutti i membri di un team
     *
     * @param idTeam
     * @return arraylist di dipendenti
     * @throws SQLException
     * @pre idTeam>0
     * @post dipendenti.size>0
     */
    public static ArrayList<Dipendente> doRetrieveAllTMember(int idTeam) throws SQLException {
        if(idTeam<1) return null;
        Connection connection = DatabaseManager.getInstance().getConnection();
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_DIPENDENTE + " WHERE IdTeam=?";
        PreparedStatement stmt = null;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            result = stmt.executeQuery();
            while (result.next()) {
                Dipendente dip = new Dipendente();
                dip.setIdDipendente(result.getInt("IdDipendente"));
                dip.setResidenza(result.getString("Residenza"));
                dip.setTelefono(result.getString("Telefono"));
                dip.setStato(result.getBoolean("Stato"));
                dip.setAnnoNascita(result.getInt("AnnoDiNascita"));
                dip.setIdTeam(result.getInt("IdTeam"));
                dipendenti.add(dip);
            }
            if (dipendenti.size() > 0) {
                return dipendenti;
            } else {
                return null;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    /**
     * Questa funzionalità permette di recuperare il numero massimo di membri in un team
     *
     * @param idTeam
     * @return
     * @throws SQLException
     * @pre idTeam!=null
     */
    public static int doRetrieveNTMember(int idTeam) throws SQLException {
        if(idTeam<1) return -1;
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "SELECT NumeroDipendenti FROM " + TABLE_TEAM + " WHERE IdTeam=?";
        PreparedStatement stmt = null;
        ResultSet result;
        int n;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            result = stmt.executeQuery();
            n=result.getInt(query);
            if(n>0){
                return n;
            }
            else{
                return -1;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }
    public static int doRetrieveLastIDTeam() throws SQLException{
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement stmt = null;
        String query = " SELECT max(team.IdTeam)  FROM " + TABLE_TEAM;
        int idTeam = 0;
        try {
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();
            if (result.next()) {
                idTeam = result.getInt(1);
            }
            if(idTeam>0){
                return idTeam;
            }
            else{
                return -1;
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
