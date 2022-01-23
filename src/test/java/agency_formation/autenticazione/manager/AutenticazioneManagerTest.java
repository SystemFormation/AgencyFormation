package agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAOImpl;
import it.unisa.agency_formation.autenticazione.DAO.DipendenteDao;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAOImpl;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

//Questa classe testa i metodi della classe AutenticazioneManager Impl
//21 test
public class AutenticazioneManagerTest {
    private AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl();
    private UtenteDAO daoUtente = mock(UtenteDAOImpl.class);
    private DipendenteDao daoDipendente = mock(DipendenteDAOImpl.class);

    public void init(){
        AutenticazioneManagerImpl.daoUtente = daoUtente;
        AutenticazioneManagerImpl.daoDipendente = daoDipendente;
    }



    @Test
    public void registrationFail() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        init();
        when(daoUtente.salvaUtente(user)).thenReturn(false);
        assertFalse(aut.registrazione(user));

    }

    @Test // utente già registrato
    public void registrationFail2() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        when(daoUtente.login(user.getEmail(), user.getPwd())).thenReturn(user);
        init();
        assertFalse(aut.registrazione(user));
    }

    @Test
    public void registrationPass() throws SQLException {
        Utente user = new Utente("Francescoz", "Ceccoz", "frza@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        when(daoUtente.salvaUtente(user)).thenReturn(true);
        init();
        assertTrue(aut.registrazione(user));


    }

    @Test
    public void loginFail() throws SQLException {
        String email = "manuel@gmail.com";
        String pwd = "lol";
        when(daoUtente.login(email,pwd)).thenReturn(null);
        init();
        assertNull(aut.login(email, pwd));
    }

    @Test
    public void loginPass() throws SQLException {
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        String email = "fra@gmail.com";
        String pwd = "lol";
        when(daoUtente.login(email,pwd)).thenReturn(user);
        init();
        assertNotNull(aut.login(email, pwd));


    }


    @Test
    public void getDipendeteFail1() throws SQLException {
        int id = 2;
        when(daoDipendente.recuperoDipendenteById(id)).thenReturn(null);
        init();
        assertNull(aut.getDipendente(id));
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
        when(daoDipendente.recuperoDipendenteById(id)).thenReturn(dipendente);
        init();
        assertNotNull(aut.getDipendente(id));

    }

    @Test //not pass there aren't candidates with candidature
    public void getCandidatesWithCandidature1() throws SQLException {
        when(daoUtente.doRetrieveCandidatoConCandidatura()).thenReturn(null);
        init();
        assertNull(aut.getCandidatiConCandidatura());
    }

    @Test //pass
    public void getCandidatesWithCandidature2() throws SQLException {
        ArrayList<Utente> candidati = new ArrayList<>();
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        candidati.add(user);
        when(daoUtente.doRetrieveCandidatoConCandidatura()).thenReturn(candidati);
        init();
        assertNotNull(aut.getCandidatiConCandidatura());
    }


    @Test // non ci sono dipendenti
    public void getTuttiDipendentiFail() throws SQLException {
        when(daoDipendente.recuperaDipendenti()).thenReturn(null);
        init();
        assertNull(aut.getTuttiDipendenti());
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
        when(daoDipendente.recuperaDipendenti()).thenReturn(dipendenti);
        init();
        assertEquals(dipendenti.size(), aut.getTuttiDipendenti().size());
    }

    @Test //non ci sono candidati per il colloquio
    public void getCandidatiColloquioFail() throws SQLException {
        ArrayList<Utente> candidati = null;
        when(daoUtente.recuperoCandidatiColloquio()).thenReturn(candidati);
        init();
        assertNull(aut.getCandidatiColloquio());

    }

    @Test //pass
    public void getCandidatiColloquioPass() throws SQLException {
        ArrayList<Utente> utenti = new ArrayList<>();
        Utente user = new Utente("Francesco", "Cecco", "fra@gmail.com", "lol", RuoliUtenti.CANDIDATO);
        utenti.add(user);
        when(daoUtente.recuperoCandidatiColloquio()).thenReturn(utenti);
        init();
        assertEquals(utenti.size(), aut.getCandidatiColloquio().size());
    }

    @Test
    public void setTeamDipendenteFail() throws SQLException {
        int idDip = 1;
        int idTeam = 2;
        when(daoDipendente.setIdTeamDipendente(idDip,idTeam)).thenReturn(false);
        init();
        assertFalse(aut.setTeamDipendente(idDip, idTeam));
    }

    @Test
    public void setTeamDipendentePass() throws SQLException {
        int idDip = 1;
        int idTeam = 2;
        when(daoDipendente.setIdTeamDipendente(idDip,idTeam)).thenReturn(true);
        init();
        assertTrue(aut.setTeamDipendente(idDip, idTeam));
    }


}
