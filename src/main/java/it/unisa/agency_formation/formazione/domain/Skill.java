package it.unisa.agency_formation.formazione.domain;

public class Skill {
    private int idSkill;
    private String nomeSkill;
    private String descrizioneSkill;

    public Skill(){}
    public Skill(String nomeSkill, String descrizioneSkill) {
        this.nomeSkill = nomeSkill;
        this.descrizioneSkill = descrizioneSkill;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    public String getNomeSkill() {
        return nomeSkill;
    }

    public void setNomeSkill(String nomeSkill) {
        this.nomeSkill = nomeSkill;
    }

    public String getDescrizioneSkill() {
        return descrizioneSkill;
    }

    public void setDescrizioneSkill(String descrizioneSkill) {
        this.descrizioneSkill = descrizioneSkill;
    }
}
