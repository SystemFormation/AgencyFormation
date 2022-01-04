package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AcceptCandidatureControl")
public class AcceptCandidatureControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCandidato = Integer.parseInt(request.getParameter("idCandidato"));
        Utente user = (Utente) request.getSession().getAttribute("user");
        try {
            Candidatura candidatura = getCandidatura(idCandidato);
            if(acceptCandidature(candidatura.getIdCandidatura(), user.getId())) {
               response.getWriter().write("1");//accettazione avvenuta
            }else{
                //todo errore nell'accettazione
                response.getWriter().write("2");//accettazione non avvenuta

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public static Candidatura getCandidatura(int idCandidato) throws SQLException{
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.getCandidaturaById(idCandidato);
    }

    public static boolean acceptCandidature(int idCandidatura, int idHR) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.acceptCandidature(idCandidatura,idHR);
    }
}
