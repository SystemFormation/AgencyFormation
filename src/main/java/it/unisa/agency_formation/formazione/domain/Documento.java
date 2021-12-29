package it.unisa.agency_formation.formazione.domain;

public class Documento {
    private String materialeDiFormazione;
    private int idUtente;
    private int idTeam;

    public Documento() {}
    public Documento(String materialeDiFormazione, int idUtente, int idTeam) {
        this.materialeDiFormazione = materialeDiFormazione;
        this.idUtente = idUtente;
        this.idTeam = idTeam;
    }
    public String getMaterialeDiFormazione() {return materialeDiFormazione;}
    public void setMaterialeDiFormazione(String materialeDiFormazione) {this.materialeDiFormazione = materialeDiFormazione;}
    public int getIdUtente() {return idUtente;}
    public void setIdUtente(int idUtente) {this.idUtente = idUtente;}
    public int getIdTeam() {return idTeam;}
    public void setIdTeam(int idTeam) {this.idTeam = idTeam;}
}
