package agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.RejectCandidatureControl;
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
import java.io.File;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RejectCandidatureControlTestIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values (7,'TestNome','TestCognome','lol','test1@gmail.com', 1)";
        String query2 = "Insert into candidature (IdCandidatura,Curriculum,DocumentiAggiuntivi,Stato,DataCandidatura,IdCandidato,IdHR) values(10,'test','test','NonRevisionato','2022-01-10', 7,3)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(query1);
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement1.executeUpdate();
        statement2.executeUpdate();

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
    public void rejectCandidaturePass() throws IOException, ServletException, ParseException {
        int idUser = 4;
        Utente user = new Utente("Domenico", "Pagliuca", "mario.rossi@gmail.com", "123", RuoliUtenti.HR);
        user.setId(idUser);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        RejectCandidatureControl rejectCandidatureControl = new RejectCandidatureControl();
        RejectCandidatureControl servlet = Mockito.spy(rejectCandidatureControl);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getSession(true)).thenReturn(session);
        when(request.getParameter("idCandidato")).thenReturn(String.valueOf(7));
        when(request.getServletContext()).thenReturn(context);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("1"));

    }
}