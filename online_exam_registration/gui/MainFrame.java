package gui;

import model.Admin;
import model.Student;
import model.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        // Set up the frame
        super("Online Exam Registration System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Use CardLayout for switching between different panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // Create and add the login panel
        mainPanel.add(createLoginPanel(), "login");
        
        // Show login by default
        cardLayout.show(mainPanel, "login");
        
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());
        
        // Title
        JLabel titleLabel = new JLabel("🎓 Online Exam Registration System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        
        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);
        
        // Email field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        emailField = new JTextField(20);
        formPanel.add(emailField, gbc);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        
        JButton studentButton = new JButton("Login as Student");
        studentButton.addActionListener(e -> login(1));
        buttonPanel.add(studentButton);
        
        JButton adminButton = new JButton("Login as Admin");
        adminButton.addActionListener(e -> login(2));
        buttonPanel.add(adminButton);
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);
        
        // Add form and buttons to login panel
        loginPanel.add(formPanel, BorderLayout.CENTER);
        loginPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return loginPanel;
    }
    
    private void login(int userType) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        
        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both name and email", 
                "Login Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        User user;
        if (userType == 1) {
            user = new Student(name, email);
            StudentPanel studentPanel = new StudentPanel(this, (Student) user);
            mainPanel.add(studentPanel, "student");
            cardLayout.show(mainPanel, "student");
        } else {
            user = new Admin(name, email);
            AdminPanel adminPanel = new AdminPanel(this, (Admin) user);
            mainPanel.add(adminPanel, "admin");
            cardLayout.show(mainPanel, "admin");
        }
    }
    
    public void showLoginPanel() {
        cardLayout.show(mainPanel, "login");
        nameField.setText("");
        emailField.setText("");
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
} 