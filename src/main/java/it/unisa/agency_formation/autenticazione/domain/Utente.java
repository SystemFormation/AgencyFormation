package it.unisa.agency_formation.autenticazione.domain;

public class Utente {
    private String nome,cognome, email, pwd;
    private int ruolo, id;

    public Utente(String nome,String cognome, String email,String pwd,int ruolo){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.pwd = pwd;
        this.ruolo = ruolo;
    }

    public Utente(){}

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public int getRuolo() {
        return ruolo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }
}
