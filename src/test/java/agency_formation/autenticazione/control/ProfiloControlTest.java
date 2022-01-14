package agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.control.ProfiloControl;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.team.domain.Team;
import org.junit.jupiter.api.Assertions;
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
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

public class ProfiloControlTest {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;


    @Test
    public void userNull() throws IOException, ServletException {
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
    public void userRuoloNonDipendente() throws IOException, ServletException {
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
    public void userPass() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        Dipendente dipendente = new Dipendente();
        Team team = new Team();
        team.setIdTeam(4);
        dipendente.setTeam(team);
        dipendente.setIdDipendente(1);
        dipendente.setStato(StatiDipendenti.OCCUPATO);
        dipendente.setResidenza("TestResidenza");
        dipendente.setTelefono("123");
        dipendente.setAnnoNascita(2000);
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
        MockedStatic<ProfiloControl> mockedStatic = mockStatic(ProfiloControl.class);
            mockedStatic.when(() -> ProfiloControl.getAllDataDipFromManager(1)).thenReturn(dipendente);
            mockedStatic.when(() -> ProfiloControl.getSkillDipendenteFromManager(1)).thenReturn(skills);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("1"));


    }
    /*
    @Test
    public void userPass1() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setId(1);
        user.setPwd("lol");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setSurname("TestCognome");
        user.setName("TestNome");
        user.setEmail("test@gmail.com");
        user.setName("TestName");
        Dipendente dipendente = new Dipendente();
        Team team = new Team();
        team.setIdTeam(4);
        dipendente.setTeam(team);
        dipendente.setIdDipendente(1);
        dipendente.setStato(StatiDipendenti.OCCUPATO);
        dipendente.setResidenza("TestResidenza");
        dipendente.setTelefono("123");
        dipendente.setAnnoNascita(2000);
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
        try(MockedStatic mockedStatic = mockStatic(ProfiloControl.class)) {
            mockedStatic.when(() -> ProfiloControl.getAllDataDipFromManager(1)).thenThrow(SQLException.class);
            mockedStatic.when(() -> ProfiloControl.getSkillDipendenteFromManager(1)).thenReturn(skills);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().contains("3"));
        }

    }*/


}
