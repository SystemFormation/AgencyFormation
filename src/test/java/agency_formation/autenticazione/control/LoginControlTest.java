package agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.control.LoginControl;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.mockito.Mockito.mockStatic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

public class LoginControlTest {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;


    @Test
    public void loginUserNotNull1() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(LoginControl.class)) {
            mockedStatic.when(() -> LoginControl.getCandidaturafromManager(user.getId())).thenReturn(null);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().equals("6"));
        }
    }

    @Test //candidatura non null
    public void loginUserNotNull2() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(1);
        candidatura.setIdCandidato(1);
        candidatura.setDataCandidatura(new Date(new java.util.Date().getTime()));
        candidatura.setCurriculum("testPath");
        candidatura.setStato(StatiCandidatura.NonRevisionato);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(LoginControl.class)) {
            mockedStatic.when(() -> LoginControl.getCandidaturafromManager(user.getId())).thenReturn(candidatura);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().equals("6"));
        }
    }

    @Test
    public void loginUserNotNull3() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().equals("7"));

    }

    @Test
    public void loginUserNotNull4() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.HR);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().equals("9"));
    }

    @Test
    public void loginUserNotNull5() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.TM);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().equals("8"));

    }

    @Test
    public void loginEmailNull() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getParameter("email")).thenReturn(null);
        Mockito.when(request.getParameter("password")).thenReturn("lol");
        Mockito.when(request.getSession()).thenReturn(session);
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
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getParameter("email")).thenReturn("alberto@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn(null);
        Mockito.when(request.getSession()).thenReturn(session);
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
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getParameter("email")).thenReturn(" ");
        Mockito.when(request.getParameter("password")).thenReturn("lol");
        Mockito.when(request.getSession()).thenReturn(session);
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
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getParameter("email")).thenReturn("fra@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn(" ");
        Mockito.when(request.getSession()).thenReturn(session);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new LoginControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));
    }

    @Test
    public void loginPassCandidato() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getParameter("email")).thenReturn("l.giacchetti@studenti.unisa.it");
        Mockito.when(request.getParameter("password")).thenReturn("lol1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(LoginControl.class)) {
            mockedStatic.when(() -> LoginControl.loginFromManager("l.giacchetti@studenti.unisa.it", "lol1")).thenReturn(user);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().equals("3-10"));
        }
    }

    @Test
    public void loginPassCandidatoConCandidatura() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(1);
        candidatura.setIdCandidato(1);
        candidatura.setDataCandidatura(new Date(new java.util.Date().getTime()));
        candidatura.setCurriculum("testPath");
        candidatura.setStato(StatiCandidatura.NonRevisionato);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getParameter("email")).thenReturn("l.giacchetti@studenti.unisa.it");
        Mockito.when(request.getParameter("password")).thenReturn("lol1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(LoginControl.class)) {
            mockedStatic.when(() -> LoginControl.loginFromManager("l.giacchetti@studenti.unisa.it", "lol1")).thenReturn(user);
            mockedStatic.when(() -> LoginControl.getCandidaturafromManager(user.getId())).thenReturn(candidatura);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().equals("3-10")); // 3 perchè l'utente è loggato 10 perchè è un candidato
        }
    }

    @Test
    public void loginPassDipendente() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getParameter("email")).thenReturn("l.giacchetti@studenti.unisa.it");
        Mockito.when(request.getParameter("password")).thenReturn("lol1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(LoginControl.class)) {
            mockedStatic.when(() -> LoginControl.loginFromManager("l.giacchetti@studenti.unisa.it", "lol1")).thenReturn(user);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().equals("3-11"));
        }
    }

    @Test
    public void loginPassTM() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.TM);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getParameter("email")).thenReturn("l.giacchetti@studenti.unisa.it");
        Mockito.when(request.getParameter("password")).thenReturn("lol1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(LoginControl.class)) {
            mockedStatic.when(() -> LoginControl.loginFromManager("l.giacchetti@studenti.unisa.it", "lol1")).thenReturn(user);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().equals("3-12"));
        }
    }
    @Test
    public void loginPassHR() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.HR);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = Mockito.spy(LoginControl.class);
        Mockito.when(request.getParameter("email")).thenReturn("l.giacchetti@studenti.unisa.it");
        Mockito.when(request.getParameter("password")).thenReturn("lol1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(LoginControl.class)) {
            mockedStatic.when(() -> LoginControl.loginFromManager("l.giacchetti@studenti.unisa.it", "lol1")).thenReturn(user);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().equals("3-13"));
        }
    }

    @Test
    public void loginFailEmailWrong() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        LoginControl servlet = new LoginControl();
        Mockito.when(request.getParameter("email")).thenReturn("genny@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("lol");
        Mockito.when(request.getSession()).thenReturn(session);
        context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().equals("4"));
    }

    @Test
    public void loginFailPasswordWrong() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        LoginControl servlet = new LoginControl();
        Mockito.when(request.getParameter("email")).thenReturn("mario@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("lol123");
        Mockito.when(request.getSession()).thenReturn(session);
        context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().equals("4"));
    }

    @Test
    public void loginFailEmailPasswordWrong() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        LoginControl servlet = new LoginControl();
        Mockito.when(request.getParameter("email")).thenReturn("pippo@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("lol123");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().equals("4"));
    }
}
