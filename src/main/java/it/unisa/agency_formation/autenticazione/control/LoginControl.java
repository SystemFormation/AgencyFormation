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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("user");
        if (user != null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
            dispatcher.forward(request,response);
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
                        dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
                        dispatcher.forward(request,response);
                    } else {
                        response.getWriter().write("4");//utente non loggato
                        response.sendRedirect("./html/Login.html"); //TODO-r: magari potreste aggiungere un parametro tipo errore=1, in questo modo nella pagina potete far visualizzare errore di user o password non corretti (potete usare jstl per leggere il parametro, o js)
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                response.getWriter().write("5");//email e password null
                response.sendRedirect("./html/Login.html");
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
