package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.AcceptCandidatureControl;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

public class AcceptCandidatureControlTestIT {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(150,'TestNome','TestCognome','lol','test1@gmail.com', 1)";
        String query2 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato) values(200,'test','test','NonRevisionato','2022-01-10', 150);";
        String query3 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(151,'TestNome','TestCognome','lol','test2@gmail.com', 1)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query1);
        preparedStatement.executeUpdate(query1);
        preparedStatement.executeUpdate(query2);
        preparedStatement.executeUpdate(query3);

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


    @Test //mi aspetto 2
    public void acceptCandidaturaPass() throws ServletException, IOException, ParseException, SQLException {
        Utente user = new Utente("Domenico", "Pagliuca", "d.pagliuca@studenti.unisa.it", "lol", RuoliUtenti.HR);
        user.setId(4);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        AcceptCandidatureControl servlet = Mockito.spy(AcceptCandidatureControl.class);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("150");
        Mockito.when(request.getParameter("data1")).thenReturn("2022-04-03");
        Mockito.when(request.getParameter("time")).thenReturn("17:30");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().contains("1"));
    }
    @Test //mi aspetto 2
    public void acceptCandidaturaFail() throws ServletException, IOException, ParseException, SQLException {
        Utente user = new Utente("Domenico", "Pagliuca", "d.pagliuca@studenti.unisa.it", "lol", RuoliUtenti.HR);
        user.setId(4);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        AcceptCandidatureControl servlet = Mockito.spy(AcceptCandidatureControl.class);
        Mockito.when(request.getParameter("idCandidato")).thenReturn("151");
        Mockito.when(request.getParameter("data1")).thenReturn("2022-04-03");
        Mockito.when(request.getParameter("time")).thenReturn("17:30");
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