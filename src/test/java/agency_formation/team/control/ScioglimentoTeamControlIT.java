package agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.control.ScioglimentoTeamControl;
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
import static org.mockito.Mockito.spy;

public class ScioglimentoTeamControlIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(200, 'ReqMem',8,'Inspiegabili','Non siamo eroi',null,3)";
        String insertUtente = "Insert into Utenti (IdUtente,Nome,Cognome,Pwd,Mail,Ruolo) values(201,'test','test','test','test', 2)";
        String insertDipendente = "insert into dipendenti (IdDipendente,Residenza, Telefono, Stato, AnnoDiNascita, IdTeam) values(201, 'Fisciano','77777',1,2000,200)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement1 = connection.prepareStatement(insertTeam);
        PreparedStatement statement2 = connection.prepareStatement(insertUtente);
        PreparedStatement statement3 = connection.prepareStatement(insertDipendente);
        statement1.executeUpdate(insertTeam);
        statement2.executeUpdate(insertUtente);
        statement3.executeUpdate(insertDipendente);
    }

    @AfterAll
    public static void finish() throws SQLException {
        String deleteTeam = "delete from team where idTeam>1";
        String deleteDipendente = "delete from dipendenti where idDipendente > 2";
        String deleteUtente = "delete from utenti where idUtente > 4";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteTeam);
        preparedStatement.executeUpdate();
        preparedStatement.executeUpdate(deleteDipendente);
        preparedStatement.executeUpdate(deleteUtente);
        Const.nomeDB = Const.NOME_DB_MANAGER;
    }

    @Test
    public void scioglimentoOK() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(3);
        user.setPwd("lol");
        user.setEmail("m.nocerino@studenti.unisa.it");
        user.setRole(RuoliUtenti.TM);
        user.setName("Manuel");
        user.setSurname("Nocerino");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ScioglimentoTeamControl servlet = spy(ScioglimentoTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("idTeam")).thenReturn("200");
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("3"));
    }
    @Test
    public void scioglimentoFail() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(3);
        user.setPwd("lol");
        user.setEmail("m.nocerino@studenti.unisa.it");
        user.setRole(RuoliUtenti.TM);
        user.setName("Manuel");
        user.setSurname("Nocerino");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        ScioglimentoTeamControl servlet = spy(ScioglimentoTeamControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("idTeam")).thenReturn("-50");
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("5"));
    }





}
