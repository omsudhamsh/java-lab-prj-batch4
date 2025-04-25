package gui;

import model.Admin;
import model.Exam;
import service.ExamService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminPanel extends JPanel {
    private MainFrame mainFrame;
    private Admin admin;
    private ExamService examService;
    private JTable examTable;
    private DefaultTableModel examTableModel;
    private JTextField idField;
    private JTextField subjectField;
    private JTextField dateField;

    public AdminPanel(MainFrame mainFrame, Admin admin) {
        this.mainFrame = mainFrame;
        this.admin = admin;
        this.examService = new ExamService();
        
        setLayout(new BorderLayout());
        
        // Welcome label at top
        JPanel topPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome, Admin " + admin.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(welcomeLabel);
        add(topPanel, BorderLayout.NORTH);
        
        // Main panel with two sections: exam list and add exam form
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Exam list section
        JPanel examListPanel = new JPanel(new BorderLayout());
        examListPanel.setBorder(BorderFactory.createTitledBorder("Available Exams"));
        
        String[] examColumns = {"ID", "Subject", "Date"};
        examTableModel = new DefaultTableModel(examColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        examTable = new JTable(examTableModel);
        JScrollPane examScrollPane = new JScrollPane(examTable);
        examListPanel.add(examScrollPane, BorderLayout.CENTER);
        
        JPanel examButtonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshExamList());
        examButtonPanel.add(refreshButton);
        
        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(e -> removeExam());
        examButtonPanel.add(removeButton);
        
        examListPanel.add(examButtonPanel, BorderLayout.SOUTH);
        mainPanel.add(examListPanel);
        
        // Add exam form section
        JPanel addExamPanel = new JPanel(new BorderLayout());
        addExamPanel.setBorder(BorderFactory.createTitledBorder("Add New Exam"));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // ID field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Exam ID:"), gbc);
        
        gbc.gridx = 1;
        idField = new JTextField(10);
        formPanel.add(idField, gbc);
        
        // Subject field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Subject:"), gbc);
        
        gbc.gridx = 1;
        subjectField = new JTextField(10);
        formPanel.add(subjectField, gbc);
        
        // Date field
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Date (dd-mm-yyyy):"), gbc);
        
        gbc.gridx = 1;
        dateField = new JTextField(10);
        formPanel.add(dateField, gbc);
        
        JButton addButton = new JButton("Add Exam");
        addButton.addActionListener(e -> addExam());
        
        addExamPanel.add(formPanel, BorderLayout.CENTER);
        addExamPanel.add(addButton, BorderLayout.SOUTH);
        
        mainPanel.add(addExamPanel);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Logout button
        JPanel bottomPanel = new JPanel();
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Initial data loading
        refreshExamList();
    }
    
    private void refreshExamList() {
        examTableModel.setRowCount(0);
        ArrayList<Exam> exams = ExamService.getExams();
        
        if (exams.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No exams have been added yet.",
                "Information", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        for (Exam exam : exams) {
            examTableModel.addRow(new Object[]{
                exam.getExamId(),
                exam.getSubject(),
                exam.getDate()
            });
        }
    }
    
    private void addExam() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String subject = subjectField.getText().trim();
            String date = dateField.getText().trim();
            
            if (subject.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all fields.",
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // New method in ExamService
            boolean success = examService.addExam(id, subject, date);
            
            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Exam added successfully!",
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                refreshExamList();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Exam ID already exists!",
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid numeric ID.",
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removeExam() {
        int selectedRow = examTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an exam to remove.",
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int examId = (Integer) examTableModel.getValueAt(selectedRow, 0);
        
        // Add this method to ExamService
        boolean success = examService.removeExam(examId);
        
        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Exam removed successfully!",
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            refreshExamList();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Failed to remove exam.",
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        idField.setText("");
        subjectField.setText("");
        dateField.setText("");
    }
    
    private void logout() {
        mainFrame.showLoginPanel();
    }
} 