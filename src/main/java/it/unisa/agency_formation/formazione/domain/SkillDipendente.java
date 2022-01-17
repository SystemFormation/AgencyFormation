package it.unisa.agency_formation.formazione.domain;

public class SkillDipendente {
    private int idDipendente;
    private int idSkill;
    private int livello;

    public SkillDipendente(int idDip, int idSkil, int level) {
        this.idDipendente = idDip;
        this.idSkill = idSkil;
        this.livello = level;
    }

    public SkillDipendente() {
    }

    public int getIdDipendente() {
        return idDipendente;
    }

    public void setIdDipendente(int idDip) {
        this.idDipendente = idDip;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int id) {
        this.idSkill = id;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int level) {
        this.livello = level;
    }
}
