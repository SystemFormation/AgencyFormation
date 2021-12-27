package it.unisa.agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;

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
    private AutenticazioneManager aut;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        try {
            Utente user = aut.login(email, pwd);
            RequestDispatcher dispatcher;
            if(user!=null){
                HttpSession session = request.getSession(true);
                session.setAttribute("User",user);
                switch (user.getRuolo()){
                    case 1:
                        dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/nameJSPCandidate");
                        dispatcher.forward(request,response);
                        break;
                    case 2:
                        dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/nameJSPEmployee");
                        dispatcher.forward(request,response);
                        break;
                    case 3:
                        dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/nameJSPTM");
                        dispatcher.forward(request,response);
                        break;
                    case 4:
                        dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/nameJSPHR");
                        dispatcher.forward(request,response);
                        break;
                }
            }else{
                //Da raffinare
                String error = "Email o Password errati";
                request.setAttribute("Error",error);
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
                dispatcher.forward(request,response);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
