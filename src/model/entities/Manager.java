package model.entities;

import java.math.BigDecimal;

public class Manager extends Employee {
    private Double bonus;

    public Manager(String name, String cpf, String email, BigDecimal baseSalary) {
        super(name, cpf, email, baseSalary);
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

}