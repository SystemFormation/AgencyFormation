package it.unisa.agency_formation.autenticazione.manager;

public class AutenticazioneManagerImpl implements AutenticazioneManager{
        UtenteDAO utDAO= new UtenteDAO();

        @Override
        public void registration(Utente user) {
            utDAO.doSaveUtente(user);
        }

        @Override
        public Utente login(String email, String password) {
                return utDAO.doRetrieveUser(email,password);
        }

        @Override
        public Utente getAllData(int idUser) {
                return utDAO.doRetrieveUserByID(idUser);
        }
}
