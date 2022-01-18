package it.unisa.agency_formation.formazione.DAO;

import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SkillDAO {
    private static final String TABLE_SKILL = "skill";
    private static final String TABLE_SKILLDIPENDENTE = "skillsdipendenti";

    /**
     * Questa funzionalità permette di salvare una nuova skill
     * @param skill != null, rappresenta la skill da salvare
     * @throws SQLException
     * @return boolean true se la skill è stata salvata, false altrimenti
     */
    public static boolean salvaSkill(Skill skill) throws SQLException {
        if (skill == null) {
            return false;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
            PreparedStatement save = null;
            String query = "insert into " + TABLE_SKILL + " (NomeSkill, DescrizioneSkill)"
                    + " values(?,?)";
            try {
                save = connection.prepareStatement(query);
                save.setString(1, skill.getNomeSkill());
                save.setString(2, skill.getDescrizioneSkill());
                return save.executeUpdate() != 0;
            } finally {
                DatabaseManager.closeConnessione(connection);
            }
        }


    /**
     * Questa funzionalità permette di recuperare una skill tramite il nome
     * @param nomeSkill != null, rappresenta il nome della skill da recuperare
     * @return skill se la skill è presente, null altrimenti
     * @throws SQLException
     */
    public static Skill recuperaSkillByNome(String nomeSkill) throws SQLException {
        if (nomeSkill == null) {
            return null;
        }
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement stmt = null;
        String query = "Select * From " + TABLE_SKILL + " where NomeSkill=?";
        Skill skill = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nomeSkill);
            result = stmt.executeQuery();
            if (result.next()) {
                skill = new Skill();
                skill.setNomeSkill(result.getString("NomeSkill"));
                skill.setDescrizioneSkill(result.getString("DescrizioneSkill"));
                skill.setIdSkill(result.getInt("IdSkill"));
            }
                return skill;
        } finally {
            DatabaseManager.closeConnessione(connection);

        }
    }

    /**
     * Questa funzionalità permette di salvare una skill ad un dipendente
     * @param idSkill > 0 rappresenta l'id della skill da associare
     * @param idDip > 0 rappresenta l'id del dipendente
     * @param skillLivello > 0 && < 6 rappresenta il livello della skill
     * @throws SQLException
     * @return boolean true se il salvataggio va a buon fine, false altrimenti
     */
    public static boolean salvaSkillDipendente(int idSkill, int idDip, int skillLivello) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        if (idSkill < 1 || idDip < 1) {
            return false;
        }
        PreparedStatement save = null;
        String query = "insert into " + TABLE_SKILLDIPENDENTE + " (idDipendente, idSkill, Livello) "
                + " values(?,?,?)";
        try {
            save = connection.prepareStatement(query);
            save.setInt(1, idDip);
            save.setInt(2, idSkill);
            save.setInt(3, skillLivello);
            return save.executeUpdate() != 0;
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }


    /**
     * Questa funzionalità permette di recuperare l'id dell'ultima skill aggiunta
     * @return id ultima skill, altrimenti -1
     * @throws SQLException
     */
    public static int recuperaUltimaSkill() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement stmt = null;
        String query = " SELECT max(IdSkill) FROM " + TABLE_SKILL;
        int n = -1;
        try {
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();
            if (result.next()) {
                n = result.getInt(1);
            }
                return n;
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }

    /**
     * Questa funzionalità permette di recuperare tutte le skill di un dipendente
     * @param idDip > 0 rappresenta l'id del dipendente
     * @return ArrayList<Skill> se il dipendente ha delle skill, null altrimenti
     * @throws SQLException
     */
    public static ArrayList<Skill> recuperoSkillsByIdDipendente(int idDip) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        ResultSet result;
        PreparedStatement stmt = null;
        ArrayList<Skill> skills = new ArrayList<>();
        String query = "Select * from " + TABLE_SKILL + " inner join " + TABLE_SKILLDIPENDENTE + " where skill.Idskill=skillsdipendenti.IdSkill and skillsdipendenti.IdDipendente=?";
        stmt = connection.prepareStatement(query);
        try {
            stmt.setInt(1, idDip);
            result = stmt.executeQuery();
            while (result.next()) {
                Skill skill = new Skill();
                skill.setIdSkill(result.getInt("IdSkill"));
                skill.setNomeSkill(result.getString("NomeSkill"));
                skill.setDescrizioneSkill(result.getString("DescrizioneSkill"));
                skills.add(skill);
            }
            return skills.size()>0 ? skills : null;
        } finally {
            DatabaseManager.closeConnessione(connection);
        }
    }
}
