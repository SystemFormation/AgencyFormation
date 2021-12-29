package it.unisa.agency_formation.autenticazione.domain;

public class Dipendente {
    private int idDipendente, idTeam, annoNascita;
    private String residenza,telefono;
    private boolean stato;

    public Dipendente(int idDipendente, int idTeam, int annoNascita, String residenza, String telefono, boolean stato) {
        this.idDipendente = idDipendente;
        this.idTeam = idTeam;
        this.annoNascita = annoNascita;
        this.residenza = residenza;
        this.telefono = telefono;
        this.stato = stato;
    }

    public Dipendente(){}

    public int getIdDipendente() {
        return idDipendente;
    }

    public void setIdDipendente(int idUtente) {
        this.idDipendente= idUtente;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public int getAnnoNascita() {
        return annoNascita;
    }

    public void setAnnoNascita(int annoNascita) {
        this.annoNascita = annoNascita;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
}
