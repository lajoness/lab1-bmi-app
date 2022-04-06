package com.example.bmi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BMICalculatorUnitTest {

    @Test
    public void testCalculateBMI() throws BMIViewModel.ZeroNumberException {

        BMIViewModel bmiVM = new BMIViewModel();
        bmiVM.height = 155.0;
        bmiVM.mass = 55.0;
        double bmi = bmiVM.calculateBMI("metric");
        assertEquals(String.format("%.2f", bmi), "22,89");

        bmiVM.height = 61.0;
        bmiVM.mass = 121.0;
        bmi = bmiVM.calculateBMI("imperial");
        assertEquals(String.format("%.2f", bmi), "22,86");
    }
}