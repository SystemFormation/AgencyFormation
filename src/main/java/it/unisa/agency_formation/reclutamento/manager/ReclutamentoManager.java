package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ReclutamentoManager {

    boolean caricaCandidatura(Candidatura candidatura) throws SQLException;
    Candidatura getCandidaturaById(int idCandidato) throws SQLException;
    boolean accettaCandidatura(int idCandidatura, int idHR, Timestamp data) throws SQLException;
    boolean rifiutaCandidatura(int idCandidatura, int idHR) throws SQLException;
    ArrayList<Candidatura> getTutteCandidature() throws SQLException;
    boolean ricandidatura(int idCandidato) throws SQLException;
    boolean modificaStatoCandidatura(int idCandidato, StatiCandidatura stato) throws SQLException;
}
