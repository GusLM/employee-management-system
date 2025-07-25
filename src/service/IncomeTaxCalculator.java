package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IncomeTaxCalculator {

    // --- Brazilian Income Tax (IRRF) Progressive Table for 2025 ---
    // The constants below define the different tax brackets (tiers), including their
    // upper limits, tax rates, and the amount to be deducted from the calculated tax.

    /**
     * Tier 1: Exempt. For taxable incomes up to R$ 2,428.80.cc
     */
    private static final BigDecimal TIER_1_LIMIT = new BigDecimal("2428.80");
    private static final BigDecimal TIER_1_RATE = new BigDecimal("0.00");
    private static final BigDecimal TIER_1_DEDUCTION = new BigDecimal("0.00");

    /**
     * Tier 2: 7.5% rate. For taxable incomes between R$ 2,428.81 and R$ 2,826.65.
     */
    private static final BigDecimal TIER_2_LIMIT = new BigDecimal("2826.65");
    private static final BigDecimal TIER_2_RATE = new BigDecimal("0.075");
    private static final BigDecimal TIER_2_DEDUCTION = new BigDecimal("182.16");

    /**
     * Tier 3: 15% rate. For taxable incomes between R$ 2,826.66 and R$ 3,751.05.
     */
    private static final BigDecimal TIER_3_LIMIT = new BigDecimal("3751.05");
    private static final BigDecimal TIER_3_RATE = new BigDecimal("0.15");
    private static final BigDecimal TIER_3_DEDUCTION = new BigDecimal("394.16");

    /**
     * Tier 4: 22.5% rate. For taxable incomes between R$ 3,751.06 and R$ 4,664.68.
     */
    private static final BigDecimal TIER_4_LIMIT = new BigDecimal("4664.68");
    private static final BigDecimal TIER_4_RATE = new BigDecimal("0.225");
    private static final BigDecimal TIER_4_DEDUCTION = new BigDecimal("675.49");

    /**
     * Tier 5: 27.5% rate. For taxable incomes above R$ 4,664.68.
     * Note: This limit is technically redundant in the current logic but defines the start of the highest bracket.
     */
    private static final BigDecimal TIER_5_LIMIT = new BigDecimal("4664.69");
    private static final BigDecimal TIER_5_RATE = new BigDecimal("0.275");
    private static final BigDecimal TIER_5_DEDUCTION = new BigDecimal("908.73");

    /**
     * A helper method to calculate the tax for a specific bracket.
     * Note: This method's current implementation contains a logical flaw. It does not use the limits correctly
     * and always applies the tax formula, even if the income is below the lower limit.
     *
     * @param lowerLimit The lower boundary of the tax bracket (not currently used effectively).
     * @param upperLimit The upper boundary of the tax bracket (not currently used).
     * @param rate The tax rate for this bracket.
     * @param deduction The fixed amount to deduct for this bracket.
     * @param baseIRRF The taxable income base.
     * @return The calculated tax amount for the given base, or zero if the base is below the lower limit.
     */
    private static BigDecimal calculateBracket(
            BigDecimal lowerLimit,
            BigDecimal upperLimit,
            BigDecimal rate,
            BigDecimal deduction,
            BigDecimal baseIRRF) {

        // This check is flawed; it should be handled by the calling if-else structure.
        if (baseIRRF.compareTo(lowerLimit) <= 0) return BigDecimal.ZERO;

        // Calculates the gross tax amount by applying the rate.
        BigDecimal taxableBase = baseIRRF.multiply(rate);

        // Subtracts the fixed deduction amount for the corresponding tier.
        return taxableBase.subtract(deduction);

    }

    /**
     * Calculates the final IRRF (Brazilian Income Tax) to be withheld from a given base salary.
     * The process involves first deducting the Social Security (INSS) contribution to find the taxable base,
     * and then applying the corresponding tax bracket.
     *
     * @param baseSalary The employee's gross monthly salary.
     * @return The final income tax amount to be withheld, rounded to 2 decimal places.
     */
    public static BigDecimal calculateIRRF(BigDecimal baseSalary) {
        BigDecimal total;

        // Step 1: Calculate the Social Security (INSS) deduction.
        // This assumes the existence of a BrazilianSocialSecurity class with a static calculateINSS method.
        BigDecimal discountINSS =  InssCalculator.calculateINSS(baseSalary);

        // Step 2: Calculate the taxable base for the income tax (Base de Cálculo do IRRF).
        // Taxable Base = Gross Salary - INSS Deduction.
        // Note: Deductions for dependents should also be subtracted here if applicable.
        BigDecimal baseIRRF = baseSalary.subtract(discountINSS).setScale(2, RoundingMode.HALF_UP);

        // Step 3: Determine the correct tax bracket and calculate the tax.
        // The if-else-if structure finds the appropriate tier for the calculated taxable base.
        if (baseIRRF.compareTo(TIER_1_LIMIT) <= 0){
            // Taxable base is in the exempt tier.
            total = (calculateBracket(BigDecimal.ZERO, TIER_1_LIMIT, TIER_1_RATE, TIER_1_DEDUCTION, baseIRRF));
        } else if (baseIRRF.compareTo(TIER_2_LIMIT) <= 0) {
            // Taxable base is in the 7.5% tier.
            total = (calculateBracket(TIER_1_LIMIT, TIER_2_LIMIT, TIER_2_RATE, TIER_2_DEDUCTION, baseIRRF));
        } else if (baseIRRF.compareTo(TIER_3_LIMIT) <= 0) {
            // Taxable base is in the 15% tier.
            total = (calculateBracket(TIER_2_LIMIT, TIER_3_LIMIT, TIER_3_RATE, TIER_3_DEDUCTION, baseIRRF));
        } else if (baseIRRF.compareTo(TIER_4_LIMIT) <= 0) {
            // Taxable base is in the 22.5% tier.
            total = (calculateBracket(TIER_3_LIMIT, TIER_4_LIMIT, TIER_4_RATE, TIER_4_DEDUCTION, baseIRRF));
        } else {
            // Taxable base is in the highest tier (27.5%).
            total = (calculateBracket(TIER_4_LIMIT, TIER_5_LIMIT, TIER_5_RATE, TIER_5_DEDUCTION, baseIRRF));
        }

        // Step 4: Return the final calculated tax, ensuring it's scaled to 2 decimal places.
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
