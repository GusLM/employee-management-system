package model;

import model.enums.EmployeeRole;
import model.enums.PerformanceLevel;

import java.math.BigDecimal;

public class EmployeesReport {

    private String name;
    private String cpf;
    private String email;
    private BigDecimal baseSalary;
    private EmployeeRole employeeRole;
    private PerformanceLevel performanceRate;

    public EmployeesReport(
            String name,
            String cpf,
            String email,
            BigDecimal baseSalary,
            EmployeeRole employeeRole,
            PerformanceLevel performanceLevel

    ) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.baseSalary = baseSalary;
        this.employeeRole = employeeRole;
        this.performanceRate = performanceLevel;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public PerformanceLevel getPerformanceRate() {
        return performanceRate;
    }
}
