package com.game.engine.rendering.shapes;


import com.game.engine.msc.Vector2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Rect extends LinkedList<Vector2> {

    public Rect(int width, int height){
        add(new Vector2(-width/2,-height/2)); // top left
        add(new Vector2(-width/2,height/2));  // bottom left
        add(new Vector2(width/2,height/2));   // bottom right
        add(new Vector2(width/2,-height/2));   // top right
    }
    public Rect(Vector2 scale){
        int width = (int) scale.getX();
        int height = (int) scale.getY();
        add(new Vector2(-width/2,-height/2)); // top left
        add(new Vector2(-width/2,height/2));  // bottom left
        add(new Vector2(width/2,height/2));   // bottom right
        add(new Vector2(width/2,-height/2));   // top right
    }
}
