package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.control.AddTeamControl;
import it.unisa.agency_formation.team.control.AggiuntaDipendente;
import it.unisa.agency_formation.team.control.CreateTeamControl;
import it.unisa.agency_formation.team.domain.Team;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.MidiUnavailableException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

public class CreateListaTeamTest {
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
        new CreateTeamControl().doPost(request, response);
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
        Mockito.when(request.getServletContext()).thenReturn(context);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        new CreateTeamControl().doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("5"));
    }

    @Test
    public void createTeamActionNull() throws ServletException, IOException {
        int idUser = 10;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        Team team = new Team();
        user.setId(idUser);
        int idTM = user.getId();
        int numeroDipendenti = 10;
        String nomeProgetto = "Agency Formation";
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "bad action";

        CreateTeamControl servlet = Mockito.spy(CreateTeamControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);

        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("idTM")).thenReturn(String.valueOf(idTM));



        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("4"));

    }
    @Test
    public void createTeamNumEmployeeMoreThan8() throws ServletException, IOException {
        int idUser = 10;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        Team team = new Team();
        user.setId(idUser);
        int idTM = user.getId();
        int numeroDipendenti = 10;
        String nomeProgetto = "Agency Formation";
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "crea";

        CreateTeamControl servlet = Mockito.spy(CreateTeamControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);

        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("idTM")).thenReturn(String.valueOf(idTM));
        Mockito.when(request.getParameter("lname")).thenReturn(nomeProgetto);
        Mockito.when(request.getParameter("quantity")).thenReturn(String.valueOf(numeroDipendenti));

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("1"));

    }
    @Test
    public void createTeamNumEmployeeFail() throws ServletException, IOException {
        int idUser = 10;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTM = user.getId();
        int numeroDipendenti = 7;
        String nomeProgetto = "Agency Formation";
        Team team = new Team(nomeProgetto,numeroDipendenti,"System Errors","Ragazzi molto bravi",null,idTM);

        team.setIdTeam(3);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "crea";

        CreateTeamControl servlet = Mockito.spy(CreateTeamControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);

        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("idTM")).thenReturn(String.valueOf(idTM));
        Mockito.when(request.getParameter("lname")).thenReturn(nomeProgetto);
        Mockito.when(request.getParameter("quantity")).thenReturn(String.valueOf(numeroDipendenti));
        Mockito.when(request.getParameter("fname")).thenReturn(team.getNomeTeam());
        Mockito.when(request.getParameter("teamDescr")).thenReturn(team.getDescrizione());

        try (MockedStatic mockedStatic = mockStatic(CreateTeamControl.class)) {
            mockedStatic.when(() -> CreateTeamControl.creaTeamFromManager(team,idTM)).thenReturn(false);
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
    public void createTeamNumEmployee() throws ServletException, IOException {
        int idUser = 10;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTM = user.getId();
        int numeroDipendenti = 7;
        String nomeProgetto = "Agency Formation";
        Team team = new Team(nomeProgetto,numeroDipendenti,"System Errors","Ragazzi molto bravi",null,idTM);

        team.setIdTeam(3);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "crea";

        CreateTeamControl servlet = Mockito.spy(CreateTeamControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);

        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("idTM")).thenReturn(String.valueOf(idTM));
        Mockito.when(request.getParameter("lname")).thenReturn(nomeProgetto);
        Mockito.when(request.getParameter("quantity")).thenReturn(String.valueOf(numeroDipendenti));
        Mockito.when(request.getParameter("fname")).thenReturn(team.getNomeTeam());
        Mockito.when(request.getParameter("teamDescr")).thenReturn(team.getDescrizione());

       Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
       try (MockedStatic<CreateTeamControl> mockedStatic = mockStatic(CreateTeamControl.class,Mockito.RETURNS_MOCKS)) {
           mockedStatic.when(() -> CreateTeamControl.creaTeamFromManager(team,idTM)).thenReturn(true);
           mockedStatic.when(() -> CreateTeamControl.getIdUltimoTeamCreatoFromManager()).thenReturn(999);
           StringWriter stringWriter = new StringWriter();
           PrintWriter writer = new PrintWriter(stringWriter);
           Mockito.when(response.getWriter()).thenReturn(writer);
           servlet.init(config);
           servlet.doGet(request, response);
           writer.flush();
           assertTrue(stringWriter.toString().contains("3"));
       }

    }
    /*@Test
    public void createTeamNumEmployee2() throws ServletException, IOException {
        int idUser = 10;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idUser);
        int idTM = user.getId();
        int numeroDipendenti = 7;
        String nomeProgetto = "Agency Formation";
        Team team = new Team(nomeProgetto,numeroDipendenti,"System Errors","Ragazzi molto bravi",null,idUser);

        team.setIdTeam(3);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "crea";

        CreateTeamControl servlet = Mockito.spy(CreateTeamControl.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);

        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("idTM")).thenReturn(String.valueOf(idTM));
        Mockito.when(request.getParameter("lname")).thenReturn(nomeProgetto);
        Mockito.when(request.getParameter("quantity")).thenReturn(String.valueOf(numeroDipendenti));
        Mockito.when(request.getParameter("fname")).thenReturn(team.getNomeTeam());
        Mockito.when(request.getParameter("teamDescr")).thenReturn(team.getDescrizione());

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            writer.flush();
            assertTrue(stringWriter.toString().contains("2"));

    }*/
}
