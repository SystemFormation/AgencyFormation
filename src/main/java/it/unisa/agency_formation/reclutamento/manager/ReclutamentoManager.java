package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;

import java.sql.SQLException;

public interface ReclutamentoManager {

    boolean uploadCandidatureWithoutDocument(Candidatura candidatura) throws SQLException;

    boolean uploadDocument(String document, int idUtente) throws SQLException;

    Candidatura getCandidaturaById(int idCandidato) throws SQLException;

    boolean acceptCandidature(int idCandidatura);

    boolean rejectCandidature(int idCandidatura);

    boolean hiringCandidate(int idUtente);

    boolean rejectCandidate(int idCandidatura);
}
