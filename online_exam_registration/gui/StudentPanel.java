package gui;

import model.Exam;
import model.Student;
import service.ExamService;
import service.RegistrationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentPanel extends JPanel {
    private MainFrame mainFrame;
    private Student student;
    private RegistrationService registrationService;
    private JTable examTable;
    private DefaultTableModel examTableModel;
    private JTable registeredTable;
    private DefaultTableModel registeredTableModel;

    public StudentPanel(MainFrame mainFrame, Student student) {
        this.mainFrame = mainFrame;
        this.student = student;
        this.registrationService = new RegistrationService(student);
        
        setLayout(new BorderLayout());
        
        // Welcome label at top
        JPanel topPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome, " + student.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(welcomeLabel);
        add(topPanel, BorderLayout.NORTH);
        
        // Main content panel with tables
        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Available exams section
        JPanel availablePanel = new JPanel(new BorderLayout());
        availablePanel.setBorder(BorderFactory.createTitledBorder("Available Exams"));
        
        String[] examColumns = {"ID", "Subject", "Date"};
        examTableModel = new DefaultTableModel(examColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        examTable = new JTable(examTableModel);
        JScrollPane examScrollPane = new JScrollPane(examTable);
        availablePanel.add(examScrollPane, BorderLayout.CENTER);
        
        JButton registerButton = new JButton("Register for Selected Exam");
        registerButton.addActionListener(e -> registerForExam());
        JButton refreshExamsButton = new JButton("Refresh Exam List");
        refreshExamsButton.addActionListener(e -> refreshExamList());
        
        JPanel examButtonPanel = new JPanel();
        examButtonPanel.add(registerButton);
        examButtonPanel.add(refreshExamsButton);
        availablePanel.add(examButtonPanel, BorderLayout.SOUTH);
        
        contentPanel.add(availablePanel);
        
        // Registered exams section
        JPanel registeredPanel = new JPanel(new BorderLayout());
        registeredPanel.setBorder(BorderFactory.createTitledBorder("My Registered Exams"));
        
        String[] registeredColumns = {"ID", "Subject", "Date"};
        registeredTableModel = new DefaultTableModel(registeredColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        registeredTable = new JTable(registeredTableModel);
        JScrollPane registeredScrollPane = new JScrollPane(registeredTable);
        registeredPanel.add(registeredScrollPane, BorderLayout.CENTER);
        
        JButton viewButton = new JButton("Refresh My Exams");
        viewButton.addActionListener(e -> refreshRegisteredList());
        registeredPanel.add(viewButton, BorderLayout.SOUTH);
        
        contentPanel.add(registeredPanel);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Logout button at bottom
        JPanel bottomPanel = new JPanel();
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Initial data loading
        refreshExamList();
        refreshRegisteredList();
    }
    
    private void refreshExamList() {
        examTableModel.setRowCount(0);
        ArrayList<Exam> exams = ExamService.getExams();
        
        if (exams.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No exams available at the moment.",
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
    
    private void refreshRegisteredList() {
        registeredTableModel.setRowCount(0);
        // Call a new method in RegistrationService to get registered exams
        ArrayList<Exam> registeredExams = registrationService.getRegisteredExams();
        
        if (registeredExams.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "You have not registered for any exams yet.",
                "Information", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        for (Exam exam : registeredExams) {
            registeredTableModel.addRow(new Object[]{
                exam.getExamId(),
                exam.getSubject(),
                exam.getDate()
            });
        }
    }
    
    private void registerForExam() {
        int selectedRow = examTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an exam to register for.",
                "Registration Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int examId = (Integer) examTableModel.getValueAt(selectedRow, 0);
        
        // We'll add this method to RegistrationService
        boolean success = registrationService.registerForExamById(examId);
        
        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Successfully registered for the exam!",
                "Registration Successful", 
                JOptionPane.INFORMATION_MESSAGE);
            refreshRegisteredList();
        }
    }
    
    private void logout() {
        mainFrame.showLoginPanel();
    }
} 