package agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/*public class FormazioneManagerTest {
    SkillDAO dao = Mockito.mock(SkillDAO.class);
    FormazioneManagerImpl aut = new FormazioneManagerImpl(dao);

    @Test
    public void addSkillFailNomeNull()throws SQLException {
        Skill skill = new Skill("","Competenza Basilare");
        Dipendente dip = new Dipendente(4,3,2000,"Fisciano","118",false);
        Mockito.when(dao.doSaveSkill(skill,dip)).thenReturn(false);
        assertFalse(aut.addSkill(skill,dip));
    }
    @Test
    public void addSkillFailDescNull()throws SQLException {
        Skill skill = new Skill("CSS","");
        Dipendente dip = new Dipendente(4,3,2000,"Fisciano","118",false);
        Mockito.when(dao.doSaveSkill(skill,dip)).thenReturn(false);
        assertFalse(aut.addSkill(skill,dip));
    }
    @Test
    public void addSkillPass()throws SQLException {
        Skill skill = new Skill("CSS","Competenza Basilare");
        Dipendente dip = new Dipendente(4,3,2000,"Fisciano","118",false);
        Mockito.when(dao.doSaveSkill(skill,dip)).thenReturn(true);
        assertTrue(aut.addSkill(skill,dip));
    }
    @Test
    public void getLastCreatedIdPass() throws SQLException{
        assertNotNull(aut.getLastIdSkillCreated());
    }
    @Test
    public void addSkillDipFailId() throws  SQLException{
        int idSkill = 0;
        Dipendente dip = new Dipendente(4,3,2000,"Fisciano","118",false);
        Mockito.when(dao.doSaveSkillDip(idSkill,dip)).thenReturn(false);
        assertFalse(aut.addSkillDip(idSkill,dip));
    }
    @Test
    public void addSkillDipFailDip() throws  SQLException{
        int idSkill = 2;
        Dipendente dip = null;
        Mockito.when(dao.doSaveSkillDip(idSkill,dip)).thenReturn(false);
        assertFalse(aut.addSkillDip(idSkill,dip));
    }
    @Test
    public void addSkillDipPass() throws SQLException{
        int idSkill = 2;
        Dipendente dip = new Dipendente(4,3,2000,"Fisciano","118",false);
        Mockito.when(dao.doSaveSkillDip(idSkill,dip)).thenReturn(true);
        assertTrue(aut.addSkillDip(idSkill,dip));
    }

    //fare getIdEmplyee TODO

}*/
