package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.ViewCandidaturaControl;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.View;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

public class ViewCandidatureControlTest {
    private static HttpServletRequest request;
    private static HttpServletResponse response;



    @Test
    public void idCandidatoNll() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        ViewCandidaturaControl servlet = Mockito.spy(ViewCandidaturaControl.class);
        Mockito.when(request.getParameter("idCandidato")).thenReturn(null);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().contains("4"));
    }

    @Test
    public void candidaturaNull() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        ViewCandidaturaControl servlet = Mockito.spy(ViewCandidaturaControl.class);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        try(MockedStatic<ViewCandidaturaControl> mockedStatic = mockStatic(ViewCandidaturaControl.class)){
            mockedStatic.when(() -> ViewCandidaturaControl.getCandidaturaByIdFromManager(1)).thenReturn(null);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);

            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().contains("1"));
        }

    }


    @Test
    public void candidaturaNotNull() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        ViewCandidaturaControl servlet = Mockito.spy(ViewCandidaturaControl.class);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(1);
        candidatura.setCurriculum("test");
        try(MockedStatic<ViewCandidaturaControl> mockedStatic = mockStatic(ViewCandidaturaControl.class)){
            mockedStatic.when(() -> ViewCandidaturaControl.getCandidaturaByIdFromManager(1)).thenReturn(candidatura);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);

            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().contains("3"));
        }

    }
    @Test
    public void candidaturaConDocumenti() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        ViewCandidaturaControl servlet = Mockito.spy(ViewCandidaturaControl.class);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("1");
        Candidatura candidatura = new Candidatura();
        candidatura.setIdCandidatura(1);
        candidatura.setCurriculum("test");
        candidatura.setDocumentiAggiuntivi("test");
        try(MockedStatic<ViewCandidaturaControl> mockedStatic = mockStatic(ViewCandidaturaControl.class)){
            mockedStatic.when(() -> ViewCandidaturaControl.getCandidaturaByIdFromManager(1)).thenReturn(candidatura);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().contains("2"));
        }

    }

}
