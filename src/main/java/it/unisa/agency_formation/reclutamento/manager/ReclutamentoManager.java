package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
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
    ArrayList<Candidatura> getTutteCandidature()throws SQLException;
    boolean assumiCandidato(Dipendente dipendente) throws SQLException;
    boolean rifiutaCandidato(int idCandidatura) throws SQLException;
    boolean ricandidatura(int idCandidatura) throws SQLException;
    ArrayList<Candidatura> getCandidatiConColloquio(StatiCandidatura stato)throws SQLException;
}
