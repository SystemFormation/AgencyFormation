package agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.control.AcceptCandidatureControl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DownloadMaterialeControlTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @Test //mi aspetto 1
    public void userNull(){
        Utente user = new Utente("Gianpaolo", "Laurenzano", "m.nocerino@studenti.unisa.it", "lol" , RuoliUtenti.HR);
        user.setId(1);
        ServletConfig config = Mockito.mock(ServletConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        AcceptCandidatureControl servlet = new AcceptCandidatureControl();
    }

    @Test //mi aspetto 2
    public void dipendenteNull(){

    }

    @Test //mi aspetto 3
    public void documentoNull(){

    }
    @Test //mi aspetto 4
    public void downloadDocumentoPass(){

    }

    @Test //mi aspetto 5
    public void downloadDocumentoFail(){

    }

}
