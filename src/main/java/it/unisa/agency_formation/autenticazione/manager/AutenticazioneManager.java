package it.unisa.agency_formation.autenticazione.manager;

public interface AutenticazioneManager {
    void registration(Utente user);
    Utente login(String email, String password);
    Utente getAllData(int idUser);
}