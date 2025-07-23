package model;

public class Manager extends Employee {
    private Double bonus;

    public Manager(String name, String cpf, String email, Double baseSalary, Double bonus) {
        super(name, cpf, email, baseSalary);
        this.bonus = bonus;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double calculateNetSalary() {
        double discount = 0.0;

        if (getBaseSalary() <= 2000) {
            discount = getBaseSalary() * 0.07;
        } else if (getBaseSalary() <= 4000) {
            discount = getBaseSalary() * 0.09;
        } else if (getBaseSalary() <= 6000) {
            discount = getBaseSalary() * 0.11;
        } else {
            discount = getBaseSalary() * 0.14;
        }

        return getBaseSalary() - discount + bonus;
    }
}