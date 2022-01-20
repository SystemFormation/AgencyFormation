package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.control.ListaDipendentiControl;
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

public class ListaDipendentiIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String query1 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) " +
                "values(500,'TestNome','TestCognome','lol','test@gmail.com', 2)";
        String query2 = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) " +
                "values(501,'TestNome1','TestCognome1','lol','test1@gmail.com', 2)";
        String query3 = "insert into Dipendenti(`IdDipendente`,`Residenza`, `Telefono`, `Stato`, `AnnoDiNascita`) " +
                "values(500,'Roma','3423738987', 0 ,2000)";
        String query4 = "insert into Dipendenti(`IdDipendente`,`Residenza`, `Telefono`, `Stato`, `AnnoDiNascita`) " +
                "values(501,'Roma','3423409026', 0 ,1999)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.executeUpdate(query1);
        statement.executeUpdate(query2);
        statement.executeUpdate(query3);
        statement.executeUpdate(query4);
    }

    @AfterAll
    public static void finish() throws SQLException {
        String delete1 = "DELETE  FROM dipendenti WHERE IdDipendente >2";
        String delete2 = "Delete from utenti where IdUtente>4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(delete1);
        statement.executeUpdate(delete1);
        statement.executeUpdate(delete2);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    public void ListaDipendentiITPass() throws ServletException, IOException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setId(3);
        user.setRole(RuoliUtenti.TM);
        user.setPwd("lol");
        user.setEmail("m.nocerino@studenti.unisa.it");
        user.setName("Manuel");
        user.setSurname("Nocerino");
        ListaDipendentiControl servlet = Mockito.spy(ListaDipendentiControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));
    }
}
