package agency_formation.formazione.control;

import it.unisa.agency_formation.formazione.control.CheckMaterialeFormazione;
import it.unisa.agency_formation.formazione.domain.Documento;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.mockStatic;

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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckMaterialeFormazioneTest {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @Test
    public void checkIdTeamFail() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        CheckMaterialeFormazione servlet = Mockito.spy(CheckMaterialeFormazione.class);
        Mockito.when(request.getParameter("idTeam")).thenReturn("0");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("1"));
    }

    @Test
    public void checkDocumentoPass() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        Documento documento = new Documento();
        CheckMaterialeFormazione servlet = Mockito.spy(CheckMaterialeFormazione.class);
        Mockito.when(request.getParameter("idTeam")).thenReturn("1");
        try(MockedStatic<CheckMaterialeFormazione> mockedStatic = mockStatic(CheckMaterialeFormazione.class)){
            mockedStatic.when(() -> CheckMaterialeFormazione.getDocumentofromManager(1)).thenReturn(documento);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("2"));
        }

    }
    @Test
    public void checkDocumentoFail() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        CheckMaterialeFormazione servlet = Mockito.spy(CheckMaterialeFormazione.class);
        Mockito.when(request.getParameter("idTeam")).thenReturn("1");
        try(MockedStatic<CheckMaterialeFormazione> mockedStatic = mockStatic(CheckMaterialeFormazione.class)){
            mockedStatic.when(() -> CheckMaterialeFormazione.getDocumentofromManager(1)).thenReturn(null);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("3"));
        }

    }



}
