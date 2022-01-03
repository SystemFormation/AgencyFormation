package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/TeamControl")
public class TeamControl extends HttpServlet {
    TeamManagerImpl aut = new TeamManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        Utente d = (Utente) req.getSession().getAttribute("user");
        if(d.getRole() == 3){ //sei tm
            try {
                ArrayList<Team> list = aut.viewTeams(d.getId());
                req.setAttribute("listTeam", list);
                resp.getWriter().write("1");
                dispatcher = req.getServletContext().getRequestDispatcher("/static/CreateTeam.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            resp.getWriter().write("2");
            dispatcher = req.getServletContext().getRequestDispatcher("/static/CreateTeam.jsp");
            dispatcher.forward(req, resp);
        }
    }
    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
