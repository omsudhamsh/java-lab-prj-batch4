package model;

import service.ExamService;

public class Admin extends User {
    private ExamService examService;

    public Admin(String username, String email) {
        super(username, email);
        examService = new ExamService();
    }

    @Override
    public void showMenu() {
        examService.adminMenu();
    }
}
