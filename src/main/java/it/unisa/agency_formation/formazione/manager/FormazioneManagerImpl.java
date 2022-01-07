package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;

public class FormazioneManagerImpl implements FormazioneManager {

    @Override
    public void creaCompetenza(int idTeam, String competenza) throws SQLException {

    }

    @Override
    public boolean salvaDocumento(Documento documento) throws SQLException {
        return DocumentoDAO.salvaDocumento(documento);
    }

    @Override
    public String visualizzaCompetenza(int idTeam) {
        return null;
    }

    @Override
    public void caricaDocumenti(String MaterialeDiFormazione) {

    }

    //doRetrieveById //Dipendente
    @Override
    public boolean aggiungiSkill(Skill skill) throws SQLException {

            return SkillDAO.salvaSkill(skill);

    }

    @Override
    public void remuoviSkill(Skill skill) {

    }

    @Override
    public void visualizzaSkill(int idSkill) {

    }

    @Override
    public int getLastIdSkillCreated() throws  SQLException{
        return SkillDAO.doRetrieveLastId();
    }

    @Override
    public boolean addSkillDipendente(int idSkill, Dipendente dip) throws SQLException{
         return SkillDAO.salvaSkillDipendente(idSkill,dip);
    }

    @Override
    public Dipendente getIdDipendente(int idDip) throws SQLException {
        return DipendenteDAO.doRetrieveById(idDip);
    }

    private boolean alreadyInsertSkill(Skill skill) throws SQLException {
        Skill result = SkillDAO.recuperaSkillByNome(skill.getNomeSkill());
        if (result == null) {
            return false;
        } else {
            return true;
        }

    }
    @Override
    public Documento getMaterialeByIdTeam(int idTeam) throws SQLException{
        return DocumentoDAO.recuperaByTeam(idTeam);
    }
}
