package it.unisa.agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.manager.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;

import java.sql.SQLException;

public class AutenticazioneManagerImpl implements AutenticazioneManager{
        UtenteDAO utDAO= new UtenteDAO();

        @Override
        public void registration(Utente user) throws SQLException {
            utDAO.doSaveUser(user);
        }

        @Override
        public Utente login(String email, String password) throws SQLException {
                return utDAO.doRetrieveUser(email,password);
        }

        @Override
        public Utente getAllData(int idUser) throws SQLException {
                return utDAO.doRetrieveByID(idUser);
        }
}
