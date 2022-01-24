package agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.control.ProfiloControl;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

public class ProfiloControlIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
       /* String query1 = "Insert into team (IdTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,idTM) values(4,'TestTeam',5,'Test','test descr','Java EE',3)";

       // String query2 = "insert into dipendenti (IdDipendente, Residenza, Telefono, Stato, AnnoDiNascita,IdTeam) " +
               // "values (3,'TestResidenza','118',false,2000,1)";

        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(query1);
        //PreparedStatement statement2 = connection.prepareStatement(query2);
        statement1.executeUpdate();
        //statement2.executeUpdate();*/
    }
    @AfterAll
    public static void finish() throws SQLException {
        /*String delete1 = "Delete from dipendenti where IdDipendente>2";
        String delete2 = "Delete from team where IdTeam>1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(delete1);
        PreparedStatement statement2 = connection.prepareStatement(delete2);
        statement1.executeUpdate();
        statement2.executeUpdate();*/
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    public void userNullIt() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        ProfiloControl servlet = Mockito.spy(ProfiloControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("2"));
    }
    @Test
    public void userRuoloNonDipendenteIt() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        ProfiloControl servlet = Mockito.spy(ProfiloControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().contains("2"));
    }

    @Test
    public void userPassIt() throws IOException, ServletException{
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setId(2);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        ArrayList<Skill> skills = new ArrayList<>();
        Skill skill = new Skill();
        skill.setDescrizioneSkill("TestDescr");
        skill.setNomeSkill("TestNome");
        skill.setIdSkill(1);
        skills.add(skill);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ProfiloControl servlet = Mockito.spy(ProfiloControl.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().contains("1"));
    }
}
