package model.entities;

import model.enums.EmployeeRole;
import model.enums.PerformanceLevel;

import java.math.BigDecimal;

public class Employee {

    private String name;
    private String cpf;
    private String email;
    private BigDecimal baseSalary;
    private EmployeeRole employeeRole;
    private PerformanceLevel performanceRate;

    public Employee(String name, String cpf, String email, BigDecimal baseSalary) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public PerformanceLevel getPerformanceRate() {
        return performanceRate;
    }

    public void setPerformanceRate(PerformanceLevel performanceRate) {
        this.performanceRate = performanceRate;
    }

    @Override
    public String toString() {
        return name
                + " | CPF: " + cpf
                + " | Email: " + email
                + " | Base Salary: R$" + baseSalary
                + " | Role: " + employeeRole;
    }

}
