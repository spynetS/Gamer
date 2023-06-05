package com.game.engine.editor;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Scene;
import com.game.engine.components.Component;
import com.game.engine.components.RectangleGameObject;
import com.game.engine.components.Transform;
import com.game.engine.editor.components.ComponentUi;
import com.game.engine.editor.components.GeneretedComponentUI;
import com.game.engine.editor.components.PlayerMovement;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class Editor extends JFrame{
    private JTree tree1;
    private JPanel game;

    private JPanel rrom;
    private JButton button1;
    private JButton addComponentButton;
    private JPanel componentHolder;

    private GameObject selectedGameObject;

    public static Editor editor;

    public Editor(){
        editor = this;
        GameEngine e = new GameEngine(){
            @Override
            public void update() {
                super.update();
                updateS();
                updateComponentUiValues();
            }
        };

        Scene scene = new Scene();
        RectangleGameObject gameObject = new RectangleGameObject(Color.RED);
        gameObject.addComponent(new Rigidbody());

        gameObject.addComponent(new PlayerMovement());

        scene.add(gameObject);
        selectedGameObject = gameObject;

        GameEngine.setSelectedScene(scene);
        e.start();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Debug.log("hes");
                RectangleGameObject g = new RectangleGameObject(Color.blue);
                g.transform.setPosition(new Vector2(0,100));
                setSelectedGameObject(g);
                GameEngine.getSelectedScene().getGameObjectHandler().getAddGameObject().add(g);
            }
        });

        //getComponentHolder().add(new TransformUi("Transform"));

        addComponentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddComponent addComponent = new AddComponent(getSelectedGameObject());
                addComponent.setVisible(true);

            }
        });
    }

    public void setSelectedGameObject(GameObject selectedGameObject) {
        this.selectedGameObject = selectedGameObject;
        updateS();
    }

    public void updateComponentUiValues(){
        JPanel componentHolder = getComponentHolder();


        for(java.awt.Component c : componentHolder.getComponents()){
            if(c instanceof ComponentUi as) {
                for(Component g : selectedGameObject.components){
                    if(g.getClass() == as.getComponent().getClass()){
                        as.setComponent(g);
                        as.updateValues();
                        getComponentHolder().validate();
                        getComponentHolder().repaint();
                    }
                }
                if(as.getComponent().getClass() == Transform.class){
                as.setComponent(getSelectedGameObject().transform);
                as.updateValues();
                getComponentHolder().validate();
                getComponentHolder().repaint();
                }
            }
        }

    }

    public void updateS(){

        if(getComponentHolder().getComponents().length != getSelectedGameObject().components.size()+1){
            getComponentHolder().removeAll();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            getComponentHolder().add(new GeneretedComponentUI("Transform", getSelectedGameObject().transform),gbc);
            for(Component g : selectedGameObject.components){

                getComponentHolder().add(new GeneretedComponentUI(g.getClass().getSimpleName(), g),gbc);
                getComponentHolder().validate();
                getComponentHolder().repaint();
            }
        }


    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
