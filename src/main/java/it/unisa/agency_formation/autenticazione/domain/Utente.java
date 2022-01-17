package it.unisa.agency_formation.autenticazione.domain;

public class Utente {
    private String nome;
    private String cognome;
    private String email;
    private String pwd;
    private int id;
    private RuoliUtenti ruolo;

    public Utente(String name, String surname, String mail, String password, RuoliUtenti role) {
        this.nome = name;
        this.cognome = surname;
        this.email = mail;
        this.pwd = password;
        this.ruolo = role;
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

    public RuoliUtenti getRole() {
        return ruolo;
    }

    public void setId(int idUser) {
        this.id = idUser;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public void setSurname(String surname) {
        this.cognome = surname;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public void setPwd(String password) {
        this.pwd = password;
    }

    public void setRole(RuoliUtenti role) {
        this.ruolo = role;
    }
}
