package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.control.SpecificaCompetenzeControl;
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

public class SpecificaCompetenzeControlIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init() throws SQLException{
        Const.nomeDB = Const.NOME_DB_TEST;
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(500, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(insertTeam);
        statement.executeUpdate();

    }
    @AfterAll
    public static void finish() throws SQLException {
        String deleteTeam = "delete from team where idTeam > 4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteTeam);
        statement.executeUpdate();
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }
    @Test
    public void userfail() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new SpecificaCompetenzeControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("3"));
    }
    @Test
    public void rolefail() throws IOException, ServletException {
        int idUser = 10;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.CANDIDATO);
        user.setId(idUser);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new SpecificaCompetenzeControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("3"));
    }
    @Test
    public void specificaCompetenzaActionPass() throws ServletException, IOException {
        int idUser = 10;
        int idTeam = 500;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        String competenza = "HTML e CSS";
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "competenze";

        SpecificaCompetenzeControl servlet = Mockito.spy(SpecificaCompetenzeControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));
        Mockito.when(request.getParameter("specCompetenze")).thenReturn(competenza);
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
    public void specificaCompetenzaFail() throws ServletException, IOException {
        int idUser = 10;
        int idTeam = 500;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        String competenza = "TEST@?!";
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "competenze";

        SpecificaCompetenzeControl servlet = Mockito.spy(SpecificaCompetenzeControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));
        Mockito.when(request.getParameter("specCompetenze")).thenReturn(competenza);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("4"));

    }

    @Test
    public void specificaCompetenzaFail2() throws ServletException, IOException {
        int idUser = 10;
        int idTeam = 502;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        String competenza = "HTML e CSS";
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "competenze";

        SpecificaCompetenzeControl servlet = Mockito.spy(SpecificaCompetenzeControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));
        Mockito.when(request.getParameter("specCompetenze")).thenReturn(competenza);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("5"));

    }
}