package agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;
import it.unisa.agency_formation.team.domain.Team;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

//Questa classe testa i metodi della classe FormazioneManagerImpl
public class FormazioneManagerTest {
    FormazioneManagerImpl aut = new FormazioneManagerImpl();

    @Test
    public void addSkillFail()throws SQLException {
        Skill skill = null;
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.salvaSkill(skill)).thenReturn(false);
        }
        assertFalse(aut.aggiungiSkill(skill));
    }
    @Test
    public void addSkillPass()throws SQLException {
        Skill skill = new Skill("CSS","Competenza Basilare");
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.salvaSkill(skill)).thenReturn(true);
        }
        assertTrue(aut.aggiungiSkill(skill));
    }
    // Da rivedere
    @Test
    public void getLastCreatedIdPass() throws SQLException{
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.recuperaUltimaSkill()).thenReturn(0);
        }
        assertNotNull(aut.getUltimaSkill());
    }
    @Test
    public void addSkillDipFailId() throws  SQLException {
        Skill skill = new Skill("PHP", "lunguage PHP");
        Dipendente dip = new Dipendente("Andrea", "Capone", "adrea@gmail.com", "lol", RuoliUtenti.DIPENDENTE, 5, 2000, "Roma", "3423983792", StatiDipendenti.DISPONIBILE);
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.salvaSkillDipendente(skill.getIdSkill(), dip.getIdDipendente(), 3)).thenReturn(false);
        }
        assertFalse(aut.addSkillDipendente(skill.getIdSkill(), dip.getIdDipendente(), 3));
    }
    @Test
    public void addSkillDipFailDip() throws  SQLException{
        Skill skill = new Skill("PHP", "lunguage PHP");
        Dipendente dip = null;
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.salvaSkillDipendente(skill.getIdSkill(),dip.getIdDipendente() ,3)).thenReturn(false);
        }
        assertFalse(aut.addSkillDipendente(skill.getIdSkill(), dip.getIdDipendente() ,3));
    }
    //errore ritorno
    @Test
    public void addSkillDipPass() throws SQLException {
        Skill skill = new Skill();
        Utente user=new Utente("Angelo","Perna","aperna@live.it","lol",RuoliUtenti.DIPENDENTE);
        Dipendente dipendente;
        dipendente= new Dipendente(user.getName(), user.getSurname(),user.getEmail(),user.getPwd(),user.getRole(), user.getId(), 1999,"Roma","3345678678",StatiDipendenti.DISPONIBILE);
        int level=3;
        try (MockedStatic mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.salvaSkillDipendente(skill.getIdSkill(), dipendente.getIdDipendente(), level)).thenReturn(true);
        }
        assertTrue(aut.addSkillDipendente(skill.getIdSkill(), dipendente.getIdDipendente(), level));
    }

    @Test //documento==null
    public void getMaterialeByIdTeamFail() throws SQLException {
        Team team = new Team("Progetto", 3, "Team", "testing", "html", 3);
        try (MockedStatic mocked = mockStatic(DocumentoDAO.class)) {
            mocked.when(() -> DocumentoDAO.recuperaDocumentoByTeam(team.getIdTeam())).thenReturn(null);
        }
        assertNull(aut.getMaterialeByIdTeam(team.getIdTeam()));
    }

    @Test //pass
    public void getMaterialeByIdTeamPass() throws SQLException {
        Team team = new Team("Progetto1", 3, "Team1", "testing", "html", 3);
        Documento document = new Documento();
        team.setDocumento(document);
        try (MockedStatic mocked = mockStatic(DocumentoDAO.class)) {
            mocked.when(() -> DocumentoDAO.recuperaDocumentoByTeam(team.getIdTeam())).thenReturn(team.getDocumento());
        }
        assertNotNull(aut.getMaterialeByIdTeam(team.getIdTeam()));
    }

}
