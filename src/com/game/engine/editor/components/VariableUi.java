package com.game.engine.editor.components;

import javax.swing.*;

public class VariableUi extends JPanel {
    JLabel title = new JLabel();

    public VariableUi(String name) {
        title.setText(name);
        add(title);
    }

    public VariableUi() {
    }

    public void addElements(){}
}
