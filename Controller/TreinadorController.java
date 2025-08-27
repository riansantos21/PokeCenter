package Controller;

import Model.DAO.TreinadorDao;
import Model.Treinador;

import java.sql.SQLException;
import java.util.List;

public class TreinadorController {

    private TreinadorDao treinadorDao;

    public TreinadorController() {
        this.treinadorDao = new TreinadorDao();
    }

    public void cadastrarTreinador(String nome, String cidade) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("Nome é Obrigatório.");
        }
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new Exception("Cidade é Obrigatória.");
        }

        Treinador treinador = new Treinador(nome, cidade);
        try {
            treinadorDao.inserirTreinador(treinador);
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar Treinador: " + e.getMessage(), e);
        }
    }



    public void atualizarTreinador(Treinador treinador) throws Exception {
        if (treinador.getNomeTreinador() == null || treinador.getNomeTreinador().trim().isEmpty()) {
            throw new Exception("O nome é Obrigatório.");
        }
        if (treinador.getCidade() == null || treinador.getCidade().trim().isEmpty()) {
            throw new Exception("A cidade é Obrigatória.");
        }

        try {
            treinadorDao.atualizarTreinador(treinador);
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar Treinador: " + e.getMessage(), e);
        }
    }


    public void atualizarTreinador(int id, String nome, String cidade) throws Exception {
        Treinador treinador = new Treinador(id, nome, cidade);
        atualizarTreinador(treinador);
    }

    public void removerTreinador(int id) throws Exception {
        try {
            treinadorDao.removerTreinador(id);
        } catch (SQLException e) {
            throw new Exception("Erro ao remover Treinador: " + e.getMessage(), e);
        }
    }


    public List<Treinador> buscarTreinadorPorNome(String nome) {
        return treinadorDao.buscarTreinadorPorNome(nome);
    }

    public List<Treinador> listarTodosTreinadores() {
        return treinadorDao.listarTodosTreinadores();
    }

    public Treinador buscarPorId(int id) {
        return treinadorDao.buscarPorIdTreinador(id);
    }
}