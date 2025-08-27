package Model.DAO;

import Conexao.ConexaoPostgresDB;
import Model.Treinador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinadorDao {

    // ----------- Inserir -----------
    public void inserirTreinador(Treinador treinador) throws SQLException {
        String sql = "INSERT INTO treinador (nome, cidade) VALUES (?, ?)";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, treinador.getNomeTreinador());
            ps.setString(2, treinador.getCidade());
            ps.executeUpdate();
        }
    }

    // ----------- Atualizar -----------
    public void atualizarTreinador(Treinador treinador) throws SQLException {
        String sql = "UPDATE treinador SET nome = ?, cidade = ? WHERE id_treinador = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, treinador.getNomeTreinador());
            ps.setString(2, treinador.getCidade());
            ps.setInt(3, treinador.getIdTreinador());
            ps.executeUpdate();
        }
    }

    // ----------- Remover -----------
    public void removerTreinador(int id) throws SQLException {
        String sql = "DELETE FROM treinador WHERE id_treinador = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // ----------- Buscar por ID -----------
    public Treinador buscarPorIdTreinador(int id) {
        String sql = "SELECT * FROM treinador WHERE id_treinador = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Treinador(
                            rs.getInt("id_treinador"),
                            rs.getString("nome"),
                            rs.getString("cidade")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ----------- Listar todos -----------
    public List<Treinador> listarTodosTreinadores() {
        List<Treinador> lista = new ArrayList<>();
        String sql = "SELECT * FROM treinador ORDER BY id_treinador";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Treinador(
                        rs.getInt("id_treinador"),
                        rs.getString("nome"),
                        rs.getString("cidade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ----------- Buscar por nome -----------
    public List<Treinador> buscarTreinadorPorNome(String nome) {
        List<Treinador> lista = new ArrayList<>();
        String sql = "SELECT * FROM treinador WHERE nome ILIKE ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Treinador(
                            rs.getInt("id_treinador"),
                            rs.getString("nome"),
                            rs.getString("cidade")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
