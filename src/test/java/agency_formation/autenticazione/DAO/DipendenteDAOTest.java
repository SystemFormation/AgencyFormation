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
        String query= "Insert into utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(5,'Luca','Rossi','lol','luca@gmail.com',1)";
        String query1= "Insert into team (IdTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,idTM) values(2,'TestTeam',5,'Test','test descr','Java EE',3)";

        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
        statement.executeUpdate(query1);
    }
    @AfterAll
    public static void finish() throws SQLException {
       String delete = "Delete from utenti where IdUtente>4";
       String update = "update dipendenti set IdTeam=null where idTeam>1";
        String delete1 = "Delete from team where IdTeam>1";
       String insert = "insert into dipendenti (IdDipendente, Residenza, Telefono, Stato, AnnoDiNascita,IdTeam) " +
                    "values (2,'Fisciano','118',false,2000,1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.executeUpdate(delete);
        statement.executeUpdate(delete1);
        statement.executeUpdate(insert);
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
        Utente user = UtenteDAO.doRetrieveUtenteByID(5);
        assertNotNull(UtenteDAO.doRetrieveUtenteByID(5));
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
        assertNull(DipendenteDAO.doRetrieveDipendenteById(id));
    }

    @Test //id non presente nel db
    @Order(7)
    public void doRetrieveDipendeteById2() throws SQLException {
        int id = 484; //queso id non esiste
        assertNull(DipendenteDAO.doRetrieveDipendenteById(id));
    }

    @Test //pass
    @Order(8)
    public void doRetrieveById3() throws SQLException {
        int id = 2;
        Dipendente dip = DipendenteDAO.doRetrieveDipendenteById(id);
        assertNotNull(dip);
    }
    @Test //non ci sono dipendenti
    @Order(16)
    public void doRetrieveAll1() throws SQLException {
        String query = "Delete from dipendenti";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(DipendenteDAO.recuperaDipendenti());
    }

    @Test // pass
    @Order(9)
    public void doRetrieveAll2() throws SQLException {
        assertNotNull(DipendenteDAO.recuperaDipendenti().size());
    }

    @Test
    @Order(10)
    public void doRetrieveByStateSizeLessOne() throws SQLException {
        String query = "update dipendenti set Stato=1 where Stato = 0";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        assertNull(DipendenteDAO.recuperaByStato(StatiDipendenti.OCCUPATO));
    }
    @Test
    @Order(11)
    public void doRetrieveByStateSizePass() throws SQLException {
        assertNotNull(DipendenteDAO.recuperaByStato(StatiDipendenti.DISPONIBILE));
    }

    @Test //not pass because idDip<1
    @Order(12)
    public void setTeamDipendente1() throws SQLException {
        assertFalse(DipendenteDAO.setTeamDipendente(0,1));
    }
    @Test //not pass because idTeam<1
    @Order(13)
    public void setTeamDipendente2() throws SQLException {
        assertFalse(DipendenteDAO.setTeamDipendente(2,0));
    }
    @Test //not pass because idDip doesn't exists
    @Order(14)
    public void setTeamDipendente3() throws SQLException {
        assertFalse(DipendenteDAO.setTeamDipendente(200,1));
    }
    @Test //pass
    @Order(15)
    public void setTeamDipendente4() throws SQLException {
        assertTrue(DipendenteDAO.setTeamDipendente(5,2));
    }
}