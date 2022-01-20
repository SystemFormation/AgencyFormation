package agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.formazione.control.SkillControl;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

public class SkillControlTestIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;


    @BeforeAll
    public static void init(){
        Const.nomeDB = Const.NOME_DB_TEST;
    }

    @AfterAll
    public static void finish() throws SQLException {
        String deleteSkill = "Delete from skill where IdSkill>2";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSkill);
        preparedStatement.executeUpdate();
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }
    @Test
    public void addSkillOK() throws IOException, ServletException {
        Utente user = new Utente();
        user.setSurname("Severino");
        user.setName("Pasquale");
        user.setPwd("lol");
        user.setEmail("p.severino@studenti.unisa.it");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setId(2);
        String skillName = "TestNomeSkill";
        String skillDescr = "TestDescrizioneSkill";
        String livelloSkill = "2";
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getParameter("skillName")).thenReturn(skillName);
        Mockito.when(request.getParameter("skillDescr")).thenReturn(skillDescr);
        Mockito.when(request.getParameter("quantity")).thenReturn(livelloSkill);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().equals("4"));
    }
    @Test // livello più alto di 5
    public void addSkillLivelloAlto() throws IOException, ServletException {
        Utente user = new Utente();
        user.setSurname("Severino");
        user.setName("Pasquale");
        user.setPwd("lol");
        user.setEmail("p.severino@studenti.unisa.it");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setId(2);
        String skillName = "TestNomeSkill";
        String skillDescr = "TestDescrizioneSkill";
        String livelloSkill = "6";
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getParameter("skillName")).thenReturn(skillName);
        Mockito.when(request.getParameter("skillDescr")).thenReturn(skillDescr);
        Mockito.when(request.getParameter("quantity")).thenReturn(livelloSkill);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().equals("3"));
    }
}
