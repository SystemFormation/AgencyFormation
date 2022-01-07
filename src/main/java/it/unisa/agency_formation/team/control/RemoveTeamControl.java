package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.team.manager.TeamManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RemoveTeamControl")
public class RemoveTeamControl extends HttpServlet {
    private TeamManagerImpl teamManager = new TeamManagerImpl();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        int idDip = Integer.parseInt(req.getParameter("idDip"));
        if(idDip != 0){
            try {
                teamManager.removeEmployee(idDip);
                resp.getWriter().write("1");
                dispatcher = req.getServletContext().getRequestDispatcher("/TeamControl");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            resp.getWriter().write("2");
            dispatcher = req.getServletContext().getRequestDispatcher("/TeamControl");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
