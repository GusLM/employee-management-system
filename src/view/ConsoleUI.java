package view;

import controller.EmployeeController;
import util.validators.IntegerInputValidator;

import java.util.Scanner;

public class ConsoleUI {

   private final Scanner sc = new Scanner(System.in);
   private EmployeeController employeeController;

    public void run(){
        boolean running = true;

        while(running) {
            printMenu();
            int option = IntegerInputValidator.readValidInteger(sc, "\nSelect a valid option: ");

            switch (option) {
                case 1:
                    registerEmployee();
                    break;
                case 2:
                    System.out.println("teste");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("==============================\n" +
                " EMPLOYEE MANAGEMENT SYSTEM\n" +
                "==============================\n" +
                "[1] Register Employee\n" +
                "[2] List All Employees\n" +
                "[3] Search Employee by CPF\n" +
                "[4] Calculate Net Salary\n" +
                "[5] Generate Monthly Report\n" +
                "[6] Edit Employee\n" +
                "[7] Remove Employee\n" +
                "[0] Exit\n" +
                "==============================");
    }

    private void menuOption(int option) {
        switch (option) {
            case 1: registerEmployee();
            case 2:
                System.out.println("nt");
        }
    }

    private void registerEmployee() {
        System.out.println("\n-- REGISTER NEW EMPLOYEE --");
        sc.nextLine();
        System.out.print("Enter name: ");
        String employeeName = sc.nextLine();
        System.out.print("Enter CPF: ");
        String employeeCPF = sc.nextLine();
        System.out.print("Enter e-mail: ");
        String employeeMail = sc.next();
        System.out.print("Enter base salary: ");
        double baseSalary = sc.nextDouble();
        System.out.println(
                """
                        Select role:
                        [1] Manager
                        [2] Developer
                        [3] Intern""");
        int roleOption = IntegerInputValidator.readValidInteger(sc, "\nSelect a valid option: ");

    }
}
