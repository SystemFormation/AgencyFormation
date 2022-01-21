package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.control.LoginControl;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.RejectCandidatureControl;
import it.unisa.agency_formation.reclutamento.control.RifiutaColloquioControl;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
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
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

public class RifiutaColloquioControlTest {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;


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
        new RifiutaColloquioControl().doPost(request, response);
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
        new RifiutaColloquioControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("3"));
    }


    @Test
    public void rejectInterviewPass() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(4);
        user.setRole(RuoliUtenti.HR);
        user.setPwd("lol");
        user.setEmail("d.pagliuca@studenti.unisa.it");
        user.setName("Domenico");
        user.setSurname("Pagliuca");
        Utente candidato = new Utente();
        candidato.setId(5);
        candidato.setRole(RuoliUtenti.CANDIDATO);
        candidato.setPwd("lol");
        candidato.setEmail("a.esposito@studenti.unisa.it");
        candidato.setName("Antonio");
        candidato.setSurname("Esposito");
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(1);
        candidatura.setIdCandidato(5);
        candidatura.setDataCandidatura(new Date(new java.util.Date().getTime()));
        candidatura.setCurriculum("testPath");
        candidatura.setStato(StatiCandidatura.NonRevisionato);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        RifiutaColloquioControl servlet = Mockito.spy(RifiutaColloquioControl.class);
        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("idCandidato")).thenReturn(String.valueOf(candidato.getId()));
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(RifiutaColloquioControl.class)) {
            mockedStatic.when(() -> RifiutaColloquioControl.getCandidaturaFromManager(candidato.getId())).thenReturn(candidatura);
            mockedStatic.when(() -> RifiutaColloquioControl.rejectCandidaturaFromManager(candidatura.getIdCandidatura(), user.getId())).thenReturn(true);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().contains("1"));
        }
    }
    @Test
    public void rejectInterviewFail() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(4);
        user.setRole(RuoliUtenti.HR);
        user.setPwd("lol");
        user.setEmail("d.pagliuca@studenti.unisa.it");
        user.setName("Domenico");
        user.setSurname("Pagliuca");
        Utente candidato = new Utente();
        candidato.setId(5);
        candidato.setRole(RuoliUtenti.CANDIDATO);
        candidato.setPwd("lol");
        candidato.setEmail("a.esposito@studenti.unisa.it");
        candidato.setName("Antonio");
        candidato.setSurname("Esposito");
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(1);
        candidatura.setIdCandidato(5);
        candidatura.setDataCandidatura(new Date(new java.util.Date().getTime()));
        candidatura.setCurriculum("testPath");
        candidatura.setStato(StatiCandidatura.NonRevisionato);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        RifiutaColloquioControl servlet = Mockito.spy(RifiutaColloquioControl.class);
        Mockito.when(request.getSession()).thenReturn(session);

        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("idCandidato")).thenReturn(String.valueOf(candidato.getId()));
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(RifiutaColloquioControl.class)) {
            mockedStatic.when(() -> RifiutaColloquioControl.getCandidaturaFromManager(candidato.getId())).thenReturn(candidatura);
            mockedStatic.when(() -> RifiutaColloquioControl.rejectCandidaturaFromManager(candidatura.getIdCandidatura(), user.getId())).thenReturn(false);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().contains("2"));
        }
    }
}
