package agency_formation.autenticazione.control;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.control.LoginControl;
import it.unisa.agency_formation.autenticazione.control.RegistrazioneControl;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrazioneControlTest extends Mockito {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private ServletContext context;
    private UtenteDAO dao;
    private AutenticazioneManagerImpl aut;

    @Before
    public void setup(){
         request = mock(HttpServletRequest.class);
         response = mock(HttpServletResponse.class);


    }


    @Test
    public void regTestServletPass(){
        when(request.getParameter("nome")).thenReturn("Alberto");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("email")).thenReturn("albe@gmail.com");
        when(request.getParameter("password")).thenReturn("lol");


    }
}
