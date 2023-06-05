package com.game.engine.components;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.msc.Vector2;

import java.awt.*;

public class EditorComponent extends Component{

    Vector2 offset = new Vector2();
    boolean moveX = false;
    boolean moveY = false;
    private boolean moveAll = false;

    public void render(Graphics2D g){

        Point p = new Point((int) Input.getMousePosition().getX(), (int) Input.getMousePosition().getY());

        Color c = g.getColor();

        int length = (int) (50/(GameEngine.game.getHeight()*0.001f));
        int width = 10;

        // we create a rectangle and check if the mouse is insdoe
        // if the mouse is inside and the mouse is down we set move[instert acsis]
        // then we check if that axis is true if so we update the posision
        // if the mouse not pressed that axis is false


        g.setColor(new Color(255, 116, 116));

        Rectangle xLed = new Rectangle(
                (int) transform.getGlobalPosition().getX(),
                (int) transform.getGlobalPosition().getY(),
                length,
                width
        );

        if(!Input.isMouseDown()) {
            moveX = false;
            moveY = false;
            moveAll = false;
        }

        if(xLed.contains(p) && !moveY) {
            g.setColor(Color.red);
            if(Input.isMouseDown())
                moveX = true;
        }

        if(moveX){
            g.setColor(Color.red);
            Vector2 x = transform.getGlobalPosition().removeX();
            transform.setPosition(x.add(Input.getMousePosition().add(-40).removeY()));
        }

        g.fill(xLed);

        g.setColor(new Color(117, 255, 121));

        Rectangle yLed = new Rectangle(
                (int) transform.getGlobalPosition().getX(),
                (int) transform.getGlobalPosition().getY()-length,
                width,
                length
        );

        if(yLed.contains(p) && !moveX) {
            g.setColor(Color.green);
            if(Input.isMouseDown())
                moveY = true;
        }

        if(moveY){
            g.setColor(Color.green);
            Vector2 x = transform.getGlobalPosition().removeY();
            transform.setPosition(x.add(Input.getMousePosition().add(40).removeX()));
        }
        g.fill(yLed);

        g.setColor(new Color(117, 165, 255));

        Rectangle middle = new Rectangle(
                (int) transform.getGlobalPosition().getX()-width*2,
                (int) transform.getGlobalPosition().getY()-width*2,
                width*4,
                width*4
        );

        if(middle.contains(p) && !moveX && !moveY) {
            g.setColor(Color.green);
            if(Input.isMouseDown()){
                moveAll = true;

            }
        }

        if(moveAll){
            g.setColor(Color.green);
            transform.setPosition(Input.getMousePosition());
        }
        g.fill(middle);



        g.setColor(c);
    }


}
