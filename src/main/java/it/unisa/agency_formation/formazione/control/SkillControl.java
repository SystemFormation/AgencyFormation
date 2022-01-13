package it.unisa.agency_formation.formazione.control;


import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManager;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/SkillControl")
public class SkillControl extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");

        if (user != null && user.getRole() == RuoliUtenti.DIPENDENTE) {
            Skill skill = new Skill();
            String skillName = request.getParameter("skillName");
            String skillDescr = request.getParameter("skillDescr");
            int skillLivello = Integer.parseInt(request.getParameter("quantity"));

            skill.setNomeSkill(skillName);
            skill.setDescrizioneSkill(skillDescr);
            if (skillName != null && skillDescr != null) {
                if (skillName.trim().equalsIgnoreCase("")) {
                    response.getWriter().write("1"); // Skillnome vuoto
                }
                if (skillDescr.trim().equalsIgnoreCase("")) {
                    response.getWriter().write("2"); //Skilldesc vuoto
                }
                try {
                    Dipendente dip = DipendenteDAO.doRetrieveDipendenteById(user.getId());
                    if (dip != null) {
                        //TODO r:controllare se l'aggiunta è andata a buon fine
                        addSkillFromManager(skill);
                        int idSkill = getLastIdSkillCreatedFromManager();
                        //TODO r:controllare se l'aggiunta è andata a buon fine
                        addSkillDipFromManager(idSkill, dip.getIdDipendente(), skillLivello);
                        response.getWriter().write("3"); // aggiunta avvenuta con successo.
                        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeDipendente.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        response.getWriter().write("4"); // aggiunta fallita.
                        response.sendRedirect("./static/Profilo.jsp");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                response.getWriter().write("5"); //skillNome e skillDescr null
                response.sendRedirect("./static/Profilo.jsp");
            }
        } else {
            response.sendRedirect("./static/Login.html");
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static boolean addSkillFromManager(Skill skill) throws SQLException {
        FormazioneManager formazioneManager = new FormazioneManagerImpl();
        return formazioneManager.aggiungiSkill(skill);
    }
    public static int getLastIdSkillCreatedFromManager() throws SQLException {
        FormazioneManager formazioneManager = new FormazioneManagerImpl();
        return formazioneManager.getUltimaSkill();
    }
    public static boolean addSkillDipFromManager(int idSkill, int idDipendente, int skillLivello)throws SQLException {
        FormazioneManager formazioneManager = new FormazioneManagerImpl();
        return formazioneManager.addSkillDipendente(idSkill, idDipendente, skillLivello);
    }
}
