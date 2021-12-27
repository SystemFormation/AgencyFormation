package it.unisa.agency_formation.autenticazione.manager.DAO;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DipendenteDAO {
    private static final String TABLE_DIPENDENTE = "Dipendente";
    private DatabaseManager connection;
    private ResultSet result;

    /**
     * Questa funzionalità permette di salvare un dipendente
     *
     * @param dipendente è il dipendente da registrare
     * @return void
     * @throws SQLException
     * @pre dip!=null
     */
    public boolean doSaveEmploye(Dipendente dipendente) throws SQLException {
        if(dipendente == null){
            return false;
        }
        PreparedStatement save = null;
        String query = "insert into " + TABLE_DIPENDENTE + " (idUtente, Residenza,Telefono,Stato,AnnoNascita,idTeam)" +
                " values(?,?,?,?,?,?)";
        try {
            save = connection.getConnection().prepareStatement(query);
            save.setInt(1, dipendente.getIdUtente());
            save.setString(2, dipendente.getResidenza());
            save.setString(3, dipendente.getTelefono());
            save.setBoolean(4, dipendente.isStato());
            save.setInt(5, dipendente.getAnnoNascita());
            save.setInt(6, dipendente.getIdTeam());
            int result = save.executeUpdate();
            if(result !=-1){return true;}
            else{return false;}
        } finally {
            try {
                if (save != null)
                    save.close();
            } finally {
                if (connection.getConnection() != null)
                    connection.getConnection().close();
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
    public Dipendente doRetrieveById(int id) throws SQLException {
        if(id<=0){return null;}
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_DIPENDENTE + " where IdUtente=?";
        Dipendente user = new Dipendente();
        try {
            retrieve = connection.getConnection().prepareStatement(query);
            retrieve.setInt(1, id);
            result = retrieve.executeQuery();
            if (result.next()) {
                user.setIdUtente(result.getInt("IdUtente"));
                user.setResidenza(result.getString("Residenza"));
                user.setTelefono(result.getString("Telefono"));
                user.setStato(result.getBoolean("Stato"));
                user.setAnnoNascita(result.getInt("AnnoNascita"));
                user.setIdTeam(result.getInt("IdTeam"));
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
     * Questa funzionalità permette di recuperare tutti i dipendenti
     * @return arraylist di dipendenti
     * @throws SQLException
     * @post dipendenti.size()>0
     */
    public ArrayList<Dipendente> doRetrieveAll() throws SQLException {
        PreparedStatement retrieve = null;
        String query = "Select * from " + TABLE_DIPENDENTE;
        ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
        try {
            retrieve = connection.getConnection().prepareStatement(query);
            result = retrieve.executeQuery();
            while (result.next()) {
                Dipendente dip = new Dipendente();
                dip.setIdUtente(result.getInt("IdUtente"));
                dip.setResidenza(result.getString("Residenza"));
                dip.setTelefono(result.getString("Telefono"));
                dip.setStato(result.getBoolean("Stato"));
                dip.setAnnoNascita(result.getInt("AnnoNascita"));
                dip.setIdTeam(result.getInt("IdTeam"));
                dipendenti.add(dip);
            }
            if(dipendenti.size()>0){return dipendenti;}
            else{return null;}
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
    }

    /**
     * Questa funzionalità permette di modificare lo stato del dipendente
     *
     * @param idUtente
     * @param stato
     * @throws SQLException
     * @pre idUtente>0
     *
     */
    public boolean updateState(int idUtente, boolean stato) throws SQLException {
        if(idUtente<=0){return false;}
        PreparedStatement retrieve = null;
        String query = "update " + TABLE_DIPENDENTE + " set Stato= ? where IdUtente=?";
        try {
            retrieve = connection.getConnection().prepareStatement(query);
            retrieve.setBoolean(1,stato);
            retrieve.setInt(2,idUtente);
            int result = retrieve.executeUpdate();
            if(result != -1){
                return true;
            }else{return false;}
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
    }

    /**
     * Questa funzionalità permette di recuperare un dipendente attraverso lo stato
     * @param stato
     * @return arraylist di dipendenti
     * @throws SQLException
     * @post dipendenti.size()>0
     */
    public ArrayList<Dipendente> doRetrieveBySatate(boolean stato) throws SQLException{
        PreparedStatement retrieve = null;
        String query = "Select * from "+TABLE_DIPENDENTE+" Where Stato=?";
        ArrayList<Dipendente> dipendenti= new ArrayList<Dipendente>();
        try{
            retrieve = connection.getConnection().prepareStatement(query);
            retrieve.setBoolean(1,stato);
            result = retrieve.executeQuery();
            while (result.next()) {
                Dipendente dip = new Dipendente();
                dip.setIdUtente(result.getInt("IdUtente"));
                dip.setResidenza(result.getString("Residenza"));
                dip.setTelefono(result.getString("Telefono"));
                dip.setStato(result.getBoolean("Stato"));
                dip.setAnnoNascita(result.getInt("AnnoNascita"));
                dip.setIdTeam(result.getInt("IdTeam"));
                dipendenti.add(dip);
            }
            if(dipendenti.size()>0){return dipendenti;}
            else{return null;}
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
    }

}