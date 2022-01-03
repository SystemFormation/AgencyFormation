package agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.control.LoginControl;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

public class SkillControlTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;


    //Da rivere //TODO
    @Test
    public void skillNameDescNull() throws IOException, ServletException{
        Mockito.when(request.getSession().getAttribute("user").thenReturn("user"));
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("skillName")).thenReturn(null);
        Mockito.when(request.getParameter("skillDescr")).thenReturn(null);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new SkillControl().doPost(request,response);
        writer.flush();
        assertFalse(stringWriter.toString().contains("5"));
    }
    @Test
    public void skillNameNull() throws IOException, ServletException{
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("skillName")).thenReturn(null);
        Mockito.when(request.getParameter("skillDescr")).thenReturn("Conoscenza base");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new SkillControl().doPost(request,response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("1"));
    }
    @Test
    public void skillPass() throws IOException,ServletException{
        Mockito.when(request.getSession(true)).thenReturn(session);
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        SkillControl servlet = new SkillControl();
        Mockito.when(request.getParameter("skillName")).thenReturn("JAVASCRIPT");
        Mockito.when(request.getParameter("skillDescr")).thenReturn("Conoscenza avanzata");
        ServletContext context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request,response);
        assertTrue(stringWriter.toString().equals("3"));
    }
}
