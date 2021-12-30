package it.unisa.agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/ProfiloControl")
public class ProfiloControl extends HttpServlet {
    DipendenteDAO dipendenteDAO = new DipendenteDAO();
    private AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl(dipendenteDAO);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        Utente user = (Utente) request.getSession().getAttribute("user");
        int id = user.getId();
        try {
            if(user != null) {
                Dipendente dip = aut.getAllDataDip(id);
                if(id == dip.getIdDipendente()) {
                    request.setAttribute("dip", dip);
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Profilo.jsp");
                    dispatcher.forward(request, response);
                }else{
                    // roba da fare
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
