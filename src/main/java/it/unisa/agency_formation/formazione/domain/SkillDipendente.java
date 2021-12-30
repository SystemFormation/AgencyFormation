package it.unisa.agency_formation.formazione.domain;

public class SkillDipendente {
    private int idDipendente;
    private int idSkill;
    private int livello;

    public SkillDipendente(int idDipendente, int idSkill, int livello) {
        this.idDipendente = idDipendente;
        this.idSkill = idSkill;
        this.livello = livello;
    }

    public SkillDipendente() {
    }

    public int getIdDipendente() {
        return idDipendente;
    }

    public void setIdDipendente(int idDipendente) {
        this.idDipendente = idDipendente;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }
}
