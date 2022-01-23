package it.unisa.agency_formation.formazione.DAO;

import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SkillDAO {
    boolean salvaSkill(Skill skill) throws SQLException;

    Skill recuperaSkillByNome(String nomeSkill) throws SQLException;

    boolean salvaSkillDipendente(int idSkill, int idDip, int skillLivello) throws SQLException;

    int recuperaUltimaSkill() throws SQLException;

    ArrayList<Skill> recuperoSkillsByIdDipendente(int idDip) throws SQLException;
}
