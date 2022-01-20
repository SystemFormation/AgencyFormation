package agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.control.RegistrazioneControl;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
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

public class RegistrazioneControlIT {
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
        String query = "Delete from af_db_test.utenti where IdUtente > 4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test //nome==null
    public void regTestNome1It() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Mockito.when(request.getParameter("nome")).thenReturn(null);
        Mockito.when(request.getParameter("cognome")).thenReturn("Cecco");
        Mockito.when(request.getParameter("email")).thenReturn("genny@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("1"));
    }
    @Test //nome==empty
    public void regTestNome2It() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Mockito.when(request.getParameter("nome")).thenReturn("");
        Mockito.when(request.getParameter("cognome")).thenReturn("Cecco");
        Mockito.when(request.getParameter("email")).thenReturn("genny@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("1"));
    }
    @Test //nome==wrong
    public void regTestNome3It() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Mockito.when(request.getParameter("nome")).thenReturn("434324");
        Mockito.when(request.getParameter("cognome")).thenReturn("Cecco");
        Mockito.when(request.getParameter("email")).thenReturn("genny@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("1"));
    }

    @Test //cognome==null
    public void regTestCognome1It() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Mockito.when(request.getParameter("nome")).thenReturn("Gennaro");
        Mockito.when(request.getParameter("cognome")).thenReturn(null);
        Mockito.when(request.getParameter("email")).thenReturn("genny@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("2"));
    }

    @Test //cognome==empty
    public void regTestCognome2It() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Mockito.when(request.getParameter("nome")).thenReturn("Gennaro");
        Mockito.when(request.getParameter("cognome")).thenReturn("");
        Mockito.when(request.getParameter("email")).thenReturn("genny@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("2"));
    }

    @Test //cognome==wrong
    public void regTestCognome3It() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Mockito.when(request.getParameter("nome")).thenReturn("Gennaro");
        Mockito.when(request.getParameter("cognome")).thenReturn("453543");
        Mockito.when(request.getParameter("email")).thenReturn("genny@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("2"));
    }

    @Test //email==null
    public void regTestEmail1It() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Mockito.when(request.getParameter("nome")).thenReturn("Gennaro");
        Mockito.when(request.getParameter("cognome")).thenReturn("Cecco");
        Mockito.when(request.getParameter("email")).thenReturn(null);
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("3"));
    }

    @Test //email==empty
    public void regTestEmail2It() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Mockito.when(request.getParameter("nome")).thenReturn("Gennaro");
        Mockito.when(request.getParameter("cognome")).thenReturn("Cecco");
        Mockito.when(request.getParameter("email")).thenReturn("");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("3"));
    }

    @Test //email==wrong
    public void regTestEmail3It() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Mockito.when(request.getParameter("nome")).thenReturn("Gennaro");
        Mockito.when(request.getParameter("cognome")).thenReturn("Cecco");
        Mockito.when(request.getParameter("email")).thenReturn("432@gmail.32com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("3"));
    }

    @Test
    public void regPassIt() throws IOException, ServletException, SQLException {
        config = Mockito.mock(ServletConfig.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Utente user = new Utente();
        user.setName("Manuel");
        user.setSurname("Nocerino");
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setEmail("manuel@gmail.com");
        user.setPwd("lol");

        Mockito.when(request.getParameter("nome")).thenReturn("Manuel");
        Mockito.when(request.getParameter("cognome")).thenReturn("Nocerino");
        Mockito.when(request.getParameter("email")).thenReturn("manuel@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().equals("5"));
        }
}
