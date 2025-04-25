package service;

import model.Exam;
import model.Student;
import util.InputUtil;

import java.util.ArrayList;

public class RegistrationService {
    private Student student;
    private ArrayList<Exam> registeredExams = new ArrayList<>();

    public RegistrationService(Student student) {
        this.student = student;
    }

    public void studentMenu() {
        int choice;
        do {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View Available Exams");
            System.out.println("2. Register for Exam");
            System.out.println("3. View My Registered Exams");
            System.out.println("0. Logout");
            choice = InputUtil.getInt("Enter choice: ");

            switch (choice) {
                case 1 -> viewExams();
                case 2 -> registerExam();
                case 3 -> viewRegistered();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void viewExams() {
        ArrayList<Exam> exams = ExamService.getExams();
        if (exams.isEmpty()) {
            System.out.println("⚠️ No exams available at the moment.");
            return;
        }
        
        System.out.println("📘 Available Exams:");
        for (Exam e : exams) {
            System.out.println(e);
        }
    }

    private void registerExam() {
        ArrayList<Exam> availableExams = ExamService.getExams();
        if (availableExams.isEmpty()) {
            System.out.println("⚠️ No exams available for registration.");
            return;
        }
        
        int id = InputUtil.getInt("Enter Exam ID to Register: ");
        
        // Check if already registered
        for (Exam e : registeredExams) {
            if (e.getExamId() == id) {
                System.out.println("⚠️ You are already registered for this exam.");
                return;
            }
        }
        
        // Check if exam exists and register
        for (Exam e : availableExams) {
            if (e.getExamId() == id) {
                registeredExams.add(e);
                System.out.println("✅ Successfully registered for " + e.getSubject());
                return;
            }
        }
        System.out.println("❌ Exam ID not found.");
    }

    private void viewRegistered() {
        if (registeredExams.isEmpty()) {
            System.out.println("⚠️ You have not registered for any exams yet.");
            return;
        }
        
        System.out.println("🗂️ Your Registered Exams:");
        for (Exam e : registeredExams) {
            System.out.println(e);
        }
    }
    
    // New methods for GUI
    
    public ArrayList<Exam> getRegisteredExams() {
        return registeredExams;
    }
    
    public boolean registerForExamById(int examId) {
        ArrayList<Exam> availableExams = ExamService.getExams();
        
        // Check if already registered
        for (Exam e : registeredExams) {
            if (e.getExamId() == examId) {
                return false; // Already registered
            }
        }
        
        // Find and register exam
        for (Exam exam : availableExams) {
            if (exam.getExamId() == examId) {
                registeredExams.add(exam);
                return true; // Successfully registered
            }
        }
        
        return false; // Exam not found
    }
}
