package service;

import model.entities.Developer;
import model.entities.Employee;
import model.entities.Intern;
import model.entities.Manager;
import model.enums.EmployeeRole;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRegisterService {

    private List<Employee> employeeList = new ArrayList<>();

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

        System.out.println("\nEmployee registered successfully.");
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void showEmployeeList() {
        List<Employee> employees = getEmployeeList();
        if (employeeList.isEmpty()) {
            System.out.println("\nNo employees have been registered yet.");
        } else {
            System.out.println(
                    """
                            
                            ==============================
                              EMPLOYEES LIST
                            ==============================
                            """);
            for (int i=0; i<employeeList.size(); i++) {
                Employee employee = employeeList.get(i);
                System.out.printf("#%d %s - %s", i+1, employee.getName(), employee.getEmployeeRole());
            }
        }
    }
}
