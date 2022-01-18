package it.unisa.agency_formation.formazione.manager;

import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;

import java.sql.SQLException;
import java.util.ArrayList;

public class FormazioneManagerImpl implements FormazioneManager {

    /**
     * Questa funzionalità permette di salvare un documento
     * @param documento, rappresenta il documento da salvare
     * @return true se il documento è stato salvato, false altrimenti
     * @throws SQLException
     */
    @Override
    public boolean salvaDocumento(Documento documento) throws SQLException {
        return DocumentoDAO.salvaDocumento(documento);
    }

    /**
     * Questa funzionalità permette di salvare una skill
     * @param skill rappresenta la skill da salvare
     * @return boolean true se la skill è stata salvata, false altrimenti
     * @throws SQLException
     */
    @Override
    public boolean aggiungiSkill(Skill skill) throws SQLException {
        return SkillDAO.salvaSkill(skill);
    }

    /**
     * Questa funzionalità permette di recuperare l'id dell'ultima skill inserita
     * @return l'id della skill, altrimenti -1
     * @throws SQLException
     */
    @Override
    public int getUltimaSkill() throws SQLException {
        return SkillDAO.recuperaUltimaSkill();
    }

    /**
     * Questa funzionalità permette di aggiungere una skill al dipendente
     *@param idSkill > 0 rappresenta l'id della skill da associare
     * @param idDip > 0 rappresenta l'id del dipendente
     * @param skillLivello > 0 && < 6 rappresenta il livello della skill
     * @return boolean true se l'inserimento è andato a buon fine, false altrimenti
     * @throws SQLException
     */
    @Override
    public boolean addSkillDipendente(int idSkill, int idDip, int skillLivello) throws SQLException {
        return SkillDAO.salvaSkillDipendente(idSkill, idDip, skillLivello);
    }

    /**
     * Questa funzionalità permette di recuperare il materiale di formazione in base al team
     * @param idTeam rappresenta l'id del team
     * @return Documento se esiste, null altrimenti
     * @throws SQLException
     */
    @Override
    public Documento getMaterialeByIdTeam(int idTeam) throws SQLException {
        return DocumentoDAO.recuperaDocumentoByTeam(idTeam);
    }

    /**
     * Questa funzionalità permette di recuperare le skill di un dipendente
     * @param idDipendete rappresenta l'id del dipendente
     * @return ArrayList<Skill> se esistono skill, null altrimenti
     * @throws SQLException
     */
    @Override
    public ArrayList<Skill> recuperoSkillConIdDipendente(int idDipendete) throws SQLException {
        return SkillDAO.recuperoSkillsByIdDipendente(idDipendete);
    }

}
