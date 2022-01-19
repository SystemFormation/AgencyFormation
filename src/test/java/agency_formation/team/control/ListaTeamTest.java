package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.control.ListaTeam;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

public class ListaTeamTest {
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
        new ListaTeam().doPost(request, response);
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
        new ListaTeam().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("3"));
    }
    @Test
    public void listTeamTMPass() throws IOException, ServletException {
        ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
        ArrayList<Team> teams = new ArrayList<Team>();
        int idUser = 10;
        Team team = new Team("TestNomeProgetto", 8, "TestNomeTeam", "Esempio di una descrizione", null, idUser);
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        Dipendente dip = new Dipendente(user.getName(), user.getSurname(), user.getEmail(), user.getPwd(), user.getRole(), idUser, 1999, "Scafati", "113", StatiDipendenti.DISPONIBILE);
        user.setId(idUser);
        dipendenti.add(dip);
        teams.add(team);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);

        ListaTeam servlet = Mockito.spy(ListaTeam.class);


        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(ListaTeam.class)) {
            mockedStatic.when(() -> ListaTeam.recuperoDipendentiDiUnTeamFromManager()).thenReturn(dipendenti);
            mockedStatic.when(() -> ListaTeam.visualizzaTeamOfTMFromManager(idUser)).thenReturn(teams);

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
    public void listTeamHRPass() throws IOException, ServletException {
        ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
        ArrayList<Team> teams = new ArrayList<Team>();
        int idUser = 10;
        Team team = new Team("TestNomeProgetto", 8, "TestNomeTeam", "Esempio di una descrizione", null, idUser);
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.HR);
        Dipendente dip = new Dipendente(user.getName(), user.getSurname(), user.getEmail(), user.getPwd(), user.getRole(), idUser, 1999, "Scafati", "113", StatiDipendenti.DISPONIBILE);
        user.setId(idUser);
        dipendenti.add(dip);
        teams.add(team);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);

        ListaTeam servlet = Mockito.spy(ListaTeam.class);


        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(ListaTeam.class)) {
            mockedStatic.when(() -> ListaTeam.getAllDipendentiFromManager()).thenReturn(dipendenti);
            mockedStatic.when(() -> ListaTeam.visuallizzaTeamsForHRFromManager()).thenReturn(teams);

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
