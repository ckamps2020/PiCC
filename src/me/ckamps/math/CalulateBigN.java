package me.ckamps.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

public class CalulateBigN implements Callable<Double> {

    private double effort;

    public CalulateBigN(double effort) {
        this.effort = effort;
    }

    @Override
    public Double call() throws Exception {
        BigDecimal e = BigDecimal.ONE;
        BigDecimal fact = BigDecimal.ONE;

        for(int i=1;i<effort;i++) {
            fact = fact.multiply(new BigDecimal(i));
            e = e.add(BigDecimal.ONE.divide(fact, new MathContext(10000, RoundingMode.HALF_UP)));
        }
        return e.doubleValue();
    }
}
