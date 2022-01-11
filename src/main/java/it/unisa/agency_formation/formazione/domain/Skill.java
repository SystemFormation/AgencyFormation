package it.unisa.agency_formation.formazione.domain;

public class Skill {
    private int idSkill;
    private String nomeSkill;
    private String descrizioneSkill;

    public Skill() {
    }

    public Skill(String nameSkill, String descriptionSkill) {
        this.nomeSkill = nameSkill;
        this.descrizioneSkill = descriptionSkill;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int id) {
        this.idSkill = id;
    }

    public String getNomeSkill() {
        return nomeSkill;
    }

    public void setNomeSkill(String nameSkill) {
        this.nomeSkill = nameSkill;
    }

    public String getDescrizioneSkill() {
        return descrizioneSkill;
    }

    public void setDescrizioneSkill(String descriptionSkill) {
        this.descrizioneSkill = descriptionSkill;
    }
}
