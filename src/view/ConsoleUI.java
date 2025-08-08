package view;

import controller.EmployeeController;
import util.validators.IntegerInputValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI {

   private final Scanner sc = new Scanner(System.in);
   private final EmployeeController employeeController;

    public ConsoleUI(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

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
                    showEmployeeList();
                    break;
                case 3:
                    showEmployeeData();
                    break;
                case 4:
                    showEmployeeNetSalary();
                    break;
                case 5:
                    generateMonthlyReport();
                    break;
                case 6:
                    editEmployee();
                    break;
                case 7:
                    removeEmployee();
                    break;
                case 0 :
                    running = false;
                    break;
                default:
                    System.out.println("Try an valid option!");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("==============================\n" +
                " EMPLOYEE MANAGEMENT SYSTEM\n" +
                "==============================\n" +
                "[1] Register Employee\n" +
                "[2] List All Employees\n" +
                "[3] Search Employee by CPF\n" +
                "[4] Show Employee Net Salary\n" +
                "[5] Generate Monthly Report\n" +
                "[6] Edit Employee\n" +
                "[7] Remove Employee\n" +
                "[0] Exit\n" +
                "==============================");
    }

    private void registerEmployee() {
        try {
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

            employeeController.registerEmployee(employeeName, employeeCPF, employeeMail, baseSalary, roleOption);
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showEmployeeList() {
        System.out.println();
        employeeController.showEmployeeList();
    }

    private void showEmployeeData() {
        System.out.println();
        System.out.println("Enter the employee name or cpf: ");
        sc.nextLine();
        String identifier = sc.nextLine();
        System.out.println();
        employeeController.showEmployeeData(identifier);
    }

    private void showEmployeeNetSalary() {
        System.out.println();
        sc.nextLine();
        System.out.println("Enter the employee name or cpf: ");
        String identifier = sc.nextLine();
        System.out.println();
        employeeController.EmployeeNetSalary(identifier);
    }

    private void generateMonthlyReport() {
        System.out.println();
        System.out.println("Enter the employee cpf: ");
        String identifier = sc.next();
        System.out.println();
        employeeController.generateMonthlyReport(identifier);
    }

    private void editEmployee() {
        System.out.println();
        System.out.println("Enter the employee cpf: ");
        String cpf = sc.next();
        System.out.println();
        employeeController.editEmployee(cpf, sc);
    }

    private void removeEmployee() {
        System.out.println();
        System.out.println("Enter the employee cpf: ");
        String cpf = sc.next();
        employeeController.removeEmployee(cpf, sc);
    }
}
