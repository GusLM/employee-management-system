package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class to calculate the INSS (Brazilian Social Security) contribution for the year 2025,
 * using the progressive, tier-based calculation method.
 */
public class InssCalculator {

    // --- INSS Table for 2025 ---
    // Tier 1: Up to R$ 1,518.00 (7.5% rate)
    private static final BigDecimal TIER_1_LIMIT = new BigDecimal("1518.00");
    private static final BigDecimal TIER_1_RATE = new BigDecimal("0.075");

    // Tier 2: From R$ 1,518.01 to R$ 2,793.88 (9% rate)
    private static final BigDecimal TIER_2_LIMIT = new BigDecimal("2793.88");
    private static final BigDecimal TIER_2_RATE = new BigDecimal("0.09");

    // Tier 3: From R$ 2,793.89 to R$ 4,190.83 (12% rate)
    private static final BigDecimal TIER_3_LIMIT = new BigDecimal("4190.83");
    private static final BigDecimal TIER_3_RATE = new BigDecimal("0.12");

    // Tier 4: From R$ 4,190.84 to R$ 8,157.41 (14% rate)
    private static final BigDecimal TIER_4_LIMIT = new BigDecimal("8157.41");
    private static final BigDecimal TIER_4_RATE = new BigDecimal("0.14");



    /**
     * Calculates the contribution for a specific salary bracket.
     * This method applies a given rate only to the portion of the salary that falls within the specified range.
     *
     * @param lowerLimit The lower bound of the salary bracket (inclusive).
     * @param upperLimit The upper bound of the salary bracket (exclusive).
     * @param rate The contribution rate (e.g., 0.075 for 7.5%).
     * @param baseSalary The employee's gross salary (capped by the INSS ceiling beforehand).
     * @return The calculated contribution amount for this bracket.
     */
    private static BigDecimal calculateBracket(BigDecimal lowerLimit, BigDecimal upperLimit, BigDecimal rate, BigDecimal baseSalary) {
        // If the salary is less than or equal to the lower limit, there's nothing to calculate for this bracket.
        if (baseSalary.compareTo(lowerLimit) <= 0) return BigDecimal.ZERO;

        // Determine the taxable portion for this bracket:
        // - If the salary exceeds the upper limit, use the full range (upperLimit - lowerLimit)
        // - If the salary is within the bracket, subtract only up to the salary
        BigDecimal taxableBase = baseSalary.min(upperLimit).subtract(lowerLimit);

        // Apply the rate to the taxable portion and return the contribution amount
        return taxableBase.multiply(rate);
    }

    /**
     * Calculates the total INSS (Brazilian Social Security) contribution based on the given gross salary,
     * using the progressive tax brackets defined for the year.
     *
     * The calculation is done using a "slice-by-slice" approach, where only the portion of the salary
     * that falls within each bracket is taxed at that bracket's specific rate.
     *
     * @param baseSalary The employee's gross salary.
     * @return The total INSS contribution, rounded to 2 decimal places.
     */
    public static BigDecimal calculateINSS(BigDecimal baseSalary) {
        // Initialize the total deduction amount to zero
        BigDecimal total = BigDecimal.ZERO;

        // The contribution salary is capped at the INSS ceiling (TIER_4_LIMIT).
        // If the salary exceeds the ceiling, only the ceiling value is used for the calculation.
        BigDecimal salary = baseSalary.min(TIER_4_LIMIT);

        // Apply the 7.5% rate on the amount between R$ 0.00 and R$ 1,518.00
        total = total.add(calculateBracket(BigDecimal.ZERO, TIER_1_LIMIT, TIER_1_RATE, salary));

        // Apply the 9% rate on the amount between R$ 1,518.01 and R$ 2,793.88
        total = total.add(calculateBracket(TIER_1_LIMIT, TIER_2_LIMIT, TIER_2_RATE, salary));

        // Apply the 12% rate on the amount between R$ 2,793.89 and R$ 4,190.83
        total = total.add(calculateBracket(TIER_2_LIMIT, TIER_3_LIMIT, TIER_3_RATE, salary));

        // Apply the 14% rate on the amount between R$ 4,190.84 and R$ 8,157.41
        total = total.add(calculateBracket(TIER_3_LIMIT, TIER_4_LIMIT, TIER_4_RATE, salary));

        // Return the total INSS contribution, rounded to 2 decimal places using HALF_UP rounding mode
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
