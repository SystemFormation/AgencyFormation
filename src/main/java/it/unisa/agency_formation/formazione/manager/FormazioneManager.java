package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FormazioneManager {
    void creaCompetenza(int idTeam, String competenza) throws SQLException;

    boolean salvaDocumento(Documento documento) throws SQLException;

    String visualizzaCompetenza(int idTeam);

    void caricaDocumenti(String MaterialeDiFormazione);

    Documento getMaterialeByIdTeam(int idTeam) throws SQLException;

    boolean aggiungiSkill(Skill skill) throws SQLException;

    void rimuoviSkill(Skill skill);

    void visualizzaSkill(int idSkill);

    int getUltimaSkill() throws SQLException;

    boolean addSkillDipendente(int idSkill, Dipendente dip, int skillLivello) throws SQLException;

    ArrayList<Skill> recuperoSkillConIdDipendete(int idDipendete) throws SQLException;
}
