package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.control.ScioglimentoTeamControl;
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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class ScioglimentoTeamControlTest {
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
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new ScioglimentoTeamControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("5"));
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
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new ScioglimentoTeamControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("5"));
    }
    @Test
    public void idTeamLess0() throws IOException, ServletException {
        int idUser = 10;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTeam = -1;
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ScioglimentoTeamControl servlet = Mockito.spy(ScioglimentoTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));
        Mockito.when(request.getServletContext()).thenReturn(context);

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("5"));
    }

    @Test
    public void scioglimentoTeamIdDipententiNull() throws IOException, ServletException {
        int idUser = 10;
        ArrayList<Integer> listaIdDips = null;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTeam = 5;
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ScioglimentoTeamControl servlet = Mockito.spy(ScioglimentoTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        try (MockedStatic mockedStatic = mockStatic(ScioglimentoTeamControl.class)) {
            mockedStatic.when(() -> ScioglimentoTeamControl.recuperaIdDipendentiFromManager(idTeam)).thenReturn(null);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("4"));
        }
    }
    @Test
    public void scioglimentoTeamIdDipendentiSize0() throws IOException, ServletException {
        int idUser = 10;
        ArrayList<Integer> listaIdDips = new ArrayList<>();
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTeam = 5;
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ScioglimentoTeamControl servlet = Mockito.spy(ScioglimentoTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        try (MockedStatic mockedStatic = mockStatic(ScioglimentoTeamControl.class)) {
            mockedStatic.when(() -> ScioglimentoTeamControl.recuperaIdDipendentiFromManager(idTeam)).thenReturn(listaIdDips);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("4"));
        }
    }
    @Test
    public void scioglimentoTeamFail1() throws IOException, ServletException {
        int idUser = 10;
        ArrayList<Integer> listaIdDips = new ArrayList<>();
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTeam = 5;
        int IdDip1 = 1;
        int IdDip2 = 2;
        listaIdDips.add(IdDip1);
        listaIdDips.add(IdDip2);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ScioglimentoTeamControl servlet = Mockito.spy(ScioglimentoTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        try (MockedStatic mockedStatic = mockStatic(ScioglimentoTeamControl.class)) {
            mockedStatic.when(() -> ScioglimentoTeamControl.recuperaIdDipendentiFromManager(idTeam)).thenReturn(listaIdDips);
            mockedStatic.when(() -> ScioglimentoTeamControl.updateStatoDipendenteFromManager(anyInt())).thenReturn(true);
            mockedStatic.when(() -> ScioglimentoTeamControl.eliminaTeamFromManager(anyInt())).thenReturn(false);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("2"));
        }
    }
    @Test
    public void scioglimentoTeamFail2() throws IOException, ServletException {
        int idUser = 10;
        ArrayList<Integer> listaIdDips = new ArrayList<>();
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTeam = 5;
        int IdDip1 = 1;
        int IdDip2 = 2;
        listaIdDips.add(IdDip1);
        listaIdDips.add(IdDip2);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ScioglimentoTeamControl servlet = Mockito.spy(ScioglimentoTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));
        try (MockedStatic mockedStatic = mockStatic(ScioglimentoTeamControl.class)) {
            mockedStatic.when(() -> ScioglimentoTeamControl.recuperaIdDipendentiFromManager(idTeam)).thenReturn(listaIdDips);
            mockedStatic.when(() -> ScioglimentoTeamControl.updateStatoDipendenteFromManager(anyInt())).thenReturn(true);
            mockedStatic.when(() -> ScioglimentoTeamControl.eliminaTeamFromManager(anyInt())).thenReturn(false);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("2"));
        }
    }
    @Test
    public void scioglimentoTeamPass() throws IOException, ServletException {
        int idUser = 10;
        ArrayList<Integer> listaIdDips = new ArrayList<>();
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTeam = 5;
        int IdDip1 = 1;
        int IdDip2 = 2;
        listaIdDips.add(IdDip1);
        listaIdDips.add(IdDip2);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ScioglimentoTeamControl servlet = Mockito.spy(ScioglimentoTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        try (MockedStatic<ScioglimentoTeamControl> mockedStatic = mockStatic(ScioglimentoTeamControl.class)) {
            mockedStatic.when(() -> ScioglimentoTeamControl.recuperaIdDipendentiFromManager(idTeam)).thenReturn(listaIdDips);
            mockedStatic.when(() -> ScioglimentoTeamControl.eliminaTeamFromManager(idTeam)).thenReturn(true);
            mockedStatic.when(() -> ScioglimentoTeamControl.updateStatoDipendenteFromManager(anyInt())).thenReturn(true);
            StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("3"));
        }
    }

}
