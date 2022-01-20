package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ViewCandidaturaControl")
public class ViewCandidaturaControl extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCandidato = 0;
        if (request.getParameter("idCandidato") == null) {
            response.getWriter().write("4"); // idCandidato null
            String descrizione = "Si è verficato un problema con IdCandidato";
            response.sendRedirect("./static/Error.jsp?descrizione="+descrizione);
        } else {
            idCandidato = Integer.parseInt(request.getParameter("idCandidato"));
        }
        Candidatura candidatura = null;
        try {
            candidatura = getCandidaturaByIdFromManager(idCandidato);
            if (candidatura == null) {
                response.getWriter().write("1"); //candidatura null
                String descrizione = "Si è verficato un problema con il recupero della candidatura";
                response.sendRedirect("./static/Error.jsp?descrizione="+descrizione);
            } else {
                String cv = "curriculum.";
                request.setAttribute("curriculum", cv);
                response.getWriter().write(cv);
                String documenti = candidatura.getDocumentiAggiuntivi();
                if (documenti != null) {
                    response.getWriter().write("2"); //documenti non null
                    documenti = "documenti";
                    response.getWriter().write(documenti);
                    response.getWriter().close();
                } else {
                    response.getWriter().write("3"); //documenti null
                    String descrizione = "Si è verficato un problema con il recupero dei documenti";
                    response.sendRedirect("./static/Error.jsp?descrizione="+descrizione);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Candidatura getCandidaturaByIdFromManager(int idCandidato) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.getCandidaturaById(idCandidato);
    }
}
