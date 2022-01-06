package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;

public class FormazioneManagerImpl implements FormazioneManager {

    @Override
    public void createCompetence(int idTeam, String competenza) {

    }

    @Override
    public boolean saveDocument(Documento documento) throws SQLException {
        return DocumentoDAO.doSaveDocument(documento);
    }

    @Override
    public String viewCompetence(int idTeam) {
        return null;
    }

    @Override
    public void uploadDocument(String MaterialeDiFormazione) {

    }

    //doRetrieveById //Dipendente
    @Override
    public boolean addSkill(Skill skill) throws SQLException {
        if (!alreadyInsertSkill(skill)) {
            return SkillDAO.doSaveSkill(skill);
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
    public boolean addSkillDip(int idSkill, Dipendente dip) throws SQLException{
         return SkillDAO.doSaveSkillDip(idSkill,dip);
    }

    @Override
    public Dipendente getIdEmployee(int idDip) throws SQLException {
        return DipendenteDAO.doRetrieveById(idDip);
    }

    private boolean alreadyInsertSkill(Skill skill) throws SQLException {
        Skill result = SkillDAO.doRetrieveByName(skill.getNomeSkill());
        if (result == null) {
            return false;
        } else {
            return true;
        }

    }
    @Override
    public Documento getMaterialeByIdTeam(int idTeam) throws SQLException{
        return DocumentoDAO.doRetrieveByTeam(idTeam);
    }
}
