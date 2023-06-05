package com.game.engine.editor.components;

import com.game.engine.msc.Debug;

import javax.swing.*;

public class ColorUi extends VariableUi{
    JPanel color = new JPanel();
    public ColorUi(String name) {
        super(name);
        add(color);
    }
}
