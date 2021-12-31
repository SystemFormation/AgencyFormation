package it.unisa.agency_formation.reclutamento.control;

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
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ViewCandidaturaControl")
public class ViewCandidatureControl extends HttpServlet {
    private CandidaturaDAO dao = new CandidaturaDAO();
    private ReclutamentoManager reclutamento = new ReclutamentoManagerImpl(dao);
    private String directory = System.getProperty("user.home");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCandidato = Integer.parseInt(request.getParameter("idCandidato"));
        Candidatura candidatura = null;
        try {
            candidatura = reclutamento.getCandidaturaById(idCandidato);
            if(candidatura!=null){
                String cv = "curriculum.";
                request.setAttribute("curriculum",cv);
                response.getWriter().write(cv);
                String documenti  = candidatura.getDocumentiAggiuntivi();
                if(documenti!=null){
                    documenti = "documenti";
                    response.getWriter().write(documenti);
                    response.getWriter().close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
