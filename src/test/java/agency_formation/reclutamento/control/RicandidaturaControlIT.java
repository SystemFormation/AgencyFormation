package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.RicandidaturaControl;
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

public class RicandidaturaControlIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "Insert into candidature (IdCandidatura,Curriculum,Stato,DataCandidatura,DataOraColloquio,IdCandidato,IdHR) " +
                "values(200,'test','Accettata','2022-01-10','2022-01-20 17:30:00', 500,4);";
        String query2 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) " +
                "values(500,'TestNome','TestCognome','lol','test2@gmail.com', 1)";
        String query3 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) " +
                "values(501,'TestNome','TestCognome','lol','test3@gmail.com', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query2);
        statement.executeUpdate(query2);
        statement.executeUpdate(query3);
        statement.executeUpdate(query1);
    }
    @AfterAll
    public static void finish() throws SQLException {
        String delete1 = "Delete from candidature where IdCandidatura>=1";
        String delete2 = "Delete from utenti where IdUtente>4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete1);
        statement.executeUpdate(delete1);
        statement.executeUpdate(delete2);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    public void ricandidaturaPass() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        RicandidaturaControl servlet = Mockito.spy(RicandidaturaControl.class);
        Utente user = new Utente();
        user.setId(500);
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setPwd("lol");
        user.setEmail("test2@gmail.com");
        user.setName("TestNome");
        user.setSurname("TestCognome");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().contains("3"));
    }
    @Test
    public void ricandidaturaFail() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        RicandidaturaControl servlet = Mockito.spy(RicandidaturaControl.class);
        Utente user = new Utente();
        user.setId(501);
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setPwd("lol");
        user.setEmail("test2@gmail.com");
        user.setName("TestNome");
        user.setSurname("TestCognome");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().contains("2"));
    }

}
