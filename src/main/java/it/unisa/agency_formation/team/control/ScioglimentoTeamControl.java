package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/ScioglimentoTeamControl")
public class ScioglimentoTeamControl extends HttpServlet {
    TeamManagerImpl aut = new TeamManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTeam = Integer.parseInt(req.getParameter("idTeam"));
        RequestDispatcher dispatcher;
        if(idTeam != 0){
            try {
                ArrayList<Integer> listaIdDip = aut.retriveDips(idTeam);
                System.out.println(listaIdDip);
                for(int i = 0; i<listaIdDip.size(); i++){
                    int x =  listaIdDip.get(i);
                    aut.updateDipsDisso(x);
                }
                TeamDAO.doRemoveTeam(idTeam);
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Team.jsp");
                dispatcher.forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            //Errore TODO
            dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Dipendenti.jsp");
            dispatcher.forward(req,resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
