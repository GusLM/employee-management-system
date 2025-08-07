package controller;

import model.entities.Employee;
import service.EmployeeService;

import java.util.List;
import java.util.Scanner;

public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeRegisterService) {
        this.employeeService = employeeRegisterService;
    }

    public void registerEmployee(
            String employeeName,
            String employeeCPF,
            String employeeMail,
            double baseSalary,
            int option
    ) {
        employeeService.registerEmployee(
                employeeName,
                employeeCPF,
                employeeMail,
                baseSalary,
                option
        );
    }

    public List<Employee> getEmployeeList() {
        return employeeService.getEmployeeList();
    }

    public void showEmployeeList() {
        employeeService.showEmployeeList();
    }

    public void EmployeeNetSalary(String identifier) {
        employeeService.employeeNetSalary(identifier);
    }

    public void generateMonthlyReport(String identifier) {
        employeeService.generateMonthlyReport(identifier);
    }

    public void showEmployeeData(String identifier) {
        employeeService.showEmployeeData(identifier);
    }

    public void editEmployee(String cpf, Scanner scanner) {
        employeeService.editEmployee(cpf, scanner);
    }

    public void removeEmployee(String cpf, Scanner scanner) {
        employeeService.removeEmployee(cpf, scanner);
    }

}
