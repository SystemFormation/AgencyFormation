package it.unisa.agency_formation.team.domain;

public class Team {
    private int idTeam;
    private String NomeProgetto;
    private int NumeroDipendenti;
    private String NomeTeam;
    private String Descrizione;
    private String Competenza;
    private int idTM;

    public Team(String nomeProgetto, int numeroDipendenti, String nomeTeam, String descrizione, String competenza, int IdTM) {
        this.NomeProgetto = nomeProgetto;
        this.NumeroDipendenti = numeroDipendenti;
        this.NomeTeam = nomeTeam;
        this.Descrizione = descrizione;
        this.Competenza = competenza;
        this.idTM = IdTM;
    }
    public Team() {
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int IdTeam) {
        this.idTeam = IdTeam;
    }

    public String getNomeProgetto() {
        return NomeProgetto;
    }

    public void setNomeProgetto(String nameProgetto) {
        NomeProgetto = nameProgetto;
    }

    public int getNumeroDipendenti() {
        return NumeroDipendenti;
    }

    public void setNumeroDipendenti(int numDipendenti) {
        NumeroDipendenti = numDipendenti;
    }

    public String getNomeTeam() {
        return NomeTeam;
    }

    public void setNomeTeam(String nameTeam) {
        NomeTeam = nameTeam;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String description) {
        Descrizione = description;
    }

    public String getCompetenza() {
        return Competenza;
    }

    public void setCompetenza(String competence) {
        Competenza = competence;
    }

    public int getIdTM() {
      return idTM;
    }

    public void setIdTM(int IdTM) {
        this.idTM = IdTM;
    }
}
