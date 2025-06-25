
package dao;

import db.Conexao;
import model.Pokemon;
import model.Regiao;
import model.Treinador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinadorDAO {

    public Treinador buscarTreinadorPorId(int id) {
        String sql = "SELECT * FROM Treinador WHERE idTreinador = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Treinador(
                        rs.getInt("idTreinador"),
                        rs.getInt("nivel"),
                        rs.getInt("insignias"),
                        rs.getString("nome"),
                        rs.getInt("Pokedex_idPokedex")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pokemon> buscarPokemonsDoTreinador(int idTreinador) {
        List<Pokemon> pokemons = new ArrayList<>();
        String sql = """
            SELECT 
                p.idPokemon, p.nome, p.vida, p.physicalAtk, p.specialAtk, 
                p.physicalDef, p.specialDef, p.speed
            FROM pokemon p
            WHERE p.Treinador_idTreinador = ?
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreinador);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Treinador treinador = buscarTreinadorPorId(idTreinador); // ou passe como parâmetro

                Pokemon pokemon = new Pokemon(
                        rs.getInt("idPokemon"),
                        rs.getString("nome"),
                        rs.getInt("vida"),
                        rs.getInt("physicalAtk"),
                        rs.getInt("specialAtk"),
                        rs.getInt("physicalDef"),
                        rs.getInt("specialDef"),
                        rs.getInt("speed")
                );
                pokemons.add(pokemon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pokemons;
    }
}
