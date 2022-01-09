package agency_formation.team.manager;



import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

//Questa classe testa i metodi della classe TeamManagerImpl

public class TeamManagerTest {
    TeamManagerImpl aut = new TeamManagerImpl();

    @Test
    public void createTeamPass() throws SQLException{
        int id = 3;
        Team team = new Team("Agency Formation",4,"The system errors","i membri sono dei geni",null,3);
        try (MockedStatic mocked = mockStatic(TeamDAO.class)) {
            mocked.when(() -> TeamDAO.salvaTeam(team,id)).thenReturn(true);
        }
        assertTrue(aut.creaTeam(team,id));
    }

    @Test
    public void getEmployee() throws SQLException { }
    @Test
    public void addEmployee() throws SQLException { }
    @Test
    public void removeEmployee() throws SQLException { }
    @Test
    public void disbandTeam() throws SQLException { }
}
