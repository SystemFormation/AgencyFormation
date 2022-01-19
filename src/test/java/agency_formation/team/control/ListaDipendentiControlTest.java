package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.control.ProfiloControl;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.team.control.AddTeamControl;
import it.unisa.agency_formation.team.control.ListaDipendentiControl;
import it.unisa.agency_formation.team.control.ScioglimentoTeamControl;
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

public class ListaDipendentiControlTest {
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
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        ListaDipendentiControl servlet = Mockito.spy(ListaDipendentiControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
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
        new ListaDipendentiControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("3"));
    }
    @Test
    public void ListaDipendentiNull() throws IOException, ServletException {
        int idUser = 10;
        ArrayList<Dipendente> listaDipendenti = null;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTeam = 5;
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ListaDipendentiControl servlet = Mockito.spy(ListaDipendentiControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));
        try (MockedStatic mockedStatic = mockStatic(ListaDipendentiControl.class)) {
            mockedStatic.when(() -> ListaDipendentiControl.getTuttiDipendentiFromManager()).thenReturn(listaDipendenti);
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
    public void listaDipendentiPass() throws IOException, ServletException {
        ArrayList<Dipendente> listaDipendenti = new ArrayList<Dipendente>();
        Utente user1 = new Utente();
        user1.setId(2);
        user1.setPwd("lol");
        user1.setRole(RuoliUtenti.TM);
        user1.setSurname("TestCognome");
        user1.setName("TestNome");
        user1.setEmail("test@gmail.com");
        user1.setName("TestName");
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        Dipendente dipendente = new Dipendente();
        dipendente.setStato(StatiDipendenti.DISPONIBILE);
        dipendente.setIdDipendente(1);
        dipendente.setTelefono("1589634786");
        dipendente.setResidenza("Londra");
        dipendente.setAnnoNascita(2000);
        Team team = new Team();
        team.setIdTeam(1);
        team.setNomeTeam("Testnome");
        team.setNumeroDipendenti(5);
        team.setDescrizione("TestDescr");
        team.setIdTM(3);
        team.setNomeProgetto("TestProget");
        ArrayList<Skill> skills = new ArrayList<Skill>();
        Skill skill = new Skill();
        skill.setIdSkill(1);
        skill.setDescrizioneSkill("testDescr");
        skill.setNomeSkill("Testnome");
        skills.add(skill);
        dipendente.setSkills(skills);
        listaDipendenti.add(dipendente);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ListaDipendentiControl servlet = Mockito.spy(ListaDipendentiControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user1);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic<ListaDipendentiControl> mockedStatic = mockStatic(ListaDipendentiControl.class)) {
            mockedStatic.when(() -> ListaDipendentiControl.getTuttiDipendentiFromManager()).thenReturn(listaDipendenti);
            mockedStatic.when(() -> ListaDipendentiControl.getSkillDipendenteFromManager(dipendente.getIdDipendente())).thenReturn(skills);
            mockedStatic.when(() -> ListaDipendentiControl.getTeamIdFromManager(team.getIdTeam())).thenReturn(team);
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
