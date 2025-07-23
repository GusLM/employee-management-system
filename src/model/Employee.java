package model;

public abstract class Employee {
    private String name;
    private String cpf;
    private String email;
    private Double baseSalary;

    public Employee(String name, String cpf, String email, double baseSalary) {
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

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double calculateNetSalary();

    @Override
    public String toString() {
        return name
                + " | CPF: " + cpf
                + " | Email: " + email
                + " | Base Salary: R$" + String.format("%.2f", baseSalary);
    }

}
