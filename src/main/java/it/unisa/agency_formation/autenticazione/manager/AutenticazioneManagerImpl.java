package it.unisa.agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;

import java.sql.SQLException;

public class AutenticazioneManagerImpl implements AutenticazioneManager{
        private final UtenteDAO utDAO;

        public AutenticazioneManagerImpl(UtenteDAO utDAO) {
                this.utDAO = utDAO;
        }

        @Override
        public boolean registration(Utente user) throws SQLException {
                if(!alreadyRegisteredUser(user)) {
                        return utDAO.doSaveUser(user);
                }else{
                        return false;
                }
        }

        @Override
        public Utente login(String email, String password) throws SQLException {
                return utDAO.login(email,password);
        }

        @Override
        public Utente getAllData(int idUser) throws SQLException {
                return utDAO.doRetrieveByID(idUser);
        }

        private boolean alreadyRegisteredUser(Utente user) throws SQLException {
                Utente result = utDAO.login(user.getEmail(),user.getPwd());
                if(result == null){
                        return false;
                }
                else{
                        return true;
                }

        }
}
