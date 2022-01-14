package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.domain.Team;
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

@WebServlet("/CreateTeamControl")
public class CreateTeamControl extends HttpServlet {
    //da raffinare
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.setProperty("file.encoding" , "UTF-8");
        Utente user = (Utente) req.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.TM) {
            Team team = new Team();
            RequestDispatcher dispatcher;
            String action = req.getParameter("action");
        //    Utente userTM = (Utente) req.getSession().getAttribute("user");
            int idTM = user.getId();
            try {
                if (action.equalsIgnoreCase("crea")) {
                    String nomeProgetto = req.getParameter("lname");
                    int numeroDipendenti = Integer.parseInt(req.getParameter("quantity"));
                    if (numeroDipendenti > 8) {
                        resp.getWriter().write("1");
                        resp.sendRedirect("/static/CreaTeam.jsp");
                    }else{
                        String nomeTeam = req.getParameter("fname");
                        String descrizione = req.getParameter("teamDescr");
                        team.setNomeProgetto(nomeProgetto);
                        team.setDescrizione(descrizione);
                        team.setNomeTeam(nomeTeam);
                        team.setNumeroDipendenti(numeroDipendenti);
                        creaTeamFromManager(team, idTM);
                        int idTeam = getIdUltimoTeamCreatoFromManager();
                        req.setAttribute("idTeam", idTeam);
                        resp.getWriter().write("2");
                        dispatcher = req.getServletContext().getRequestDispatcher("/ListaTeam");
                        dispatcher.forward(req, resp);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            resp.getWriter().write("4");
            resp.sendRedirect("./static/Login.html");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
//TODO GESTIRE IL RETURN
    public static boolean creaTeamFromManager(Team team, int idTM) throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.creaTeam(team, idTM);
    }

    public static int getIdUltimoTeamCreatoFromManager() throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.viewLastIdTeam();
    }
}
