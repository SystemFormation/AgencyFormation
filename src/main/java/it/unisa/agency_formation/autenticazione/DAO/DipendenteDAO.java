package it.unisa.agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DipendenteDAO {
    private static final String TABLE_DIPENDENTE = "dipendenti";

    /**
     * Questa funzionalità permette di salvare un dipendente
     *
     * @param dipendente è il dipendente da registrare
     * @return boolean
     * @throws SQLException
     * @pre dip!=null
     */
    public static boolean salvaDipendente(Dipendente dipendente) throws SQLException {
        if (dipendente == null) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement save = null;
        String query = "insert into " + TABLE_DIPENDENTE + " (IdDipendente,Residenza,Telefono,Stato,AnnoDiNascita,idTeam)"
                + " values(?,?,?,?,?,?)";
        modificaRuoloUtente(dipendente.getIdDipendente());
        try {
            save = connection.prepareStatement(query);
            save.setInt(1, dipendente.getIdDipendente());
            save.setString(2, dipendente.getResidenza());
            save.setString(3, dipendente.getTelefono());
            if (dipendente.getStato() == StatiDipendenti.OCCUPATO) {
                save.setBoolean(4, false);
            } else if (dipendente.getStato() == StatiDipendenti.DISPONIBILE) {
                save.setBoolean(4, true);
            }
            save.setInt(5, dipendente.getAnnoNascita());
            save.setInt(6, dipendente.getTeam().getIdTeam());
            int result = save.executeUpdate();
            if (result != -1) {
                return true;
            }
                return false;
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
     * @param id
     * @return vero se il ruolo è stato modificato
     * @throws SQLException
     * @pre id>0
     */

    public static boolean modificaRuoloUtente(int id) throws SQLException {
        if (id <= 0) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement update = null;
        String query = "Update Utenti Set Ruolo=2 Where IdUtente=?";
        try {
            update = connection.prepareStatement(query);
            update.setInt(1, id);
            int result = update.executeUpdate();
            return result != -1;
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
     * Questa funzionalità permette di recuperare un dipendente attraverso il suo id
     *
     * @param id è l'Id del dipendente che vogliamo recuperare
     * @return Dipendente
     * @throw SQLException
     * @pre id>0
     */

    public static Dipendente doRetrieveDipendenteById(int id) throws SQLException {
        if (id <= 0) {
            return null;
        }
        ResultSet result;
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_DIPENDENTE + " inner join utenti on utenti.IdUtente = dipendenti.IdDipendente where IdDipendente=?";
        Dipendente user = new Dipendente();
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setInt(1, id);
            result = retrieve.executeQuery();
            if (result.next()) {
                Team team = new Team();
                user.setIdDipendente(result.getInt("IdDipendente"));
                user.setResidenza(result.getString("Residenza"));
                user.setTelefono(result.getString("Telefono"));
                boolean stato = result.getBoolean("Stato");
                if (!(stato)) {
                    user.setStato(StatiDipendenti.OCCUPATO);
                } else if (stato) {
                    user.setStato(StatiDipendenti.DISPONIBILE);
                }
                user.setAnnoNascita(result.getInt("AnnoDiNascita"));
                team.setIdTeam(result.getInt("IdTeam"));
                user.setTeam(team);
                user.setId(result.getInt("IdUtente"));
                user.setName(result.getString("Nome"));
                user.setSurname(result.getString("Cognome"));
                user.setPwd(result.getString("Pwd"));
                user.setEmail(result.getString("Mail"));
                switch (result.getInt("Ruolo")) {
                    case 1:
                        user.setRole(RuoliUtenti.CANDIDATO);
                        break;
                    case 2:
                        user.setRole(RuoliUtenti.DIPENDENTE);
                        break;
                    case 3:
                        user.setRole(RuoliUtenti.TM);
                        break;
                    case 4:
                        user.setRole(RuoliUtenti.HR);
                        break;
                    default:
                        break;
                }
                return user;
            }
        } finally {
            if (retrieve != null) {
                retrieve.close();
            }
        }
        return null;
    }
    /**
     * Questa funzionalità permette di recuperare tutti i dipendenti
     *
     * @return arraylist di dipendenti
     * @throws SQLException
     * @post dipendenti.size()>0
     */
    public static ArrayList<Dipendente> recuperaDipendenti() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT * FROM " + TABLE_DIPENDENTE + " inner join utenti on utenti.IdUtente = dipendenti.IdDipendente";
        ArrayList<Dipendente> DipsUsers = new ArrayList<Dipendente>();
        ResultSet result;
        try {
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                Team team = new Team();
                Dipendente dipUser = new Dipendente();
                dipUser.setIdDipendente(result.getInt("idDipendente"));
                dipUser.setResidenza(result.getString("Residenza"));
                dipUser.setTelefono(result.getString("Telefono"));
                if (!result.getBoolean("Stato")) {
                    dipUser.setStato(StatiDipendenti.OCCUPATO);
                }
                if (result.getBoolean("Stato")) {
                    dipUser.setStato(StatiDipendenti.DISPONIBILE);
                }
                dipUser.setAnnoNascita(result.getInt("AnnoDiNascita"));
                if (result.getInt("IdTeam") != 0) {
                    team.setIdTeam(result.getInt("IdTeam"));
                    dipUser.setTeam(team);
                }
                team.setIdTeam(result.getInt("IdTeam"));
                dipUser.setTeam(team);
                dipUser.setId(result.getInt("IdUtente"));
                dipUser.setName(result.getString("Nome"));
                dipUser.setSurname(result.getString("Cognome"));
                dipUser.setPwd(result.getString("Pwd"));
                dipUser.setEmail(result.getString("Mail"));
                switch (result.getInt("Ruolo")) {
                    case 1:
                        dipUser.setRole(RuoliUtenti.CANDIDATO);
                        break;
                    case 2:
                        dipUser.setRole(RuoliUtenti.DIPENDENTE);
                        break;
                    case 3:
                        dipUser.setRole(RuoliUtenti.TM);
                        break;
                    case 4:
                        dipUser.setRole(RuoliUtenti.HR);
                        break;
                    default:
                        break;
                }
                DipsUsers.add(dipUser);
            }
            if (DipsUsers.size() > 0) {
                return DipsUsers;
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
     * Questa funzionalità permette di modificare lo stato del dipendente
     *
     * @param idUtente
     * @param stato
     * @return boolean for confirm
     * @throws SQLException
     * @pre idUtente>0
     * @post setStato(stato)
     */
    public static boolean modificaStatoDipendente(int idUtente, StatiDipendenti stato) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        if (idUtente <= 0) {
            return false;
        }
        PreparedStatement retrieve = null;
        String query = "update " + TABLE_DIPENDENTE + " set Stato= ? where IdDipendente=?";
        try {
            retrieve = connection.prepareStatement(query);
            if (StatiDipendenti.DISPONIBILE == stato) {
                retrieve.setBoolean(1, true);
            }
            if (StatiDipendenti.OCCUPATO == stato) {
                retrieve.setBoolean(1, false);
            }

            retrieve.setInt(2, idUtente);
            int res = retrieve.executeUpdate();
            if (res != -1) {
                return true;
            }
                return false;
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
     * Questa funzionalità permette di recuperare un dipendente attraverso lo stato
     *
     * @param stato
     * @return arraylist di dipendenti
     * @throws SQLException
     * @post dipendenti.size()>0
     */
    public static ArrayList<Dipendente> recuperaByStato(StatiDipendenti stato) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_DIPENDENTE + " Where Stato=?";
        ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
        try {
            retrieve = connection.prepareStatement(query);
            if (StatiDipendenti.DISPONIBILE == stato) {
                retrieve.setBoolean(1, true);
            } else if (StatiDipendenti.OCCUPATO == stato) {
                retrieve.setBoolean(1, false);
            }
            result = retrieve.executeQuery();
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
                if (result.getInt("IdTeam") != 0) {
                    team.setIdTeam(result.getInt("IdTeam"));
                    dip.setTeam(team);
                }
                dip.setId(result.getInt("IdUtente"));
                dip.setName(result.getString("Nome"));
                dip.setSurname(result.getString("Cognome"));
                dip.setPwd(result.getString("Pwd"));
                dip.setEmail(result.getString("Mail"));
                switch (result.getInt("Ruolo")) {
                    case 1:
                        dip.setRole(RuoliUtenti.CANDIDATO);
                        break;
                    case 2:
                        dip.setRole(RuoliUtenti.DIPENDENTE);
                        break;
                    case 3:
                        dip.setRole(RuoliUtenti.TM);
                        break;
                    case 4:
                        dip.setRole(RuoliUtenti.HR);
                        break;
                    default:
                        break;
                }
                dipendenti.add(dip);
            }
            if (dipendenti.size() > 0) {
                return dipendenti;
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
     * Questa funzionalità permette di aggiornare idTeam quando un dipendente viene aggiunto
     *
     * @param idDip,idTeam
     * @return void
     * @throws SQLException
     */

    public static boolean setTeamDipendente(int idDip, int idTeam) throws SQLException {
        if (idDip <= 0 || idTeam <= 0) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement stm = null;
        String query = "update " + TABLE_DIPENDENTE + " set IdTeam = ?, Stato = ? where IdDipendente = ?";
        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, idTeam);
            stm.setBoolean(2, false);
            stm.setInt(3, idDip);
            int result = stm.executeUpdate();
            return result != -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }
}
