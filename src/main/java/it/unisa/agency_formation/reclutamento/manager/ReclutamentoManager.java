package it.unisa.agency_formation.reclutamento.manager;

import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ReclutamentoManager {
    /**
     * Questa funzionalità è l'interfaccia del metodo che permette di caricare la cadidatura
     *
     * @param candidatura, specifica la candidaatura da creare
     * @return boolean (false = i parametri non vengono rispettati o la candidaturà è gia esistente,
     * true = la funzionalità va a buon fine)
     * @throws SQLException errore nella query errore nella query
     */
    boolean caricaCandidatura(Candidatura candidatura) throws SQLException;

    /**
     * Questa funzionalità è l'interfaccia del metodo che permette di restituire tutte le candidature caricate
     *
     * @return {@literal ArrayList<@link Candidatura>} , una lista con tutte le candidature
     * @throws SQLException errore nella query errore nella query
     */

    Candidatura getCandidaturaById(int idCandidato) throws SQLException;

    boolean accettaCandidatura(int idCandidatura, int idHR, Timestamp data) throws SQLException;

    boolean rifiutaCandidatura(int idCandidatura, int idHR) throws SQLException;

    ArrayList<Candidatura> getTutteCandidature() throws SQLException;

    /**
     * Questa funzionalità è l'interfaccia del metodo che permette di ricandidarsi
     *
     * @param idCandidato , identifica il candidato
     * @return boolean (false = la funzionalità non va a buon fine,
     * true = la funzionalità va a buon fine)
     * @throws SQLException errore nella query errore nella query
     */

    boolean ricandidatura(int idCandidato) throws SQLException;

    /**
     * Questa funzionalità è l'interfaccia del metodo che permette di modificare lo stato della candidatura
     *
     * @param idCandidatura , identifica la candidatura
     * @param stato         , specifica con quale stato dev'essere modificata la candidatura
     * @return boolean (false = la funzionalità non va a buon fine,
     * true = la funzionalità va a buon fine)
     * @throws SQLException errore nella query errore nella query
     */

    boolean modificaStatoCandidatura(int idCandidatura, StatiCandidatura stato) throws SQLException;
}
