package com.game.engine.physics;

import com.game.engine.components.Component;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;


public class Collider extends Component {

    @Getter boolean isColliding = false;
    private Color orgColor;

    public void setColliding(boolean colliding) {
        isColliding = colliding;
        ShapeRender c = getComponent(ShapeRender.class);
        if(colliding){
            orgColor = c.getColor();
            c.setColor(Color.green);
        }else{
            c.setColor(orgColor);
        }
    }

    public boolean collides(Collider c2){

        Shape shape = getComponent(Renderer.class).getPolygon();
        LinkedList<Vector2> points = c2.getComponent(Renderer.class).getShapeGlobal();
        for(Vector2 p : points) {
            if (shape.contains(new Point((int)(p.getX()),(int)(p.getY())))){
                getComponent(ShapeRender.class).setColor(Color.GREEN);
                return true;
            }
        }
        shape = c2.getComponent(Renderer.class).getPolygon();
        points = getComponent(Renderer.class).getShapeGlobal();
        for(Vector2 p : points) {
            if (shape.contains(new Point((int)(p.getX()),(int)(p.getY())))){
                return true;
            }
        }
        return false;
    }
}
