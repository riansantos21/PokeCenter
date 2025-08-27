package Model.DAO;

import Conexao.ConexaoPostgresDB;
import Model.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDao {

    // Create
    public void inserir(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO pokemon (nome, tipo_primario, tipo_secundario, nivel, hp_maximo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pokemon.getNome());
            ps.setString(2, pokemon.getTipoPrimario());
            ps.setString(3, pokemon.getTipoSecundario());
            ps.setInt(4, pokemon.getNivel());
            ps.setInt(5, pokemon.getHpMaximo());
            ps.executeUpdate();
        }
    }

    // Update
    public void atualizar(Pokemon pokemon) throws SQLException {
        String sql = "UPDATE pokemon SET nome=?, tipo_primario=?, tipo_secundario=?, nivel=?, hp_maximo=? WHERE id=?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pokemon.getNome());
            ps.setString(2, pokemon.getTipoPrimario());
            ps.setString(3, pokemon.getTipoSecundario());
            ps.setInt(4, pokemon.getNivel());
            ps.setInt(5, pokemon.getHpMaximo());
            ps.setInt(6, pokemon.getId());
            ps.executeUpdate();
        }
    }

    // Delete
    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM pokemon WHERE id=?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Read by ID
    public Pokemon buscarPorId(int id) {
        String sql = "SELECT * FROM pokemon WHERE id=?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Pokemon(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo_primario"),
                        rs.getString("tipo_secundario"),
                        rs.getInt("nivel"),
                        rs.getInt("hp_maximo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // List all
    public List<Pokemon> listarTodos() {
        List<Pokemon> lista = new ArrayList<>();
        String sql = "SELECT * FROM pokemon";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Pokemon(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo_primario"),
                        rs.getString("tipo_secundario"),
                        rs.getInt("nivel"),
                        rs.getInt("hp_maximo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Search by name
    public List<Pokemon> buscarPorNome(String nome) {
        List<Pokemon> lista = new ArrayList<>();
        String sql = "SELECT * FROM pokemon WHERE nome ILIKE ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Pokemon(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo_primario"),
                        rs.getString("tipo_secundario"),
                        rs.getInt("nivel"),
                        rs.getInt("hp_maximo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Batch insert
    public static void inserirPokemonsEmLote(List<Pokemon> pokemons) throws SQLException {
        String sql = "INSERT INTO pokemon (nome, tipo_primario, tipo_secundario, nivel, hp_maximo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Pokemon p : pokemons) {
                ps.setString(1, p.getNome());
                ps.setString(2, p.getTipoPrimario());
                ps.setString(3, p.getTipoSecundario());
                ps.setInt(4, p.getNivel());
                ps.setInt(5, p.getHpMaximo());
                ps.addBatch();
            }

            ps.executeBatch(); // executa todas as inserções de uma vez
        }
    }
}
