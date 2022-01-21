package agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.control.ProfiloControl;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.control.ViewMaterialeControl;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.team.domain.Team;
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
import javax.swing.text.View;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

public class ViewMaterialeControlTest {

    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @Test //mi aspetto 4
    public void userNull() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        ViewMaterialeControl servlet = Mockito.spy(ViewMaterialeControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("4"));
    }
    @Test //mi aspetto 1
    public void retrieveDipendenteFail() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setEmail("dip@gmail.com");
        user.setPwd("lol");
        user.setName("Dip");
        user.setSurname("Test");
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        ViewMaterialeControl servlet = Mockito.spy(ViewMaterialeControl.class);
        try(MockedStatic<ViewMaterialeControl> mockedStatic = mockStatic(ViewMaterialeControl.class)){
            mockedStatic.when(() -> ViewMaterialeControl.getDipendenteFromManager(user.getId())).thenReturn(null);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("1"));
        }

    }
    @Test //mi aspetto 3
    public void documentoNull() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setEmail("dip@gmail.com");
        user.setPwd("lol");
        user.setName("Dip");
        user.setSurname("Test");
        Dipendente dipendente = new Dipendente();
        dipendente.setIdDipendente(1);
        dipendente.setStato(StatiDipendenti.OCCUPATO);
        Team team = new Team();
        team.setIdTeam(1);
        dipendente.setTeam(team);
        dipendente.setResidenza("TestRes");
        dipendente.setTelefono("1258974569");
        dipendente.setAnnoNascita(2000);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        ViewMaterialeControl servlet = Mockito.spy(ViewMaterialeControl.class);
        try(MockedStatic<ViewMaterialeControl> mockedStatic = mockStatic(ViewMaterialeControl.class)){
            mockedStatic.when(() -> ViewMaterialeControl.getDipendenteFromManager(user.getId())).thenReturn(dipendente);
            mockedStatic.when(() -> ViewMaterialeControl.getDocumentoFromManager(dipendente.getTeam().getIdTeam())).thenReturn(null);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("3"));
        }
    }

    @Test //mi aspetto 2
    public void documentoPass() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setEmail("dip@gmail.com");
        user.setPwd("lol");
        user.setName("Dip");
        user.setSurname("Test");
        Dipendente dipendente = new Dipendente();
        dipendente.setIdDipendente(1);
        dipendente.setStato(StatiDipendenti.OCCUPATO);
        Team team = new Team();
        team.setIdTeam(1);
        dipendente.setTeam(team);
        dipendente.setResidenza("TestRes");
        dipendente.setTelefono("1258974569");
        dipendente.setAnnoNascita(2000);
        Documento documento = new Documento();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        ViewMaterialeControl servlet = Mockito.spy(ViewMaterialeControl.class);
        try(MockedStatic<ViewMaterialeControl> mockedStatic = mockStatic(ViewMaterialeControl.class)){
            mockedStatic.when(() -> ViewMaterialeControl.getDipendenteFromManager(user.getId())).thenReturn(dipendente);
            mockedStatic.when(() -> ViewMaterialeControl.getDocumentoFromManager(dipendente.getTeam().getIdTeam())).thenReturn(documento);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("2"));
        }
    }


}
