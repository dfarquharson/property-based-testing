package com.dsjf.property;

public class TrickyMath {

    public Integer add(Integer i, Integer j) {
        return i + j;
    }

    public Double divide(Double i, Double j) {
        return i / j;
    }

    public Boolean cleanDivisor(Integer i, Integer j) {
        return i % j == 0;
    }

}
