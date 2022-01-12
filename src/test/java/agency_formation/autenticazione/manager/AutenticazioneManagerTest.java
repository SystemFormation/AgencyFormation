package agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import it.unisa.agency_formation.team.domain.Team;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.internal.MockedStaticImpl;

import java.sql.SQLException;
import java.util.ArrayList;

//Questa classe testa i metodi della classe AutenticazioneManager Impl
//21 test
public class AutenticazioneManagerTest {
    AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl();

    @Test
    public void registrationFail() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.salvaUtente(user)).thenReturn(false);
            assertFalse(aut.registrazione(user));
        }
    }

    @Test // utente già registrato
    public void registrationFail2() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.login(user.getEmail(), user.getPwd())).thenReturn(user);
            assertFalse(aut.registrazione(user));
        }
    }

    @Test
    public void registrationPass() throws SQLException {
        Utente user = new Utente("Francescoz", "Ceccoz", "frza@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.salvaUtente(user)).thenReturn(true);
            assertTrue(aut.registrazione(user));
        }

    }

    @Test
    public void loginFail() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        String email = "manuel@gmail.com";
        String pwd = "lol";
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.login(email, pwd)).thenReturn(null);
            assertNull(aut.login(email, pwd));
        }

    }

    @Test
    public void loginPass() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        String email = "fra@gmail.com";
        String pwd = "lol";
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.login(email, pwd)).thenReturn(user);
            assertNotNull(aut.login(email, pwd));
        }

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

    @Test
    public void getDipendeteFail1() throws SQLException {
        int id = 2;
        try (MockedStatic mocked = mockStatic(DipendenteDAO.class)) {
            mocked.when(() -> DipendenteDAO.doRetrieveDipendenteById(id)).thenReturn(null);
            assertNull(aut.getDipendente(id));
        }

    }

    @Test
    public void getDipendetePass() throws SQLException {
        int id = 2;
        Dipendente dipendente = new Dipendente();
        dipendente.setStato(StatiDipendenti.DISPONIBILE);
        dipendente.setIdDipendente(2);
        dipendente.setTelefono("1589634786");
        dipendente.setResidenza("Londra");
        dipendente.setAnnoNascita(2000);
        try (MockedStatic mocked = mockStatic(DipendenteDAO.class)) {
            mocked.when(() -> DipendenteDAO.doRetrieveDipendenteById(id)).thenReturn(dipendente);
            assertNotNull(aut.getDipendente(id));
        }

    }

    @Test //not pass there aren't candidates with candidature
    public void getCandidatesWithCandidature1() throws SQLException {
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.doRetrieveCandidatoConCandidatura()).thenReturn(null);
            assertNull(aut.getCandidatiConCandidatura());
        }
    }

    @Test //pass
    public void getCandidatesWithCandidature2() throws SQLException {
        ArrayList<Utente> candidati = new ArrayList<>();
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        candidati.add(user);
        try (MockedStatic mocked = mockStatic(UtenteDAO.class)) {
            mocked.when(() -> UtenteDAO.doRetrieveCandidatoConCandidatura()).thenReturn(candidati);
            assertNotNull(aut.getCandidatiConCandidatura());
        }
    }
/*
    @Test //fail non ci sono utenti con quel ruolo
    public void getUtentiByRuoloFail() throws SQLException {
        try (MockedStatic mockedStatic = mockStatic(UtenteDAO.class)) {
            mockedStatic.when(() -> UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.HR)).thenReturn(null);
            assertNull(aut.getUtentiByRuolo(RuoliUtenti.HR));
        }
    }

    @Test //pass
    public void getUtentiByRuoloPass() throws SQLException {
        ArrayList<Utente> utenti = new ArrayList<>();
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        utenti.add(user);
        try (MockedStatic mockedStatic = mockStatic(UtenteDAO.class)) {
            mockedStatic.when(() -> UtenteDAO.doRetrieveUtenteByRuolo(RuoliUtenti.CANDIDATO)).thenReturn(utenti);
            assertNotNull(aut.getUtentiByRuolo(RuoliUtenti.CANDIDATO));
        }
    }
*/
    @Test // non ci sono dipendenti
    public void getTuttiDipendentiFail() throws SQLException {
        try (MockedStatic mockedStatic = mockStatic(DipendenteDAO.class)) {
            mockedStatic.when(() -> DipendenteDAO.recuperaDipendenti()).thenReturn(null);
            assertNull(aut.getTuttiDipendenti());
        }
    }

    @Test // pass (assert diverso ci sono dei dubbi sul test del manager)
    public void getTuttiDipendentiPass() throws SQLException {
        Dipendente dipendente = new Dipendente();
        dipendente.setStato(StatiDipendenti.DISPONIBILE);
        dipendente.setIdDipendente(2);
        dipendente.setTelefono("1589634786");
        dipendente.setResidenza("Londra");
        dipendente.setAnnoNascita(2000);
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        dipendenti.add(dipendente);
        try (MockedStatic mockedStatic = mockStatic(DipendenteDAO.class)) {
            mockedStatic.when(() -> DipendenteDAO.recuperaDipendenti()).thenReturn(dipendenti);
            assertEquals(dipendenti.size(), aut.getTuttiDipendenti().size());
        }
    }

    @Test // non ci sono dipendenti con lo statoc specificato
    public void getDipendentiByStatoFail() throws SQLException {
        try (MockedStatic mockedStatic = mockStatic(DipendenteDAO.class)) {
            mockedStatic.when(() -> DipendenteDAO.recuperaByStato(StatiDipendenti.OCCUPATO)).thenReturn(null);
            assertNull(aut.getDipendentiByStato(StatiDipendenti.OCCUPATO));
        }
    }

    @Test // pass
    public void getDipendentiByStatoPass() throws SQLException {
        Dipendente dipendente = new Dipendente();
        dipendente.setStato(StatiDipendenti.OCCUPATO);
        dipendente.setIdDipendente(2);
        dipendente.setTelefono("1589634786");
        dipendente.setResidenza("Londra");
        dipendente.setAnnoNascita(2000);
        Team team = new Team();
        team.setIdTeam(1);
        dipendente.setTeam(team);
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        dipendenti.add(dipendente);
        try (MockedStatic mockedStatic = mockStatic(DipendenteDAO.class)) {
            mockedStatic.when(() -> DipendenteDAO.recuperaByStato(StatiDipendenti.OCCUPATO)).thenReturn(dipendenti);
            assertEquals(dipendenti.size(), aut.getDipendentiByStato(StatiDipendenti.OCCUPATO).size());
        }
    }

    @Test
    public void modificaRuoloFail() throws SQLException {
        int id = 1;
        try (MockedStatic mockedStatic = mockStatic(DipendenteDAO.class)) {
            mockedStatic.when(() -> DipendenteDAO.modificaRuoloUtente(id)).thenReturn(false);
            assertFalse(aut.modificaRuolo(id));
        }
    }

    @Test
    public void modificaRuoloPass() throws SQLException {
        int id = 1;
        try (MockedStatic mockedStatic = mockStatic(DipendenteDAO.class)) {
            mockedStatic.when(() -> DipendenteDAO.modificaRuoloUtente(id)).thenReturn(true);
            assertTrue(aut.modificaRuolo(id));
        }
    }

    @Test //non ci sono candidati per il colloquio
    public void getCandidatiColloquioFail() throws SQLException {
        ArrayList<Utente> candidati = null;
        try (MockedStatic mockedStatic = mockStatic(UtenteDAO.class)) {
            mockedStatic.when(() -> UtenteDAO.recuperoCandidatiColloquio()).thenReturn(candidati);
            assertNull(aut.getCandidatiColloquio());
        }
    }

    @Test //pass
    public void getCandidatiColloquioPass() throws SQLException {
        ArrayList<Utente> utenti = new ArrayList<>();
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        utenti.add(user);
        try (MockedStatic mockedStatic = mockStatic(UtenteDAO.class)) {
            mockedStatic.when(() -> UtenteDAO.recuperoCandidatiColloquio()).thenReturn(utenti);
            assertEquals(utenti.size(), aut.getCandidatiColloquio().size());
        }
    }

    @Test
    public void setTeamDipendenteFail() throws SQLException {
        int idDip = 1;
        int idTeam = 2;
        try (MockedStatic mockedStatic = mockStatic(DipendenteDAO.class)) {
            mockedStatic.when(() -> DipendenteDAO.setTeamDipendente(idDip, idTeam)).thenReturn(false);
            assertFalse(aut.setTeamDipendente(idDip, idTeam));
        }
    }

    @Test
    public void setTeamDipendentePass() throws SQLException {
        int idDip = 1;
        int idTeam = 2;
        try (MockedStatic mockedStatic = mockStatic(DipendenteDAO.class)) {
            mockedStatic.when(() -> DipendenteDAO.setTeamDipendente(idDip, idTeam)).thenReturn(true);
            assertTrue(aut.setTeamDipendente(idDip, idTeam));
        }
    }


}
