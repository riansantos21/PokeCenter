package View;

import Controller.TreinadorController;
import Model.Treinador;

import javax.swing.*;
import java.awt.*;

public class TreinadorForm extends JInternalFrame {

    private TreinadorController controller;
    private JTextField txtId, txtNome, txtCidade;
    private JButton btnSalvar, btnBuscar, btnRemover;
    private Integer treinadorIdParaEdicao;

    public TreinadorForm(TreinadorController controller, Integer treinadorId) {
        super("Cadastro de Treinador", true, true, true, true);
        this.controller = controller;
        this.treinadorIdParaEdicao = treinadorId;

        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false);
        add(txtId);

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);



        add(new JLabel("Cidade:"));
        txtCidade = new JTextField();
        add(txtCidade);

        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        btnBuscar = new JButton("Buscar");
        add(btnBuscar);

        btnRemover = new JButton("Remover");
        add(btnRemover);

        // --- Ações dos botões ---
        btnSalvar.addActionListener(e -> salvarTreinador());
        btnBuscar.addActionListener(e -> buscarTreinador());
        btnRemover.addActionListener(e -> removerTreinador());

        // Se estiver em modo edição, carregar os dados
        if (treinadorId != null) {
            carregarTreinador(treinadorId);
        }
    }

    private void salvarTreinador() {
        try {
            String nome = txtNome.getText();
            String cidade = txtCidade.getText();

            if (treinadorIdParaEdicao == null) {
                controller.cadastrarTreinador(nome, cidade);
                JOptionPane.showMessageDialog(this, "Treinador cadastrado com sucesso!");
            } else {
                controller.atualizarTreinador(treinadorIdParaEdicao, nome, cidade);
                JOptionPane.showMessageDialog(this, "Treinador atualizado com sucesso!");
            }

            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarTreinador() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Digite o ID do Treinador:");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Treinador t = controller.buscarPorId(id);

                if (t != null) {
                    txtId.setText(String.valueOf(t.getIdTreinador()));
                    txtNome.setText(t.getNomeTreinador());
                    txtCidade.setText(t.getCidade());
                    treinadorIdParaEdicao = t.getIdTreinador();
                } else {
                    JOptionPane.showMessageDialog(this, "Treinador não encontrado!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar Treinador: " + ex.getMessage());
        }
    }

    private void removerTreinador() {
        try {
            if (treinadorIdParaEdicao == null) {
                JOptionPane.showMessageDialog(this, "Nenhum treinador carregado para remover!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover este treinador?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                controller.removerTreinador(treinadorIdParaEdicao);
                JOptionPane.showMessageDialog(this, "Treinador removido com sucesso!");
                dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao remover Treinador: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarTreinador(int id) {
        Treinador t = controller.buscarPorId(id);
        if (t != null) {
            txtId.setText(String.valueOf(t.getIdTreinador()));
            txtNome.setText(t.getNomeTreinador());
            txtCidade.setText(t.getCidade());
            treinadorIdParaEdicao = t.getIdTreinador();
        }
    }
}
