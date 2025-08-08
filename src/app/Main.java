package app;

import controller.EmployeeController;
import model.entities.Employee;
import service.EmployeeService;
import view.ConsoleUI;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        List<Employee> employeeList = new ArrayList<>();
        EmployeeService employeeService = new EmployeeService(employeeList);
        EmployeeController employeeController = new EmployeeController(employeeService);
        ConsoleUI consoleUI = new ConsoleUI(employeeController);
        consoleUI.run();
    }
}
