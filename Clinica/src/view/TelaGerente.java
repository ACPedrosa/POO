package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controller.FuncionarioController;
import model.Pessoa;

public class TelaGerente extends JFrame {
    private static final long serialVersionUID = 1L;

    public TelaGerente() {
        // Configurações da janela
        setTitle("Gerenciamento de Funcionários");
        setSize(1024, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Barra de navegação
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(new Color(47, 85, 151));
        navBar.setPreferredSize(new Dimension(0, 60));

        JLabel navTitle = new JLabel("GERENTE - FUNCIONÁRIOS", JLabel.LEFT);
        navTitle.setFont(new Font("Arial", Font.BOLD, 16));
        navTitle.setForeground(Color.WHITE);
        navTitle.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

        JButton sairButton = new JButton("Sair");
        sairButton.setForeground(Color.WHITE);
        sairButton.setFont(new Font("Arial", Font.PLAIN, 14));
        sairButton.setBackground(new Color(220, 53, 69));
        sairButton.setFocusPainted(false);
        sairButton.setBorderPainted(false);
        sairButton.setPreferredSize(new Dimension(80, 40));
        sairButton.addActionListener(e -> dispose());

        navBar.add(navTitle, BorderLayout.WEST);
        navBar.add(sairButton, BorderLayout.EAST);
        add(navBar, BorderLayout.NORTH);

        // Painel central
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(240, 248, 255));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Gerenciamento de Funcionários", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(47, 85, 151));
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        // Tabela de dados
        String[] colunas = {"ID", "Nome", "Cargo"};
        String[][] funcionariosData = {
            {"1", "João Silva", "Recepcionista"},
            {"2", "Maria Oliveira", "Profissional"},
            {"3", "Carlos Lima", "Profissional"}
        };

        DefaultTableModel model = new DefaultTableModel(funcionariosData, colunas);
        JTable tabela = new JTable(model);
        tabela.setFont(new Font("Arial", Font.PLAIN, 14));
        tabela.setRowHeight(25);
        tabela.setSelectionBackground(new Color(184, 214, 247));
        tabela.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(tabela);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setBackground(new Color(240, 248, 255));

        JButton btnCadastrar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        configurarBotao(btnCadastrar, new Color(40, 167, 69), Color.WHITE);
        configurarBotao(btnEditar, new Color(255, 193, 7), Color.BLACK);
        configurarBotao(btnExcluir, new Color(220, 53, 69), Color.WHITE);

        buttonsPanel.add(btnCadastrar);
        buttonsPanel.add(btnEditar);
        buttonsPanel.add(btnExcluir);

        centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        /**
         * Adicionar os métodos com as ações de cada botão
         * ------------ Cadastro -------------
         *
         */
        btnCadastrar.addActionListener(e -> {
            // Campos do formulário
            JTextField nomeField = new JTextField();
            JTextField telefoneField = new JTextField();
            JTextField rgField = new JTextField();
            JTextField cpfField = new JTextField();
            JTextField dataNascimentoField = new JTextField();
            JTextField sexoField = new JTextField();
            JTextField profissaoField = new JTextField();
            JTextField enderecoField = new JTextField();
            JTextField loginField = new JTextField();
            JPasswordField senhaField = new JPasswordField();
            JComboBox<String> cargoComboBox = new JComboBox<>(new String[] { "Recepcionista", "Profissional" });
            JTextField especialidadeField = new JTextField();
            JTextField registroConselhoField = new JTextField();
            JTextField dataInscricaoField = new JTextField();
        
            // Painel do formulário
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(14, 2, 10, 10)); // Ajustado para 14 linhas e 2 colunas, com espaçamento
            formPanel.add(new JLabel("Nome:"));
            formPanel.add(nomeField);
            formPanel.add(new JLabel("Telefone:"));
            formPanel.add(telefoneField);
            formPanel.add(new JLabel("RG:"));
            formPanel.add(rgField);
            formPanel.add(new JLabel("CPF:"));
            formPanel.add(cpfField);
            formPanel.add(new JLabel("Data de Nascimento (yyyy-MM-dd):"));
            formPanel.add(dataNascimentoField);
            formPanel.add(new JLabel("Sexo:"));
            formPanel.add(sexoField);
            formPanel.add(new JLabel("Profissão:"));
            formPanel.add(profissaoField);
            formPanel.add(new JLabel("Endereço:"));
            formPanel.add(enderecoField);
            formPanel.add(new JLabel("Login:"));
            formPanel.add(loginField);
            formPanel.add(new JLabel("Senha:"));
            formPanel.add(senhaField);
            formPanel.add(new JLabel("Cargo:"));
            formPanel.add(cargoComboBox);
        
            // Campos adicionais para "Profissional"
            formPanel.add(new JLabel("Especialidade:"));
            formPanel.add(especialidadeField);
            formPanel.add(new JLabel("Registro no Conselho:"));
            formPanel.add(registroConselhoField);
            formPanel.add(new JLabel("Data de Inscrição:"));
            formPanel.add(dataInscricaoField);
        
            // Inicialmente, os campos adicionais para "Profissional" ficam invisíveis
            especialidadeField.setVisible(false);
            registroConselhoField.setVisible(false);
            dataInscricaoField.setVisible(false);
        
            // Adiciona um ouvinte para o JComboBox (quando mudar a seleção do cargo)
            cargoComboBox.addActionListener(event -> {
                String cargoSelecionado = (String) cargoComboBox.getSelectedItem();
                if ("Profissional".equals(cargoSelecionado)) {
                    especialidadeField.setVisible(true);
                    registroConselhoField.setVisible(true);
                    dataInscricaoField.setVisible(true);
                } else {
                    especialidadeField.setVisible(false);
                    registroConselhoField.setVisible(false);
                    dataInscricaoField.setVisible(false);
                }
                formPanel.revalidate(); // Revalida o painel para aplicar a visibilidade correta dos campos
            });
        
            // Exibe o formulário em um JOptionPane
            int result = JOptionPane.showConfirmDialog(null, formPanel, "Cadastrar Funcionário",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
            if (result == JOptionPane.OK_OPTION) {
                try {
                    // Captura os valores dos campos
                    String nome = nomeField.getText();
                    String telefone = telefoneField.getText();
                    String rg = rgField.getText();
                    String cpf = cpfField.getText();
                    String dataNascimento = dataNascimentoField.getText();
                    String sexo = sexoField.getText();
                    String profissao = profissaoField.getText();
                    String endereco = enderecoField.getText();
                    String login = loginField.getText();
                    String senha = new String(senhaField.getPassword());
                    String cargo = (String) cargoComboBox.getSelectedItem();
                    String especialidade = especialidadeField.getText();
                    String registroConselho = registroConselhoField.getText();
                    String dataInscricao = dataInscricaoField.getText();
        
                    // Verifique se todos os campos obrigatórios foram preenchidos
                    if (nome.isEmpty() || telefone.isEmpty() || rg.isEmpty() || cpf.isEmpty() || dataNascimento.isEmpty() ||
                            sexo.isEmpty() || profissao.isEmpty() || endereco.isEmpty() || login.isEmpty() || senha.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios devem ser preenchidos.");
                        return;
                    }
        
                    // Criar a instância de Pessoa e Funcionario
                    Pessoa pessoa = new Pessoa(nome, telefone, rg, cpf, Date.valueOf(dataNascimento), sexo, profissao, endereco);
                    FuncionarioController controller = new FuncionarioController();
                    controller.createFuncionario(pessoa, login, senha, cargo);
        
                    // Mensagem de sucesso
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + ex.getMessage());
                }
            }
        });


        btnEditar.addActionListener(e -> {
            int selectedRow = funcionariosData.length;
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null, "Selecione um funcionário para editar!");
                return;
            }
        
            // Obtém os dados da linha selecionada
            String loginAtual = model.getValueAt(selectedRow, 8).toString();
            String cargoAtual = model.getValueAt(selectedRow, 9).toString();
        
            // Campos do formulário
            JTextField loginField = new JTextField(loginAtual);
            JPasswordField senhaField = new JPasswordField(); 
            JTextField cargoField = new JTextField(cargoAtual);
        
            // Painel do formulário
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(4, 2, 10, 10));
            formPanel.add(new JLabel("Login:"));
            formPanel.add(loginField);
            formPanel.add(new JLabel("Senha (nova):"));
            formPanel.add(senhaField);
            formPanel.add(new JLabel("Cargo:"));
            formPanel.add(cargoField);
        
            // Exibe o formulário em um JOptionPane
            int result = JOptionPane.showConfirmDialog(null, formPanel, "Editar Funcionário",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
            // Captura os dados preenchidos
            if (result == JOptionPane.OK_OPTION) {
                try {
                    // Captura os valores dos campos
                    String login = loginField.getText();
                    String senha = new String(senhaField.getPassword());
                    String cargo = cargoField.getText();
        
                    if (login.isEmpty() || senha.isEmpty() || cargo.isEmpty()) {
                        throw new Exception("Preencha todos os campos obrigatórios!");
                    }
        
                    // Atualiza os dados no controlador
                    FuncionarioController controller = new FuncionarioController();
                    controller.updateFuncionario(
                        Integer.parseInt(model.getValueAt(selectedRow, 10).toString()), 
                        login, senha, cargo
                    );
        
                    // Atualiza os dados na tabela
                    model.setValueAt(login, selectedRow, 8);
                    model.setValueAt(cargo, selectedRow, 9);
        
                    JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
        
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar os dados: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao editar funcionário: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        
        btnExcluir.addActionListener(e -> {
            // Verifica se há uma linha selecionada na tabela
            int selectedRow = funcionariosData.length;
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null, "Selecione um funcionário para excluir!");
                return;
            }
        
            int idFuncionario = Integer.parseInt(model.getValueAt(selectedRow, 10).toString());
        
            // Confirmação da exclusão
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Tem certeza que deseja excluir este funcionário?",
                    "Confirmação de Exclusão",
                    JOptionPane.YES_NO_OPTION);
        
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // Chama o controlador para excluir o funcionário
                    FuncionarioController controller = new FuncionarioController();
                    controller.deleteFuncionario(idFuncionario);
        
                    // Remove a linha da tabela
                    model.removeRow(selectedRow);
        
                    JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    private void configurarBotao(JButton botao, Color corFundo, Color corTexto) {
        botao.setBackground(corFundo);
        botao.setForeground(corTexto);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(150, 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaGerente().setVisible(true));
    }
}
