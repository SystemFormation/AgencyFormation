package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ReclutamentoManager {

    boolean caricaCandidatura(Candidatura candidatura) throws SQLException;
    Candidatura getCandidaturaById(int idCandidato) throws SQLException;
    boolean accettaCandidature(int idCandidatura, int idHR, Timestamp data) throws SQLException;
    boolean rifiutaCandidature(int idCandidatura, int idHR) throws SQLException;
    ArrayList<Candidatura> getTutteCandidature()throws SQLException;
    boolean assumiCandidato(int idUtente) throws SQLException;
    boolean rifiutaCandidato(int idCandidatura) throws SQLException;
    boolean reCandidate(Candidatura candidatura) throws SQLException;
}
