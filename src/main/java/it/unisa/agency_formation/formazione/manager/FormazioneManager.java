package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;

public interface FormazioneManager {
    void createCompetence(int idTeam, String competenza);
    boolean saveDocument(Documento documento) throws SQLException;
    String viewCompetence(int idTeam);

    void uploadDocument(String MaterialeDiFormazione);
    String getMaterialeByIdTeam(int idTeam) throws SQLException;
    //   Documento viewDocument(int idDocument);     Fare Entita Documento
    boolean addSkill(Skill skill) throws SQLException;

    void removeSkill(Skill skill);

    void viewSkill(int idSkill);

    int getLastIdSkillCreated() throws SQLException;

    boolean addSkillDip(int idSkill, Dipendente dip) throws SQLException;

    Dipendente getIdEmployee(int idDip) throws  SQLException;
}
