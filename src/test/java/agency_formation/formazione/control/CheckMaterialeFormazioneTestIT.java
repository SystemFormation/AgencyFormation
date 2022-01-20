package agency_formation.formazione.control;

import it.unisa.agency_formation.utils.Const;
import it.unisa.agency_formation.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckMaterialeFormazioneTestIT {

    @BeforeAll
    public static void init() throws SQLException {
        Const.nomeDB = Const.NOME_DB_TEST;
        String insertTeam = "insert into team (idTeam,NomeProgetto,NumeroDipendenti,NomeTeam,Descrizione,Competenza,IdTM) values(200, 'ReqMem',8,'Inspiegabili','Non siamo eroi','HTML',3)";
        String insertDocumento="insert into documenti (IdDocumento,MaterialeDiFormazione,IdHR,IdTeam) values (1,'test',4,200)";
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(insertTeam);
        statement.executeUpdate(insertTeam);
        statement.executeUpdate(insertDocumento);
    }

    @AfterAll
    public static void finish() throws SQLException {
        String deleteDocumento = "delete from documenti where IdDocumento>=1";
        String deleteTeam = "delete from team where idTeam>1";
        Connection connection = DatabaseManager.getInstance().getConnection();

    }




}
