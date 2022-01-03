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
    private AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        int id = user.getId();
        try {
            if (user != null && user.getRole() == 2) {
                Dipendente dip = aut.getAllDataDip(id);
                response.getWriter().write("1");//
                if (id == dip.getIdDipendente()) {
                    response.getWriter().write("2");//
                    request.setAttribute("dip", dip);
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/static/Profilo.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.getWriter().write("3");//
                }
            }
            else{
                response.getWriter().write("4");//
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
