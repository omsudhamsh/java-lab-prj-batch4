package service;

import model.Exam;
import util.InputUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ExamService {
    private static ArrayList<Exam> examList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void adminMenu() {
        int choice;
        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Exam");
            System.out.println("2. View All Exams");
            System.out.println("0. Logout");
            choice = InputUtil.getInt("Enter choice: ");

            switch (choice) {
                case 1 -> addExam();
                case 2 -> viewExams();
            }
        } while (choice != 0);
    }

    public static ArrayList<Exam> getExams() {
        return examList;
    }

    private void addExam() {
        int id = InputUtil.getInt("Enter Exam ID: ");
        String subject = InputUtil.getString("Enter Subject: ");
        String date = InputUtil.getString("Enter Date (dd-mm-yyyy): ");

        examList.add(new Exam(id, subject, date));
        System.out.println("✅ Exam Added.");
    }

    private void viewExams() {
        System.out.println("📘 Available Exams:");
        for (Exam e : examList) {
            System.out.println(e);
        }
    }
}
