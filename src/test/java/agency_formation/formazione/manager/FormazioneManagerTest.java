package agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

//Questa classe testa i metodi della classe FormazioneManagerImpl
public class FormazioneManagerTest {
    FormazioneManagerImpl aut = new FormazioneManagerImpl();

    @Test
    public void addSkillFail()throws SQLException {
        Skill skill = new Skill("CSS","Competenza Basilare");
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.doSaveSkill(skill)).thenReturn(false);
        }
        assertFalse(aut.addSkill(skill));
    }
    @Test
    public void addSkillPass()throws SQLException {
        Skill skill = new Skill("CSS","Competenza Basilare");
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.doSaveSkill(skill)).thenReturn(true);
        }
        assertTrue(aut.addSkill(skill));
    }
    // Da rivedere
    @Test
    public void getLastCreatedIdPass() throws SQLException{
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.doRetrieveLastId()).thenReturn(true);
        }
        assertNotNull(aut.getLastIdSkillCreated());
    }
    @Test
    public void addSkillDipFailId() throws  SQLException{
        int idSkill = 0;
        Dipendente dip = new Dipendente(4,3,2000,"Fisciano","118",false);
       try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.doSaveSkillDip(idSkill,dip)).thenReturn(false);
        }
        assertFalse(aut.addSkillDip(idSkill,dip));
    }
    @Test
    public void addSkillDipFailDip() throws  SQLException{
        int idSkill = 2;
        Dipendente dip = null;
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.doSaveSkillDip(idSkill,dip)).thenReturn(false);
        }
        assertFalse(aut.addSkillDip(idSkill,dip));
    }
    @Test
    public void addSkillDipPass() throws SQLException{
        int idSkill = 5;
        Dipendente dip = new Dipendente(2,1,2000,"Fisciano","118",false);
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.doSaveSkillDip(idSkill,dip)).thenReturn(true);
        }
        assertTrue(aut.addSkillDip(idSkill,dip));
    }

    //fare getIdEmplyee TODO

}
