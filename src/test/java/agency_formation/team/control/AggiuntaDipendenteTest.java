package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.control.LoginControl;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.team.control.AddTeamControl;
import it.unisa.agency_formation.team.control.AggiuntaDipendente;
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

public class AggiuntaDipendenteTest {
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
        new AggiuntaDipendente().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));
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
        new AggiuntaDipendente().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));
    }
    @Test
    public void addEmployeesListNull() throws IOException, ServletException {
        ArrayList<Dipendente> dipendenti = null;
        int idUser = 10;
        int idTeam = 5;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);

        AggiuntaDipendente servlet = Mockito.spy(AggiuntaDipendente.class);


        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);

        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(AggiuntaDipendente.class)) {
            mockedStatic.when(() -> AggiuntaDipendente.getTuttiDipendentiFromManager()).thenReturn(dipendenti);

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
    public void addEmployeesListSize0() throws IOException, ServletException {
        ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
        int idUser = 10;
        int idTeam = 5;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);

        AggiuntaDipendente servlet = Mockito.spy(AggiuntaDipendente.class);


        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);

        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(AggiuntaDipendente.class)) {
            mockedStatic.when(() -> AggiuntaDipendente.getTuttiDipendentiFromManager()).thenReturn(dipendenti);

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
        public void addEmployeesPass() throws IOException, ServletException {
            ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
            int idUser = 10;
            int idTeam = 5;
            Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
            Dipendente dip = new Dipendente(user.getName(), user.getSurname(),user.getEmail(),user.getPwd(),user.getRole(),idUser,1999,"Scafati","113", StatiDipendenti.DISPONIBILE);
            user.setId(idUser);
            dipendenti.add(dip);
            config = Mockito.mock(ServletConfig.class);
            request = Mockito.mock(HttpServletRequest.class);
            response = Mockito.mock(HttpServletResponse.class);
            session = Mockito.mock(HttpSession.class);
            dispatcher = Mockito.mock(RequestDispatcher.class);
            context = Mockito.mock(ServletContext.class);

            AggiuntaDipendente servlet = Mockito.spy(AggiuntaDipendente.class);


            Mockito.when(request.getSession()).thenReturn(session);
            Mockito.when(session.getAttribute("user")).thenReturn(user);
            Mockito.when(request.getSession(true)).thenReturn(session);
            Mockito.when(request.getServletContext()).thenReturn(context);

            Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

            Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
            try (MockedStatic mockedStatic = mockStatic(AggiuntaDipendente.class)) {
                mockedStatic.when(() -> AggiuntaDipendente.getTuttiDipendentiFromManager()).thenReturn(dipendenti);

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
    public void addEmployeesPassSkillNull() throws IOException, ServletException {
        ArrayList<Skill> skills= null;
        ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
        int idUser = 10;
        int idTeam = 5;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        Dipendente dip = new Dipendente(user.getName(), user.getSurname(),user.getEmail(),user.getPwd(),user.getRole(),idUser,1999,"Scafati","113", StatiDipendenti.DISPONIBILE);
        user.setId(idUser);
        dipendenti.add(dip);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);

        AggiuntaDipendente servlet = Mockito.spy(AggiuntaDipendente.class);


        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);

        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(AggiuntaDipendente.class)) {
            mockedStatic.when(() -> AggiuntaDipendente.getTuttiDipendentiFromManager()).thenReturn(dipendenti);
            mockedStatic.when(() -> AggiuntaDipendente.getSkillDipendenteFromManager(idUser)).thenReturn(skills);
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
    public void addEmployeesPassSkillPass() throws IOException, ServletException {
        ArrayList<Skill> skills= new ArrayList<Skill>();
        Skill skill = new Skill("HTML","Conoscenza base");
        ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
        int idUser = 10;
        int idTeam = 5;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        Dipendente dip = new Dipendente(user.getName(), user.getSurname(),user.getEmail(),user.getPwd(),user.getRole(),idUser,1999,"Scafati","113", StatiDipendenti.DISPONIBILE);
        user.setId(idUser);
        dipendenti.add(dip);
        skills.add(skill);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);

        AggiuntaDipendente servlet = Mockito.spy(AggiuntaDipendente.class);


        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(request.getServletContext()).thenReturn(context);

        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try (MockedStatic mockedStatic = mockStatic(AggiuntaDipendente.class)) {
            mockedStatic.when(() -> AggiuntaDipendente.getTuttiDipendentiFromManager()).thenReturn(dipendenti);
            mockedStatic.when(() -> AggiuntaDipendente.getSkillDipendenteFromManager(idUser)).thenReturn(skills);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("1"));
        }
    }
}
