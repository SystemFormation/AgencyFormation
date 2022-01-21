package agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.control.ViewMaterialeControl;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewMaterialeControlIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(200, 'ReqMem',8,'Inspiegabili','Non siamo eroi','HTML',3)";
        String insertTeam2 = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(201, 'ReqMemDue',8,'InspiegabiliDue','Non siamo eroiDue','HTML',3)";
        String insertDocumento="insert into documenti (IdDocumento,MaterialeDiFormazione,IdHR,IdTeam) values (1,'test',4,200)";
        String queryUtente1= "Insert into utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(100,'Luca','Rossi','lol','luca@gmail.com',2)";
        String queryUtente2= "Insert into utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(101,'Maria','Espo','lol','maria@gmail.com',2)";
        String queryDipendente1 = "insert into dipendenti (IdDipendente, Residenza, Telefono, Stato, AnnoDiNascita,IdTeam) " +
                "values (100,'Londra','118',false,2000,200)";
        String queryDipendente2 = "insert into dipendenti (IdDipendente, Residenza, Telefono, Stato, AnnoDiNascita,IdTeam) " +
                "values (101,'Parigi','148',false,2000,201)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(insertTeam);
        statement.executeUpdate(insertTeam);
        statement.executeUpdate(insertTeam2);
        statement.executeUpdate(insertDocumento);
        statement.executeUpdate(queryUtente1);
        statement.executeUpdate(queryUtente2);
        statement.executeUpdate(queryDipendente1);
        statement.executeUpdate(queryDipendente2);
    }

    @AfterAll
    public static void finish() throws SQLException {
        String deleteDocumento = "delete from documenti where IdDocumento>=1";
        String deleteTeam = "delete from team where idTeam>1";
        String delete0 = "Delete from utenti where IdUtente>4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteDocumento);
        statement.executeUpdate(deleteDocumento);
        statement.executeUpdate(deleteTeam);
        statement.executeUpdate(delete0);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    public void viewMaterialePass() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(100);
        user.setName("Luca");
        user.setSurname("Rossi");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setEmail("luca@gmail.com");
        user.setPwd("lol");
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        ViewMaterialeControl servlet = Mockito.spy(ViewMaterialeControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("2"));
    }
    @Test // il team dove lavora il dipendente non ha il documento
    public void viewMaterialeFail() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(101);
        user.setName("Maria");
        user.setSurname("Espo");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setEmail("maria@gmail.com");
        user.setPwd("lol");
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        ViewMaterialeControl servlet = Mockito.spy(ViewMaterialeControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("3"));
    }


}
