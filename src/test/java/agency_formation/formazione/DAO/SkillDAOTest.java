package agency_formation.formazione.DAO;


import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Skill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Questa classe racchiude tutti i casi di test riguardante SkillDAO
 */
public class SkillDAOTest {
    //
    @Test
    public void SaveSkillFailSkillNull() throws SQLException{
        Skill skill = null;
        assertFalse(SkillDAO.doSaveSkill(skill));
    }

    @Test
    public void SaveSkillOK() throws SQLException{
        Skill skill = new Skill("C","Conoscenze base");
        assertTrue(SkillDAO.doSaveSkill(skill));
    }

    @Test
    public void RemoveSkillOK() throws SQLException{
        int idSkill= 2;
        assertTrue(SkillDAO.doRemoveSkill(2));
    }
    @Test
    public void RemoveSkillFail() throws SQLException{
        int idSkill= 0;
        assertFalse(SkillDAO.doRemoveSkill(0));
    }

    // Funziona con il db vuoto
    @Test
    public void doRetrieveAllFail() throws SQLException{
        assertNull(SkillDAO.doRetrieveAll());
    }
    @Test
    public void doRetrieveAllPass() throws SQLException{
        assertNotNull(SkillDAO.doRetrieveAll());
    }

    @Test
    public void doRetrieveByNameNull() throws SQLException{
        String nomeSkill = null;
        assertNull(SkillDAO.doRetrieveByName(nomeSkill));
    }
    @Test
    public void doRetrieveByNamePass() throws SQLException{
        String nomeSkill = "CSS";
        assertNotNull(SkillDAO.doRetrieveByName(nomeSkill));
    }
     //Da rivedere, fare ANCHE IL FALLIMENTO //TODO
    @Test
    public void saveSkillDipPass() throws SQLException {
        int idSkill = 5;
        Dipendente dip = new Dipendente(2,1,2000,"Fisciano","118",false);
        assertTrue(SkillDAO.doSaveSkillDip(idSkill,dip));

    }
    @Test
    public void RetrieveLastIdPass() throws  SQLException{
        assertNotNull(SkillDAO.doRetrieveLastId());
    }
}
