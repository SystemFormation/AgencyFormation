package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.team.DAO.TeamDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/DipendenteControl")
public class DipendenteControl extends HttpServlet {
    private AutenticazioneManagerImpl aut= new AutenticazioneManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stato= req.getParameter("stato");
        RequestDispatcher dispatcher;
        /*visualizzo tutti i dipendenti*/
        if(stato.equalsIgnoreCase("null")){
            try {
                ArrayList<Dipendente> dipendenti= aut.getAllEmploye();
                req.setAttribute("dipendenti", dipendenti);
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Dipendenti.jsp");
                dispatcher.forward(req,resp);
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
