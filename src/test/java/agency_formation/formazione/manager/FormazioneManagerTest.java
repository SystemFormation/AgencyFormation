package agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;
import it.unisa.agency_formation.team.domain.Team;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

//Questa classe testa i metodi della classe FormazioneManagerImpl
public class FormazioneManagerTest {
    FormazioneManagerImpl aut = new FormazioneManagerImpl();

    @Test
    public void salvaDocumentoFail() throws SQLException {
        Documento doc=null;
        try(MockedStatic<DocumentoDAO> mocked= mockStatic(DocumentoDAO.class)){
            mocked.when(() -> DocumentoDAO.salvaDocumento(doc)).thenReturn(false);
            assertFalse(aut.salvaDocumento(doc));
        }
    }

    @Test
    public void salvaDocumentoPass()throws SQLException {
        Documento doc= new Documento();
        try(MockedStatic<DocumentoDAO> mocked= mockStatic(DocumentoDAO.class)){
            mocked.when(() -> DocumentoDAO.salvaDocumento(doc)).thenReturn(true);
            assertTrue(aut.salvaDocumento(doc));
        }
    }
    @Test
    public void addSkillFail() throws SQLException {
        Skill skill = null;
        try (MockedStatic<SkillDAO> mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.salvaSkill(skill)).thenReturn(false);
            assertFalse(aut.aggiungiSkill(skill));
        }
    }

    @Test
    public void addSkillPass()throws SQLException {
        Skill skill = new Skill("CSS","Competenza Basilare");
        try (MockedStatic<SkillDAO> mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.salvaSkill(skill)).thenReturn(true);
            assertTrue(aut.aggiungiSkill(skill));

        }
    }

    @Test
    public void getUltimaSkillPass() throws SQLException{
        try (MockedStatic<SkillDAO> mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.recuperaUltimaSkill()).thenReturn(1);
            assertTrue(aut.getUltimaSkill()>0);

        }
    }
    @Test
    public void getUltimaSkillFail() throws SQLException{
        try (MockedStatic<SkillDAO> mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.recuperaUltimaSkill()).thenReturn(0);
            assertFalse(aut.getUltimaSkill()>0);

        }
    }
    @Test
    public void addSkillDipendenteFail() throws  SQLException {
        Skill skill = new Skill("PHP", "lunguage PHP");
        skill.setIdSkill(1);
        Dipendente dip = new Dipendente();
        dip.setIdDipendente(1);
        dip.setAnnoNascita(2000);
        dip.setTelefono("1236987569");
        dip.setResidenza("Londra");
        dip.setStato(StatiDipendenti.DISPONIBILE);
        try (MockedStatic<SkillDAO> mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.salvaSkillDipendente(skill.getIdSkill(), dip.getIdDipendente(), 3)).thenReturn(false);
            assertFalse(aut.addSkillDipendente(skill.getIdSkill(), dip.getIdDipendente(), 3));
        }
    }
    @Test
    public void addSkillDipendentePass() throws  SQLException{
        Skill skill = new Skill("PHP", "lunguage PHP");
        skill.setIdSkill(1);
        Dipendente dip = new Dipendente();
        dip.setIdDipendente(1);
        dip.setAnnoNascita(2000);
        dip.setTelefono("1236987569");
        dip.setResidenza("Londra");
        dip.setStato(StatiDipendenti.DISPONIBILE);
        try (MockedStatic<SkillDAO> mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.salvaSkillDipendente(skill.getIdSkill(),dip.getIdDipendente() ,3)).thenReturn(true);
            assertTrue(aut.addSkillDipendente(skill.getIdSkill(), dip.getIdDipendente() ,3));

        }
    }


    @Test //documento==null
    public void getMaterialeByIdTeamFail() throws SQLException {
        Team team = new Team("Progetto", 3, "Team", "testing", "html", 3);
        team.setIdTeam(3);
        try (MockedStatic<DocumentoDAO> mocked = mockStatic(DocumentoDAO.class)) {
            mocked.when(() -> DocumentoDAO.recuperaDocumentoByTeam(team.getIdTeam())).thenReturn(null);
            assertNull(aut.getMaterialeByIdTeam(team.getIdTeam()));

        }
    }

    @Test //pass
    public void getMaterialeByIdTeamPass() throws SQLException {
        Team team = new Team("Progetto1", 3, "Team1", "testing", "html", 3);
        team.setIdTeam(3);
        Documento document = new Documento();
        try (MockedStatic<DocumentoDAO> mocked = mockStatic(DocumentoDAO.class)) {
            mocked.when(() -> DocumentoDAO.recuperaDocumentoByTeam(team.getIdTeam())).thenReturn(document);
            assertNotNull(aut.getMaterialeByIdTeam(team.getIdTeam()));

        }
    }

    @Test
    public void recuperaSkillConIdDipFail() throws SQLException {
        ArrayList<Skill> skills = null;
        try (MockedStatic<SkillDAO> mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.recuperoSkillsByIdDipendente(5)).thenReturn(skills);
            assertNull(SkillDAO.recuperoSkillsByIdDipendente(5));
        }
    }
    @Test
    public void recuperaSkillConIdDipPass() throws SQLException {
        ArrayList<Skill> skills = new ArrayList<>();
        try (MockedStatic<SkillDAO> mocked = mockStatic(SkillDAO.class)) {
            mocked.when(() -> SkillDAO.recuperoSkillsByIdDipendente(5)).thenReturn(skills);
            assertNotNull(SkillDAO.recuperoSkillsByIdDipendente(5));
        }
    }
}
