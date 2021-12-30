package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;
import it.unisa.agency_formation.utils.Check;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/TeamControl")
public class TeamControl extends HttpServlet {
    private TeamManagerImpl team = new TeamManagerImpl();
    private TeamDAO dao = new TeamDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeProgetto = req.getParameter("NomeProgetto");
        int numeroDipendenti = Integer.parseInt(req.getParameter("NumeroDipendenti"));
        String nomeTeam = req.getParameter("NomeTeam");
        int idTM = Integer.parseInt(req.getParameter("IdTM"));
        String descrizione = req.getParameter("Descrizione");
        String competenza = req.getParameter("Competenza");
        RequestDispatcher dispatcher;
        ArrayList<Team> teams = new ArrayList<>();
        Team createTeam = new Team();
        // check delle variabili per la creazione del team
        if (Check.checkProjectName(nomeProgetto) && Check.checkTeamName(nomeTeam)
                && Check.checkDescription(descrizione) && Check.checkCompetence(competenza)) {
            createTeam.setNomeProgetto(nomeProgetto);
            createTeam.setCompetenza(competenza);
            createTeam.setDescrizione(descrizione);
            createTeam.setIdTM(idTM);
            createTeam.setNomeTeam(nomeTeam);
            createTeam.setNumeroDipendenti(numeroDipendenti);
            // ArrayList dei team esistenti
            try {
                teams = dao.doRetrieveAllTeam();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (Team t : teams) {
                if (t.getNomeTeam().equals(nomeTeam)) {
                    resp.getWriter().write("exsist team"); //team gia esistente
                }
            }
            if (nomeProgetto.trim().length() == 0) {
                resp.getWriter().write("nomeProgVuoto"); // nome progetto vuoto
            }

            if (numeroDipendenti <= 0) {
                resp.getWriter().write("numeroDipInvalid"); // numero di dipendenti massimo inserito non è valido
            }

            if (nomeTeam.trim().length() == 0) {
                resp.getWriter().write("nomeTeamVuoto"); // nome team vuoto
            }


            if (descrizione.trim().length() == 0) {
                resp.getWriter().write("descVuota");//descrizione vuota
            }

            if (nomeProgetto != null && numeroDipendenti > 0 && nomeTeam != null && descrizione != null) {
                try {
                    boolean save = team.createTeam(nomeTeam, nomeProgetto, descrizione, numeroDipendenti, competenza, idTM);
                    if (save = true) {
                        HttpSession session = req.getSession(true);
                        //session.setAttribute("user", save);
                        dispatcher = req.getServletContext().getRequestDispatcher("/fileTeam.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        resp.sendRedirect("/WEB-INF/jsp/fileTeam.jsp");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
