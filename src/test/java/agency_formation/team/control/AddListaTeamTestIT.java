package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.control.AddTeamControl;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

public class AddListaTeamTestIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;


    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(5, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        String insertUtente = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(20,'test','test','test','test@gmail.com', 2)";
        String insertDipendente = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(20, 'Fisciano','77777',1,2000,5)";

        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(insertTeam);
        PreparedStatement statement2 = connection.prepareStatement(insertUtente);
        PreparedStatement statement3 = connection.prepareStatement(insertDipendente);
        statement.executeUpdate();
        statement2.executeUpdate();
        statement3.executeUpdate();
    }

    @AfterAll
    public static void finish() throws SQLException {
        String deleteTeam = "delete from team where idTeam > 4";
        String deleteUtente = "delete from Utenti where IdUtente > 19";
        String deleteDipendente = "delete from dipendenti where idDipendente > 19";
        Connection connection = DatabaseManager.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement(deleteTeam);
        PreparedStatement statement2 = connection.prepareStatement(deleteUtente);
        PreparedStatement statement3 = connection.prepareStatement(deleteDipendente);
        statement.executeUpdate();
        statement2.executeUpdate();
        statement3.executeUpdate();
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }
    @Test
    public void addTeamPass() throws IOException, ServletException, SQLException {
        int idDip = 10;
        int idTeam = 5;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(idDip);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "aggiungi";

        AddTeamControl servlet = Mockito.spy(AddTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);

        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(20));
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));

        }
    @Test
    public void addTeamFail() throws IOException, ServletException {
        int idDip = 10;
        int idTeam = 2;
        Utente user = new Utente("Mario", "Rossi", "mario.rossi@gmail.com", "123", RuoliUtenti.TM);
        user.setId(10);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        String action = "aggiungi";

        AddTeamControl servlet = Mockito.spy(AddTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);

        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("action")).thenReturn(action);
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(1));
        Mockito.when(request.getParameter("idTeam")).thenReturn(String.valueOf(idTeam));

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("1"));
        }
}
