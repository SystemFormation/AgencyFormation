package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/AcceptCandidatureControl")
public class AcceptCandidatureControl extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Utente user = (Utente) request.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.HR) {
            int idCandidato = Integer.parseInt(request.getParameter("idCandidato"));
            String data = request.getParameter("data1");
            String tempo = request.getParameter("time");
            String dataOra = data + " " + tempo;
            try {
                Date data1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataOra);
                Timestamp timestamp = new Timestamp(data1.getTime());
                try {
                    Candidatura candidatura = getCandidaturaFromManager(idCandidato);
                    if (acceptCandidatureFromManager(candidatura.getIdCandidatura(), user.getId(), timestamp)) {
                        response.getWriter().write("1"); //accettazione avvenuta
                    } else {
                        response.getWriter().write("2"); //accettazione non avvenuta
                        response.sendRedirect("./static/Error.html");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            response.getWriter().write("3"); // user null o ruolo non adeguato
            response.sendRedirect("./static/Login.html");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Candidatura getCandidaturaFromManager(int idCandidato) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.getCandidaturaById(idCandidato);
    }

    public static boolean acceptCandidatureFromManager(int idCandidatura, int idHR, Timestamp timestamp) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.accettaCandidatura(idCandidatura, idHR, timestamp);
    }

    public static ArrayList<Candidatura> getTutteCandidatureFromManager() throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.getTutteCandidature();
    }
}
