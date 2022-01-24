package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.control.RemoveTeamControl;
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
import static org.mockito.Mockito.spy;

public class RemoveTeamControlIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init(){
        Const.nomeDB = Const.NOME_DB_TEST;
    }

    @AfterAll
    public static void finish() throws SQLException {
        String update = "update dipendenti set Stato = false, IdTeam = 1 where IdDipendente=2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.executeUpdate();
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    public void removeFail() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(3);
        user.setPwd("lol");
        user.setEmail("m.nocerino@studenti.unisa.it");
        user.setRole(RuoliUtenti.TM);
        user.setName("Manuel");
        user.setSurname("Nocerino");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        RemoveTeamControl servlet = spy(RemoveTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("idDip")).thenReturn("-2");
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));
    }
    @Test
    public void removeOK() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(3);
        user.setPwd("lol");
        user.setEmail("m.nocerino@studenti.unisa.it");
        user.setRole(RuoliUtenti.TM);
        user.setName("Manuel");
        user.setSurname("Nocerino");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        RemoveTeamControl servlet = spy(RemoveTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("idDip")).thenReturn("2");
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("1"));
    }


}
