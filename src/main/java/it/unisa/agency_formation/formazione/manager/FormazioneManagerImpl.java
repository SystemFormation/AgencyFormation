package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;

public class FormazioneManagerImpl implements FormazioneManager {
    private SkillDAO skDao;

    public FormazioneManagerImpl(SkillDAO dao) {
        this.skDao = dao;
    }

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
    //Aggiungere doRetrieveLastId();
    //aggiungere doSaveSkillDip(x,dip);
    @Override
    public boolean addSkill(Skill skill, Dipendente dip) throws SQLException {
        if (!alreadyInsertSkill(skill)) {
            return skDao.doSaveSkill(skill, dip);
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

    private boolean alreadyInsertSkill(Skill skill) throws SQLException {
        Skill result = skDao.doRetrieveByName(skill.getNomeSkill());
        if (result == null) {
            return false;
        } else {
            return true;
        }

    }
}
