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

    @Override
    public Utente getDatiUtente(int idUser) throws SQLException {
        return UtenteDAO.doRetrieveByID(idUser);

    }

    //Aggiunto questo metodo
    @Override
    public Dipendente getDatiDipendente(int idUser) throws SQLException {
        return DipendenteDAO.doRetrieveById(idUser);
    }

    @Override
    public ArrayList<Utente> getCandidati() throws SQLException {
        return UtenteDAO.doRetrieveUserByRuolo(RuoliUtenti.CANDIDATO);
    }
    //TODO TEST THIS METHOD
    @Override
    public ArrayList<Utente> getCandidatiConCandidatura() throws SQLException {
        return UtenteDAO.doRetrieveCandidatesWithCandidature();
    }
    @Override
    public ArrayList<Utente> getDipendentiByRuolo() throws SQLException {
        return UtenteDAO.doRetrieveUserByRuolo(RuoliUtenti.DIPENDENTE);
    }
    @Override
    public ArrayList<Dipendente> getTuttiDipendenti() throws SQLException {
        return DipendenteDAO.recuperaDipendenti();
    }

    @Override
    public ArrayList<Dipendente> getDipendenteByStato(StatiDipendenti state) throws SQLException {
        return DipendenteDAO.recuperaByStato(state);
    }

    private boolean alreadyRegisteredUser(Utente user) throws SQLException {
        Utente result = UtenteDAO.login(user.getEmail(), user.getPwd());
        if (result == null) {
            return false;
        } else {
            return true;
        }
    }
}
