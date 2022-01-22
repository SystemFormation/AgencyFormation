package agency_formation.autenticazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class AutenticazioneManagerIT {
    private AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "insert into utenti (IdUtente, Nome, Cognome, Pwd, Mail, Ruolo)" +
                "values (500, 'Mario','Rossi','lol','mario@gmail.com',1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query1);
        preparedStatement.executeUpdate();
    }

    @AfterAll
    public static void finish() throws SQLException {
        String query1 = "delete from utenti where IdUtente>4";
        String query2 = "Delete from candidature where IdCandidatura>=1";
        String insert1 = "insert into dipendenti (IdDipendente, Residenza, Telefono, Stato, AnnoDiNascita,IdTeam) " +
                "values (2,'Fisciano','118',false,2000,1)";
        String insertSkills = "insert into skillsdipendenti (IdDipendente, IdSkill,Livello) values(2,1,5)";
        String insertSkills1 = "insert into skillsdipendenti (IdDipendente, IdSkill,Livello) values(2,2,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query1);
        statement.executeUpdate(query2);
        statement.executeUpdate(insert1);
        statement.executeUpdate(insertSkills);
        statement.executeUpdate(insertSkills1);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    @Order(1)
    public void assumiCandidatoPass() throws SQLException {
        Dipendente dipendente = new Dipendente();
        dipendente.setIdDipendente(500);
        dipendente.setAnnoNascita(2000);
        dipendente.setTelefono("1589635874");
        dipendente.setResidenza("Roma");
        dipendente.setStato(StatiDipendenti.DISPONIBILE);
        assertTrue(autenticazioneManager.assumiCandidato(dipendente));
    }

    @Test
    @Order(2)
    public void registrazione1() throws SQLException {
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setSurname("Giacchetti");
        user.setName("Luigi");
        user.setRole(RuoliUtenti.CANDIDATO);
        assertFalse(autenticazioneManager.registrazione(user));
    }

    @Test
    @Order(3)
    public void registrazione2() throws SQLException {
        Utente user = new Utente();
        user.setId(501);
        user.setPwd("lol");
        user.setEmail("testgenny@studenti.unisa.it");
        user.setSurname("Cecco");
        user.setName("Genny");
        user.setRole(RuoliUtenti.CANDIDATO);
        assertTrue(autenticazioneManager.registrazione(user));
    }

    @Test //pwd <3
    @Order(4)
    public void registrazione3() throws SQLException {
        Utente user = new Utente();
        user.setId(501);
        user.setPwd("lo");
        user.setEmail("testgenny@studenti.unisa.it");
        user.setSurname("Cecco");
        user.setName("Genny");
        user.setRole(RuoliUtenti.CANDIDATO);
        assertFalse(autenticazioneManager.registrazione(user));
    }


    @Test
    @Order(5)
    public void login1() throws SQLException {
        String email = "l.giacchetti@studenti.unisa.it";
        String pwd = "lol";
        assertNotNull(autenticazioneManager.login(email,pwd));
    }

    @Test
    @Order(6)
    public void login2() throws SQLException {
        String email = "l.giacchetti@studenti.unisa.it";
        String pwd = "ol";
        assertNull(autenticazioneManager.login(email,pwd));
    }


    @Test
    @Order(7)
    public void getDipendente1() throws SQLException{
        int idDip = 500;
        assertNotNull(autenticazioneManager.getDipendente(idDip));
    }

    @Test
    @Order(8)
    public void getDipendente2() throws SQLException{
        int idDip = -1;
        assertNull(autenticazioneManager.getDipendente(idDip));
    }

    @Test
    @Order(9)
    public void getCandidatiConCandidatura1()throws SQLException{
        assertNull(autenticazioneManager.getCandidatiConCandidatura());
    }


    @Test
    @Order(10)
    public void getCandidatiConCandidatura2()throws SQLException{
        String candidatura="Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(1,'test','test','NonRevisionato','2022-01-10', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(candidatura);
        statement.executeUpdate(candidatura);
        assertNotNull(autenticazioneManager.getCandidatiConCandidatura());
    }

    @Test
    @Order(11)
    public void getTuttiDipendenti1() throws SQLException {
        assertNotNull(DipendenteDAO.recuperaDipendenti());
    }

    @Test // pass
    @Order(12)
    public void getTuttiDipendenti2() throws SQLException {
        String query = "Delete from dipendenti";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(DipendenteDAO.recuperaDipendenti());
    }


}
