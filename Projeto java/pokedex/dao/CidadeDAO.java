
package dao;

import db.Conexao;
import model.Cidade;
import model.Regiao;

import java.sql.*;
import java.util.*;

public class CidadeDAO {
    public List<Cidade> listarCidades() {
        List<Cidade> cidades = new ArrayList<>();
        String sql = """
            SELECT c.idCidade, c.locCentroPkm, c.loc_loja, c.locGinasio,
                   r.idRegião, r.idRegião AS idRegião
            FROM Cidade c
            JOIN Região r ON c.Região_idRegião = r.idRegião
        """;

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Regiao regiao = new Regiao(rs.getInt("idRegião"));
                Cidade cidade = new Cidade(
                        rs.getInt("idCidade"),
                        rs.getString("locCentroPkm"),
                        rs.getString("loc_loja"),
                        rs.getString("locGinasio"),
                        regiao
                );
                cidades.add(cidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cidades;
    }
}
