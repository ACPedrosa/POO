package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaFilaEspera extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JPanel tabPanel;

    // Construtor da tela da recepcionista
    public TelaFilaEspera() {
        setTitle("Área do Recepcionista");
        setSize(1024, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Configurações do painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Bar de navegação
        JPanel navBar = new JPanel();
        navBar.setBackground(new Color(30, 60, 114)); // Azul escuro para destaque
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Label do título
        JLabel titleLabel = new JLabel("Recepcionista");
        titleLabel.setFont(titleLabel.getFont().deriveFont(24f));
        titleLabel.setForeground(Color.WHITE);
        navBar.add(titleLabel);
        navBar.add(Box.createHorizontalGlue());

        // Botão de Logout
        JButton sairButton = new JButton("Sair");
        sairButton.setBackground(new Color(255, 100, 100)); // Vermelho claro
        sairButton.setForeground(Color.WHITE);
        sairButton.setFocusPainted(false);
        sairButton.setPreferredSize(new Dimension(100, 40));
        sairButton.addActionListener(e -> new TelaFilaEspera());
        navBar.add(sairButton);

        // Painel de guias (somente Fila de Espera)
        tabPanel = new JPanel();
        tabPanel.setLayout(new BorderLayout());
        tabPanel.setBackground(Color.WHITE);

        // Adicionando o painel da Fila de Espera
        tabPanel.add(createFilaPanel(), BorderLayout.CENTER);

        // Adicionando componentes ao painel principal
        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(tabPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    // Painel relacionado à Fila de Espera
    private JPanel createFilaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel title = new JLabel("Fila de Espera");
        title.setFont(title.getFont().deriveFont(24f));
        title.setForeground(new Color(30, 60, 114));
        panel.add(title);

        // Botão para adicionar à fila
        JButton btnAddFila = new JButton("Adicionar à Fila");
        btnAddFila.setBackground(new Color(80, 180, 100)); // Verde suave
        btnAddFila.setForeground(Color.WHITE);
        btnAddFila.setFocusPainted(false);
        btnAddFila.setPreferredSize(new Dimension(150, 40));
        btnAddFila.addActionListener(e -> showModal("Fila"));
        panel.add(btnAddFila);

        // Tabela da Fila de Espera
        String[] columnNames = {"Horário Chegada", "Paciente", "Profissional", "Status", "Ações"};
        Object[][] data = {}; // Use dados reais ou um array de dados
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(950, 300));
        panel.add(scrollPane);

        return panel;
    }

    // Modal para adicionar à Fila de Espera
    private void showModal(String type) {
        JDialog dialog = new JDialog(this, type, true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Formulário para adicionar à fila
        if ("Fila".equals(type)) {
            contentPanel.add(new JLabel("Paciente:"));
            contentPanel.add(new JTextField());
            contentPanel.add(new JLabel("Profissional:"));
            String[] profissionais = {"Selecione o profissional", "Dr. João Silva", "Dra. Maria Santos"};
            contentPanel.add(new JComboBox<>(profissionais));
            contentPanel.add(new JLabel("Observação:"));
            contentPanel.add(new JTextArea());
        }

        dialog.add(contentPanel, BorderLayout.CENTER);
        
        // Botão de salvar
        JButton btnSave = new JButton("Salvar");
        btnSave.setBackground(new Color(100, 200, 100)); // Verde claro
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setPreferredSize(new Dimension(100, 40));
        btnSave.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Dados salvos com sucesso!");
            dialog.dispose();
        });
        dialog.add(btnSave, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Método principal para executar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaFilaEspera telaFilaEspera = new TelaFilaEspera();
            telaFilaEspera.setVisible(true);
        });
    }
}
