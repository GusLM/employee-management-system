package service;

import model.entities.Developer;
import model.entities.Employee;
import model.entities.Intern;
import model.entities.Manager;
import model.enums.EmployeeRole;
import service.tax.IncomeTaxCalculator;
import service.tax.InssCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return Collections.unmodifiableList(employeeList);
    }

    public void showEmployeeList() {
        List<Employee> employees = getEmployeeList();
        if (employees.isEmpty()) {
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

    public List<Employee> findEmployeeByName(String employeeName) {
        return employeeList.stream().filter(emp -> emp.getName().equalsIgnoreCase(employeeName))
                .collect(Collectors.toList());
    }

    public Optional<Employee> findEmployeeByCpf(String employeeCPF) {
        return employeeList.stream().filter(emp -> emp.getCpf().equalsIgnoreCase(employeeCPF))
                .findFirst();
    }

    private void showEmployeeNetSalary(Employee emp) {
        System.out.println("Name: " + emp.getName());
        System.out.println("CPF: " + emp.getCpf());

        BigDecimal baseSalary = emp.getBaseSalary();
        BigDecimal netSalary = baseSalary
                .subtract(InssCalculator.calculateINSS(baseSalary))
                .subtract(IncomeTaxCalculator.calculateIRRF(baseSalary));

        System.out.println("Net salary = " + netSalary.setScale(2, RoundingMode.HALF_UP));
        System.out.println("-----------------------------");
    }

    public void employeeNetSalary(String identifier) {
        System.out.println("=== Employee Net Salary ===");

        // First try searching by CPF
        Optional<Employee> cpfMatch = findEmployeeByCpf(identifier);

        if (cpfMatch.isPresent()) {
            showEmployeeNetSalary(cpfMatch.get());
            return;
        }

        // Then try searching by name (may return several)
        List<Employee> nameMatches = findEmployeeByName(identifier);

        if (!nameMatches.isEmpty()) {
            nameMatches.forEach(this::showEmployeeNetSalary);
        } else {
            System.out.println("No employee found with that name or CPF.");
        }
    }
}
