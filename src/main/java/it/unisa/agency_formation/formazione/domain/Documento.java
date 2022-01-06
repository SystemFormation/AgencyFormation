package it.unisa.agency_formation.formazione.domain;

public class Documento {
    private String materialeDiFormazione;
    private int idHR;
    private int idTeam;
    private int idDocumento;

    public Documento() {
    }


    public String getMaterialeDiFormazione() {
        return materialeDiFormazione;
    }

    public void setMaterialeDiFormazione(String materialeDiFormazione) {
        this.materialeDiFormazione = materialeDiFormazione;
    }

    public int getIdHR() {
        return idHR;
    }

    public void setIdHR(int idUtente) {
        this.idHR = idUtente;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }
}
