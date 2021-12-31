package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;

public interface FormazioneManager {
    void createCompetence(int idTeam, String competenza);

    String viewCompetence(int idTeam);

    void uploadDocument(String MaterialeDiFormazione);

    //   Documento viewDocument(int idDocument);     Fare Entita Documento
    boolean addSkill(Skill skill, Dipendente dip) throws SQLException;

    void removeSkill(Skill skill);

    void viewSkill(int idSkill);

    int getLastIdSkillCreated() throws SQLException;

    void addSkillDip(int idSkill, Dipendente dip) throws SQLException;
}
