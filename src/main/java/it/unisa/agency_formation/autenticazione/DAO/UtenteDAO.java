package it.unisa.agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO {
    private static final String TABLE_UTENTE = "utenti";

    /**
     * Questo metodo permette di salvare un utente
     *
     * @param user è l'utente da registrare
     * @return boolean
     * @throws SQLException eccezione
     * @pre user!=null
     */
    public static boolean salvaUtente(Utente user) throws SQLException {
        if (user == null) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement save = null;
        String query = "insert into " + TABLE_UTENTE + "(Nome,Cognome,Pwd,Mail,Ruolo)"
                + " values(?,?,?,?,?)";
        try {
            save = connection.prepareStatement(query);
            save.setString(1, user.getName());
            save.setString(2, user.getSurname());
            save.setString(3, user.getPwd());
            save.setString(4, user.getEmail());
            switch (user.getRole()) {
                case CANDIDATO:
                    save.setInt(5, 1);
                    break;
                case DIPENDENTE:
                    save.setInt(5, 2);
                    break;
                case TM:
                    save.setInt(5, 3);
                    break;
                case HR:
                    save.setInt(5, 4);
                    break;
            }
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
     * Questo metodo permette di recuperare un utente
     *
     * @param email è l'email dell'utente
     * @param pwd   è la password dell'utente
     * @return Utente l'utente loggato
     * @throws SQLException
     * @pre email!=null
     * @pre pwd!=null
     */
    public static Utente login(String email, String pwd) throws SQLException {
        if (email == null || pwd == null) {
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_UTENTE + " where Mail=? and Pwd=?";
        Utente user = new Utente();
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setString(1, email);
            retrieve.setString(2, pwd);
            result = retrieve.executeQuery();
            if (result.next()) {
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
                }
                return user;
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
        return null;
    }

    /**
     * Questo medoto ritorna l'utente con l'id indicato
     *
     * @param id
     * @return Utente
     * @throws SQLException
     * @pre id>0
     */
    public static Utente doRetrieveUtenteByID(int id) throws SQLException {
        if (id <= 0) {
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "SELECT * FROM " + TABLE_UTENTE + " WHERE IdUtente=?";
        Utente user = new Utente();
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setInt(1, id);
            result = retrieve.executeQuery();
            if (result.next()) {
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
                }
                return user;
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
        return null;
    }


    /**
     * Questo metodo permette di recuperare degli utenti attraverso il ruolo
     *
     * @param ruolo
     * @return arraylist di utenti
     * @throws SQLException
     * @pre ruolo>0 and ruolo<=4
     * @post utenti.size()>0
     */


    public static ArrayList<Utente> doRetrieveUtenteByRuolo(RuoliUtenti ruolo) throws SQLException {
        if (ruolo == null) {
            return null;
        }
        int role = 0;
        switch (ruolo) {
            case CANDIDATO:
                role = 1;
                break;
            case DIPENDENTE:
                role = 2;
                break;
            case TM:
                role = 3;
                break;
            case HR:
                role = 4;
                break;
        }
        if (role == 0) {
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_UTENTE + " Where Ruolo=?";

        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setInt(1, role);
            result = retrieve.executeQuery();
            while (result.next()) {
                Utente user = new Utente();
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
                }
                utenti.add(user);
            }
            if (utenti.size() > 0) {
                return utenti;
            } else {
                utenti = null;
                return utenti;
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

    //TODO TEST THIS METHOD
    public static ArrayList<Utente> doRetrieveCandidatoConCandidatura() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement retrieve = null;
        String query = "select IdUtente,Nome,Cognome,Pwd,Mail,Ruolo from utenti inner join candidature " +
                "on utenti.IdUtente=candidature.IdCandidato and candidature.Stato NOT IN (?,?)";

        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            retrieve = connection.prepareStatement(query);
            retrieve.setString(1, StatiCandidatura.Rifiutata.toString());
            retrieve.setString(2, StatiCandidatura.Accettata.toString());
            result = retrieve.executeQuery();
            while (result.next()) {
                Utente user = new Utente();
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
                }
                utenti.add(user);
            }
            if (utenti.size() > 0) {
                return utenti;
            } else {
                utenti = null;
                return utenti;
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
}
