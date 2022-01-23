package agency_formation.formazione.manager;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.formazione.DAO.DocumentoDAO;
import it.unisa.agency_formation.formazione.DAO.DocumentoDAOImpl;
import it.unisa.agency_formation.formazione.DAO.SkillDAO;
import it.unisa.agency_formation.formazione.DAO.SkillDAOImpl;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;
import it.unisa.agency_formation.team.domain.Team;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Questa classe testa i metodi della classe FormazioneManagerImpl
public class FormazioneManagerTest {
    private FormazioneManagerImpl formazioneManager = new FormazioneManagerImpl();
    private SkillDAO daoSkill = mock(SkillDAOImpl.class);
    private DocumentoDAO daoDocumento = mock(DocumentoDAOImpl.class);

    public void init(){
        FormazioneManagerImpl.daoSkill=daoSkill;
        FormazioneManagerImpl.daoDocumento=daoDocumento;
    }


    @Test
    public void salvaDocumentoFail() throws SQLException {
        Documento doc=null;
        when(daoDocumento.salvaDocumento(doc)).thenReturn(false);
        init();
        assertFalse(formazioneManager.salvaDocumento(doc));
    }

    @Test
    public void salvaDocumentoPass() throws SQLException {
        Documento doc = new Documento();
        when(daoDocumento.salvaDocumento(doc)).thenReturn(true);
        init();
        assertTrue(formazioneManager.salvaDocumento(doc));
    }

    @Test
    public void addSkillFail() throws SQLException {
        Skill skill = null;
        when(daoSkill.salvaSkill(skill)).thenReturn(false);
        init();
        assertFalse(formazioneManager.aggiungiSkill(skill));
    }

    @Test
    public void addSkillPass()throws SQLException {
        Skill skill = new Skill("CSS","Competenza Basilare");
        when(daoSkill.salvaSkill(skill)).thenReturn(true);
        init();
        assertTrue(formazioneManager.aggiungiSkill(skill));
    }

    @Test
    public void getUltimaSkillPass() throws SQLException{
        when(daoSkill.recuperaUltimaSkill()).thenReturn(1);
        init();
        assertTrue(formazioneManager.getUltimaSkill()>0);
    }
    @Test
    public void getUltimaSkillFail() throws SQLException{
        when(daoSkill.recuperaUltimaSkill()).thenReturn(0);
        init();
        assertFalse(formazioneManager.getUltimaSkill()>0);
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
        when(daoSkill.salvaSkillDipendente(skill.getIdSkill(), dip.getIdDipendente(), 3)).thenReturn(false);
        init();
        assertFalse(formazioneManager.addSkillDipendente(skill.getIdSkill(), dip.getIdDipendente(), 3));
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
        when(daoSkill.salvaSkillDipendente(skill.getIdSkill(),dip.getIdDipendente() ,3)).thenReturn(true);
        init();
        assertTrue(formazioneManager.addSkillDipendente(skill.getIdSkill(), dip.getIdDipendente() ,3));
    }


    @Test //documento==null
    public void getMaterialeByIdTeamFail() throws SQLException {
        Team team = new Team("Progetto", 3, "Team", "testing", "html", 3);
        team.setIdTeam(3);
        when(daoDocumento.recuperaDocumentoByTeam(team.getIdTeam())).thenReturn(null);
        init();
        assertNull(formazioneManager.getMaterialeByIdTeam(team.getIdTeam()));
    }

    @Test //pass
    public void getMaterialeByIdTeamPass() throws SQLException {
        Team team = new Team("Progetto1", 3, "Team1", "testing", "html", 3);
        team.setIdTeam(3);
        Documento document = new Documento();
        when(daoDocumento.recuperaDocumentoByTeam(team.getIdTeam())).thenReturn(document);
        init();
        assertNotNull(formazioneManager.getMaterialeByIdTeam(team.getIdTeam()));
    }

    @Test
    public void recuperaSkillConIdDipFail() throws SQLException {
        ArrayList<Skill> skills = null;
        when(daoSkill.recuperoSkillsByIdDipendente(5)).thenReturn(skills);
        init();
        assertNull(formazioneManager.recuperoSkillConIdDipendente(5));
    }
    @Test
    public void recuperaSkillConIdDipPass() throws SQLException {
        ArrayList<Skill> skills = new ArrayList<>();
        when(daoSkill.recuperoSkillsByIdDipendente(5)).thenReturn(skills);
        init();
        assertNotNull(formazioneManager.recuperoSkillConIdDipendente(5));
    }


}
