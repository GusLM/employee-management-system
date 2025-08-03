package service;

import model.entities.Developer;
import model.entities.Employee;
import model.entities.Intern;
import model.entities.Manager;
import model.enums.EmployeeRole;
import model.enums.PerformanceLevel;
import service.tax.IncomeTaxCalculator;
import service.tax.InssCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeRegisterService {

    private final List<Employee> employeeList = new ArrayList<>();

    private final LocalDate localDate = LocalDate.now();

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

    public void generateMonthlyReport(String identifier) {

        Optional<Employee> cpfMatch = findEmployeeByCpf(identifier);

        if (cpfMatch.isPresent()) {
            System.out.println("Name: " + cpfMatch.get().getName());
            System.out.println("CPF: " + cpfMatch.get().getCpf());
            System.out.println("Role: " + cpfMatch.get().getEmployeeRole());
            System.out.println("Net salary: ");
            showEmployeeNetSalary(cpfMatch.get());
            System.out.println("Report Date: " + localDate);
        } else {
            System.out.println("No employee found with that CPF.");
        }
    }

    public void showEmployeeData(String identifier) {

        Optional<Employee> cpfMatch = findEmployeeByCpf(identifier);

        if (cpfMatch.isPresent()) {
            System.out.println("Name: " + cpfMatch.get().getName());
            System.out.println("CPF: " + cpfMatch.get().getCpf());
            System.out.println("Email: " + cpfMatch.get().getEmail());
            System.out.println("Base Salary: " + cpfMatch.get().getBaseSalary());
            System.out.println("Role: " + cpfMatch.get().getEmployeeRole());
            System.out.println("Performance Rate: " + cpfMatch.get().getPerformanceRate());
            return;
        }

        List<Employee> nameMatches = findEmployeeByName(identifier);

        if (!nameMatches.isEmpty()) {
            for(Employee emp : nameMatches) {
                System.out.println("Name: " + emp.getName());
                System.out.println("CPF: " + emp.getCpf());
                System.out.println("Email: " + emp.getEmail());
                System.out.println("Base Salary: " + emp.getBaseSalary());
                System.out.println("Role: " + emp.getEmployeeRole());
                System.out.println("Performance Rate: " + emp.getPerformanceRate());
                System.out.println("___________________________________");
            }
        } else {
            System.out.println("No employee found with that name or CPF.");
        }
    }

    



}
