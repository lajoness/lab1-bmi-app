package com.example.bmi;

public class BMICalculator {

    private Double height;
    private Double mass;

    public BMICalculator(Double mass, Double height) {

        this.height = height;
        this.mass = mass;
    }

    public double calculateBMI(String dataType) throws ZeroNumberException {

        if (mass != 0 && height != 0) {

            double bmi;

            if (dataType == "metric") {

                bmi = mass/Math.pow(height/100, 2);
            }
            else {

                bmi = mass*703/Math.pow(height, 2);
            }

            return bmi;
        }
        else { throw new ZeroNumberException("Number is 0"); }
    }

    public class ZeroNumberException extends Exception {
        public ZeroNumberException(String errorMessage) {
            super(errorMessage);
        }
    }
}
