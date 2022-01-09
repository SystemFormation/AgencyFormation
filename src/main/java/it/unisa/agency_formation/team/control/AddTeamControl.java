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

@WebServlet("/AddTeamControl")
public class AddTeamControl extends HttpServlet {

    private TeamManagerImpl teamManager = new TeamManagerImpl();
    //
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String action = req.getParameter("action");
        int idTeam = Integer.parseInt(req.getParameter("idTeam"));
        try {
            if(action.equalsIgnoreCase("aggiungi")) {
                int idDip = Integer.parseInt(req.getParameter("id"));
                if(idDip != 0){  //messo questo controllo
                    teamManager.updateDipOnTeam(idDip, idTeam);
                    resp.getWriter().write("2");//action null
                    dispatcher = req.getServletContext().getRequestDispatcher("/TeamControl");
                    dispatcher.forward(req, resp);
                }else{
                    resp.sendRedirect("/static/CreaTeam.jsp");

                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}