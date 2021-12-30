package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;

import java.sql.SQLException;

public class ReclutamentoManagerImpl implements ReclutamentoManager {
    private CandidaturaDAO dao;

    public ReclutamentoManagerImpl(CandidaturaDAO dao) {
        this.dao = dao;
    }


    @Override
    public boolean uploadCandidatureComplete(Candidatura candidatura) throws SQLException {
        /*
        if (!alreadyLoaded(candidatura.getIdCandidato())) {
            dao.doSaveCandidaturaComplete(candidatura);
            return true;
        } else {
            return false;
        }
        */
         return false;
    }


    public boolean uploadCandidatureWithoutDocument(Candidatura candidatura) throws SQLException {
        if (!alreadyLoaded(candidatura.getIdCandidato())) {
            dao.doSaveCandidaturaWithoutDocument(candidatura);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean uploadDocument(String document, int idUtente) throws SQLException {
        if(dao.addDocument(document,idUtente)){
            return true;
        }
        return false;
    }

    @Override
    public Candidatura getCandidaturaById(int idCandidato) throws SQLException {
        Candidatura result = dao.doRetrieveById(idCandidato);
        if (result != null) {
            return result;
        } else {
            return null;
        }
    }

    @Override
    public boolean acceptCandidature(int idCandidatura) {
        //TODO
        return false;
    }

    @Override
    public boolean rejectCandidature(int idCandidatura) {
        //TODO
        return false;
    }

    @Override
    public boolean hiringCandidate(int idUtente) {
        //TODO
        return false;
    }

    @Override
    public boolean rejectCandidate(int idCandidatura) {
        //TODO
        return false;
    }

    private boolean alreadyLoaded(int idUtente) throws SQLException {
        if (dao.doRetrieveById(idUtente) == null) {
            return false;
        } else {
            return true;
        }

    }
}
