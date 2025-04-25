package util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt(String msg) {
        System.out.print(msg);
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                return value;
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    public static String getString(String msg) {
        System.out.print(msg);
        String input = scanner.next();
        scanner.nextLine(); // consume the newline
        return input;
    }
    
    public static String getLine(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }
}
