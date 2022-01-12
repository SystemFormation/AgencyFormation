package agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.control.LoginControl;
import it.unisa.agency_formation.autenticazione.control.RegistrazioneControl;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

/*Questa classe testa la registrazione di un utente*/
public class RegistrazioneControlTest extends Mockito {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;


    @BeforeAll
    public static void setup(){
    }


    @Test //nome==null
    public void regTestNome1() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        RegistrazioneControl servlet = new RegistrazioneControl();
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
        RegistrazioneControl servlet = new RegistrazioneControl();
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
        RegistrazioneControl servlet = new RegistrazioneControl();
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
        RegistrazioneControl servlet = new RegistrazioneControl();
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
        RegistrazioneControl servlet = new RegistrazioneControl();
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
        RegistrazioneControl servlet = new RegistrazioneControl();
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
        RegistrazioneControl servlet = new RegistrazioneControl();
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
        RegistrazioneControl servlet = new RegistrazioneControl();
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
        RegistrazioneControl servlet = new RegistrazioneControl();
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
    }

    @Test //da controllare
    public void regPass() throws IOException, ServletException {
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        LoginControl servlet = new LoginControl();
        Mockito.when(request.getParameter("nome")).thenReturn("Manuel");
        Mockito.when(request.getParameter("cognome")).thenReturn("Nocerino");
        Mockito.when(request.getParameter("email")).thenReturn("manuel@gmail.com");
        Mockito.when(request.getParameter("pwd")).thenReturn("lol");
        Mockito.when(request.getSession(true)).thenReturn(session);
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request,response);
        assertTrue(stringWriter.toString().equals("5"));
    }
}
