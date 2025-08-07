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

public class EmployeeService {

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

    public void editEmployee(String cpf, Scanner scanner) {
        Optional<Employee> match = findEmployeeByCpf(cpf);

        if (match.isEmpty()) {
            System.out.println("Employee with CPF " + cpf + " not found.");
            return;
        }

        Employee employee = match.get();

        System.out.println("Editing employee: " + employee.getName());

        // Name
        System.out.print("Enter new name (or press Enter to keep '" + employee.getName() + "'): ");
        String newName = scanner.nextLine();
        if (!newName.isBlank()) {
            employee.setName(newName);
        }

        // CPF
        System.out.print("Enter new cpf (or press Enter to keep '" + employee.getCpf() + "'): ");
        String newCpf = scanner.nextLine();
        if (!newCpf.isBlank()) {
            employee.setCpf(newCpf);
        }

        // Email
        System.out.print("Enter new email (or press Enter to keep '" + employee.getEmail() + "'): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isBlank()) {
            employee.setEmail(newEmail);
        }

        // Salary
        System.out.print("Enter new base salary (or press Enter to keep '" + employee.getBaseSalary() + "'): ");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isBlank()) {
            try {
                BigDecimal newSalary = new BigDecimal(salaryInput);
                employee.setBaseSalary(newSalary);
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary. Keeping current value.");
            }
        }

        // Role
        System.out.print("Enter the number for a new role (or type the number of '" + employee.getEmployeeRole() + "'): ");
        System.out.println(
                """
                        [1] - MANAGER\
                        [2] - DEVELOPER\
                        [3] - INTERN"""
        );

        int roleNumber = scanner.nextInt();
        try {
            switch (roleNumber) {
                case 1:
                    employee.setEmployeeRole(EmployeeRole.MANAGER);
                    break;
                case 2:
                    employee.setEmployeeRole(EmployeeRole.DEVELOPER);
                    break;
                case 3:
                    employee.setEmployeeRole(EmployeeRole.INTERN);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Performance
        System.out.print("Enter the number for a new Performance status (or type the number of '" + employee.getPerformanceRate() + "'): ");
        System.out.println(
                """
                        [1] - BAD\
                        [2] - REGULAR\
                        [3] - GOOD
                        [4] - GREAT
                        [5] - EXCELLENT"""
        );

        int performanceOption = scanner.nextInt();

        try {
            switch (performanceOption) {
                case 1:
                    employee.setPerformanceRate(PerformanceLevel.BAD);
                    break;
                case 2:
                    employee.setPerformanceRate(PerformanceLevel.REGULAR);
                    break;
                case 3:
                    employee.setPerformanceRate(PerformanceLevel.GOOD);
                    break;
                case 4:
                    employee.setPerformanceRate(PerformanceLevel.GREAT);
                    break;
                case 5:
                    employee.setPerformanceRate(PerformanceLevel.EXCELLENT);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("✅ Employee updated successfully.");
    }

    public void removeEmployee(String cpf, Scanner scanner) {
        Optional<Employee> cpfMatch = findEmployeeByCpf(cpf);

        if (cpfMatch.isEmpty()) {
            System.out.println("Employee with CPF " + cpf + " not found.");
            return;
        }

        Employee employee = cpfMatch.get();

        System.out.println("Name: " + cpfMatch.get().getName());
        System.out.println("CPF: " + cpfMatch.get().getCpf());
        System.out.println("Email: " + cpfMatch.get().getEmail());
        System.out.println("Base Salary: " + cpfMatch.get().getBaseSalary());
        System.out.println("Role: " + cpfMatch.get().getEmployeeRole());
        System.out.println("Performance Rate: " + cpfMatch.get().getPerformanceRate());
        System.out.println("----------------------------------");

        System.out.println();
        System.out.println("Are you sure you want to remove the employee?");

        String answer;

        do {
            System.out.println();
            System.out.println("[Type 's' for remove or 'n' to exit].");
            answer = scanner.next();
        } while (!answer.equalsIgnoreCase("s") && !answer.equalsIgnoreCase("n"));

        if (answer.equalsIgnoreCase("s")) {
            employeeList.remove(employee);
            System.out.println();
            System.out.println("✅ Employee successfully removed.");
        } else {
            System.out.println();
            System.out.println("\uD83C\uDFC3 Leaving the operation!");
        }
    }
}
