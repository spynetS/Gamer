package com.game.engine.editor;

import com.game.engine.GameObject;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.ShapeRender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddComponent extends JFrame{
    private JPanel panel1;
    private JButton rigidbodyButton;
    private JButton shapeRendererButton;

    public AddComponent(GameObject toAddTo){
        setSize(300,200);
        add(panel1);

        rigidbodyButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toAddTo.addComponent(new Rigidbody());
            }
        });


        shapeRendererButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toAddTo.addComponent(new ShapeRender());
            }
        });
    }

}
