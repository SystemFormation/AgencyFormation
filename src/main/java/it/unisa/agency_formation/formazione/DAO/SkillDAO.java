package it.unisa.agency_formation.formazione.DAO;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SkillDAO {
    private static final String TABLE_SKILL = "skill";
    private static final String TABLE_SKILLDIPENDENTE = "skillsdipendente";

    /**
     * Questa funzionalità permette di salvare una nuova skill
     *
     * @param skill
     * @throws SQLException
     * @pre skill!=null
     */
    public boolean doSaveSkill(Skill skill, Dipendente dip) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        if (skill != null) {
            PreparedStatement save = null;
            PreparedStatement save2 = null;

            String query = "insert into " + TABLE_SKILL + " (NomeSkill, DescrizioneSkill)" +
                    " values(?,?)";

            String query2 = "insert into" + TABLE_SKILLDIPENDENTE + " (idDipendente, idSkill, Livello) "+
                    " values(?,?,?)";
            try {
                save = connection.prepareStatement(query);
                save.setString(1, skill.getNomeSkill());
                save.setString(2, skill.getDescrizioneSkill());
                save.executeUpdate();

                save2 = connection.prepareStatement(query2);
                save2.setInt(1,dip.getIdDipendente());
                save2.setInt(2,skill.getIdSkill());
                save2.setInt(3,1);
                save2.executeUpdate();
                return true;
            } finally {
                try {
                    if (save != null)
                        save.close();
                    if (save2 != null)
                        save2.close();
                } finally {
                    if (connection != null)
                        connection.close();
                }
            }
        }
        return false;
    }

    /**
     * Questa funzionalità permette di rimuovere una skill persa
     *
     * @param IdSkill
     * @throws SQLException
     * @pre idSkill!=null
     */
    public void doRemoveSkill(int IdSkill) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
    }

    /**
     * Questa funzionalità permette di recuperare tutte le skill
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Skill> doRetrieveAll() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        String query = "Select From " + TABLE_SKILL;
        ArrayList<Skill> skills = new ArrayList<>();
        ResultSet result;
        try {
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                Skill skill = new Skill();
                skill.setNomeSkill(result.getString("NomeSkill"));
                skill.setDescrizioneSkill(result.getString("DescrizioneSkill"));
                skills.add(skill);
            }
            if (skills.size() > 0) return skills;
            else return null;
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
    public Skill doRetrieveByName(String nomeSkill) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        String query = "Select From " + TABLE_SKILL + " where NomeSkill = ?";

        Skill skill = new Skill();

        ResultSet result;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1,nomeSkill);
            result = stmt.executeQuery();
           if(result.next()) {
                skill.setNomeSkill(result.getString("NomeSkill"));
                return skill;
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
        return null;
    }
}