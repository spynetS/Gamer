package com.game.engine.editor.components;

import javax.swing.*;

public class ComponentUi extends JPanel {
    JLabel title = new JLabel();

    public ComponentUi(String name) {
        title.setText(name);
        add(title);
    }

    public ComponentUi() {
    }

    public void addElements(){}
}
