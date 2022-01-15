package it.unisa.agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;

import java.sql.SQLException;
import java.util.ArrayList;

public class AutenticazioneManagerImpl implements AutenticazioneManager {

    @Override
    public boolean registrazione(Utente user) throws SQLException {
        if (!alreadyRegisteredUser(user)) {
            return UtenteDAO.salvaUtente(user);
        } else {
            return false;
        }
    }

    @Override
    public Utente login(String email, String password) throws SQLException {
        return UtenteDAO.login(email, password);

    }
/*
    @Override
    public Utente getDatiUtente(int idUser) throws SQLException {
        return UtenteDAO.doRetrieveUtenteByID(idUser);

    }*/

    //Aggiunto questo metodo
    @Override
    public Dipendente getDipendente(int idUser) throws SQLException {
        return DipendenteDAO.doRetrieveDipendenteById(idUser);
    }
/*
    @Override
    public ArrayList<Utente> getCandidati() throws SQLException {
        return UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.CANDIDATO);
    }*/
    @Override
    public ArrayList<Utente> getCandidatiConCandidatura() throws SQLException {
        return UtenteDAO.doRetrieveCandidatoConCandidatura();
    }
   /* @Override
    public ArrayList<Utente> getUtentiByRuolo(RuoliUtenti ruolo) throws SQLException {
        return UtenteDAO.doRetrieveUtenteByRuolo(ruolo);
    }*/
    @Override
    public ArrayList<Dipendente> getTuttiDipendenti() throws SQLException {
        return DipendenteDAO.recuperaDipendenti();
    }

    @Override
    public ArrayList<Dipendente> getDipendentiByStato(StatiDipendenti state) throws SQLException {
        return DipendenteDAO.recuperaByStato(state);
    }
    @Override
    public boolean modificaRuolo(int idUtente) throws SQLException {
        return DipendenteDAO.modificaRuoloUtente(idUtente);
    }

    @Override
    public ArrayList<Utente> getCandidatiColloquio() throws SQLException {
        return UtenteDAO.recuperoCandidatiColloquio();
    }

    @Override
    public boolean setTeamDipendente(int dip, int idTeam) throws SQLException {
        return DipendenteDAO.setTeamDipendente(dip, idTeam);
    }

    private boolean alreadyRegisteredUser(Utente user) throws SQLException {
        if (UtenteDAO.login(user.getEmail(), user.getPwd()) == null) {
            return false;
        }
            return true;
    }
}
