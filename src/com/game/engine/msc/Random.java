package com.game.engine.msc;

public class Random extends java.util.Random {

    public double nextdouble(double min, double max){
        return min + nextDouble() * (max - min);
    }

}
