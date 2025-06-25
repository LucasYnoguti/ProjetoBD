
package dao;

import db.Conexao;
import model.*;

import java.sql.*;
import java.util.*;

public class RotaDAO {
    public List<Rota> listarRotas() {
        List<Rota> rotas = new ArrayList<>();
        String sql = "SELECT r.idRota, r.nomeRota, r.bioma, r.clima," +
                " c1.idCidade AS idInicio, c2.idCidade AS idDestino" +
                " FROM Rota r" +
                " JOIN Cidade c1 ON r.Cidade_idCidadeInicio = c1.idCidade" +
                " JOIN Cidade c2 ON r.Cidade_idCidadeDestino = c2.idCidade";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cidade inicio = new Cidade(rs.getInt("idInicio"), "", "", "", null);
                Cidade destino = new Cidade(rs.getInt("idDestino"), "", "", "", null);

                Rota rota = new Rota(
                        rs.getInt("idRota"),
                        rs.getString("nomeRota"),
                        rs.getString("bioma"),
                        rs.getString("clima"),
                        inicio,
                        destino
                );
                rotas.add(rota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rotas;
    }

    public List<Pokemon> listarPokemonsDaRota(int idRota) {
        List<Pokemon> pokemons = new ArrayList<>();
        String sql = "SELECT p.idPokemon, p.nome, p.vida, p.physicalAtk, p.physicalDef, p.specialAtk, p.specialDef, p.speed FROM Rota_has_pokemon rp" +
                     " JOIN pokemon p ON rp.pokemon_idPokemon = p.idPokemon" +
                     " WHERE rp.Rota_idRota = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRota);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pokemons.add(new Pokemon(rs.getInt("idPokemon"), rs.getString("nome"), rs.getInt("vida"), rs.getInt("physicalAtk"), rs.getInt("physicalDef"), rs.getInt("specialAtk"), rs.getInt("specialDef"), rs.getInt("speed")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemons;
    }
}
