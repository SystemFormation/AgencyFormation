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
    public boolean uploadCandidature(Candidatura candidatura) throws SQLException {
        if (!alreadyLoaded(candidatura.getIdCandidato())) {
            dao.doSaveCandidatura(candidatura);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Candidatura getCandidaturaById(int idUtente) throws SQLException {
        Candidatura result = dao.doRetrieveById(idUtente);
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
        Candidatura result = dao.doRetrieveById(idUtente);
        if (result == null) {
            return false;
        } else {
            return true;
        }

    }
}
