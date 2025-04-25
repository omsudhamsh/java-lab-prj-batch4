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
            }
        } while (choice != 0);
    }

    private void viewExams() {
        for (Exam e : ExamService.getExams()) {
            System.out.println(e);
        }
    }

    private void registerExam() {
        int id = InputUtil.getInt("Enter Exam ID to Register: ");
        for (Exam e : ExamService.getExams()) {
            if (e.getExamId() == id) {
                registeredExams.add(e);
                System.out.println("✅ Registered for " + e.getSubject());
                return;
            }
        }
        System.out.println("❌ Exam ID not found.");
    }

    private void viewRegistered() {
        System.out.println("🗂️ Registered Exams:");
        for (Exam e : registeredExams) {
            System.out.println(e);
        }
    }
}
