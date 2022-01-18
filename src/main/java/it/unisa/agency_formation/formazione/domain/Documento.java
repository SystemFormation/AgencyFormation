package it.unisa.agency_formation.formazione.domain;

public class Documento {
    private String materialeDiFormazione;
    private int idHR;
    private int idTeam;
    private int idDocumento;

    public Documento() {
    }

    public Documento(int IdDocumento, String MaterialeDiFormazione, int IdHR, int IdTeam) {
        this.idDocumento = IdDocumento;
        this.materialeDiFormazione = MaterialeDiFormazione;
        this.idHR = IdHR;
        this.idTeam = IdTeam;
    }


    public String getMaterialeDiFormazione() {
        return materialeDiFormazione;
    }

    public void setMaterialeDiFormazione(String materialeFormazione) {
        this.materialeDiFormazione = materialeFormazione;
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

    public void setIdTeam(int id) {
        this.idTeam = id;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDoc) {
        this.idDocumento = idDoc;
    }
}
