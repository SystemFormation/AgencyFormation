package it.unisa.agency_formation.autenticazione.domain;

import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.team.domain.Team;

import java.util.ArrayList;

public class Dipendente extends Utente {
    private int idDipendente;
    private int annoNascita;
    private String residenza;
    private String telefono;
    private StatiDipendenti stato;
    private Team team;
    private ArrayList<Skill> skills;

    public Dipendente(String name, String surname, String mail, String password, RuoliUtenti role, int idDip, int yearNascita, String residence, String tel, StatiDipendenti state) {
        super(name, surname, mail, password, role);
        this.idDipendente = idDip;
        this.annoNascita = yearNascita;
        this.residenza = residence;
        this.telefono = tel;
        this.stato = state;
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

    public void setAnnoNascita(int yearNascita) {
        this.annoNascita = yearNascita;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenz) {
        this.residenza = residenz;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String tel) {
        this.telefono = tel;
    }

    public StatiDipendenti getStato() {
        return stato;
    }

    public void setStato(StatiDipendenti state) {
        this.stato = state;
    }

    public void setTeam(Team Team) {
        this.team = Team;
    }

    public Team getTeam() {
        return team;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skill) {
        this.skills = skill;
    }
}
