package it.unisa.agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManager;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet("/ProfiloControl")
public class ProfiloControl extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        int id = user.getId();
        try {
            if (user != null && user.getRole() == RuoliUtenti.DIPENDENTE) {
                Dipendente dip = getAllDataDipFromManager(id);
                ArrayList<Skill> skills = new ArrayList<>();
                if (getSkillDipendenteFromManager(dip.getIdDipendente()) != null && getSkillDipendenteFromManager(dip.getIdDipendente()).size() > 0) {
                    skills = getSkillDipendenteFromManager(dip.getIdDipendente());
                    dip.setSkills(skills);
                }
                response.getWriter().write("1"); // retrieve data ok
                request.setAttribute("dip", dip);
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/static/Profilo.jsp");
                dispatcher.forward(request, response);
            } else {
                response.getWriter().write("2"); //errore
                response.sendRedirect("./static/Login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Dipendente getAllDataDipFromManager(int id) throws SQLException {
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.getDipendente(id);
    }

    public static ArrayList<Skill> getSkillDipendenteFromManager(int idDip) throws SQLException {
        FormazioneManager formazioneManager = new FormazioneManagerImpl();
        return formazioneManager.recuperoSkillConIdDipendente(idDip);
    }
}
