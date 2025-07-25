package util.validators;

import java.util.Scanner;

public class IntegerInputValidator {
    public static int readValidInteger(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("\nInvalid input. Please enter a valid integer.");
            sc.next(); // descarta entrada inválida
            System.out.print(message);
        }
        return sc.nextInt();
    }
}
