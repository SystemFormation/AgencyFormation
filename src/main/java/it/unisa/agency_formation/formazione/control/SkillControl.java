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
   private SkillDAO dao = new SkillDAO();
   private DipendenteDAO dao2= new DipendenteDAO();

   private FormazioneManagerImpl aut = new FormazioneManagerImpl(dao);

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
            Dipendente dip = dao2.doRetrieveById(user.getId());
            aut.addSkill(skill,dip);               //da rivedere
            int x = dao.doRetrieveLastId();  //da rivedere
            dao.doSaveSkillDip(x,dip);                  //da rivedere
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
