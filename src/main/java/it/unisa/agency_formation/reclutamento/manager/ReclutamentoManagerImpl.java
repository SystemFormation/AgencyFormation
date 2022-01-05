package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;

import java.sql.SQLException;

public class ReclutamentoManagerImpl implements ReclutamentoManager {


    @Override
    public boolean uploadCandidature(Candidatura candidatura) throws SQLException {
        if(candidatura == null){
            return false;
        }else {
            if (!alreadyLoaded(candidatura.getIdCandidato())) {
                if(candidatura.getCurriculum()!=null && candidatura.getDocumentiAggiuntivi()==null) {
                    return CandidaturaDAO.doSaveCandidaturaWithoutDocument(candidatura);
                }
                else{
                   return CandidaturaDAO.addDocument(candidatura.getDocumentiAggiuntivi(), candidatura.getIdCandidato());
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean reCandidate(Candidatura candidatura) {
        return false;
    }

    @Override
    public Candidatura getCandidaturaById(int idCandidato) throws SQLException {
        Candidatura result = CandidaturaDAO.doRetrieveById(idCandidato);
        if (result != null) {
            return result;
        } else {
            return null;
        }
    }
    //TODO TEST THIS METHOD
    @Override
    public boolean acceptCandidature(int idCandidatura, int idHR) throws SQLException {
        return CandidaturaDAO.acceptCandidatura(idCandidatura,idHR);
    }
    //TODO TEST THIS METHOD
    @Override
    public boolean rejectCandidature(int idCandidatura) throws SQLException{
        if(CandidaturaDAO.doRejectCandidatura(idCandidatura)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean hiringCandidate(int idUtente) throws SQLException{
        //TODO
        return false;
    }
//DA CONTROLLARE
    @Override
    public boolean rejectCandidate(int idCandidatura) throws SQLException{
        if(CandidaturaDAO.doRemoveCandidatura(idCandidatura)==true){
            CandidaturaDAO.updateState(idCandidatura, StatiCandidatura.Rifiutata);
            return true;
        } else{
            return false;
        }
    }

    private boolean alreadyLoaded(int idUtente) throws SQLException {
        if (CandidaturaDAO.doRetrieveById(idUtente) == null) {
            return false;
        } else {
            return true;
        }

    }
}
