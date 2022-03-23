package com.example.bmi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BMICalculatorUnitTest {

    @Test
    public void testCalculateBMI() throws BMICalculator.ZeroNumberException {

        BMICalculator bmiCalcM = new BMICalculator(55.0, 155.0);
        double bmi = bmiCalcM.calculateBMI("metric");
        assertEquals(String.format("%.2f", bmi), "22,89");

        BMICalculator bmiCalcI = new BMICalculator(121.0, 61.0);
        bmi = bmiCalcI.calculateBMI("imperial");
        assertEquals(String.format("%.2f", bmi), "22,86");
    }
}