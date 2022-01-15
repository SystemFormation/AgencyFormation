package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.AcceptCandidatureControl;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class AcceptCandidatureControlTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @Test
    public void dataPass() throws ServletException, IOException {
        Utente user = new Utente("Manuel", "Nocerino",  "m.nocerino@studenti.unisa.it", "lol",RuoliUtenti.HR);
        user.setId(1);
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        AcceptCandidatureControl servlet = new AcceptCandidatureControl();
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Mockito.when(request.getParameter("data1")).thenReturn("2022-04-03");
        Mockito.when(request.getParameter("time")).thenReturn("17:30");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertEquals("1", stringWriter.toString());
    }

    @Test
    public void dataNull() throws IOException, ServletException {
        Utente user = new Utente("Manuel", "Nocerino",  "m.nocerino@studenti.unisa.it","lol", RuoliUtenti.HR);
        user.setId(0);
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        AcceptCandidatureControl servlet = new AcceptCandidatureControl();
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Mockito.when(request.getParameter("data1")).thenReturn("2022-04-03");
        Mockito.when(request.getParameter("time")).thenReturn("17:30");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertEquals("2", stringWriter.toString());
    }

    /* Da rifinire @Test
    public void candidatureNull() throws IOException, ServletException {
        Utente user = new Utente ( "Manuel", "Nocerino", "lol", "m.nocerino@studenti.unisa.it", RuoliUtenti.HR);
        user.setId(1);
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        AcceptCandidatureControl servlet = new AcceptCandidatureControl();
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Mockito.when(request.getParameter("data1")).thenReturn("2022-04-03");
        Mockito.when(request.getParameter("time")).thenReturn("17:30");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertEquals("3", stringWriter.toString());
    }*/
}