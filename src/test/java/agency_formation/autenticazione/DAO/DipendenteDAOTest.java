package agency_formation.autenticazione.DAO;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DipendenteDAOTest {

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String queryTeam1= "Insert into team (IdTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,idTM) values(2,'TestTeam',5,'Test','test descr','Java EE',3)";
        String queryUtente0= "Insert into utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(5,'Test','Rossi','lol','test@gmail.com',1)";
        String queryUtente1= "Insert into utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(6,'Luca','Rossi','lol','luca@gmail.com',2)";
        String queryUtente2= "Insert into utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(7,'Maria','Espo','lol','maria@gmail.com',2)";
        String queryDipendente1 = "insert into dipendenti (IdDipendente, Residenza, Telefono, Stato, AnnoDiNascita) " +
                "values (6,'Londra','118',true,2000)";
        String queryDipendente2 = "insert into dipendenti (IdDipendente, Residenza, Telefono, Stato, AnnoDiNascita,IdTeam) " +
                "values (7,'Parigi','148',false,2000,2)";

        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(queryUtente1);
        statement.executeUpdate(queryUtente0);
        statement.executeUpdate(queryUtente1);
        statement.executeUpdate(queryTeam1);
        statement.executeUpdate(queryUtente2);
        statement.executeUpdate(queryDipendente1);
        statement.executeUpdate(queryDipendente2);
    }
    @AfterAll
    public static void finish() throws SQLException {
        String delete0 = "Delete from utenti where IdUtente>4";
        String delete2 = "Delete from team where IdTeam>1";
        String insert = "insert into dipendenti (IdDipendente, Residenza, Telefono, Stato, AnnoDiNascita,IdTeam) " +
                "values (2,'Fisciano','118',false,2000,1)";
        String insertSkills = "insert into skillsdipendenti (IdDipendente, IdSkill,Livello) values(2,1,5)";
        String insertSkills1 = "insert into skillsdipendenti (IdDipendente, IdSkill,Livello) values(2,2,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete0);
        statement.executeUpdate(delete0);
        statement.executeUpdate(delete2);
        statement.executeUpdate(insert);
        statement.executeUpdate(insertSkills);
        statement.executeUpdate(insertSkills1);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test //not pass id<1
    @Order(1)
    public void modificaRuoloUtente1() throws SQLException{
        assertFalse(DipendenteDAO.modificaRuoloUtente(0));
    }

    @Test //non passa id non presente nel db
    @Order(2)
    public void modificaRuoloUtente2() throws SQLException{
        assertFalse(DipendenteDAO.modificaRuoloUtente(15));
    }

    @Test //pass
    @Order(3)
    public void modificaRuoloUtente3() throws SQLException{
        assertTrue(DipendenteDAO.modificaRuoloUtente(5));
    }


    @Test
    @Order(4)
    public void doSaveEmployeeFail() throws SQLException {
        Dipendente dip = null;
        assertFalse(DipendenteDAO.salvaDipendente(dip));
    }


    @Test
    @Order(5)
    public void doSaveEmployeeOk() throws SQLException {
       Utente user = UtenteDAO.login("test@gmail.com","lol");
        Dipendente dip = new Dipendente();
        dip.setIdDipendente(user.getId());
        dip.setStato(StatiDipendenti.DISPONIBILE);
        dip.setResidenza("Boscoreale");
        dip.setTelefono("333456214");
        dip.setAnnoNascita(2000);
        assertTrue(DipendenteDAO.salvaDipendente(dip));
    }

    @Test // id < 1
    @Order(6)
    public void doRetrieveDipendenteById1() throws SQLException {
        int id = -1;
        assertNull(DipendenteDAO.recuperoDipendenteById(id));
    }

    @Test //id non presente nel db
    @Order(7)
    public void doRetrieveDipendeteById2() throws SQLException {
        int id = 484; //queso id non esiste
        assertNull(DipendenteDAO.recuperoDipendenteById(id));
    }

    @Test //pass
    @Order(8)
    public void doRetrieveById3() throws SQLException {
        int id = 7;
        assertNotNull(DipendenteDAO.recuperoDipendenteById(id));
    }
    @Test //pass
    @Order(9)
    public void doRetrieveById4() throws SQLException {
        int id = 5;
        assertNotNull(DipendenteDAO.recuperoDipendenteById(id));
    }


    @Test // pass
    @Order(10)
    public void doRetrieveAll1() throws SQLException {
        assertNotNull(DipendenteDAO.recuperaDipendenti());
    }

    @Test //not pass because idDip<1
    @Order(11)
    public void setTeamDipendente1() throws SQLException {
        assertFalse(DipendenteDAO.setIdTeamDipendente(0,1));
    }
    @Test //not pass because idTeam<1
    @Order(12)
    public void setTeamDipendente2() throws SQLException {
        assertFalse(DipendenteDAO.setIdTeamDipendente(2,0));
    }
    @Test //not pass because idDip doesn't exists
    @Order(13)
    public void setTeamDipendente3() throws SQLException {
        assertFalse(DipendenteDAO.setIdTeamDipendente(200,1));
    }
    @Test //pass
    @Order(14)
    public void setTeamDipendente4() throws SQLException {
        assertTrue(DipendenteDAO.setIdTeamDipendente(5,2));
    }

    @Test //non ci sono dipendenti
    @Order(15)
    public void doRetrieveAll2() throws SQLException {
        String query = "Delete from dipendenti";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(DipendenteDAO.recuperaDipendenti());
    }
}