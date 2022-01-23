package it.unisa.agency_formation.formazione.DAO;

import it.unisa.agency_formation.formazione.domain.Documento;

import java.sql.SQLException;

public interface DocumentoDAO {
    boolean salvaDocumento(Documento doc) throws SQLException;

    Documento recuperaDocumentoByTeam(int idTeam) throws SQLException;
}
