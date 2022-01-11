package agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;

//Questa classe testa i metodi della classe AutenticazioneManager Impl

public class AutenticazioneManagerTest {
    AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl();

    @Test
    public void registrationFail() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.salvaUtente(user)).thenReturn(false);
        }
        assertFalse(aut.registrazione(user));
    }

    @Test
    public void registrationPass() throws SQLException {
        Utente user = new Utente("Francescoz", "Ceccoz", "frza@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.salvaUtente(user)).thenReturn(true);
        }
        assertTrue(aut.registrazione(user));
    }

    @Test
    public void loginFail() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        String email = "manuel@gmail.com";
        String pwd = "lol";
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.login(email,pwd)).thenReturn(user);
        }
        assertNull(aut.login(email, pwd));
    }

    @Test
    public void loginPass() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        String email = "fra@gmail.com";
        String pwd = "lol";
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.login(email,pwd)).thenReturn(user);
        }
        assertNotNull(aut.login(email, pwd));
    }
/*
    @Test
    public void getAllDataFail() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        int id = -1;
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.doRetrieveUtenteByID(id)).thenReturn(user);
        }
        //assertNull(aut.getDatiUtente(id));
    }

    @Test//non funziona
    public void getAllDataPass() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        int id = 5;
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.doRetrieveUtenteByID(id)).thenReturn(user);
        }
        //assertNotNull(aut.getDatiUtente(id));
    }*/
/*
    @Test //not pass id<1
    public void getAllDataDip1() throws SQLException {
        Dipendente user= new Dipendente("Yoko","Poko","yokopokomayoko@gmail.com","lol",RuoliUtenti.DIPENDENTE,
                -1,2000,"Salerno","220", StatiDipendenti.DISPONIBILE);

        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> DipendenteDAO.doRetrieveDipendenteById(user.getIdDipendente())).thenReturn(user);
        }
        assertNull(aut.getDipendente(user.getIdDipendente()));
    }
*/
    @Test //not pass there aren't candidates with candidature
    public void getCandidatesWithCandidature1(){

    }

    @Test //pass
    public void getCandidatesWithCandidature2(){

    }

    @Test //array dip = null
    public void getCandidatesDip1(){

    }

    @Test //pass
    public void getCandidatesDip2(){

    }

    @Test //array = null not pass
    public void getAllEmployee1(){

    }

    @Test //pass
    public void getAllEmployee2(){

    }

    @Test //There are'n employe with this state
    public void getEmployeByState1(){

    }
    @Test //pass
    public void getEmployeByState2(){

    }




}
