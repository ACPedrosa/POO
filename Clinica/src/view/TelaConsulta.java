package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.Consulta;
import controller.ProntuarioController;

public class TelaConsulta extends JFrame {
    private static final long serialVersionUID = 1L;
    private DefaultTableModel model;

    public TelaConsulta() {
        // Configurações da janela
        setTitle("Gerenciamento de Consultas");
        setSize(1024, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel superior (Barra de navegação)
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(new Color(47, 85, 151));
        navBar.setPreferredSize(new Dimension(0, 60));

        JLabel userInfoLabel = new JLabel("CONSULTAS", JLabel.LEFT);
        userInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userInfoLabel.setForeground(Color.WHITE);
        userInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

        JButton sairButton = new JButton("Sair");
        sairButton.setForeground(Color.WHITE);
        sairButton.setFont(new Font("Arial", Font.PLAIN, 14));
        sairButton.setBackground(new Color(220, 53, 69));
        sairButton.setFocusPainted(false);
        sairButton.setBorderPainted(false);
        sairButton.setPreferredSize(new Dimension(80, 40));
        //adicionar condicional para verificar o tipo de usuário
        sairButton.addActionListener(e -> new TelaProfissional());

        navBar.add(userInfoLabel, BorderLayout.WEST);
        navBar.add(sairButton, BorderLayout.EAST);
        add(navBar, BorderLayout.NORTH);

        // Painel central (Tabela e botões)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(240, 248, 255));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("Gerenciamento de Consultas", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(47, 85, 151));
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        // Definir as colunas da tabela para a consulta
        String[] colunas = {
            "ID Consulta", "ID Paciente", "ID Profissional", "Data Consulta", "Hora Consulta", "Status Consulta"
        };

        // Dados fictícios para a consulta
        String[][] consultasData = {
            {"101", "2001", "3001", "2024-11-25", "09:00", "AGENDADA"},
            {"102", "2002", "3002", "2024-11-26", "10:30", "CANCELADA"},
            {"103", "2003", "3003", "2024-11-27", "14:00", "CONCLUIDA"},
            {"104", "2004", "3004", "2024-11-28", "08:30", "AGENDADA"},
            {"105", "2005", "3005", "2024-11-29", "11:00", "CONCLUIDA"}
        };


        // Atualizar o modelo da tabela

        model = new DefaultTableModel(consultasData, colunas);

        JTable consultasTable = new JTable(model);
        consultasTable.setFont(new Font("Arial", Font.PLAIN, 14));
        consultasTable.setRowHeight(25);
        consultasTable.setSelectionBackground(new Color(184, 214, 247));
        consultasTable.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(consultasTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setBackground(new Color(240, 248, 255));

        JButton cadastrarButton = new JButton("Nova Consulta");
        JButton editarButton = new JButton("Editar Consulta");
        JButton deletarButton = new JButton("Deletar Consulta");

        configurarBotao(cadastrarButton, new Color(40, 167, 69), Color.WHITE, e -> JOptionPane.showMessageDialog(null, "Adicionar Nova Consulta."));
        configurarBotao(editarButton, new Color(255, 193, 7), Color.BLACK, e -> {
            int selectedRow = consultasTable.getSelectedRow();
            if (selectedRow == -1) {
                String id = (String) model.getValueAt(selectedRow, 0);
                JOptionPane.showMessageDialog(null, "Editar Consulta: ID - " + id);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma consulta para editar.");
            }
        });
        configurarBotao(deletarButton, new Color(220, 53, 69), Color.WHITE, e -> {
            int selectedRow = consultasTable.getSelectedRow();
            if (selectedRow == -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um prontuário para deletar.");
            }
        });

        /**
         * Adicionar os métodos com as ações de cada botão
         * ------------ Cadastro -------------
         * 
         */
        cadastrarButton.addActionListener(e -> {
            // Campos do formulário
            JTextField idPacienteField = new JTextField();
            JTextField idProfissionalField = new JTextField();
            JTextField dataConsultaField = new JTextField();  
            JTextField horaConsultaField = new JTextField();
            JComboBox<String> statusConsultaComboBox = new JComboBox<>(new String[]{"AGENDADA", "CANCELADA", "CONCLUIDA"}); // Status da consulta
            
            // Painel do formulário
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(6, 2, 10, 10)); 
            formPanel.add(new JLabel("ID do Paciente:"));
            formPanel.add(idPacienteField);
            formPanel.add(new JLabel("ID do Profissional:"));
            formPanel.add(idProfissionalField);
            formPanel.add(new JLabel("Data da Consulta (yyyy-MM-dd):"));
            formPanel.add(dataConsultaField);
            formPanel.add(new JLabel("Hora da Consulta (HH:mm):"));
            formPanel.add(horaConsultaField);
            formPanel.add(new JLabel("Status da Consulta:"));
            formPanel.add(statusConsultaComboBox);

            // Exibe o formulário em um JOptionPane
            int result = JOptionPane.showConfirmDialog(null, formPanel, "Cadastrar Consulta",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Captura os dados preenchidos
            if (result == JOptionPane.OK_OPTION) {
                try {
                    // Captura os valores dos campos
                    String dataConsulta = dataConsultaField.getText();
                    String horaConsulta = horaConsultaField.getText();
                    String statusConsulta = (String) statusConsultaComboBox.getSelectedItem();

                    // Cria uma nova consulta, add lógica do controller, no caso consulta
                    

                    model.addRow(new Object[]{
                            String.valueOf(model.getRowCount() + 1),
                            dataConsulta,
                            horaConsulta,
                            statusConsulta
                    });

                    JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar consulta: " + ex.getMessage());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        

        /*
         * --------- Editar Paciente ------------
         */
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = consultasTable.getSelectedRow(); 
                    if (selectedRow != -1) {
                        int idConsulta = (int) model.getValueAt(selectedRow, 0);
            
                        // Pegar os dados de acordo com a seleção feita
                        int idPacienteAtual = (int) model.getValueAt(selectedRow, 1);
                        int idProfissionalAtual = (int) model.getValueAt(selectedRow, 2);
                        String dataConsultaAtual = (String) model.getValueAt(selectedRow, 3);
                        String horaConsultaAtual = (String) model.getValueAt(selectedRow, 4);
                        String statusConsultaAtual = (String) model.getValueAt(selectedRow, 5);
            
                        // Para as novas informações
                        String novaDataConsulta = JOptionPane.showInputDialog("Informe a nova data da consulta (yyyy-MM-dd)", dataConsultaAtual);
                        String novaHoraConsulta = JOptionPane.showInputDialog("Informe a nova hora da consulta (HH:mm)", horaConsultaAtual);
                        String novoStatusConsulta = (String) JOptionPane.showInputDialog(null, "Selecione o novo status da consulta", "Status da Consulta",
                                JOptionPane.QUESTION_MESSAGE, null, new String[]{"AGENDADA", "CANCELADA", "CONCLUIDA"}, statusConsultaAtual);
            
                        // Add lógica do controller, no caso a lógica está no model mesmo
                        
            
                        // Atualizar a tabela com os novos valores
                        model.setValueAt(novaDataConsulta, selectedRow, 3);
                        model.setValueAt(novaHoraConsulta, selectedRow, 4);
                        model.setValueAt(novoStatusConsulta, selectedRow, 5);
            
                        JOptionPane.showMessageDialog(null, "Consulta alterada com sucesso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione uma consulta para editar.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });
        
        
        

        /*
         * --------- Deletar Paciente -----------
         */
        deletarButton.addActionListener(e -> {
            int selectedRow = consultasTable.getSelectedRow();
            if (selectedRow != -1) {
                String nomePaciente = (String) model.getValueAt(selectedRow, 1);  
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja deletar a consulta do paciente: " + nomePaciente + "?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
        
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        String idConsultaStr = (String) model.getValueAt(selectedRow, 0);
                        int idConsulta = Integer.parseInt(idConsultaStr);  // Converte para inteiro
        
                        // Chama o método deleteConsulta do controlador para excluir a consulta
                        Consulta consulta = new Consulta();
                        consulta.deleteConsulta(idConsulta);
        
                        // Após a exclusão, remove a linha da tabela
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Consulta excluída com sucesso!");
        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir consulta: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma consulta para deletar.");
            }
        });
        
        

        
        buttonsPanel.add(cadastrarButton);
        buttonsPanel.add(editarButton);
        buttonsPanel.add(deletarButton);

        centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void configurarBotao(JButton botao, Color corFundo, Color corTexto, ActionListener action) {
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(corFundo);
        botao.setForeground(corTexto);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.addActionListener(action);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaConsulta tela = new TelaConsulta();
            tela.setVisible(true);
        });
    }
}
