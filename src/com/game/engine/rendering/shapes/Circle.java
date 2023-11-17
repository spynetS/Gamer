package com.game.engine.rendering.shapes;


import com.game.engine.msc.Vector2;

import java.util.LinkedList;

public class Circle extends LinkedList<Vector2> {

    int width, height;

    public Circle(int width, int height){
        // higher scaler better preformance badder circles
        double scaler = 3f;
        for(int x = 0;x <= 360/scaler;x++){
            add(new Vector2(width* (double) Math.cos(Math.toRadians(x*scaler)), height * (double) Math.sin(Math.toRadians(x*scaler))));
        }

    }
    public Circle(int radius){
        int width  = radius;
        int height = radius;
        for(int x = 0;x <= 360;x++){
            add(new Vector2(width* (double) Math.cos(Math.toRadians(x)), height * (double) Math.sin(Math.toRadians(x))));
        }
    }
    public Circle(Vector2 scale){
        int width = (int) scale.getX();
        int height = (int) scale.getY();
        for(int x = 0;x <= 360;x++){
            add(new Vector2(width* (double) Math.cos(Math.toRadians(x)), height * (double) Math.sin(Math.toRadians(x))));
        }
    }
}