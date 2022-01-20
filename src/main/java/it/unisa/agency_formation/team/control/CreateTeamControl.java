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

    /**
     * Questo metodo controlla le operazioni per effettuare la creazione del team
     *
     * @param req  , request
     * @param resp , response
     * @throws ServletException errore Servlet
     * @throws IOException      errore input output
     */

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente user = (Utente) req.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.TM) {
            Team team = new Team();
            RequestDispatcher dispatcher;
            String action = req.getParameter("action");
            int idTM = user.getId();
            try {
                if (action.equalsIgnoreCase("crea")) {
                    String nomeProgetto = req.getParameter("lname");
                    int numeroDipendenti = Integer.parseInt(req.getParameter("quantity"));
                    if (numeroDipendenti > 8) {
                        resp.getWriter().write("1");
                        String descrizione = "Si è verificato un errore. Numero dei dipendenti maggiore di 8";
                        resp.sendRedirect("/static/Error.jsp?descrizione=" + descrizione);
                    } else {
                        String nomeTeam = req.getParameter("fname");
                        String descrizione = req.getParameter("teamDescr");
                        team.setNomeProgetto(nomeProgetto);
                        team.setDescrizione(descrizione);
                        team.setNomeTeam(nomeTeam);
                        team.setNumeroDipendenti(numeroDipendenti);
                        if (!creaTeamFromManager(team, idTM)) {
                            resp.getWriter().write("2"); //errore creazione team
                            String errore = "creazione team non avvenuta";
                            resp.sendRedirect("./static/Error.jsp?descrizione=" + errore);
                            return;
                        } else {
                            int idTeam = getIdUltimoTeamCreatoFromManager();
                            req.setAttribute("idTeam", idTeam);
                            resp.getWriter().write("3");
                            dispatcher = req.getServletContext().getRequestDispatcher("/ListaTeam");
                            dispatcher.forward(req, resp);
                        }
                    }
                } else {
                    resp.getWriter().write("4");
                    resp.sendRedirect("/static/CreaTeam.jsp");
                }
            } catch (SQLException e) {
                String errore = "creazione team non avvenuta";
                resp.sendRedirect("./static/Error.jsp?descrizione=" + errore);
            }
        } else {
            resp.getWriter().write("5"); //non sei TM
            req.getSession().invalidate();
            resp.sendRedirect("./static/Login.html");
        }
    }

    /**
     * Questo metodo richiama il doGet
     *
     * @param req  , request
     * @param resp , response
     * @throws ServletException errore Servlet
     * @throws IOException      errore input output
     */

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * Questo metodo permette di creare un team utilizzando il manager
     *
     * @param team il team da creare
     * @param idTM id del TM che crea il team
     * @return boolean (true = creazione andata a buon fine, false = altrimenti)
     * @throws SQLException errore nella query
     */

    public static boolean creaTeamFromManager(Team team, int idTM) throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.creaTeam(team, idTM);

    }

    /**
     * Questo metodo permette di ottenere l'ultimo team creato utilizzando il manager
     *
     * @return l'id dell'ultimo team creato
     * @throws SQLException errore nella query
     */

    public static int getIdUltimoTeamCreatoFromManager() throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.viewLastIdTeam();
    }
}
