package model;

import java.math.BigDecimal;

public class Developer extends Employee{
    private Double bonus;

    public Developer(String name, String cpf, String email, BigDecimal baseSalary) {
        super(name, cpf, email, baseSalary);
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
}
