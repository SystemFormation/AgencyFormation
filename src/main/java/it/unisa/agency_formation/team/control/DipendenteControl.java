package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;

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

@WebServlet("/DipendenteControl")
public class DipendenteControl extends HttpServlet {
    private DipendenteDAO dipdao=new DipendenteDAO();
    private TeamDAO tdao = new TeamDAO();
    private AutenticazioneManagerImpl aut= new AutenticazioneManagerImpl(dipdao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stato= req.getParameter("stato");
        RequestDispatcher dispatcher;
        /*non inserendo il filtro visualizzo tutti i dipendenti*/
        if(stato == null){
            try {
                ArrayList<Dipendente> dipendenti= aut.getAllEmploye();
                req.setAttribute("dipendenti", dipendenti);
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Dipendenti.jsp");
                dispatcher.forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            boolean state = true;
            if (stato.equals("disponibili")) state = true;
            else if (stato.equals("occupati")) state = false;

            try {
                ArrayList<Dipendente> dipendenti = aut.getEmployeByState(state);
                req.setAttribute("dipendenti", dipendenti);
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Dipendenti.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
