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

@WebServlet("/DownloadControl")
public class DownloadControl extends HttpServlet {
    private CandidaturaDAO dao = new CandidaturaDAO();
    private ReclutamentoManager reclutamento = new ReclutamentoManagerImpl(dao);
    private String directory = System.getProperty("user.home");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String whatDownload = request.getParameter("toDownload");
        int idCandidato = Integer.parseInt(request.getParameter("idCandidato"));

        RequestDispatcher dispatcher;
        Candidatura candidatura = null;
        try {
            candidatura = reclutamento.getCandidaturaById(idCandidato);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(candidatura!=null) {
            if (whatDownload.equalsIgnoreCase("curriculum")) {
                String pathCurriculum = directory + candidatura.getCurriculum();
                FileInputStream file = new FileInputStream(pathCurriculum);
                int i;
                while ((i = file.read()) != -1) {
                    response.getWriter().write(i);
                }
                file.close();
                response.getWriter().close();
                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Candidati.jsp");
                dispatcher.forward(request, response);
            }else if(whatDownload.equalsIgnoreCase("documenti")){
                String pathDocumenti = directory + candidatura.getDocumentiAggiuntivi();
                FileInputStream file = new FileInputStream(pathDocumenti);
                int i;
                while ((i = file.read()) != -1) {
                    response.getWriter().write(i);
                }
                file.close();
                response.getWriter().close();
                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Candidati.jsp");
                dispatcher.forward(request, response);

            }
        }else{
            //TODO FILE NON ESISTENTI
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
