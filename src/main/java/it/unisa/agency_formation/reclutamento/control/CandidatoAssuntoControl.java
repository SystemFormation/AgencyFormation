package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CandidatoAssuntoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        if (user == null || user.getRole() != RuoliUtenti.CANDIDATO) {
            response.sendRedirect("./static/Login.html");
        } else {

            try {
                int idDipendente = user.getId();
                boolean set = setRuolo(idDipendente);
                if (!set) {
                    response.getWriter().write("1"); //errore settaggio ruolo
                    response.sendRedirect("/static/Error.html");
                } else {
                    int annoNascita = Integer.parseInt(request.getParameter("annoNascita"));
                    String residenza = request.getParameter("residenza");
                    String telefono = request.getParameter("telefono");

                    /*POTREMMO SETTARE EMAIL E PASSWORD IN QUESTO MODO VOLENDO.
                    NEL FORM DIREMO AL CANDIDATO CHE LE SUE CREDENZIALI SARANNO
                    EMAIL:N.COGNOME@AFCONSULTING.IT E PWD:NOME.COGNOMEANNO
                    String email=user.getName().charAt(0) +"."+ user.getSurname()+"@afconsulting.it";
                    String password=user.getName()+"."+user.getSurname()+annoNascita;
                    */
                    Dipendente dipendente = new Dipendente(user.getName(), user.getSurname(), user.getEmail(), user.getPwd(), RuoliUtenti.DIPENDENTE,
                            idDipendente, annoNascita, residenza, telefono, StatiDipendenti.DISPONIBILE);
                    assumiCandidato(dipendente);
                    if (!assumiCandidato(dipendente)) {
                        response.getWriter().write("2"); //errore assunzione
                        response.sendRedirect("/WEB-INF/jsp/HomeCandidato.jsp");
                    } else {
                        response.sendRedirect("/static/Login.html");
                    }

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

    public static boolean setRuolo(int idCandidato) throws SQLException {
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.modificaRuolo(idCandidato);
    }

    public static boolean assumiCandidato(Dipendente dipendente) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.assumiCandidato(dipendente);
    }
}
