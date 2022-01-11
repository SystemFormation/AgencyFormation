package it.unisa.agency_formation.autenticazione.control;

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
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("user");
        if (user != null) {
            switch (user.getRole()) {
                case CANDIDATO:
                    Candidatura candidatura = null;
                    try {
                        candidatura = getCandidaturafromManager(user.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (candidatura != null) {
                        request.setAttribute("candidatura", candidatura);
                    }
                    dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeCandidato.jsp");
                    dispatcher.forward(request, response);
                    break;
                case DIPENDENTE:
                    dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeDipendente.jsp");
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
                default:
                    response.sendRedirect("/static/Login.html");
                    break;
            }
        } else {
            String email = request.getParameter("email");
            String pwd = request.getParameter("password");

            if (email != null && pwd != null) {
                if (email.trim().length() == 0) {
                    response.getWriter().write("1"); //email vuota
                }
                if (pwd.trim().length() == 0) {
                    response.getWriter().write("2"); //password vuota
                }
                try {
                    user = loginFromManager(email, pwd);
                    if (user != null) {
                        session = request.getSession(true);
                        session.setAttribute("user", user);
                        response.getWriter().write("3"); //utente loggato
                        switch (user.getRole()) {
                            case CANDIDATO:
                                Candidatura candidatura = getCandidaturafromManager(user.getId());
                                if (candidatura != null) {
                                    request.setAttribute("candidatura", candidatura);
                                }
                                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeCandidato.jsp");
                                dispatcher.forward(request, response);
                                break;
                            case DIPENDENTE:
                                dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeDipendente.jsp");
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
                            default:
                                break;
                        }
                    } else {
                        response.getWriter().write("4"); //utente non valido
                        response.sendRedirect("./static/Login.html");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                response.getWriter().write("5"); //email e password null
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

    public static Utente loginFromManager(String email, String pwd) throws SQLException {
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.login(email, pwd);
    }
}
