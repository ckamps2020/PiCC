package me.ckamps.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.*;

public class Main {

    private static final int method = 2;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        if (method == 1) {
            Multithreading.runAsync(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Calculating pi and e...");
                    final long start = System.currentTimeMillis();
                    final long piStart = System.currentTimeMillis();
                    double pi = computePi(2147483647);
                    final long piEnd = System.currentTimeMillis();
                    System.out.println("Finished calculating pi, about to calculate e!");
                    final long eStart = System.currentTimeMillis();
                    double e = calculateE(10000);
                    final long eEnd = System.currentTimeMillis();
                    final long end = System.currentTimeMillis();
                    System.out.println("Finished!");
                    System.out.println("pi was " + pi + " and was generated in " + TimeUnit.MILLISECONDS.toSeconds(piEnd - piStart) + " seconds!");
                    System.out.println("e was " + e + " and was generated in " + TimeUnit.MILLISECONDS.toSeconds(eEnd - eStart) + " seconds!");
                    System.out.println("pi + e = " + (pi + e) + ". Total execution time was " + TimeUnit.MILLISECONDS.toSeconds(end - start) + " seconds!");
                }
            });
        } else if (method == 2) {
            System.out.println("Calculating pi and e...");
            final long start = System.currentTimeMillis();
            final long piStart = System.currentTimeMillis();
            ExecutorService exe = Executors.newFixedThreadPool(12);
            CalculatePi cp = new CalculatePi(2147483647);
            Future<Double> res = exe.submit(cp);
            final long eStart = System.currentTimeMillis();
            CalulateBigN bn = new CalulateBigN(10000);
            Future<Double> r = exe.submit(bn);
            while (!res.isDone()) {
                //
            }
            final long piEnd = System.currentTimeMillis();
            System.out.println("Finished calculating pi, about to finish calculating e!");
            while (!r.isDone()) {
                //
            }
            final long eEnd = System.currentTimeMillis();
            try {
                AdditionTime at = new AdditionTime(cp.call(), bn.call());
                Future<Double> lol = exe.submit(at);
                while (!lol.isDone()) {
                    //
                }
                System.out.println("Finished!");
                final long end = System.currentTimeMillis();
                System.out.println("pi was " + cp.call() + " and was generated in " + TimeUnit.MILLISECONDS.toSeconds(piEnd - piStart) + " seconds!");
                System.out.println("e was " + bn.call() + " and was generated in " + TimeUnit.MILLISECONDS.toSeconds(eEnd - eStart) + " seconds!");
                System.out.println("pi + e = " + at.call() + ". Total execution time was " + TimeUnit.MILLISECONDS.toSeconds(end - start) + " seconds!");
                System.exit(1);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    private static double computePi(long effort) { // http://stackoverflow.com/a/26922753
        double pi = 1;
        for (int i = 3; i < effort; i += 4) {
            pi = pi - (1 / (double) i) + (1 / (double) (i + 2));
        }
        return pi * 4;
    }
    private static double calculateE(long effort) {
        BigDecimal e = BigDecimal.ONE;
        BigDecimal fact = BigDecimal.ONE;

        for(int i=1;i<effort;i++) {
            fact = fact.multiply(new BigDecimal(i));
            e = e.add(BigDecimal.ONE.divide(fact, new MathContext(10000, RoundingMode.HALF_UP)));
        }
        return e.doubleValue();
    }

}
