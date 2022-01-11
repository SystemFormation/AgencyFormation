package it.unisa.agency_formation.reclutamento.domain;

public class Colloquio {
    private StatiCandidatura stato;
    private int idCandidato;
    private int idHR;

    public Colloquio() { }

    public StatiCandidatura getStato() {
        return stato;
    }

    public void setStato(StatiCandidatura state) {
        this.stato = state;
    }


    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int id) {
        this.idCandidato = id;
    }

    public int getIdHR() {
        return idHR;
    }

    public void setIdHR(int IdHR) {
        this.idHR = IdHR;
    }


}


