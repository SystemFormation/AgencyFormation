package it.unisa.agency_formation.reclutamento.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Candidatura {
    private int idCandidatura;
    private String cv;
    private String attestati;
    private String certificazioni;
    private String stato;
    private Date dataCandidatura;
    private int idCandidato;
    private int idHR;
    private Timestamp dataOraColloquio;

    public Candidatura() {}


    public int getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(int idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getAttestati() {
        return attestati;
    }

    public void setAttestati(String attestati) {
        this.attestati = attestati;
    }

    public String getCertificazioni() {
        return certificazioni;
    }

    public void setCertificazioni(String certificazioni) {
        this.certificazioni = certificazioni;
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
}
