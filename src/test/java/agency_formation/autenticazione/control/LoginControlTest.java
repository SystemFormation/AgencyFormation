package agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.control.LoginControl;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.ArgumentMatchers.anyString;

public class LoginControlTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

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
        Mockito.when(request.getParameter("email")).thenReturn("fra@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn(" ");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new LoginControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));
    }

    @Test
    public void loginPass() throws IOException, ServletException {
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        LoginControl servlet = new LoginControl();
        Mockito.when(request.getParameter("email")).thenReturn("l.giacchetti@studenti.unisa.it");
        Mockito.when(request.getParameter("password")).thenReturn("lol");
        Mockito.when(request.getSession(true)).thenReturn(session);
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request,response);
        assertTrue(stringWriter.toString().equals("3"));
    }
    @Test
    public void loginFailEmailWrong() throws IOException, ServletException {
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        LoginControl servlet = new LoginControl();
        Mockito.when(request.getParameter("email")).thenReturn("genny@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("lol");
        Mockito.when(request.getSession(true)).thenReturn(session);
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request,response);
        assertTrue(stringWriter.toString().equals("4"));
    }
    @Test
    public void loginFailPasswordWrong() throws IOException, ServletException {
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        LoginControl servlet = new LoginControl();
        Mockito.when(request.getParameter("email")).thenReturn("mario@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("lol123");
        Mockito.when(request.getSession(true)).thenReturn(session);
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request,response);
        assertTrue(stringWriter.toString().equals("4"));
    }
    @Test
    public void loginFailEmailPasswordWrong() throws IOException, ServletException {
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        LoginControl servlet = new LoginControl();
        Mockito.when(request.getParameter("email")).thenReturn("pippo@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("lol123");
        Mockito.when(request.getSession(true)).thenReturn(session);
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request,response);
        assertTrue(stringWriter.toString().equals("4"));
    }
}
