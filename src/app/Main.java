package app;

import service.IncomeTaxCalculator;

import java.math.BigDecimal;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        // Manual testing of tax calculation results
        IncomeTaxCalculator.calculateIRRF(new BigDecimal("3500.00"));
    }
}
