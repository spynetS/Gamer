package com.game.engine.rendering;


import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Renderer extends Component {

    @Setter
    @Getter
    private LinkedList<Vector2> shape = new Rect(10,10);

    @Getter
    @Setter
    protected LinkedList<Vector2> shapeGlobal = new Rect(10,10);

    protected Vector2 getPos(){
        if(transform.getParent() == null)
            return transform.getPosition().subtract(transform.getScale().divide(2));
        else{
            Vector2 scale = transform.getGlobalPosition().subtract(transform.getGlobalScale().divide(2));

            return scale;
        }
    }

    private float rotation = 0f;

    protected Vector2 scale = new Vector2(10,10);

    /**
     * rotates the localc vertices to by the angle
     * @param angle the angle to rate the vertice with
     */
    public void rotate(float angle,Vector2 pivot){
        //Vector2 pivot1 = new Vector2(-50,0);

        //rotation = angle;
        double radians = Math.toRadians(angle); // turns to radians from angle
        LinkedList<Vector2> vertices1 = new LinkedList<>(); // new vertices

        for (int i = 0; i < shape.size(); i++) {
            Vector2 vertex = shape.get(i).subtract(pivot);

            float[] matrix = {
                    (float) (vertex.getX() * Math.cos(radians) - vertex.getY() * Math.sin(radians)),
                    (float) (vertex.getX() * Math.sin(radians) + vertex.getY() * Math.cos(radians))};

            vertices1.add(new Vector2(matrix[0], matrix[1]).add(pivot));
        }
        //rotateChildren(angle, pivot);
        this.shape = vertices1;
    }

    /**
     * rotate to specified angle
     * @param angle angle to rotate to
     * @param pivot the povit to rotate around
     */
    public void rotateTo(float angle, Vector2 pivot){
        rotate(angle-rotation,pivot);
        rotation = angle;
    }
    @Override
    public void update() {
        super.update();
        rotateTo(transform.getRotation(), Vector2.zero);


        Vector2 d = new Vector2(1,1);
        if(transform.getParent() != null){
            if(!transform.getScale().containsZero())
                d = transform.getScale().divide(scale);
        }
        else{
            if(!transform.getScale().containsZero())
                d = transform.getScale().divide(scale);
        }

        LinkedList<Vector2> newVertices =new LinkedList<>();
        for(Vector2 vertex : shape){
            Vector2 newV = vertex.multiply(d);
            newVertices.add(newV);
        }
        shape = newVertices;
        scale = transform.getScale();

        LinkedList<Vector2> ver = new LinkedList<>();
        for(Vector2 vertex : shape){
            ver.add(vertex.add(transform.getGlobalPosition()));
        }
        shapeGlobal = ver;
    }

    public Polygon getShape(){

        int[] x = new int[shapeGlobal.size()];
        int[] y = new int[shapeGlobal.size()];
        int i = 0;

        for(Vector2 point : shapeGlobal){
            x[i] = (int) point.getX();
            y[i] = (int) point.getY();
            i++;
        }

        return new Polygon(x,y,shapeGlobal.size());
    }

    @Override
    public void start() {
        super.start();
        scale = transform.getScale();
    }

    public void render(Graphics2D g){

    }

}
