package app;

import service.IncomeTaxCalculator;
import view.ConsoleUI;

import java.math.BigDecimal;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        // Manual testing of tax calculation results
//        IncomeTaxCalculator.calculateIRRF(new BigDecimal("3500.00"));
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.run();
    }
}
