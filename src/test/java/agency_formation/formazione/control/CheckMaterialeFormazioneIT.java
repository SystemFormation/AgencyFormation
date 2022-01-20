package agency_formation.formazione.control;

import it.unisa.agency_formation.formazione.control.CheckMaterialeFormazione;
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

public class CheckMaterialeFormazioneIT {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(200, 'ReqMem',8,'Inspiegabili','Non siamo eroi','HTML',3)";
        String insertTeam2 = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(201, 'ReqMemDue',8,'InspiegabiliDue','Non siamo eroiDue','HTML',3)";
        String insertDocumento="insert into documenti (IdDocumento,MaterialeDiFormazione,IdHR,IdTeam) values (1,'test',4,200)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(insertTeam);
        statement.executeUpdate(insertTeam);
        statement.executeUpdate(insertTeam2);
        statement.executeUpdate(insertDocumento);
    }

    @AfterAll
    public static void finish() throws SQLException {
        String deleteDocumento = "delete from documenti where IdDocumento>=1";
        String deleteTeam = "delete from team where idTeam>1";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteDocumento);
        statement.executeUpdate(deleteDocumento);
        statement.executeUpdate(deleteTeam);
    }

    @Test
    public void checkPass() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        CheckMaterialeFormazione servlet = Mockito.spy(CheckMaterialeFormazione.class);
        Mockito.when(request.getParameter("idTeam")).thenReturn("200");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("2"));
    }
    @Test // non esiste il documento per il team specificato
    public void checkNotPass() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        CheckMaterialeFormazione servlet = Mockito.spy(CheckMaterialeFormazione.class);
        Mockito.when(request.getParameter("idTeam")).thenReturn("201");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("3"));
    }



}
