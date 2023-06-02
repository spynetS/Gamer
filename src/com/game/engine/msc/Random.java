package com.game.engine.msc;

public class Random extends java.util.Random {

    public float nextFloat(float min, float max){
        return min + nextFloat() * (max - min);
    }

}
