package agency_formation.formazione.DAO;

import org.junit.jupiter.api.Test;

public class DocumentoDAOTest {


    @Test//not pass document == null
    public void saveDocument1(){

    }
    @Test//pass
    public void saveDocument2(){

    }
    @Test//not pass path == null
    public void removeDocument1(){

    }
    @Test//not pass, there is no document with this path
    public void removeDocument2(){

    }
    @Test//pass
    public void removeDocumen3(){

    }
    @Test//not pass, idHR<1
    public void updateDocument1(){

    }
    /*
    @Test//not pass, idHR doesn't exists
    public void updateDocument2(){

    }*/
    @Test//not pass, materiale == null
    public void updateDocument3(){

    }
    @Test//not pass, materiale == null
    public void updateDocument4(){

    }
    @Test//not pass, idTM<1
    public void updateDocument5(){

    }
    @Test//pass
    public void updateDocument6(){

    }
    @Test//not pass, idTM<1
    public void doRetrieveByTeam1(){

    }
    @Test//pass
    public void doRetrieveByTeam2(){

    }




}
