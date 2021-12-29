package agency_formation.autenticazione.control;
import it.unisa.agency_formation.autenticazione.control.RegistrazioneControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class RegistrazioneControlTest extends Mockito {
    private HttpServletRequest request;
    private HttpServletResponse response;


    @Test
    public void regTestServletPass() throws IOException, ServletException {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        when(request.getParameter("nome")).thenReturn(null);
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("email")).thenReturn("albe@gmail.com");
        when(request.getParameter("password")).thenReturn("lol");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new RegistrazioneControl().doPost(request,response);
        Assertions.assertTrue(stringWriter.toString().contains("1"));
    }
}
