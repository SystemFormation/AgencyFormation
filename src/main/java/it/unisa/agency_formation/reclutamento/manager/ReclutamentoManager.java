package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;

import java.sql.SQLException;

public interface ReclutamentoManager {
    boolean uploadCandidature(Candidatura candidatura) throws SQLException;
    Candidatura getCandidaturaById(int idUtente) throws SQLException;
    boolean acceptCandidature(int idCandidatura);
    boolean rejectCandidature(int idCandidatura);
    boolean hiringCandidate(int idUtente);
    boolean rejectCandidate(int idCandidatura);
}
