package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/SpecificaCompetenzeControl")
public class SpecificaCompetenzeControl extends HttpServlet {
    TeamManagerImpl aut = new TeamManagerImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
            //String comp = TeamDAO.doRetrieveCompetence(idTeam); //<---- fare il manager
            //                req.setAttribute("Competence",comp);
        if(req.getParameter("action").equalsIgnoreCase("competenze")){ //update competenze
            int idTeam = Integer.parseInt(req.getParameter("idTeam"));
            String competence = req.getParameter("specCompetenze");
            try {
                TeamDAO.modificaCompetenze(competence,idTeam); //<---- fare il manager
                resp.getWriter().write("2");
                dispatcher = req.getServletContext().getRequestDispatcher("/TeamControl");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            resp.getWriter().write("2");
            dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ListaTeamTM.jsp");
            dispatcher.forward(req, resp);
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
