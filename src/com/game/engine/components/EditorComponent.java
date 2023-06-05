package com.game.engine.components;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.InputComponent;
import com.game.engine.Input.Keys;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

import java.awt.*;

public class EditorComponent extends Component{

    boolean moveX = false;
    boolean moveY = false;
    private boolean moveAll = false;
    InputComponent i = new InputComponent();
    private int mode = 1; // 0 pos 1 rot, 2 scale
    private float scale = 0;
    @Override
    public void start() {
        super.start();
        i.setContext("all");
        transform.getGameObject().addComponent(i);
    }

    public void render(Graphics2D g){
        if(GameEngine.getSelectedScene().getSelectedGameObject() == transform.getGameObject()){
            Point p = new Point((int) Input.getMousePosition().getX(), (int) Input.getMousePosition().getY());
            if(mode == 1)
                scale(g, p);
            if(mode == 0)
                position(g, p);

            if(i.isKeyDown(Keys.W)) mode = 0;
            if(i.isKeyDown(Keys.R)) mode = 1;
            if(i.isKeyDown(Keys.E)) mode = 2;

        }
    }
    float offset = 0;
    public void scale(Graphics2D g, Point p){
        Color c = g.getColor();

        int length = (int) (50/(GameEngine.game.getHeight()*0.001f));
        int width = 10;

        // we create a rectangle and check if the mouse is insdoe
        // if the mouse is inside and the mouse is down we set move[instert acsis]
        // then we check if that axis is true if so we update the posision
        // if the mouse not pressed that axis is false

        if(!Input.isMouseDown()) {
            moveX = false;
            moveY = false;
            moveAll = false;
        }

        if(!moveX && !moveY){
            offset = 0;
            scale = 0;
        }

        g.setColor(new Color(255, 186, 116));

        Rectangle xLed = new Rectangle(
                (int) transform.getGlobalPosition().getX(),
                (int) transform.getGlobalPosition().getY(),
                length,
                width
        );


        if(xLed.contains(p) && !moveY) {
            g.setColor(Color.orange);
            if(Input.isMouseDown())
                moveX = true;
        }

        if(moveX){
            g.setColor(Color.orange);

            if(scale == 0) scale = transform.getScale().getX();
            if(offset == 0)
                offset = Input.getMousePosition().subtract(transform.getGlobalPosition()).getX();

            float x = Input.getMousePosition().subtract(transform.getPosition()).getX()+scale-offset;

            transform.setScale(new Vector2(x,transform.getScale().getY()));
        }

        g.fill(xLed);

        g.setColor(new Color(255, 248, 117));

        Rectangle yLed = new Rectangle(
                (int) transform.getGlobalPosition().getX(),
                (int) transform.getGlobalPosition().getY()-length,
                width,
                length
        );

        if(yLed.contains(p) && !moveX) {
            g.setColor(new Color(246, 255, 0));
            if(Input.isMouseDown())
                moveY = true;
        }

        if(moveY){
            g.setColor(new Color(246, 255, 0));
            if(scale == 0) scale = transform.getGlobalScale().getY();
            if(offset == 0)
                offset = Input.getMousePosition().subtract(transform.getGlobalPosition()).getY();

            float x = -Input.getMousePosition().add(transform.getGlobalPosition()).getY()+scale+offset;

            transform.setScale(new Vector2(transform.getScale().getX(), x));
        }

        g.fill(yLed);

        g.setColor(new Color(246, 117, 255));

        Rectangle middle = new Rectangle(
                (int) transform.getGlobalPosition().getX()-width*2,
                (int) transform.getGlobalPosition().getY()-width*2,
                width*4,
                width*4
        );

        if(middle.contains(p) && !moveX && !moveY) {
            g.setColor(new Color(193, 0, 255));
            if(Input.isMouseDown()){
                moveAll = true;

            }
        }

        if(moveAll){
            g.setColor(new Color(193, 0, 255));
            transform.setPosition(Input.getMousePosition());
        }
        g.fill(middle);

        g.setColor(c);
    }
    public void position(Graphics2D g, Point p){
        Color c = g.getColor();

        int length = (int) (50/(GameEngine.game.getHeight()*0.001f));
        int width = 10;

        // we create a rectangle and check if the mouse is insdoe
        // if the mouse is inside and the mouse is down we set move[instert acsis]
        // then we check if that axis is true if so we update the posision
        // if the mouse not pressed that axis is false

        if(!Input.isMouseDown()) {
            moveX = false;
            moveY = false;
            moveAll = false;
        }

        if(!moveX && !moveY) offset = 0;

        g.setColor(new Color(255, 116, 116));

        Rectangle xLed = new Rectangle(
                (int) transform.getGlobalPosition().getX(),
                (int) transform.getGlobalPosition().getY(),
                length,
                width
        );


        if(xLed.contains(p) && !moveY) {
            g.setColor(Color.red);
            if(Input.isMouseDown())
                moveX = true;
        }

        if(moveX){
            g.setColor(Color.red);
            Vector2 x = transform.getGlobalPosition().removeX();

            if(offset == 0)
                offset = Input.getMousePosition().subtract(transform.getGlobalPosition()).getX();

            transform.setPosition(x.add(Input.getMousePosition().add(-offset).removeY()));
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
            g.setColor(new Color(0, 201, 0));
            if(Input.isMouseDown())
                moveY = true;
        }

        if(moveY){
            g.setColor(new Color(0, 201, 0));
            Vector2 x = transform.getGlobalPosition().removeY();
            if(offset == 0)
                offset = Input.getMousePosition().subtract(transform.getGlobalPosition()).getY();

            transform.setPosition(x.add(Input.getMousePosition().add(-offset).removeX()));
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
            g.setColor(new Color(0, 117, 201));
            if(Input.isMouseDown()){
                moveAll = true;

            }
        }

        if(moveAll){
            g.setColor(new Color(0, 61, 255));
            transform.setPosition(Input.getMousePosition());
        }
        g.fill(middle);

        g.setColor(c);
    }



}
