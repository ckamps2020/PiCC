package me.ckamps.math;

import java.util.concurrent.Callable;

public class AdditionTime implements Callable<Double> {

    private double pi;
    private double e;

    public AdditionTime(double pi, double e) {
        this.pi = pi;
        this.e = e;
    }

    @Override
    public Double call() throws Exception {
        return pi + e;
    }
}
