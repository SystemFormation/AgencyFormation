package it.unisa.agency_formation.reclutamento.domain;

import java.sql.Date;
import java.sql.Timestamp;

//todo refactor
public class Candidatura {
    private int idCandidatura;
    private String curriculum;
    private String documentiAggiuntivi;
    private StatiCandidatura stato;
    private Date dataCandidatura;
    private int idCandidato;
    private int idHR;
    private Timestamp dataOraColloquio;

    public Candidatura() {
    }


    public int getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(int idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public StatiCandidatura getStato() {
        return stato;
    }

    public void setStato(StatiCandidatura stato) {
        this.stato = stato;
    }

    public Date getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(Date dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public Timestamp getDataOraColloquio() {
        return dataOraColloquio;
    }

    public void setDataOraColloquio(Timestamp dataOraColloquio) {
        this.dataOraColloquio = dataOraColloquio;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getIdHR() {
        return idHR;
    }

    public void setIdHR(int idHR) {
        this.idHR = idHR;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getDocumentiAggiuntivi() {
        return documentiAggiuntivi;
    }

    public void setDocumentiAggiuntivi(String documentiAggiuntivi) {
        this.documentiAggiuntivi = documentiAggiuntivi;
    }
}
