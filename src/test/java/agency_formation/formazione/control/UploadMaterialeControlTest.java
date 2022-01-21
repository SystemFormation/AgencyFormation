package agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.control.ProfiloControl;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.control.UploadMaterialeControl;
import it.unisa.agency_formation.formazione.domain.Documento;
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
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class UploadMaterialeControlTest {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @Test //mi aspetto 1
    public void userNull() throws IOException, ServletException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        UploadMaterialeControl servlet = Mockito.spy(UploadMaterialeControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("1"));
    }
    @Test //mi aspetto 2
    public void idTeamNull() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.HR);
        user.setEmail("hr@gmail.com");
        user.setPwd("lol");
        user.setName("HR");
        user.setSurname("Test");
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        Mockito.when(request.getParameter("idTeam")).thenReturn(null);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        UploadMaterialeControl servlet = Mockito.spy(UploadMaterialeControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("2"));
    }
    @Test //mi aspetto 3
    public void materialeNonPassato() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.HR);
        user.setEmail("hr@gmail.com");
        user.setPwd("lol");
        user.setName("HR");
        user.setSurname("Test");
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        Mockito.when(request.getParameter("idTeam")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getPart("materiale")).thenReturn(null);
        UploadMaterialeControl servlet = Mockito.spy(UploadMaterialeControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("3"));
    }
    @Test //mi aspetto 5, quindi la servlet funziona come mi aspetto
    public void documentoSalvato() throws ServletException, IOException {
        Part part = mock(Part.class);
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.HR);
        user.setEmail("hr@gmail.com");
        user.setPwd("lol");
        user.setName("HR");
        user.setSurname("Test");
        config = Mockito.mock(ServletConfig.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getParameter("idTeam")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getPart("materiale")).thenReturn(part);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        UploadMaterialeControl servlet = Mockito.spy(UploadMaterialeControl.class);
        try(MockedStatic<UploadMaterialeControl> mockedStatic = mockStatic(UploadMaterialeControl.class)){
            mockedStatic.when(() -> UploadMaterialeControl.saveDocumentFromManager(any(Documento.class))).thenReturn(true);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("5"));
        }
    }

    @Test //mi aspetto 6
    public void documentoNonSalvato() throws IOException, ServletException {
        Part part = mock(Part.class);
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.HR);
        user.setEmail("hr@gmail.com");
        user.setPwd("lol");
        user.setName("HR");
        user.setSurname("Test");
        config = Mockito.mock(ServletConfig.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getParameter("idTeam")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getPart("materiale")).thenReturn(part);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        UploadMaterialeControl servlet = Mockito.spy(UploadMaterialeControl.class);
        try(MockedStatic<UploadMaterialeControl> mockedStatic = mockStatic(UploadMaterialeControl.class)){
            mockedStatic.when(() -> UploadMaterialeControl.saveDocumentFromManager(any(Documento.class))).thenReturn(false);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("6"));
        }
    }
}
