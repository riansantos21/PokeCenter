package View;

import Controller.TreinadorController;
import Model.Treinador;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaTreinadorPanel extends JInternalFrame {

    private TreinadorController controller;
    private JTable tabelaTreinadores;
    private DefaultTableModel tableModel;
    private JButton btnAtualizar, btnRemover, btnBuscar, btnEditar, btnCadastrar;
    private JTextField txtBuscaNome;

    public ListaTreinadorPanel(TreinadorController controller) {
        super("Lista de Treinadores", true, true, true, true);
        this.controller = controller;

        setSize(800, 500);
        setLayout(new BorderLayout());

        // Colunas da tabela
        String[] colunas = {"ID", "Nome", "Cidade"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaTreinadores = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaTreinadores);
        add(scrollPane, BorderLayout.CENTER);

        // Estilo da tabela
        tabelaTreinadores.setBackground(new Color(240, 255, 240));
        tabelaTreinadores.setForeground(Color.BLACK);
        tabelaTreinadores.setRowHeight(25);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(100, 200, 150));
        headerRenderer.setForeground(Color.BLACK);
        for (int i = 0; i < tabelaTreinadores.getColumnCount(); i++) {
            tabelaTreinadores.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        // Painel de ações
        JPanel panelAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAcoes.setBackground(new Color(220, 255, 220));

        txtBuscaNome = new JTextField(20);
        btnBuscar = new JButton("Buscar por Nome");
        btnAtualizar = new JButton("Atualizar Tabela");
        btnRemover = new JButton("Remover Selecionado");
        btnEditar = new JButton("Editar Selecionado");
        btnCadastrar = new JButton("Cadastrar Novo");

        panelAcoes.add(new JLabel("Nome:"));
        panelAcoes.add(txtBuscaNome);
        panelAcoes.add(btnBuscar);
        panelAcoes.add(btnAtualizar);
        panelAcoes.add(btnEditar);
        panelAcoes.add(btnRemover);
        panelAcoes.add(btnCadastrar);

        add(panelAcoes, BorderLayout.NORTH);

        // Listeners
        btnAtualizar.addActionListener(e -> carregarTreinadoresNaTabela());
        btnRemover.addActionListener(e -> removerTreinadorSelecionado());
        btnBuscar.addActionListener(e -> buscarTreinadoresPorNome());
        btnEditar.addActionListener(e -> editarTreinadorSelecionado());
        btnCadastrar.addActionListener(e -> cadastrarNovoTreinador());

        carregarTreinadoresNaTabela();
    }

    private void carregarTreinadoresNaTabela() {
        tableModel.setRowCount(0);
        List<Treinador> treinadores = controller.listarTodosTreinadores();
        for (Treinador t : treinadores) {
            tableModel.addRow(new Object[]{
                    t.getIdTreinador(),
                    t.getNomeTreinador(),
                    t.getCidade()
            });
        }
    }

    private void removerTreinadorSelecionado() {
        int selectedRow = tabelaTreinadores.getSelectedRow();
        if (selectedRow >= 0) {
            int idTreinador = (int) tableModel.getValueAt(selectedRow, 0);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover o Treinador ID: " + idTreinador + "?",
                    "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    controller.removerTreinador(idTreinador);
                    JOptionPane.showMessageDialog(this, "Treinador removido com sucesso!");
                    carregarTreinadoresNaTabela();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Treinador: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Treinador para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void buscarTreinadoresPorNome() {
        String nomeBusca = txtBuscaNome.getText().trim();
        tableModel.setRowCount(0);

        List<Treinador> treinadores = controller.buscarTreinadorPorNome(nomeBusca);

        if (treinadores.isEmpty() && !nomeBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum treinador encontrado com o nome: '" + nomeBusca + "'", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }

        for (Treinador t : treinadores) {
            tableModel.addRow(new Object[]{
                    t.getIdTreinador(),
                    t.getNomeTreinador(),
                    t.getCidade()
            });
        }
    }

    private void editarTreinadorSelecionado() {
        int selectedRow = tabelaTreinadores.getSelectedRow();
        if (selectedRow >= 0) {
            int idTreinador = (int) tableModel.getValueAt(selectedRow, 0);

            TreinadorForm form = new TreinadorForm(controller, idTreinador);
            this.getDesktopPane().add(form);
            form.setVisible(true);
            form.toFront();

            form.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                    carregarTreinadoresNaTabela();
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Treinador para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cadastrarNovoTreinador() {
        TreinadorForm form = new TreinadorForm(controller, null);
        this.getDesktopPane().add(form);
        form.setVisible(true);
        form.toFront();

        form.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                carregarTreinadoresNaTabela();
            }
        });
    }
}