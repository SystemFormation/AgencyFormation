package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;

import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ReclutamentoManager {

    boolean uploadCandidature(Candidatura candidatura) throws SQLException;
    Candidatura getCandidaturaById(int idCandidato) throws SQLException;
    boolean acceptCandidature(int idCandidatura, int idHR, Timestamp data) throws SQLException;
    boolean rejectCandidature(int idCandidatura,int idHR) throws SQLException;
    ArrayList<Candidatura> getAll()throws SQLException;
    boolean hiringCandidate(int idUtente) throws SQLException;
    boolean rejectCandidate(int idCandidatura) throws SQLException;
    boolean reCandidate(Candidatura candidatura);
}
