package it.unisa.agency_formation.formazione.control;


import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Skill;
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
   private FormazioneManagerImpl aut = new FormazioneManagerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente user = (Utente) request.getSession().getAttribute("user");

        Skill skill = new Skill();

        String skillName = request.getParameter("skillName");
        String skillDescr = request.getParameter("skillDescr");

        skill.setNomeSkill(skillName);
        skill.setDescrizioneSkill(skillDescr);
        try{
            //Da raffinare
            Dipendente dip = DipendenteDAO.doRetrieveById(user.getId());
            aut.addSkill(skill,dip);
            int idSkill = aut.getLastIdSkillCreated();
            aut.addSkillDip(idSkill,dip);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
