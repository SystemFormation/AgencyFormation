package it.unisa.agency_formation.reclutamento.domain;

import java.sql.Date;
import java.sql.Timestamp;


public class Colloquio {
    private StatiCandidatura stato;
    private int idCandidato;
    private int idHR;

    public Colloquio() {

    public StatiCandidatura getStato() {
        return stato;
    }

    public void setStato(StatiCandidatura stato) {
        this.stato = stato;
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
}


