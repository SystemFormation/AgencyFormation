package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.CandidatoAssuntoControl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

public class CandidatoAssuntoControlTest {
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
        CandidatoAssuntoControl servlet = Mockito.spy(CandidatoAssuntoControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("1"));
    }

    @Test
    public void assumiFail() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("annoDipendente")).thenReturn("2000");
        Mockito.when(request.getParameter("residenzaDipendente")).thenReturn("Test");
        Mockito.when(request.getParameter("telefonoDipendente")).thenReturn("1259876369");
        CandidatoAssuntoControl servlet = Mockito.spy(CandidatoAssuntoControl.class);
        try (MockedStatic<CandidatoAssuntoControl> mockedStatic = mockStatic(CandidatoAssuntoControl.class)) {
            mockedStatic.when(() -> CandidatoAssuntoControl.assumiCandidatoFromManager(any(Dipendente.class))).thenReturn(false);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("2"));
        }
    }
    @Test
    public void assumiPass() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("annoDipendente")).thenReturn("2000");
        Mockito.when(request.getParameter("residenzaDipendente")).thenReturn("Test");
        Mockito.when(request.getParameter("telefonoDipendente")).thenReturn("1259876369");
        CandidatoAssuntoControl servlet = Mockito.spy(CandidatoAssuntoControl.class);
        try (MockedStatic<CandidatoAssuntoControl> mockedStatic = mockStatic(CandidatoAssuntoControl.class)) {
            mockedStatic.when(() -> CandidatoAssuntoControl.assumiCandidatoFromManager(any(Dipendente.class))).thenReturn(true);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("3"));
        }
    }
}
