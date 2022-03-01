package it.unisa.agency_formation.autenticazione.control;

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

@WebServlet("/LicenziaDipendente")
public class LicenziaDipendenteControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDipendente = Integer.parseInt(req.getParameter("idDip"));
        try {
            licenziaFromManager(idDipendente);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeHR.jsp");
            dispatcher.forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static boolean licenziaFromManager(int idDipendente) throws SQLException{
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.licenziaDipendente(idDipendente);
    }

}
