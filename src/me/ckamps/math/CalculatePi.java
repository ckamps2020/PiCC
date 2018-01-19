package me.ckamps.math;

import java.util.concurrent.Callable;

public class CalculatePi implements Callable<Double> {

    private double effort;

    public CalculatePi(double effort) {
        this.effort = effort;
    }

    @Override
    public Double call() throws Exception {
        double pi = 1;
        for (int i = 3; i < effort; i += 4) {
            pi = pi - (1 / (double) i) + (1 / (double) (i + 2));
        }
        return pi * 4;
    }
}
