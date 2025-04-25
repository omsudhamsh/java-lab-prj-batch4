package service;

import model.Exam;
import util.InputUtil;

import java.util.ArrayList;

public class ExamService {
    private static ArrayList<Exam> examList = new ArrayList<>();

    public void adminMenu() {
        int choice;
        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Exam");
            System.out.println("2. View All Exams");
            System.out.println("3. Remove Exam");
            System.out.println("0. Logout");
            choice = InputUtil.getInt("Enter choice: ");

            switch (choice) {
                case 1 -> addExam();
                case 2 -> viewExams();
                case 3 -> removeExam();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static ArrayList<Exam> getExams() {
        return examList;
    }

    private void addExam() {
        int id = InputUtil.getInt("Enter Exam ID: ");
        
        // Check if ID already exists
        for (Exam e : examList) {
            if (e.getExamId() == id) {
                System.out.println("❌ Exam ID already exists. Please use a different ID.");
                return;
            }
        }
        
        String subject = InputUtil.getString("Enter Subject: ");
        String date = InputUtil.getString("Enter Date (dd-mm-yyyy): ");

        examList.add(new Exam(id, subject, date));
        System.out.println("✅ Exam Added Successfully.");
    }

    private void viewExams() {
        if (examList.isEmpty()) {
            System.out.println("⚠️ No exams have been added yet.");
            return;
        }
        
        System.out.println("📘 Available Exams:");
        for (Exam e : examList) {
            System.out.println(e);
        }
    }
    
    private void removeExam() {
        if (examList.isEmpty()) {
            System.out.println("⚠️ No exams to remove.");
            return;
        }
        
        viewExams();
        int id = InputUtil.getInt("Enter Exam ID to remove: ");
        
        for (int i = 0; i < examList.size(); i++) {
            if (examList.get(i).getExamId() == id) {
                Exam removed = examList.remove(i);
                System.out.println("✅ Exam '" + removed.getSubject() + "' removed successfully.");
                return;
            }
        }
        
        System.out.println("❌ Exam ID not found.");
    }
}
