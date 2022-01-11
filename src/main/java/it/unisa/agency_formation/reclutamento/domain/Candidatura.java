package it.unisa.agency_formation.reclutamento.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Candidatura {
    private int idCandidatura;
    private String curriculum;
    private String documentiAggiuntivi;
    private StatiCandidatura stato;
    private Date dataCandidatura;
    private int idCandidato;
    private int idHR;
    private Timestamp dataOraColloquio;

    public Candidatura() { }

    public int getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(int idCandidature) {
        this.idCandidatura = idCandidature;
    }

    public StatiCandidatura getStato() {
        return stato;
    }

    public void setStato(StatiCandidatura state) {
        this.stato = state;
    }

    public Date getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(Date dataCand) {
        this.dataCandidatura = dataCand;
    }

    public Timestamp getDataOraColloquio() {
        return dataOraColloquio;
    }

    public void setDataOraColloquio(Timestamp dataOraCol) {
        this.dataOraColloquio = dataOraCol;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int IdCandidato) {
        this.idCandidato = IdCandidato;
    }

    public int getIdHR() {
        return idHR;
    }

    public void setIdHR(int IdHR) {
        this.idHR = IdHR;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String cv) {
        this.curriculum = cv;
    }

    public String getDocumentiAggiuntivi() {
        return documentiAggiuntivi;
    }

    public void setDocumentiAggiuntivi(String docAggiuntivi) {
        this.documentiAggiuntivi = docAggiuntivi;
    }
}
