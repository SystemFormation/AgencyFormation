package agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.control.LoginControl;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.control.DownloadMaterialeControl;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.reclutamento.control.AcceptCandidatureControl;
import it.unisa.agency_formation.team.domain.Team;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;

public class DownloadMaterialeControlTest {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;
//
    @Test //mi aspetto 1
    public void userNotDipendente() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.CANDIDATO);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        DownloadMaterialeControl servlet = Mockito.spy(DownloadMaterialeControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doGet(request, response);
        assertTrue(stringWriter.toString().equals("5"));
    }

    @Test //mi aspetto 2
    public void dipendenteNull() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        DownloadMaterialeControl servlet = Mockito.spy(DownloadMaterialeControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try(MockedStatic<DownloadMaterialeControl> mockedStatic = mockStatic(DownloadMaterialeControl.class)){
            mockedStatic.when(() -> DownloadMaterialeControl.getDipendentefromManager(1)).thenReturn(null);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().equals("14")); //14 perchè 1 è il dip nul e 4 è il documento null
                                                             // iltest esegui l'if di dip e anche else di documento che da 4
        }
    }

    @Test //mi aspetto 3
    public void documentoNull() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        Dipendente dipendente = new Dipendente();
        Team team = new Team();
        team.setIdTeam(1);
        dipendente.setTeam(team);
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        DownloadMaterialeControl servlet = Mockito.spy(DownloadMaterialeControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try(MockedStatic<DownloadMaterialeControl> mockedStatic = mockStatic(DownloadMaterialeControl.class)){
            mockedStatic.when(() -> DownloadMaterialeControl.getDipendentefromManager(1)).thenReturn(dipendente);
            mockedStatic.when(() -> DownloadMaterialeControl.getDocumentofromManager(dipendente.getTeam().getIdTeam())).thenReturn(null);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().equals("24"));
        }
    }
    @Test //mi aspetto 4
    public void downloadDocumentoPass() throws IOException, ServletException {
        Utente user = new Utente();
        user.setId(1);
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol1");
        user.setEmail("l.giacchetti@studenti.unisa.it");
        user.setName("Luigi");
        user.setSurname("Giacchetti");
        Dipendente dipendente = new Dipendente();
        Team team = new Team();
        team.setIdTeam(1);
        dipendente.setTeam(team);
        Documento documento = new Documento();
        documento.setMaterialeDiFormazione("\\AgencyFormationFile\\Test\\test.pdf");

        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        DownloadMaterialeControl servlet = Mockito.spy(DownloadMaterialeControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(response.getOutputStream()).thenReturn(new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {

            }
        });
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try(MockedStatic<DownloadMaterialeControl> mockedStatic = mockStatic(DownloadMaterialeControl.class)){
            mockedStatic.when(() -> DownloadMaterialeControl.getDipendentefromManager(1)).thenReturn(dipendente);
            mockedStatic.when(() -> DownloadMaterialeControl.getDocumentofromManager(dipendente.getTeam().getIdTeam())).thenReturn(documento);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doGet(request, response);
            assertTrue(stringWriter.toString().equals("3"));
        }
    }

}
