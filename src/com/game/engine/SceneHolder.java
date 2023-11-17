package com.game.engine;

import com.game.engine.Input.Input;
import com.game.engine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * SceneHolder is a holder for the scenes. It is also responsible to send all user inputs to the input class
 */
public class SceneHolder extends JPanel {

    public SceneHolder(){
        setLayout(new GridLayout(0, 1));
        setBackground(Color.WHITE);
        setFocusable(true);


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                Input.addKey(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Input.removeKey(e);
            }
        });

        /*
          mouse input
         */
        MouseAdapter mouseAdapter = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Input.addMouseButton(e);
                Input.setMouseEvent(e);
                Input.setMousePressed(e.getButton());
                requestFocus();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Input.removeMouseButton(e);
                Input.setMouseEvent(e);

            }
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Input.setMousePositionOnCanvas(new Vector2((double) e.getPoint().getX(), (double) e.getPoint().getY()));
                Input.setMouseEvent(e);

            }
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                Input.setMousePositionOnCanvas(new Vector2((double) e.getPoint().getX(), (double) e.getPoint().getY()));
                Input.setMouseEvent(e);
                requestFocus();
            }
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                Input.setScrollValue((double) e.getPreciseWheelRotation());
                Input.setMouseEvent(e);
            }

        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);

    }


}
