package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.DownloadControl;
import it.unisa.agency_formation.reclutamento.control.ListaCandidati;
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
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

public class DownloadControlTest {private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private ServletConfig config;
    private ServletContext context;

    @Test //User non HR
    public void downloadFail() throws ServletException, IOException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.HR);
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
        DownloadControl servlet = Mockito.spy(DownloadControl.class);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(DownloadControl.class)) {
            mockedStatic.when(() -> DownloadControl.getCandidaturaFromManager(user.getId())).thenReturn(candidatura);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertEquals("2", stringWriter.toString());
        }
    }
    @Test
    public void DownloadPass() throws ServletException, IOException, SQLException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.HR);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
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
        DownloadControl servlet = Mockito.spy(DownloadControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(DownloadControl.class)) {
            mockedStatic.when(() -> DownloadControl.getCandidaturaFromManager(user.getId())).thenReturn(candidatura);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertEquals("1", stringWriter.toString());
        }
    }
}
