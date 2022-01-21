package agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.control.RegistrazioneControl;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

/*Questa classe testa la registrazione di un utente*/
public class RegistrazioneControlTest extends Mockito {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @Test //nome==null
    public void regTestNome1() throws IOException, ServletException {
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
    public void regTestNome2() throws IOException, ServletException {
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
    public void regTestNome3() throws IOException, ServletException {
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
    public void regTestCognome1() throws IOException, ServletException {
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
    public void regTestCognome2() throws IOException, ServletException {
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
    public void regTestCognome3() throws IOException, ServletException {
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
    public void regTestEmail1() throws IOException, ServletException {
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
    public void regTestEmail2() throws IOException, ServletException {
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
    public void regTestEmail3() throws IOException, ServletException {
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
    /*
    @Test //password=null
    public void regTestPass1() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = new RegistrazioneControl();
        Mockito.when(request.getParameter("nome")).thenReturn("Manuel");
        Mockito.when(request.getParameter("cognome")).thenReturn("Nocerino");
        Mockito.when(request.getParameter("email")).thenReturn("manuel@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn(null);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("4"));
    }
    @Test //password=empty
    public void regTestPass2() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = new RegistrazioneControl();
        Mockito.when(request.getParameter("nome")).thenReturn("Manuel");
        Mockito.when(request.getParameter("cognome")).thenReturn("Nocerino");
        Mockito.when(request.getParameter("email")).thenReturn("manuel@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("4"));
    }
    @Test //password=wrong
    public void regTestPass3() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = new RegistrazioneControl();
        Mockito.when(request.getParameter("nome")).thenReturn("Manuel");
        Mockito.when(request.getParameter("cognome")).thenReturn("Nocerino");
        Mockito.when(request.getParameter("email")).thenReturn("manuel@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("L@l");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("4"));
    }*/

    @Test //da controllare
    public void regPass() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        RegistrazioneControl servlet = Mockito.spy(RegistrazioneControl.class);
        Utente user = new Utente();
        user.setName("Manuel");
        user.setSurname("Noce");
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setEmail("manuel@gmail.com");
        user.setPwd("lol");
        Mockito.when(request.getParameter("nome")).thenReturn("Manuel");
        Mockito.when(request.getParameter("cognome")).thenReturn("Noce");
        Mockito.when(request.getParameter("email")).thenReturn("manuel@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(RegistrazioneControl.class)) {
            mockedStatic.when(() -> RegistrazioneControl.registrazioneFromManager(any(Utente.class))).thenReturn(true);
            mockedStatic.when(() -> RegistrazioneControl.loginFromManager(user.getEmail(),user.getPwd())).thenReturn(user);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.doGet(request, response);
            servlet.init(config);
            assertTrue(stringWriter.toString().contains("5"));
        }
    }
}
