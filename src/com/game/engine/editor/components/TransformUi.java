package com.game.engine.editor.components;

import com.game.engine.components.Transform;

import javax.swing.*;
import java.awt.*;

public class TransformUi extends ComponentUi {

    Vector2Ui position = new Vector2Ui("Position");
    FloatUi rotation = new FloatUi("Rotation");
    Vector2Ui scale = new Vector2Ui("Scale");

    public TransformUi(String name, Transform transform){
        super(name, transform);
        position.x.setValue(transform.getPosition().getX());
        position.y.setValue(transform.getPosition().getY());

        add(position);
        add(rotation);
        add(scale);
    }


}
