package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/*----------------GENNY SO CHE E' SBAGLIATA, NON TE LA PRENDERE-------------------*/
@WebServlet("/RejectCandidateControl")
public class RejectCandidatureControl extends HttpServlet {
    private static final ReclutamentoManagerImpl rec = new ReclutamentoManagerImpl();


    //-----------------------LASCIAMI TROVARE UNA SOLUZIONE MIGLIORE----------------//
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String action = req.getParameter("action");
        //TODO
        try {
            if (action == null) {
                resp.getWriter().write("notAction");//action= null
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Candidati.jsp");
                dispatcher.forward(req, resp);
            } else if (action.equalsIgnoreCase("reject")) {
                int idCandidato = Integer.parseInt(req.getParameter("id"));
                rec.rejectCandidature(idCandidato);
                resp.getWriter().write("reject");
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Candidati.jsp");
                dispatcher.forward(req, resp);
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
