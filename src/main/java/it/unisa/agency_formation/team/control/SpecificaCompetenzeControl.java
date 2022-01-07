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

        if(req.getParameter("action") == null){ //Visualizzazione
            resp.getWriter().write("1");
            int idTeam = Integer.parseInt(req.getParameter("idTeam"));
            req.setAttribute("idTeam",idTeam);
            /*dispatcher = req.getServletContext().getRequestDispatcher("/static/Competenze.jsp");
            dispatcher.forward(req, resp);*/
            resp.sendRedirect("/static/Competenze.jsp");

            //String comp = TeamDAO.doRetrieveCompetence(idTeam); //<---- fare il manager
            //                req.setAttribute("Competence",comp);
        } else if(req.getParameter("action").equalsIgnoreCase("competenze")){ //update competenze
            String competence = req.getParameter("specCompetenze");

            try {
                int idTeam = Integer.parseInt(req.getParameter("idTeam"));
                TeamDAO.modificaCompetenze(competence,idTeam); //<---- fare il manager
                resp.getWriter().write("2");
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Team.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            resp.getWriter().write("2");
            dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Team.jsp");
            dispatcher.forward(req, resp);
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
