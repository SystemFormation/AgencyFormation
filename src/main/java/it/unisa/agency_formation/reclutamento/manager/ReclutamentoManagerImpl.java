package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ReclutamentoManagerImpl implements ReclutamentoManager {


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

    @Override
    public ArrayList<Candidatura> getTutteCandidature() throws SQLException {
        return CandidaturaDAO.recuperaCandidature();
    }

    @Override
    public boolean ricandidatura(int idCandidato) throws SQLException {
        return CandidaturaDAO.rimuoviCandidatura(idCandidato);
    }

    @Override
    public ArrayList<Candidatura> getCandidatiConColloquio(StatiCandidatura stato) throws SQLException {
        return CandidaturaDAO.recuperaCandidatureByStato(stato);
    }

    @Override
    public boolean modificaStatoCandidatura(int idCandidatura, StatiCandidatura stato) throws SQLException {
        return CandidaturaDAO.modificaStatoCandidatura(idCandidatura, stato);
    }

    @Override
    public Candidatura getCandidaturaById(int idCandidato) throws SQLException {
        return CandidaturaDAO.doRetrieveCandidaturaById(idCandidato);

    }

    @Override
    public boolean accettaCandidatura(int idCandidatura, int idHR, Timestamp data) throws SQLException {
        return CandidaturaDAO.accettaCandidatura(idCandidatura, idHR, data);
    }

    @Override
    public boolean rifiutaCandidatura(int idCandidatura, int idHR) throws SQLException {
        return CandidaturaDAO.rifiutaCandidatura(idCandidatura, idHR);
    }

    @Override
    public boolean rifiutaCandidato(int idCandidatura) throws SQLException {
        return CandidaturaDAO.modificaStatoCandidatura(idCandidatura, StatiCandidatura.Rifiutata);
    }

    private boolean alreadyLoaded(int idUtente) throws SQLException {
        if (CandidaturaDAO.doRetrieveCandidaturaById(idUtente) == null) {
            return false;
        }
        return true;
    }
}
