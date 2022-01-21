package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.AcceptCandidatureControl;
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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class AcceptCandidatureControlTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;


    @Test
    public void userNull() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        AcceptCandidatureControl servlet = Mockito.spy(AcceptCandidatureControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("3"));
    }


    @Test //mi aspetto 2
    public void acceptCandidaturaFail() throws ServletException, IOException, ParseException, SQLException {
        Utente user = new Utente("Manuel", "Nocerino", "m.nocerino@studenti.unisa.it", "lol", RuoliUtenti.HR);
        user.setId(1);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Date data = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-04-03 17:30");
        Timestamp timestamp = new Timestamp(data.getTime());
        AcceptCandidatureControl servlet = Mockito.spy(AcceptCandidatureControl.class);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Mockito.when(request.getParameter("data1")).thenReturn("2022-04-03");
        Mockito.when(request.getParameter("time")).thenReturn("17:30");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(1);
        try (MockedStatic<AcceptCandidatureControl> mockedStatic = mockStatic(AcceptCandidatureControl.class)) {
            mockedStatic.when(() -> AcceptCandidatureControl.getCandidaturaFromManager(1)).thenReturn(candidatura);
            mockedStatic.when(() -> AcceptCandidatureControl.acceptCandidatureFromManager(candidatura.getIdCandidatura(), user.getId(),timestamp)).thenReturn(false);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().contains("2"));
        }
    }

    @Test
    public void acceptPass() throws IOException, ServletException, ParseException {
        Utente user = new Utente("Manuel", "Nocerino", "m.nocerino@studenti.unisa.it", "lol", RuoliUtenti.HR);
        user.setId(1);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Date data = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-04-03 17:30");
        Timestamp timestamp = new Timestamp(data.getTime());
        AcceptCandidatureControl servlet = Mockito.spy(AcceptCandidatureControl.class);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Mockito.when(request.getParameter("data1")).thenReturn("2022-04-03");
        Mockito.when(request.getParameter("time")).thenReturn("17:30");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(1);
        try (MockedStatic<AcceptCandidatureControl> mockedStatic = mockStatic(AcceptCandidatureControl.class)) {
            mockedStatic.when(() -> AcceptCandidatureControl.getCandidaturaFromManager(1)).thenReturn(candidatura);
            mockedStatic.when(() -> AcceptCandidatureControl.acceptCandidatureFromManager(candidatura.getIdCandidatura(), user.getId(),timestamp)).thenReturn(true);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().contains("1"));
        }
    }

}