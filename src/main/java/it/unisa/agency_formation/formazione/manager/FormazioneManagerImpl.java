package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;
import java.util.ArrayList;

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

    @Override
    public boolean aggiungiSkill(Skill skill) throws SQLException {

        return SkillDAO.salvaSkill(skill);

    }

    @Override
    public void rimuoviSkill(Skill skill) {

    }

    @Override
    public void visualizzaSkill(int idSkill) {

    }

    @Override
    public int getUltimaSkill() throws SQLException {
        return SkillDAO.recuperaUltimaSkill();
    }

    @Override
    public boolean addSkillDipendente(int idSkill, Dipendente dip, int skillLivello) throws SQLException {
        return SkillDAO.salvaSkillDipendente(idSkill, dip, skillLivello);
    }

    private boolean alreadyInsertSkill(Skill skill) throws SQLException {
        Skill result = SkillDAO.recuperaSkillByNome(skill.getNomeSkill());
        if (result == null) {
            return false;
        }
        return true;
    }

    @Override
    public Documento getMaterialeByIdTeam(int idTeam) throws SQLException {
        return DocumentoDAO.recuperaDocumentoByTeam(idTeam);
    }

    @Override
    public ArrayList<Skill> recuperoSkillConIdDipendente(int idDipendete) throws SQLException {
        return SkillDAO.recuperoSkillsByIdDipendente(idDipendete);
    }
}
