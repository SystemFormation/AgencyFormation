package it.unisa.agency_formation.autenticazione.control;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
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
    private AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = new Utente();
            if (Check.checkName(request.getParameter("nome"))
                    && Check.checkSurname(request.getParameter("cognome"))
                    && Check.checkEmail(request.getParameter("email"))
                    && Check.checkPwd(request.getParameter("pwd"))) {
                user.setName(request.getParameter("nome"));
                user.setSurname(request.getParameter("cognome"));
                user.setEmail(request.getParameter("email"));
                user.setPwd(request.getParameter("pwd"));
                user.setRole(1);//il ruolo = 1 perchè il candidato è l'unico che si registra
                try {
                    aut.registration(user);
                    Utente result = aut.login(user.getEmail(), user.getPwd());
                    request.getSession().setAttribute("user", result);
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/static/Home.jsp");
                    dispatcher.forward(request, response);
                    response.getWriter().write("5");//registrazione avvenuta con successo
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                if(!Check.checkName(request.getParameter("nome"))){
                    response.getWriter().write("1");//nome non corretto
                }
                if(!Check.checkSurname(request.getParameter("cognome"))){
                    response.getWriter().write("2");//cognome non corretto
                }
                if(!Check.checkEmail(request.getParameter("email"))){
                    response.getWriter().write("3");//email non corretto
                }
                if(!Check.checkPwd(request.getParameter("pwd"))){
                    response.getWriter().write("4");//password non corretto
                }
                response.sendRedirect("./static/Registrazione.html");
            }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}