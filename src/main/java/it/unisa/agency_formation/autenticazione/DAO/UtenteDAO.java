package it.unisa.agency_formation.autenticazione.DAO;
import it.unisa.agency_formation.utils.DatabaseManager;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO {
    private static final String TABLE_UTENTE = "utenti";
    private DatabaseManager connection;
    private ResultSet result;

    /**
     * Questo metodo permette di salvare un utente
     * @param user è l'utente da registrare
     * @return void
     * @throws SQLException
     * @pre user!=null
     */
    public void doSaveUser(Utente user) throws SQLException {
        PreparedStatement save = null;
        String query = "insert into " + TABLE_UTENTE + "(Nome,Cognome,Pwd,Mail,Ruolo)"
                + " values(?,?,?,?,?)";
        try {
            save = connection.getConnection().prepareStatement(query);
            save.setString(1, user.getNome());
            save.setString(2, user.getCognome());
            save.setString(3, user.getPwd());
            save.setString(4, user.getEmail());
            save.setInt(5, user.getRuolo());
            save.executeUpdate();
        } finally {
            try {
                if (save != null) {
                    save.close();
                }
            } finally {
                if (connection.getConnection() != null) {
                    connection.getConnection().close();
                }
            }
        }
    }

    /**
     * Questo metodo permette di recuperare un utente
     * @param email è l'email dell'utente
     * @param pwd   è la password dell'utente
     * @return Utente l'utente loggato
     * @throws SQLException
     * @pre email!=null
     * @pre pwd!=null
     */
    public Utente doRetrieveUser(String email, String pwd) throws SQLException {
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_UTENTE + " where Mail=? and Pwd=?";
        Utente user = new Utente();
        try {
            retrieve = connection.getConnection().prepareStatement(query);
            retrieve.setString(1, email);
            retrieve.setString(2, pwd);
            result = retrieve.executeQuery();
            if (result.next()) {
                user.setId(result.getInt("IdUtente"));
                user.setNome(result.getString("Nome"));
                user.setCognome(result.getString("Cognome"));
                user.setPwd(result.getString("Pwd"));
                user.setEmail(result.getString("Email"));
                user.setRuolo(result.getInt("Ruolo"));
                return user;
            }
        } finally {
            try {
                if (retrieve != null) {
                    retrieve.close();
                }
            } finally {
                if (connection.getConnection() != null) {
                    connection.getConnection().close();
                }
            }
        }
        return null;
    }

    /**
     * Questo medoto ritorna l'utente con l'id indicato
     * @param id
     * @return Utente
     * @throws SQLException
     * @pre id!=null
     */
    public Utente doRetrieveByID(int id)throws SQLException{
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_UTENTE + " where IdUtente=?";
        Utente user = new Utente();
        try {
            retrieve = connection.getConnection().prepareStatement(query);
            retrieve.setInt(1, id);
            result = retrieve.executeQuery();
            if (result.next()) {
                user.setId(result.getInt("IdUtente"));
                user.setNome(result.getString("Nome"));
                user.setCognome(result.getString("Cognome"));
                user.setPwd(result.getString("Pwd"));
                user.setEmail(result.getString("Email"));
                user.setRuolo(result.getInt("Ruolo"));
                return user;
            }
        } finally {
            try {
                if (retrieve != null) {
                    retrieve.close();
                }
            } finally {
                if (connection.getConnection() != null) {
                    connection.getConnection().close();
                }
            }
        }
        return null;
    }


    /**
     * Questo metodo permette di recuperare degli utenti attraverso il ruolo
     * @param ruolo
     * @return arraylist di utenti
     * @throws SQLException
     * @pre ruolo== 3 || ruolo == 4 || ruolo == 2|| ruolo == 1
     */


    public ArrayList<Utente> doRetrieveUserByRuolo(int ruolo)throws SQLException{
        PreparedStatement retrieve = null;
        String query = "Select * from "+TABLE_UTENTE+" Where Ruolo=?";
        ArrayList<Utente> utenti = new ArrayList<>();
        try{
            retrieve = connection.getConnection().prepareStatement(query);
            retrieve.setInt(1,ruolo);
            result = retrieve.executeQuery();
            while(result.next()){
                Utente user = new Utente();
                user.setId(result.getInt("IdUtente"));
                user.setNome(result.getString("Nome"));
                user.setCognome(result.getString("Cognome"));
                user.setPwd(result.getString("Pwd"));
                user.setEmail(result.getString("Mail"));
                user.setRuolo(result.getInt("Ruolo"));
                utenti.add(user);
            }
        }finally {
            try {
                if (retrieve != null) {
                    retrieve.close();
                }
            } finally {
                if (connection.getConnection() != null) {
                    connection.getConnection().close();
                }
            }
        }
        return utenti;

    }
}
