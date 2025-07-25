package app;

import service.IncomeTaxCalculator;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // Manual testing of tax calculation results
        IncomeTaxCalculator.calculateIRRF(new BigDecimal("3500.00"));
    }
}
