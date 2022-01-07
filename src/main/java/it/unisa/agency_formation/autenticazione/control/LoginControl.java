package it.unisa.agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
    private AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("user");
        if (user != null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
            dispatcher.forward(request, response);
        } else {
            String email = request.getParameter("email");
            String pwd = request.getParameter("password");
            RequestDispatcher dispatcher;
            if (email != null && pwd != null) {
                if (email.trim().length() == 0) {
                    response.getWriter().write("1");//email vuota
                }
                if (pwd.trim().length() == 0) {
                    response.getWriter().write("2");//password vuota;
                }
                try {
                    user = aut.login(email, pwd);
                    if (user != null) {
                        session = request.getSession(true);
                        session.setAttribute("user", user);
                        response.getWriter().write("3");//utente loggato
                        switch (user.getRole()) {
                            case CANDIDATO:
                                Candidatura candidatura = getCandidaturafromManager(user.getId());
                                if (candidatura != null) {
                                    request.setAttribute("candidatura", candidatura);
                                }
                                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeCandidate.jsp");
                                dispatcher.forward(request, response);
                                break;
                            case DIPENDENTE:
                                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeEmployee.jsp");
                                dispatcher.forward(request, response);
                                break;
                            case TM:
                                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeTM.jsp");
                                dispatcher.forward(request, response);
                                break;
                            case HR:
                                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeHR.jsp");
                                dispatcher.forward(request, response);
                                break;

                        }
                    } else {
                        response.getWriter().write("5");//utente non valido
                        response.sendRedirect("/static/Login.html");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                response.getWriter().write("6");//email e password null
                response.sendRedirect("/static/Login.html");
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Candidatura getCandidaturafromManager(int idCandidato) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.getCandidaturaById(idCandidato);
    }
}
