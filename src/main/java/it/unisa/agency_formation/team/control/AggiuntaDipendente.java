package it.unisa.agency_formation.team.control;


import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManager;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManager;
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

@WebServlet("/AggiuntaDipendente")
public class AggiuntaDipendente extends HttpServlet {
    private AutenticazioneManagerImpl aut= new AutenticazioneManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher;
        /*visualizzo tutti i dipendenti Dispo*/
        int idTeam = Integer.parseInt(req.getParameter("idTeam"));
        System.out.println("mi trovo nella servlet dipendente "+ idTeam);
        //if(stato.equalsIgnoreCase("null")){
        try {
            ArrayList<Dipendente> dipendenti= aut.getTuttiDipendenti();
            for(int i=0;i<dipendenti.size();i++){
                ArrayList<Skill>  skills = new ArrayList<>();
                if(getSkillDipendenteFromManager(dipendenti.get(i).getIdDipendente())!=null && getSkillDipendenteFromManager(dipendenti.get(i).getIdDipendente()).size()>0){
                    skills = getSkillDipendenteFromManager(dipendenti.get(i).getIdDipendente());
                    dipendenti.get(i).setSkills(skills);
                }
            }
            req.setAttribute("dipendenti", dipendenti);
            req.setAttribute("idTeam",idTeam);
            dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ListaDipendenti.jsp");
            dispatcher.forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //}
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Team getTeamIdFromManager(int idTeam) throws SQLException{
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.getTeamId(idTeam);
    }

    public static ArrayList<Skill> getSkillDipendenteFromManager(int idDip) throws SQLException{
        FormazioneManager formazioneManager = new FormazioneManagerImpl();
        return formazioneManager.recuperoSkillConIdDipendete(idDip);
    }


}
