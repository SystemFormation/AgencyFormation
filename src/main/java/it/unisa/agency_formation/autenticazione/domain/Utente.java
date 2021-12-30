package it.unisa.agency_formation.autenticazione.domain;

public class Utente {
    private String nome, cognome, email, pwd;
    private int ruolo, id;

    public Utente(String nome, String cognome, String email, String pwd, int ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.pwd = pwd;
        this.ruolo = ruolo;
    }

    public Utente() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return nome;
    }

    public String getSurname() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public int getRole() {
        return ruolo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public void setSurname(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRole(int ruolo) {
        this.ruolo = ruolo;
    }
}
