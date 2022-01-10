package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RicandidaturaControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        if (user == null || user.getRole() != RuoliUtenti.CANDIDATO) {
            response.sendRedirect("./static/Login.html");
        } else {
            try {
                if (!eliminaCandidaturaFromManager(user.getId())) {
                    response.getWriter().write("1"); //eliminazione candidatura fallita
                } else {
                    response.getWriter().write("2"); // avvenuta eliminazione candidatura
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeCandidato.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static boolean eliminaCandidaturaFromManager(int idCandidatura) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.ricandidatura(idCandidatura);
    }
}
