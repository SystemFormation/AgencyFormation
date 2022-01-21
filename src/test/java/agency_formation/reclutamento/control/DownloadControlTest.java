package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.control.ProfiloControl;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.DownloadControl;
import it.unisa.agency_formation.reclutamento.control.ListaCandidati;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.*;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class DownloadControlTest {
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
        DownloadControl servlet = Mockito.spy(DownloadControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("2"));
    }


    @Test
    public void candidaturaNull() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.HR);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Mockito.when(request.getParameter("toDownload")).thenReturn("test");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        DownloadControl servlet = Mockito.spy(DownloadControl.class);
        try(MockedStatic<DownloadControl> mockedStatic = mockStatic(DownloadControl.class)){
            mockedStatic.when(() -> DownloadControl.getCandidaturaFromManager(user.getId())).thenReturn(null);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("1"));
        }
    }

    @Test
    public void downloadCurriculum() throws IOException, ServletException {
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
        candidatura.setCurriculum("\\AgencyFormationFile\\Test\\test.pdf");
        candidatura.setStato(StatiCandidatura.NonRevisionato);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);
        DownloadControl servlet = Mockito.spy(DownloadControl.class);
        Mockito.when(request.getParameter("toDownload")).thenReturn("curriculum");
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getMimeType(System.getProperty("user.home")+candidatura.getCurriculum())).thenReturn(null);
        Mockito.when(response.getOutputStream()).thenReturn(servletOutputStream);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try(MockedStatic<DownloadControl> mockedStatic = mockStatic(DownloadControl.class)){
            mockedStatic.when(() -> DownloadControl.getCandidaturaFromManager(user.getId())).thenReturn(candidatura);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("3"));
        }
    }
    @Test
    public void downloadDocumenti() throws IOException, ServletException {
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
        candidatura.setCurriculum("\\AgencyFormationFile\\Test\\test.pdf");
        candidatura.setDocumentiAggiuntivi("\\AgencyFormationFile\\Test\\test.pdf");
        candidatura.setStato(StatiCandidatura.NonRevisionato);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);
        DownloadControl servlet = Mockito.spy(DownloadControl.class);
        Mockito.when(request.getParameter("toDownload")).thenReturn("documenti");
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getMimeType(System.getProperty("user.home")+candidatura.getDocumentiAggiuntivi())).thenReturn(null);
        Mockito.when(response.getOutputStream()).thenReturn(servletOutputStream);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try(MockedStatic<DownloadControl> mockedStatic = mockStatic(DownloadControl.class)){
            mockedStatic.when(() -> DownloadControl.getCandidaturaFromManager(user.getId())).thenReturn(candidatura);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("4"));
        }
    }

}
