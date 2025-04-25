package util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt(String msg) {
        System.out.print(msg);
        return scanner.nextInt();
    }

    public static String getString(String msg) {
        System.out.print(msg);
        return scanner.next();
    }
}
