package it.unisa.agency_formation.reclutamento.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Candidatura {
    private int idCandidatura;
    private String curriculum;
    private String documentiAggiuntivi;
    private String stato;
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

    public String getCv() {
        return curriculum;
    }

    public void setCv(String cv) {
        this.curriculum = cv;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Date getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(Date dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public Date getDataOraColloquio() {
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
