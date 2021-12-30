package agency_formation.team.DAO;

import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import org.junit.jupiter.api.Test;

public class TeamDAOTest {
    TeamDAO team= new TeamDAO();

    @Test
    public void doSaveTeam(Team team){}
    @Test
    public void doRemoveTeam(int idTeam){}
    @Test
    public void addEmployee(int idTeam, int idDipendente){}
    @Test
    public void removeEmployee(int idTeam, int idDipendente){}
    @Test
    public void doRetrieveAllTeam(){}
    @Test
    public void doRetrieveTMTeam(int idUtente){}
    @Test
    public void updateCompetence(String competence, int idTeam){}
    @Test
    public void doRetrieveCompetence(int idTeam){}
    @Test
    public void doRetrieveAllTMember (int idTeam){}
    @Test
    public void doRetrieveNTMember(int idTeam){}

}
