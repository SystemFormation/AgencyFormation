package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ReclutamentoManagerImpl implements ReclutamentoManager {

    /**
     * Questo metodo permette di caricare la cadidatura
     * @param candidatura, specifica la candidaatura da creare
     * @return boolean (false = i parametri non vengono rispettati o la candidaturà è gia esistente,
                      true = la funzionalità va a buon fine)
     * @throws SQLException
     */
    @Override
    public boolean caricaCandidatura(Candidatura candidatura) throws SQLException {
        if (candidatura == null) {
            return false;
        } else {
            if (candidatura.getCurriculum() != null && candidatura.getDocumentiAggiuntivi() == null) {
                if (!alreadyLoaded(candidatura.getIdCandidato())) {
                    return CandidaturaDAO.salvaCandidaturaSenzaDocumenti(candidatura);
                } else {
                    return false;
                }
            } else {
                return CandidaturaDAO.aggiungiDocumentiAggiuntivi(candidatura.getDocumentiAggiuntivi(), candidatura.getIdCandidato());
            }
        }
    }

    /**
     * Questo metodo permette di restituire tutte le candidature caricate
     * @return ArrayList<Candidatura>
     * @throws SQLException
     */
    @Override
    public ArrayList<Candidatura> getTutteCandidature() throws SQLException {
        return CandidaturaDAO.recuperaCandidature();
    }

    /**
     * Questo metodo permette di ricandidarsi
     * @param idCandidato > 0, identifica il candidato
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean ricandidatura(int idCandidato) throws SQLException {
        return CandidaturaDAO.rimuoviCandidatura(idCandidato);
    }

    /**
     * Questo metodo permette di modificare lo stato della candidatura
     * @param idCandidatura, identifica la candidatura
     * @param stato, specifica con quale stato dev'essere modificata la candidatura
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean modificaStatoCandidatura(int idCandidatura, StatiCandidatura stato) throws SQLException {
        return CandidaturaDAO.modificaStatoCandidatura(idCandidatura, stato);
    }

    /**
     * Questo metodo permette di ritornare la candidatura in base all'id del candidato
     * @param idCandidato, identifica il candidato
     * @return Candidatura
     * @throws SQLException
     */
    @Override
    public Candidatura getCandidaturaById(int idCandidato) throws SQLException {
        return CandidaturaDAO.doRetrieveCandidaturaById(idCandidato);
    }

    /**
     * Questo metodo permette di accettare una candidatura e fissare un colloquio
     * @param idCandidatura, identifica la candidatura
     * @param idHR, identifica l'HR
     * @param data ,specifica la data del colloquio
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean accettaCandidatura(int idCandidatura, int idHR, Timestamp data) throws SQLException {
        return CandidaturaDAO.accettaCandidatura(idCandidatura, idHR, data);
    }

    /**
     * Questo metodo permette di rifiutare la candidatura
     * @param idCandidatura, identifica la candidatura
     * @param idHR, identifica l' HR
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean rifiutaCandidatura(int idCandidatura, int idHR) throws SQLException {
        return CandidaturaDAO.rifiutaCandidatura(idCandidatura, idHR);
    }

    /**
     * Questo metodo permette di controllare se la candidatura sia già esistente
     * @param idUtente, identifica il candidato
     * @return boolean (false = la candidatura non esiste,
     *                  true = la candidatura esiste)
     * @throws SQLException
     */
    private boolean alreadyLoaded(int idUtente) throws SQLException {
        if (CandidaturaDAO.doRetrieveCandidaturaById(idUtente) == null) {
            return false;
        }
        return true;
    }
}
