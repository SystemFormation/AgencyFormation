package it.unisa.agency_formation.formazione.control;

import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.manager.FormazioneManager;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CheckMaterialeFormazione")
public class CheckMaterialeFormazione extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idTeam = Integer.parseInt(request.getParameter("idTeam"));
            if (idTeam <= 0) {
                response.getWriter().write("1"); //l'id del team non può  essere minore di 1
            }
            Documento documento = getDocumentofromManager(idTeam);
            if (documento != null) {
                response.getWriter().write("2"); //il documento esiste
            } else {
                response.getWriter().write("3"); //il documento non esiste
                response.sendRedirect("./static/Error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    public static Documento getDocumentofromManager(int idTeam) throws SQLException {
        FormazioneManager formazioneManager = new FormazioneManagerImpl();
        return formazioneManager.getMaterialeByIdTeam(idTeam);
    }
}
