package model;

import service.RegistrationService;

public class Student extends User {
    private RegistrationService registrationService;

    public Student(String username, String email) {
        super(username, email);
        registrationService = new RegistrationService(this);
    }

    @Override
    public void showMenu() {
        registrationService.studentMenu();
    }
}
