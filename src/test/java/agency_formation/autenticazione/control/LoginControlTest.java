package agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.control.LoginControl;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

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

public class LoginControlTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private ServletConfig config;


    @Test
    public void loginEmailNull() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("email")).thenReturn(null);
        Mockito.when(request.getParameter("password")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new LoginControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("5"));
    }

    @Test
    public void loginPasswordNull() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("email")).thenReturn("alberto@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn(null);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new LoginControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("5"));
    }

    @Test
    public void loginEmailEmpty() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("email")).thenReturn(" ");
        Mockito.when(request.getParameter("password")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new LoginControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("1"));
    }

    @Test
    public void loginPasswordEmpty() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("email")).thenReturn("alberto@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn(" ");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new LoginControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));
    }

    @Test
    //da controllare
    public void loginPass() throws IOException, ServletException {
        Utente user = new Utente();
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(request.getParameter("email")).thenReturn("genny@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("lol");
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(new LoginControl().getServletConfig()).thenReturn(config);
        Mockito.when(request.getRequestDispatcher("/Home.jsp")).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new LoginControl().doPost(request, response);
        writer.flush();
        Mockito.verify(session).setAttribute("user", user);
        Mockito.verify(dispatcher).forward(request, response);
        assertTrue(stringWriter.toString().contains("3"));
    }
}
