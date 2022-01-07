package it.unisa.agency_formation.team.control;


import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/DipendenteDispoControl")
public class DipendenteDispoControl extends HttpServlet {
    private AutenticazioneManagerImpl aut= new AutenticazioneManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher;
        /*visualizzo tutti i dipendenti Dispo*/
        int idTeam = Integer.parseInt(req.getParameter("idTeam"));
        //if(stato.equalsIgnoreCase("null")){
        try {
            ArrayList<Dipendente> dipendenti= aut.getTuttiDipendenti();
            req.setAttribute("dipendenti", dipendenti);
            req.setAttribute("idTeam",idTeam);
            dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Dipendenti.jsp");
            dispatcher.forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //}
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
