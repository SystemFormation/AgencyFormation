package it.unisa.agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;

import java.sql.SQLException;

public class AutenticazioneManagerImpl implements AutenticazioneManager{
        UtenteDAO utDAO = new UtenteDAO();



        @Override
        public boolean registration(Utente user) throws SQLException {
            return utDAO.doSaveUser(user);
        }

        @Override
        public Utente login(String email, String password) throws SQLException {
                return utDAO.login(email,password);
        }

        @Override
        public Utente getAllData(int idUser) throws SQLException {
                return utDAO.doRetrieveByID(idUser);
        }
}
