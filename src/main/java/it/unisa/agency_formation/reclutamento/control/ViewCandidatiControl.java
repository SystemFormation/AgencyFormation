package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
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

@WebServlet("/ViewCandidatiControl")
public class ViewCandidatiControl extends HttpServlet {
    private UtenteDAO dao = new UtenteDAO();
    private AutenticazioneManager aut = new AutenticazioneManagerImpl(dao);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Utente> candidati;
        try {
            candidati = aut.getCandidates();
            if(candidati!=null && candidati.size()>0) {
                request.setAttribute("candidati", candidati);
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Candidati.jsp");
                dispatcher.forward(request, response);
            }else{
                //TODO
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
