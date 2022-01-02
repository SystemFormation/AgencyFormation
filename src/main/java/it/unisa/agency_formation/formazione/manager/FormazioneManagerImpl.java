package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;

public class FormazioneManagerImpl implements FormazioneManager {

    @Override
    public void createCompetence(int idTeam, String competenza) {

    }

    @Override
    public String viewCompetence(int idTeam) {
        return null;
    }

    @Override
    public void uploadDocument(String MaterialeDiFormazione) {

    }

    //doRetrieveById //Dipendente
    //Aggiungere doRetrieveLastId();
    //aggiungere doSaveSkillDip(x,dip);
    @Override
    public boolean addSkill(Skill skill, Dipendente dip) throws SQLException {
        if (!alreadyInsertSkill(skill)) {
            return SkillDAO.doSaveSkill(skill, dip);
        } else {
            return false;
        }


    }

    @Override
    public void removeSkill(Skill skill) {

    }

    @Override
    public void viewSkill(int idSkill) {

    }

    @Override
    public int getLastIdSkillCreated() throws  SQLException{
        return SkillDAO.doRetrieveLastId();
    }

    @Override
    public void addSkillDip(int idSkill, Dipendente dip) throws SQLException{
            SkillDAO.doSaveSkillDip(idSkill,dip);
    }

    private boolean alreadyInsertSkill(Skill skill) throws SQLException {
        Skill result = SkillDAO.doRetrieveByName(skill.getNomeSkill());
        if (result == null) {
            return false;
        } else {
            return true;
        }

    }
}
