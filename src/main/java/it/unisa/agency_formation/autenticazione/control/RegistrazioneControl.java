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
    private UtenteDAO dao = new UtenteDAO();
    private AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl(dao);

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
                    request.getSession().setAttribute("user", user);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                response.getWriter().write("1");//qualche campo è vuoto
                response.sendRedirect("./html/Registrazione.html");
            }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}