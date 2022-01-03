package agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.control.LoginControl;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.control.SkillControl;
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
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

public class SkillControlTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    //
    @Test
    public void skillNameDescNull() throws IOException, ServletException, SQLException {
        Utente user = UtenteDAO.login("p.severino@studenti.unisa.it","lol");
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("skillName")).thenReturn(null);
        Mockito.when(request.getParameter("skillDescr")).thenReturn(null);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new SkillControl().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("5"));
    }
    @Test
    public void skillNameNull() throws IOException, ServletException, SQLException {
        Utente user = UtenteDAO.login("p.severino@studenti.unisa.it","lol");
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("skillName")).thenReturn(null);
        Mockito.when(request.getParameter("skillDescr")).thenReturn("Conoscenza base");
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new SkillControl().doPost(request,response);
        writer.flush();
        assertFalse(stringWriter.toString().contains("1"));
    }
    @Test
    public void skillDescNull() throws IOException, ServletException, SQLException {
        Utente user = UtenteDAO.login("p.severino@studenti.unisa.it","lol");
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("skillName")).thenReturn("JAVASCRIPT");
        Mockito.when(request.getParameter("skillDescr")).thenReturn(null);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new SkillControl().doPost(request,response);
        writer.flush();
        assertFalse(stringWriter.toString().contains("2"));
    }
    @Test
    public void skillPass() throws IOException, ServletException, SQLException {
        Utente user = UtenteDAO.login("p.severino@studenti.unisa.it","lol");
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        SkillControl servlet = new SkillControl();
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("skillName")).thenReturn("JAVASCRIPT");
        Mockito.when(request.getParameter("skillDescr")).thenReturn("Conoscenza avanzata");
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request,response);
        assertTrue(stringWriter.toString().equals("3"));
    }
}
