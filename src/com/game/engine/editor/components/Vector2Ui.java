package com.game.engine.editor.components;

import javax.swing.*;

public class Vector2Ui extends VariableUi {

    JSpinner x = new JSpinner();
    JSpinner y = new JSpinner();

    public Vector2Ui(String name) {
        super(name);
        addElements();
    }

    public void addElements() {
        add(x);
        add(y);
    }
}
