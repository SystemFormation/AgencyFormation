package it.unisa.agency_formation.autenticazione.domain;

import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.team.domain.Team;

import java.util.ArrayList;

public class Dipendente extends Utente {
    private int idDipendente, idTeam, annoNascita;
    private String residenza, telefono;
    private StatiDipendenti stato;
    private Team team;
    private ArrayList<Skill> skills;//da riempire tramite un'altra query

    public Dipendente(String nome, String cognome, String email, String pwd, RuoliUtenti ruolo,int idDipendente, int annoNascita, String residenza, String telefono, StatiDipendenti stato){
       super(nome,cognome,email,pwd,ruolo);
        this.idDipendente = idDipendente;
        this.annoNascita = annoNascita;
        this.residenza = residenza;
        this.telefono = telefono;
        this.stato = stato;
    }

    public Dipendente() {
    }

    public int getIdDipendente() {
        return idDipendente;
    }

    public void setIdDipendente(int idUtente) {
        this.idDipendente = idUtente;
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

    public StatiDipendenti getStato() {
        return stato;
    }

    public void setStato(StatiDipendenti stato) {
        this.stato = stato;
    }

    public void setTeam(Team team){this.team=team;}

    public Team getTeam(){
        return team;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
}
