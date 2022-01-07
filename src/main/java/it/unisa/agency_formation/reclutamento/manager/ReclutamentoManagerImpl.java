package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ReclutamentoManagerImpl implements ReclutamentoManager {


    @Override
    public boolean caricaCandidatura(Candidatura candidatura) throws SQLException {
        if(candidatura == null){
            return false;
        }else {
                if(candidatura.getCurriculum()!=null && candidatura.getDocumentiAggiuntivi()==null) {
                    if(!alreadyLoaded(candidatura.getIdCandidato())) {
                        return CandidaturaDAO.salvaCandidaturaSenzaDocumenti(candidatura);
                    }else{
                        return false;
                    }
                }
                else {
                   return CandidaturaDAO.aggiungiDocumentiAggiuntivi(candidatura.getDocumentiAggiuntivi(), candidatura.getIdCandidato());
                }
            }
        }


    @Override
    public ArrayList<Candidatura> getTutteCandidature() throws SQLException {
        return CandidaturaDAO.recuperaCandidature();
    }

    @Override
    public boolean reCandidate(Candidatura candidatura) throws SQLException {
        return false;
    }

    @Override
    public Candidatura getCandidaturaById(int idCandidato) throws SQLException {
        return CandidaturaDAO.doRetrieveById(idCandidato);

    }
    //TODO TEST THIS METHOD
    @Override
    public boolean accettaCandidature(int idCandidatura, int idHR, Timestamp data) throws SQLException {
        return CandidaturaDAO.accettaCandidatura(idCandidatura,idHR,data);
    }
    //TODO TEST THIS METHOD
    @Override
    public boolean rifiutaCandidature(int idCandidatura, int idHR) throws SQLException{
        if(CandidaturaDAO.rifiutaCandidatura(idCandidatura,idHR)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean assumiCandidato(int idUtente) throws SQLException{
        //TODO
        return false;
    }
    @Override
    public boolean rifiutaCandidato(int idCandidatura) throws SQLException{
        if(CandidaturaDAO.rimuoviCandidatura(idCandidatura)){
            CandidaturaDAO.modificaStato(idCandidatura, StatiCandidatura.Rifiutata);
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
