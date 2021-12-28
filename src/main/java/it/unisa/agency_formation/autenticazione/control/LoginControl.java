package it.unisa.agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;

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
    private UtenteDAO dao = new UtenteDAO();
    private AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl(dao);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println(System.getProperty("user.home"));

        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        RequestDispatcher dispatcher;
        if(email != null && pwd != null) {
            try {
                Utente user = aut.login(email, pwd);
                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("User", user);
                    switch (user.getRole()) {
                        case 1:
                            dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                            dispatcher.forward(request, response);
                            break;
                        case 2:
                            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/nameJSPEmployee");
                            dispatcher.forward(request, response);
                            break;
                        case 3:
                            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/nameJSPTM");
                            dispatcher.forward(request, response);
                            break;
                        case 4:
                            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/nameJSPHR");
                            dispatcher.forward(request, response);
                            break;
                    }
                } else {
                    //Da raffinare
                    String error = "Email o Password errati";
                    request.setAttribute("Error", error);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Login.html");
                dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
