package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.control.ProfiloControl;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.AssunzioneCandidatoControl;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;

public class AssunzioneCandidatoControlTest {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;


    @Test
    public void userNull() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        AssunzioneCandidatoControl servlet = Mockito.spy(AssunzioneCandidatoControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("4"));
    }

    @Test
    public void candidaturaNull() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.HR);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        try (MockedStatic<AssunzioneCandidatoControl> mockedStatic = mockStatic(AssunzioneCandidatoControl.class)) {
            mockedStatic.when(() -> AssunzioneCandidatoControl.getCandidaturaFromManager(user.getId())).thenReturn(null);
            AssunzioneCandidatoControl servlet = Mockito.spy(AssunzioneCandidatoControl.class);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("1"));
        }
    }
    @Test
    public void candidaturaPass() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.HR);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        Candidatura candidatura = new Candidatura();
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        try (MockedStatic<AssunzioneCandidatoControl> mockedStatic = mockStatic(AssunzioneCandidatoControl.class)) {
            mockedStatic.when(() -> AssunzioneCandidatoControl.getCandidaturaFromManager(user.getId())).thenReturn(candidatura);
            mockedStatic.when(() -> AssunzioneCandidatoControl.setStatoFromManager(eq(1))).thenReturn(false);
            AssunzioneCandidatoControl servlet = Mockito.spy(AssunzioneCandidatoControl.class);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("3"));
        }
    }

    @Test
    public void candidaturaPass2() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.HR);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        Candidatura candidatura = new Candidatura();
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        try (MockedStatic<AssunzioneCandidatoControl> mockedStatic = mockStatic(AssunzioneCandidatoControl.class)) {
            mockedStatic.when(() -> AssunzioneCandidatoControl.getCandidaturaFromManager(user.getId())).thenReturn(candidatura);
            mockedStatic.when(() -> AssunzioneCandidatoControl.setStatoFromManager(anyInt())).thenReturn(true);
            AssunzioneCandidatoControl servlet = Mockito.spy(AssunzioneCandidatoControl.class);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("2"));
        }
    }

}
