package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FormazioneManager {

    boolean salvaDocumento(Documento documento) throws SQLException;

    Documento getMaterialeByIdTeam(int idTeam) throws SQLException;

    boolean aggiungiSkill(Skill skill) throws SQLException;

    int getUltimaSkill() throws SQLException;

    boolean addSkillDipendente(int idSkill, int iddip, int skillLivello) throws SQLException;

    ArrayList<Skill> recuperoSkillConIdDipendente(int idDipendete) throws SQLException;
}
