package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.control.ListaTeam;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
import static org.mockito.ArgumentMatchers.anyString;

public class ListaTeamIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String insertDocumento = "insert into documenti (IdDocumento,MaterialeDiFormazione,IdHR,IdTeam) values (2,'//test',4,2)";
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(2, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        String insertUtente = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(201,'test','test','test','test', 1)";
        String insertDipendente = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(201, 'Fisciano','77777',1,2000,2)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(insertTeam);
        statement.executeUpdate(insertTeam);
        statement.executeUpdate(insertDocumento);
        statement.executeUpdate(insertUtente);
        statement.executeUpdate(insertDipendente);
    }

    @AfterAll
    public static void finish() throws SQLException {
        String deleteUtente = "delete from Utenti where IdUtente>4";
        String deleteDocumento = "delete from documenti where IdDocumento>=1";
        String deleteTeam = "delete from team where idTeam>=1";
        String deleteDipendente = "delete from dipendenti where idDipendente > 2";
        String insertTeamDefault = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(1, 'TechAll', '8', 'NuoveTecnologie', 'Ricerchiamo nuove tecnologie', 'Basi di Ingegneria Meccanica', 3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteDocumento);
        statement.executeUpdate(deleteDocumento);
        statement.executeUpdate(deleteTeam);
        statement.executeUpdate(deleteDipendente);
        statement.executeUpdate(deleteUtente);
        statement.executeUpdate(insertTeamDefault);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    public void listTeamTMPass() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setId(3);
        user.setRole(RuoliUtenti.TM);
        user.setPwd("lol");
        user.setEmail("m.nocerino@studenti.unisa.it");
        user.setName("Manuel");
        user.setSurname("Nocerino");
        ListaTeam servlet = Mockito.spy(ListaTeam.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("1"));

    }

    @Test
    public void listTeamHRPass() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ListaTeam servlet = Mockito.spy(ListaTeam.class);
        Utente user = new Utente();
        user.setId(4);
        user.setRole(RuoliUtenti.HR);
        user.setPwd("lol");
        user.setEmail("d.pagliuca@studenti.unisa.it");
        user.setName("Domenico");
        user.setSurname("Pagliuca");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));

    }
}
