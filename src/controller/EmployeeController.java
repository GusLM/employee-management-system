package controller;

import model.entities.Employee;
import service.EmployeeRegisterService;
import service.EmployeeService;

import java.util.List;

public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRegisterService employeeRegisterService;

    public EmployeeController(EmployeeService employeeService, EmployeeRegisterService employeeRegisterService) {
        this.employeeService = employeeService;
        this.employeeRegisterService = employeeRegisterService;
    }

    public void registerEmployee(
            String employeeName,
            String employeeCPF,
            String employeeMail,
            double baseSalary,
            int option
    ) {
        employeeRegisterService.registerEmployee(
                employeeName,
                employeeCPF,
                employeeMail,
                baseSalary,
                option
        );
    }

    public void showEmployeeList() {
        employeeRegisterService.showEmployeeList();
    }
}
