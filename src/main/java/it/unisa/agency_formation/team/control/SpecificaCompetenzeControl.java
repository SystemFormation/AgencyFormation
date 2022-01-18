package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.manager.TeamManager;
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        Utente user = (Utente) req.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.TM) {
            String action = req.getParameter("action");
            if (action.equalsIgnoreCase("competenze")) { //update competenze
                int idTeam = Integer.parseInt(req.getParameter("idTeam"));
                String competence = req.getParameter("specCompetenze");
                try {
                    inserimentoCompetenzeNelTeam(competence, idTeam); //<---- fare il manager
                    resp.getWriter().write("1");
                    dispatcher = req.getServletContext().getRequestDispatcher("/ListaTeam");
                    dispatcher.forward(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                resp.getWriter().write("2");
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ListaTeamTM.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            resp.getWriter().write("3");
            resp.sendRedirect("./static/Login.html");
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    public static boolean inserimentoCompetenzeNelTeam(String competenze, int idTeam) throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.modificaLeCompetenze(competenze, idTeam);
    }
}
