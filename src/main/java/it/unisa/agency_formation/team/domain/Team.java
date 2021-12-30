package it.unisa.agency_formation.team.domain;

public class Team {
    private int idTeam;
    private String NomeProgetto;
    private int NumeroDipendenti;
    private String NomeTeam, Descrizione, Competenza;
    private int idTM;

    public Team(String nomeProgetto, int numeroDipendenti, String nomeTeam, String descrizione, String competenza, int idTM) {
        this.NomeProgetto = nomeProgetto;
        this.NumeroDipendenti = numeroDipendenti;
        this.NomeTeam = nomeTeam;
        this.Descrizione = descrizione;
        this.Competenza = competenza;
        this.idTM = idTM;
    }
    public Team() {
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getNomeProgetto() {
        return NomeProgetto;
    }

    public void setNomeProgetto(String nomeProgetto) {
        NomeProgetto = nomeProgetto;
    }

    public int getNumeroDipendenti() {
        return NumeroDipendenti;
    }

    public void setNumeroDipendenti(int numeroDipendenti) {
        NumeroDipendenti = numeroDipendenti;
    }

    public String getNomeTeam() {
        return NomeTeam;
    }

    public void setNomeTeam(String nomeTeam) {
        NomeTeam = nomeTeam;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public String getCompetenza() {
        return Competenza;
    }

    public void setCompetenza(String competenza) {
        Competenza = competenza;
    }

    public int getIdTM() {
        return idTM;
    }

    public void setIdTM(int idTM) {
        this.idTM = idTM;
    }
}
