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
     *
     * @param documento, rappresenta il documento da salvare
     * @return boolean (true = il documento è stato salvato, false = altrimenti)
     * @throws SQLException errore nella query errore nella query
     */
    @Override
    public boolean salvaDocumento(Documento documento) throws SQLException {
        return DocumentoDAO.salvaDocumento(documento);
    }

    /**
     * Questa funzionalità permette di salvare una skill
     *
     * @param skill rappresenta la skill da salvare
     * @return boolean (true = la skill è stata salvata, false = altrimenti)
     * @throws SQLException errore nella query errore nella query
     */
    @Override
    public boolean aggiungiSkill(Skill skill) throws SQLException {
        return SkillDAO.salvaSkill(skill);
    }

    /**
     * Questa funzionalità permette di recuperare l'id dell'ultima skill inserita
     * @return l'id della skill, altrimenti -1
     * @throws SQLException errore nella query errore nella query*/

    @Override
    public int getUltimaSkill() throws SQLException {
        return SkillDAO.recuperaUltimaSkill();
    }

    /**
     * Questa funzionalità permette di aggiungere una skill al dipendente
     *
     * @param idSkill , rappresenta l'id della skill da associare
     * @param idDip , rappresenta l'id del dipendente
     * @param skillLivello , rappresenta il livello della skill
     * @return boolean (true = l'inserimento è andato a buon fine, false = altrimenti)
     * @throws SQLException errore nella query errore nella query*/

    @Override
    public boolean addSkillDipendente(int idSkill, int idDip, int skillLivello) throws SQLException {
        return SkillDAO.salvaSkillDipendente(idSkill, idDip, skillLivello);
    }

    /**
     * Questa funzionalità permette di recuperare il materiale di formazione in base al team
     *
     * @param idTeam , rappresenta l'id del team
     * @return Documento se esiste, null altrimenti
     * @throws SQLException errore nella query errore nella query*/

    @Override
    public Documento getMaterialeByIdTeam(int idTeam) throws SQLException {
        return DocumentoDAO.recuperaDocumentoByTeam(idTeam);
    }

    /**
     * Questa funzionalità permette di recuperare le skill di un dipendente
     *
     * @param idDipendente , rappresenta l'id del dipendente
     * @return {@literal ArrayList<@link Skill>} se esistono skill, null altrimenti
     * @throws SQLException errore nella query errore nella query*/

    @Override
    public ArrayList<Skill> recuperoSkillConIdDipendente(int idDipendente) throws SQLException {
        return SkillDAO.recuperoSkillsByIdDipendente(idDipendente);
    }

}
