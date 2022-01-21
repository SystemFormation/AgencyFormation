package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.RejectCandidatureControl;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.team.control.AddTeamControl;
import it.unisa.agency_formation.team.control.AggiuntaDipendente;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

public class RejectCandidatureControlTest {
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
        new RejectCandidatureControl().doPost(request, response);
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
        new RejectCandidatureControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("3"));
    }

    @Test
    public void rejectCandidaturePass() throws IOException, ServletException, ParseException {
        int idUser = 10;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.HR);
        user.setId(idUser);
        int idCandidato = 7;
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(20);
        candidatura.setCurriculum("/Test");
        candidatura.setDocumentiAggiuntivi("/Test2");
        Date data = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-04-03 17:30");
        Timestamp timestamp = new Timestamp(data.getTime());
        candidatura.setDataCandidatura(new java.sql.Date(2010,02,21));
        candidatura.setDataOraColloquio(timestamp);
        candidatura.setIdCandidato(idCandidato);
        candidatura.setIdHR(idUser);

        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);

        RejectCandidatureControl servlet = Mockito.spy(RejectCandidatureControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getParameter("idCandidato")).thenReturn(String.valueOf(idCandidato));
        Mockito.when(request.getServletContext()).thenReturn(context);

        try (MockedStatic mockedStatic = mockStatic(RejectCandidatureControl.class)) {
            mockedStatic.when(() -> RejectCandidatureControl.getCandidatura(idCandidato)).thenReturn(candidatura);
            mockedStatic.when(() -> RejectCandidatureControl.rejectCandidatura(candidatura.getIdCandidatura(),user.getId())).thenReturn(true);

            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("1"));
        }

    }
    @Test
    public void rejectCandidatureFail() throws IOException, ServletException, ParseException {
        int idUser = 10;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.HR);
        user.setId(idUser);
        int idCandidato = 7;
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(20);
        candidatura.setCurriculum("/Test");
        candidatura.setDocumentiAggiuntivi("/Test2");
        Date data = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-04-03 17:30");
        Timestamp timestamp = new Timestamp(data.getTime());
        candidatura.setDataCandidatura(new java.sql.Date(2010,02,21));
        candidatura.setDataOraColloquio(timestamp);
        candidatura.setIdCandidato(idCandidato);
        candidatura.setIdHR(idUser);

        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);

        RejectCandidatureControl servlet = Mockito.spy(RejectCandidatureControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getParameter("idCandidato")).thenReturn(String.valueOf(idCandidato));
        Mockito.when(request.getServletContext()).thenReturn(context);

        try (MockedStatic mockedStatic = mockStatic(RejectCandidatureControl.class)) {
            mockedStatic.when(() -> RejectCandidatureControl.getCandidatura(idCandidato)).thenReturn(candidatura);
            mockedStatic.when(() -> RejectCandidatureControl.rejectCandidatura(candidatura.getIdCandidatura(),user.getId())).thenReturn(false);

            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("2"));
        }

    }
}
