import com.formdev.flatlaf.FlatDarculaLaf;
import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Scene;
import com.game.engine.components.*;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.Sprite;
import com.game.engine.rendering.SpriteRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();

        Scene scene = new Scene();

        FlatDarculaLaf.setup();

        JPanel panel = new JPanel();
        panel.setSize(500,500);
        //panel.setLayout(new GridLayout());


        JTextField text = new JTextField();
        JButton button = new JButton("Submit");
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debug.log(text.getText());
            }
        });

        panel.add(text);
        panel.add(button);
        

        GameEngine.getCanvas().setLayout(null);
        GameEngine.getCanvas().add(panel);

        GameEngine.getCanvas().setBackground(new Color(0,0,0,0));

        RectangleGameObject r = new RectangleGameObject();
        r.addComponent(new Rigidbody());
        r.addComponent(new PlayerMovement());

        scene.add(r);




        GameEngine.setSelectedScene(scene);
        gameEngine.start();
    }
}
