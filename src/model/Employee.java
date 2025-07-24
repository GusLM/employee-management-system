package model;

import java.math.BigDecimal;

public class Employee {
    private String name;
    private String cpf;
    private String email;
    private BigDecimal baseSalary;

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

    @Override
    public String toString() {
        return name
                + " | CPF: " + cpf
                + " | Email: " + email
                + " | Base Salary: R$" + baseSalary;
    }

}
