package it.unisa.agency_formation.reclutamento.DAO;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface CandidaturaDAO {

    boolean salvaCandidaturaSenzaDocumenti(Candidatura candidatura) throws SQLException;
    boolean aggiungiDocumentiAggiuntivi(String document, int idUtente) throws SQLException;
    Candidatura doRetrieveCandidaturaById(int idCandidato) throws SQLException;
    ArrayList<Candidatura> recuperaCandidature() throws SQLException;
    boolean modificaStatoCandidatura(int idCandidatura, StatiCandidatura stato) throws SQLException;
    boolean rimuoviCandidatura(int idCandidato) throws SQLException;
    boolean rifiutaCandidatura(int idCandidatura, int idHR) throws SQLException;
    boolean accettaCandidatura(int idCandidatura, int idHR, Timestamp data) throws SQLException;


}
