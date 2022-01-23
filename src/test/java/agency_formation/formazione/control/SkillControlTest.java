package agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.control.SkillControl;
import it.unisa.agency_formation.formazione.domain.Skill;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;

public class SkillControlTest {
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static HttpSession session;
    private static RequestDispatcher dispatcher;
    private static ServletContext context;
    private static ServletConfig config;


    @Test
    public void skillControlUserNull() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(null);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().equals("7"));
    }


    @Test
    public void skillControlNameNull() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setEmail("mario@gmail.com");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol");
        user.setId(1);
        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getParameter("skillName")).thenReturn(null);
        Mockito.when(request.getParameter("skillDescription")).thenReturn("Test");
        Mockito.when(request.getParameter("quantity")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().equals("6"));
    }

    @Test
    public void skillControlNameEmpty() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setEmail("mario@gmail.com");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol");
        user.setId(1);
        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getParameter("skillName")).thenReturn("");
        Mockito.when(request.getParameter("skillDescription")).thenReturn("Test");
        Mockito.when(request.getParameter("quantity")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().equals("15"));
    }
    @Test
    public void skillControlDescrEmpty() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setEmail("mario@gmail.com");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol");
        user.setId(1);
        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getParameter("skillName")).thenReturn("Test");
        Mockito.when(request.getParameter("skillDescription")).thenReturn("");
        Mockito.when(request.getParameter("quantity")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().equals("25"));
    }

    @Test
    public void skillControlDipNull() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setEmail("mario@gmail.com");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol");
        user.setId(1);

        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getParameter("skillName")).thenReturn("Test");
        Mockito.when(request.getParameter("skillDescription")).thenReturn("Test");
        Mockito.when(request.getParameter("quantity")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try(MockedStatic mockedStatic = mockStatic(SkillControl.class)){
            mockedStatic.when(() -> SkillControl.getDipendenteByIdFromManager(user.getId())).thenReturn(null);
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.init(config);
        servlet.doPost(request, response);
        assertTrue(stringWriter.toString().equals("5"));
    }

    @Test
    public void skillControlSkillAddFail() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setEmail("mario@gmail.com");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol");
        user.setId(1);
        Skill skill = new Skill();
        skill.setIdSkill(1);
        skill.setNomeSkill("Test");
        skill.setDescrizioneSkill("Test");
        Dipendente dipendente = new Dipendente();
        dipendente.setIdDipendente(1);
        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getParameter("skillName")).thenReturn("Test");
        Mockito.when(request.getParameter("skillDescription")).thenReturn("Test");
        Mockito.when(request.getParameter("quantity")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try(MockedStatic mockedStatic = mockStatic(SkillControl.class)){
            mockedStatic.when(() -> SkillControl.getDipendenteByIdFromManager(user.getId())).thenReturn(dipendente);
            mockedStatic.when(() -> SkillControl.addSkillFromManager(any(Skill.class))).thenReturn(false);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().equals("2"));
        }
    }

    @Test
    public void skillControlSkillDipAddFail() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setEmail("mario@gmail.com");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol");
        user.setId(1);
        Skill skill = new Skill();
        skill.setIdSkill(1);
        skill.setNomeSkill("Test");
        skill.setDescrizioneSkill("Test");
        Dipendente dipendente = new Dipendente();
        dipendente.setIdDipendente(1);
        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getParameter("skillName")).thenReturn("Test");
        Mockito.when(request.getParameter("skillDescription")).thenReturn("Test");
        Mockito.when(request.getParameter("quantity")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try(MockedStatic mockedStatic = mockStatic(SkillControl.class)){
            mockedStatic.when(() -> SkillControl.getDipendenteByIdFromManager(user.getId())).thenReturn(dipendente);
            mockedStatic.when(() -> SkillControl.addSkillFromManager(any(Skill.class))).thenReturn(true);
            mockedStatic.when(() -> SkillControl.addSkillDipFromManager(eq(skill.getIdSkill()),eq(dipendente.getIdDipendente()),eq(1))).thenReturn(false);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().equals("3"));
        }
    }

    @Test
    public void skillControlSkillDipAddPass() throws IOException, ServletException {
        config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        Utente user = new Utente();
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setEmail("mario@gmail.com");
        user.setRole(RuoliUtenti.DIPENDENTE);
        user.setPwd("lol");
        user.setId(1);
        Skill skill = new Skill();
        skill.setIdSkill(1);
        skill.setNomeSkill("Test");
        skill.setDescrizioneSkill("Test");
        Dipendente dipendente = new Dipendente();
        dipendente.setIdDipendente(1);
        SkillControl servlet = Mockito.spy(SkillControl.class);
        Mockito.when(request.getParameter("skillName")).thenReturn("Test");
        Mockito.when(request.getParameter("skillDescription")).thenReturn("Test");
        Mockito.when(request.getParameter("quantity")).thenReturn("1");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        try(MockedStatic<SkillControl> mockedStatic = mockStatic(SkillControl.class)){
            mockedStatic.when(() -> SkillControl.getDipendenteByIdFromManager(user.getId())).thenReturn(dipendente);
            mockedStatic.when(() -> SkillControl.addSkillFromManager(any(Skill.class))).thenReturn(true);
            mockedStatic.when(() -> SkillControl.getLastIdSkillCreatedFromManager()).thenReturn(skill.getIdSkill());
            mockedStatic.when(() -> SkillControl.addSkillDipFromManager(eq(skill.getIdSkill()),eq(dipendente.getIdDipendente()),eq(1))).thenReturn(true);
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            Mockito.when(response.getWriter()).thenReturn(writer);
            servlet.init(config);
            servlet.doPost(request, response);
            assertTrue(stringWriter.toString().equals("4"));
        }
    }
}
