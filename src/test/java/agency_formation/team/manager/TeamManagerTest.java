package agency_formation.team.manager;


import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Questa classe testa i metodi della classe TeamManagerImpl
public class TeamManagerTest {
    TeamDAO dao = Mockito.mock(TeamDAO.class);
    TeamManagerImpl team = new TeamManagerImpl();

    @Test
    public void createTeam() throws SQLException{}
    @Test
    public void getEmployee() throws SQLException {}
    @Test
    public void addEmployee() throws SQLException {}
    @Test
    public void removeEmployee() throws SQLException {}
    @Test
    public void disbandTeam() throws SQLException {}
}
