package service;

import model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRegisterService {

    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeRegisterService(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void registerEmployee(
            String employeeName,
            String employeeCPF,
            String employeeMail,
            double baseSalary,
            int option
    ) {
        switch (option) {
            case 1:
                Employee manager = new Manager(employeeName, employeeCPF, employeeMail, new BigDecimal(baseSalary));
                manager.setEmployeeRole(EmployeeRole.MANAGER);
                employeeList.add(manager);
                break;
            case 2:
                Employee developer = new Developer(employeeName, employeeCPF, employeeMail, new BigDecimal(baseSalary));
                developer.setEmployeeRole(EmployeeRole.DEVELOPER);
                employeeList.add(developer);
                break;
            case 3 :
                Employee intern = new Intern(employeeName, employeeCPF, employeeMail, new BigDecimal(baseSalary));
                intern.setEmployeeRole(EmployeeRole.INTERN);
                employeeList.add(intern);
                break;
            default:
                try {
                    throw new IllegalArgumentException("\nInvalid menu option selected.");
                } catch (IllegalArgumentException e) {
                    System.out.println("\nError: " + e.getMessage());
                }
        }
    }
}
