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
import java.util.ArrayList;

@WebServlet("/ScioglimentoTeamControl")
public class ScioglimentoTeamControl extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente user = (Utente) req.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.TM) {
            int idTeam = Integer.parseInt(req.getParameter("idTeam"));
            RequestDispatcher dispatcher;
            if (idTeam > 0) {
                try {
                    ArrayList<Integer> listaIdDip = recuperaIdDipendentiFromManager(idTeam);
                    if (listaIdDip != null && listaIdDip.size() > 0) {
                        for (int idDip : listaIdDip) {
                            if (!updateStatoDipendenteFromManager(idDip)) {
                                resp.getWriter().write("1"); // errore aggiornamento stato dip
                                String descrizione = "aggiornamento stato non andato a buon fine";
                                resp.sendRedirect("./static/Error.jsp?descrizione=" + descrizione);
                                return;
                            }
                        }
                    } else {
//TODO non so se sia errore: se non ci sono dipendenti il team e vuoto e posso scioglierlo comunque
                        resp.getWriter().write("4");
                        resp.sendRedirect("./static/Error.jsp");
                    }
                    if (!eliminaTeamFromManager(idTeam)) {
                        resp.getWriter().write("2");
                        String descrizione = "team da sciogliere non rilevato";
                        resp.sendRedirect("./static/Error.jsp?descrizione=" + descrizione);
                        return;
                    } else {
                        resp.getWriter().write("3");
                        dispatcher = req.getServletContext().getRequestDispatcher("/ListaTeam");
                        dispatcher.forward(req, resp);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                resp.getWriter().write("4");
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ListaTeamTM.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            resp.getWriter().write("5");
            resp.sendRedirect("./static/Login.html");
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static ArrayList<Integer> recuperaIdDipendentiFromManager(int idTeam) throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.recuperaIdDipendentiDelTeam(idTeam);
    }

    public static boolean updateStatoDipendenteFromManager(int idDipendente) throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.updateDipsDisso(idDipendente);
    }

    public static boolean eliminaTeamFromManager(int idTeam) throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.sciogliTeam(idTeam);
    }
}
