package com.game.engine.editor.components;

import javax.swing.*;

public class FloatUi extends VariableUi{

    JSpinner value = new JSpinner();

    public FloatUi(String name) {
        super(name);
        addElements();
    }

    @Override
    public void addElements() {
        add(value);
    }
}
