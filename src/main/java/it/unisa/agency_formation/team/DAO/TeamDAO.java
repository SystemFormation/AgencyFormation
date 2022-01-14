package it.unisa.agency_formation.team.DAO;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamDAO {
    private static final String TABLE_DIPENDENTE = "dipendenti";
    private static final String TABLE_UTENTE = "utenti";
    private static final String TABLE_TEAM = "team";

    /**
     * Questa funzionalità permette di salvare un team
     *
     * @param team team scelto
     * @param idUtente utente scelto
     * @return boolean
     * @throws SQLException
     * @pre team>0
     */
    public static boolean salvaTeam(Team team, int idUtente) throws SQLException {
        if (team == null || idUtente < 1) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();

        if (team != null) {
            PreparedStatement save = null;
            String query = "insert into " + TABLE_TEAM + "(NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM)"
                    + " values(?,?,?,?,?,?)";
            try {
                save = connection.prepareStatement(query);
                save.setString(1, team.getNomeProgetto());
                save.setInt(2, team.getNumeroDipendenti());
                save.setString(3, team.getNomeTeam());
                save.setString(4, team.getDescrizione());
                save.setString(5, null);
                save.setInt(6, idUtente);
                return save.executeUpdate()!=0;
            } finally {
                DatabaseManager.closeConnessione(connection);
            }
        }
        return false;
    }

    /**
     * Questa funzionalità permette di eliminare un dipendente
     *
     * @param idTeam
     * @throws SQLException
     * @pre idTeam>0
     */
    public static boolean rimuoviTeam(int idTeam) throws SQLException {
        if (idTeam < 1) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query1 = "UPDATE " + TABLE_DIPENDENTE + " set idTeam=null WHERE idTeam=?";
        String query = "DELETE FROM " + TABLE_TEAM + " WHERE idTeam=?";
        PreparedStatement stmt1 = null;
        PreparedStatement stmt = null;
        try {
            stmt1 = connection.prepareStatement(query1);
            stmt1.setInt(1, idTeam);
            int res1 = stmt1.executeUpdate();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            int res2 = stmt.executeUpdate();
            return res1 != -1 && res2 != -1;

        } finally {
            DatabaseManager.closeConnessione(connection);
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
    /*
    public static boolean aggiungiDipendente(int idTeam, int idDipendente) throws SQLException {
        if (idTeam < 1 || idDipendente < 1) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        if (idTeam < 1 || idDipendente < 1) {
            return false;
        }
        String query = "UPDATE " + TABLE_DIPENDENTE + " SET IdTeam=? and Stato=0 WHERE IdDipendente=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            stmt.setInt(2, idDipendente);
            int result = stmt.executeUpdate();
            return result != -1;
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
    }*/

    /**
     * Questa funzionalità permette di recuperare un team attraverso il suo id
     *
     * @param idTeam
     * @return il team
     * @throws SQLException
     */
    public static Team recuperaTeamById(int idTeam) throws SQLException {
        if (idTeam < 1) {
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        Team team = null;
        PreparedStatement stmt = null;
        ResultSet result;
        String query = "SELECT * FROM " + TABLE_TEAM + " left join documenti on team.IdTeam=documenti.IdTeam and team.IdTeam = ?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            result = stmt.executeQuery();
            if (result.next()) {
                team = new Team();
                Documento documento = new Documento();
                team.setIdTeam(result.getInt("IdTeam"));
                team.setNomeProgetto(result.getString("NomeProgetto"));
                team.setNumeroDipendenti(result.getInt("NumeroDIpendenti"));
                team.setNomeTeam(result.getString("NomeTeam"));
                team.setDescrizione(result.getString("Descrizione"));
                team.setCompetenza(result.getString("Competenza"));
                team.setIdTM(result.getInt("IdTM"));
                if(result.getString("MaterialeDiFormazione")!=null){
                    documento.setMaterialeDiFormazione(result.getString("MaterialeDiFormazione"));
                    team.setDocumento(documento);
                }
            }
            return team;
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }

    /**
     * questa funzionalità permette di rimuovere un dipendente da un team
     *
     * @param idDipendente
     * @throws SQLException
     * @pre idTeam>0
     * @pre idDipendente>0
     */
    public static boolean rimuoviDipendente(int idDipendente) throws SQLException {
        if (idDipendente < 1) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "UPDATE " + TABLE_DIPENDENTE + " SET IdTeam=NULL, Stato=1 WHERE IdDipendente=?";
        PreparedStatement stmt = null;
        int result;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idDipendente);
            result = stmt.executeUpdate();
            return result != -1;
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }

    /**
     * Questa funzionalità permette di recuperare tutti i team presenti nella piattaforma
     *
     * @return arraylist di team
     * @throws SQLException
     * @post teams.size>0
     */
    public static ArrayList<Team> recuperaTuttiTeam() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ArrayList<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TEAM + " left join documenti on team.IdTeam=documenti.IdTeam";
        PreparedStatement stmt = null;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                Team team = new Team();
                Documento documento = new Documento();
                team.setIdTeam(result.getInt("IdTeam"));
                team.setNomeProgetto(result.getString("NomeProgetto"));
                team.setNumeroDipendenti(result.getInt("NumeroDipendenti"));
                team.setNomeTeam(result.getString("NomeTeam"));
                team.setDescrizione(result.getString("Descrizione"));
                team.setCompetenza(result.getString("Competenza"));
                team.setIdTM(result.getInt("IdTM"));
                if(result.getString("MaterialeDiFormazione")!=null){
                    documento.setMaterialeDiFormazione(result.getString("MaterialeDiFormazione"));
                    team.setDocumento(documento);
                }
                teams.add(team);
            }
            if (teams.size() > 0) {
                return teams;
            } else {
                teams = null;
                return teams;
            }
        } finally {
            DatabaseManager.closeConnessione(connection);
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
    public static ArrayList<Team> recuperaTeamDiUnTM(int idUtente) throws SQLException {
        if (idUtente < 1) {
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        ArrayList<Team> teams = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet result;
        String query = "SELECT * FROM " + TABLE_TEAM + " left join documenti on team.IdTeam=documenti.IdTeam and IdTM=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idUtente);
            result = stmt.executeQuery();
            while (result.next()) {
                Team team = new Team();
                Documento documento = new Documento();
                team.setIdTeam(result.getInt("IdTeam"));
                team.setNomeProgetto(result.getString("NomeProgetto"));
                team.setNumeroDipendenti(result.getInt("NumeroDIpendenti"));
                team.setNomeTeam(result.getString("NomeTeam"));
                team.setDescrizione(result.getString("Descrizione"));
                team.setCompetenza(result.getString("Competenza"));
                team.setIdTM(result.getInt("IdTM"));
                if(result.getString("MaterialeDiFormazione")!=null){
                    documento.setMaterialeDiFormazione(result.getString("MaterialeDiFormazione"));
                    team.setDocumento(documento);
                }
                teams.add(team);
            }
            if (teams.size() > 0) {
                return teams;
            } else {
                teams = null;
                return teams;
            }
        } finally {
            DatabaseManager.closeConnessione(connection);
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
    public static boolean modificaCompetenze(String competence, int idTeam) throws SQLException {
        if (competence == null || idTeam < 1) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "UPDATE " + TABLE_TEAM + " SET Competenza=? WHERE IdTeam=?";
        PreparedStatement stmt = null;
        int result;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, competence);
            stmt.setInt(2, idTeam);
           return stmt.executeUpdate()!=0;

        } finally {
            DatabaseManager.closeConnessione(connection);
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
    public static String recuperaCompetenza(int idTeam) throws SQLException {
        if (idTeam < 1) {
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "SELECT Competenza FROM " + TABLE_TEAM + " WHERE IdTeam=?";
        PreparedStatement stmt = null;
        String path = null;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            result = stmt.executeQuery();
            if (result.next()) {
                path = result.getString("Competenza");
            }
           return path;
        } finally {
            DatabaseManager.closeConnessione(connection);
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
    /*public static ArrayList<Dipendente> recuperaTuttiTeamMember(int idTeam) throws SQLException {
        if (idTeam < 1) {
            return null;
        }
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
                Team team = new Team();
                dip.setIdDipendente(result.getInt("IdDipendente"));
                dip.setResidenza(result.getString("Residenza"));
                dip.setTelefono(result.getString("Telefono"));
                boolean state = result.getBoolean("Stato");
                if (!(state)) {
                    dip.setStato(StatiDipendenti.OCCUPATO);
                } else if (state) {
                    dip.setStato(StatiDipendenti.DISPONIBILE);
                }
                dip.setAnnoNascita(result.getInt("AnnoDiNascita"));
                team.setIdTeam(result.getInt("IdTeam"));
                dip.setTeam(team);
                dipendenti.add(dip);
            }
            if (dipendenti.size() > 0) {
                return dipendenti;
            } else {
                return null;
            }
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }
*/
    /**
     * Questa funzionalità permette di recuperare il numero massimo di membri in un team
     *
     * @param //idTeam
     * @return
     * @throws SQLException
     * @pre idTeam!=null
     */
    /*
    public static int recuperaNumeroTMember(int idTeam) throws SQLException {
        if (idTeam < 1) {
            return -1;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "SELECT NumeroDipendenti FROM " + TABLE_TEAM + " WHERE IdTeam=?";
        PreparedStatement stmt = null;
        ResultSet result;
        int n;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            result = stmt.executeQuery();
            n = result.getInt(query);
            if (n > 0) {
                return n;
            } else {
                return -1;
            }
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }
*/
    public static int recuperaIdUltimoTeamCreato() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement stmt = null;
        String query = " SELECT max(team.IdTeam) FROM " + TABLE_TEAM;
        int idTeam = 0;
        try {
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();
            if (result.next()) {
                idTeam = result.getInt(1);
            }
            return idTeam;
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }
    public static ArrayList<Integer> recuperaIdTeamMemberFromTeam(int idTeam) throws SQLException {
        System.out.println("Dentro il metodo è : "+idTeam);
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement stmt = null;
        ArrayList<Integer> listaIdDips = new ArrayList<Integer>();
        String query = "SELECT idDipendente FROM " + TABLE_DIPENDENTE + " WHERE IdTeam=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idTeam);
            result = stmt.executeQuery();
            while (result.next()) {
                int idDipendente = result.getInt("IdDipendente");
                listaIdDips.add(idDipendente);
            }
            if(listaIdDips.size()>0) {
                return listaIdDips;
            } else {
                listaIdDips = null;
                return listaIdDips;
            }
        }finally {
            DatabaseManager.closeConnessione(connection);
        }
    }

    public static boolean updateDipStateDissolution(int idDip) throws SQLException {
        if (idDip < 0) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        String query = "update " + TABLE_DIPENDENTE + " set Stato = ? where IdDipendente = ?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setInt(2, idDip);
            return stmt.executeUpdate()!=0;
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }

    public static ArrayList<Dipendente> recuperaDipendentiDelTeam() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT * FROM " + TABLE_DIPENDENTE + " inner join " + TABLE_UTENTE + " on utenti.IdUtente = dipendenti.IdDipendente";
        ArrayList<Dipendente> DipsUsers = new ArrayList<Dipendente>();
        ResultSet result;
        try {
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                Dipendente dipUser = new Dipendente();
                Team team = new Team();
                dipUser.setIdDipendente(result.getInt("idDipendente"));
                dipUser.setResidenza(result.getString("Residenza"));
                dipUser.setTelefono(result.getString("Telefono"));
                if (!result.getBoolean("Stato")) {
                    dipUser.setStato(StatiDipendenti.OCCUPATO);

                } else if (result.getBoolean("Stato")) {
                    dipUser.setStato(StatiDipendenti.DISPONIBILE);
                }
                dipUser.setAnnoNascita(result.getInt("AnnoDiNascita"));
                team.setIdTeam(result.getInt("IdTeam"));
                dipUser.setTeam(team);
                dipUser.setId(result.getInt("IdUtente"));
                dipUser.setName(result.getString("Nome"));
                dipUser.setSurname(result.getString("Cognome"));
                dipUser.setPwd(result.getString("Pwd"));
                dipUser.setEmail(result.getString("Mail"));
                if(result.getInt("Ruolo") == 2){
                    dipUser.setRole(RuoliUtenti.DIPENDENTE);
                }
                DipsUsers.add(dipUser);
            }
            if (DipsUsers.size() > 0) {
                return DipsUsers;
            } else {
                return null;
            }
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }

}
