package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;

import java.sql.SQLException;

public interface ReclutamentoManager {

    boolean uploadCandidatureWithoutDocument(Candidatura candidatura) throws SQLException;
    boolean uploadDocument(String document, int idUtente) throws SQLException;
    Candidatura getCandidaturaById(int idCandidato) throws SQLException;
    boolean acceptCandidature(int idCandidatura) throws SQLException;
    boolean rejectCandidature(int idCandidatura) throws SQLException;
    boolean hiringCandidate(int idUtente) throws SQLException;
    boolean rejectCandidate(int idCandidatura) throws SQLException;
}
