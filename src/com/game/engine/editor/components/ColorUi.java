package com.game.engine.editor.components;

import javax.swing.*;

public class ColorUi extends VariableUi{
    JColorChooser colorChooser = new JColorChooser();
    public ColorUi(String name) {
        super(name);
        add(colorChooser);
    }
}
