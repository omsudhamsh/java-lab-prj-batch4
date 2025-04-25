import model.Admin;
import model.Student;
import model.User;
import util.InputUtil;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎓 Welcome to Online Exam Registration System");

        while (true) {
            System.out.println("\nLogin as:");
            System.out.println("1. Student");
            System.out.println("2. Admin");
            System.out.println("0. Exit");

            int choice = InputUtil.getInt("Enter choice: ");
            if (choice == 0) break;

            if (choice != 1 && choice != 2) {
                System.out.println("❌ Invalid choice. Please select 1, 2, or 0.");
                continue;
            }

            String name = InputUtil.getString("Enter Name: ");
            String email = InputUtil.getString("Enter Email: ");

            User user = switch (choice) {
                case 1 -> new Student(name, email);
                case 2 -> new Admin(name, email);
                default -> null;
            };

            if (user != null) {
                System.out.println("✅ Login successful!");
                user.showMenu();
            }
        }

        System.out.println("👋 Thank you for using the system.");
    }
}
