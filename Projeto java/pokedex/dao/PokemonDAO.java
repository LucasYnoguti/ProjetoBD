package dao;

import db.Conexao;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {

    // Buscar Pokémon completo, com movimentos
    public Pokemon buscarPokemonPorId(int idPokemon) {
        Pokemon pokemon = null;
        String sqlPokemon = "SELECT idPokemon, nome, vida, physicalAtk, physicalDef, specialAtk, specialDef, speed FROM pokemon WHERE idPokemon = ?";
        String sqlMovimentos = """
            SELECT m.idMovimento, m.dano, m.prioridade, m.tipo, m.efeitosExtra
            FROM pokemon_has_Movimento pm
            JOIN Movimento m ON pm.Movimento_idMovimento = m.idMovimento
            WHERE pm.pokemon_idPokemon = ?
            """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtPoke = conn.prepareStatement(sqlPokemon);
             PreparedStatement stmtMov = conn.prepareStatement(sqlMovimentos)) {

            stmtPoke.setInt(1, idPokemon);
            ResultSet rsPoke = stmtPoke.executeQuery();

            if (rsPoke.next()) {
                pokemon = new Pokemon(rsPoke.getInt("idPokemon"), rsPoke.getString("nome"), rsPoke.getInt("vida"), rsPoke.getInt("physicalAtk"), rsPoke.getInt("physicalDef"),
                        rsPoke.getInt("specialAtk"), rsPoke.getInt("specialDef"), rsPoke.getInt("speed"));

                // Buscar movimentos
                stmtMov.setInt(1, idPokemon);
                ResultSet rsMov = stmtMov.executeQuery();

                List<Movimento> movimentos = new ArrayList<>();
                while (rsMov.next()) {
                    movimentos.add(new Movimento(
                            rsMov.getInt("idMovimento"),
                            rsMov.getInt("dano"),
                            rsMov.getInt("prioridade"),
                            rsMov.getString("tipo"),
                            rsMov.getString("efeitosExtra")
                    ));
                }

                pokemon.setMovimentos(movimentos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemon;
    }

    // Adicionar movimento a um Pokémon
    public void adicionarMovimentoAoPokemon(int idPokemon, int idMovimento) {
        String sql = "INSERT INTO pokemon_has_Movimento (pokemon_idPokemon, Movimento_idMovimento) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPokemon);
            stmt.setInt(2, idMovimento);

            stmt.executeUpdate();
            System.out.println("Movimento adicionado ao Pokémon com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
