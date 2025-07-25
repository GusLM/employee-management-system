package view;

import model.Developer;
import model.Intern;
import model.Manager;
import util.validators.IntegerInputValidator;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleUI {

   private final Scanner sc = new Scanner(System.in);

    public void run(){
        boolean running = true;

        while(running) {
            printMenu();
            int option = IntegerInputValidator.readValidInteger(sc, "\nSelect a valid option: ");
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
            case 1:
        }
    }

    private void registerEmployee() {
        System.out.println("-- REGISTER NEW EMPLOYEE --");
        System.out.print("Enter name: ");
        String employeeName = sc.nextLine();
        System.out.print("Enter CPF: ");
        String employeeCPF = sc.nextLine();
        System.out.print("Enter e-mail: ");
        String employeeMail = sc.next();
        System.out.print("Enter base salary: ");
        double baseSalary = sc.nextDouble();
        System.out.println(
                "Select role:\n" +
                "[1] Manager\n" +
                "[2] Developer\n" +
                "[3] Intern");
        int roleOption = IntegerInputValidator.readValidInteger(sc, "\nSelect a valid option: ");

        switch (roleOption) {
            case 1: new Manager(employeeName, employeeCPF, employeeMail, new BigDecimal(baseSalary));
            case 2: new Developer(employeeName, employeeCPF, employeeMail, new BigDecimal(baseSalary));
            case 3 : new Intern(employeeName, employeeCPF, employeeMail, new BigDecimal(baseSalary));
        }
    }
}
