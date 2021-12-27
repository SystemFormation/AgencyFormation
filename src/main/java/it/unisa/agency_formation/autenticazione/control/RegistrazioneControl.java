package it.unisa.agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.utils.Check;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/RegistrazioneControl")
public class RegistrazioneControl extends HttpServlet {
    private AutenticazioneManager aut;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = new Utente();
        boolean check = false;
        if(Check.checkName(request.getParameter("nome"))
                && Check.checkSurname(request.getParameter("cognome"))
                && Check.checkEmail(request.getParameter("email"))
                && Check.checkPwd(request.getParameter("pwd"))) {
            check = true;
        }
        if(check) {
            user.setName(request.getParameter("nome"));
            user.setSurname(request.getParameter("cognome"));
            user.setEmail(request.getParameter("email"));
            user.setPwd(request.getParameter("pwd"));
            user.setRole(1);//il ruolo = 1 perchè il candidato è l'unico che si registra

            try {
                aut.registration(user);
                request.getSession().setAttribute("user", user);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/nameJSPCandidate");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String error = "Formato campi errato";
            request.setAttribute("Error", error);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/nameJSPRegistration");
            dispatcher.forward(request, response);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}