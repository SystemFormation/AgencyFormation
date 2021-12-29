package it.unisa.agency_formation.reclutamento.domain;

import java.util.Date;

public class Candidatura {
    private String Cv;
    private String Attestati;
    private String Certificazioni;
    private String Stato;
    private Date DataCandidatura;
    private Date DataOraColloquio;
    private int IdCandidato;
    private int IdHR;

    public Candidatura() {}
    public Candidatura(String cv, String attestati, String certificazioni, String stato, Date dataCandidatura, Date dataOraColloquio, int idCandidato, int idHR) {
        this.Cv = cv;
        this.Attestati = attestati;
        this.Certificazioni = certificazioni;
        this.Stato = stato;
        this.DataCandidatura = dataCandidatura;
        this.DataOraColloquio = dataOraColloquio;
        this.IdCandidato = idCandidato;
        this.IdHR = idHR;
    }

    public String getCv() {return Cv;}
    public void setCv(String cv) {Cv = cv;}
    public String getAttestati() {return Attestati;}
    public void setAttestati(String attestati) {Attestati = attestati;}
    public String getCertificazioni() {return Certificazioni;}
    public void setCertificazioni(String certificazioni) {Certificazioni = certificazioni;}
    public String getStato() {return Stato;}
    public void setStato(String stato) {Stato = stato;}
    public Date getDataCandidatura() {return DataCandidatura;}
    public void setDataCandidatura(Date dataCandidatura) {DataCandidatura = dataCandidatura;}
    public Date getDataOraColloquio() {return DataOraColloquio;}
    public void setDataOraColloquio(Date dataOraColloquio) {DataOraColloquio = dataOraColloquio;}
    public int getIdCandidato() {return IdCandidato;}
    public void setIdCandidato(int idCandidato) {IdCandidato = idCandidato;}
    public int getIdHR() {return IdHR;}
    public void setIdHR(int idHR) {IdHR = idHR;}


}
