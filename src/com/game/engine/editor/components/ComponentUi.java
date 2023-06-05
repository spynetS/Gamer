package com.game.engine.editor.components;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Getter
@Setter
public class ComponentUi extends JPanel {
    JLabel title = new JLabel();
    JButton remove = new JButton();
    GridBagConstraints gbc = new GridBagConstraints();
    com.game.engine.components.Component component;
    public ComponentUi(String name, com.game.engine.components.Component component){
        this.component = component;
        setLayout(new GridBagLayout());

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        title.setText(name);
        add(title);
        remove.setText("Remove");
        add(remove);
        remove.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                component.transform.getGameObject().removeComponent(component);
            }
        });
    }

    public void updateValues(){
        updateValues(false);
    }
    public void updateValues(boolean add){}

    @Override
    public Component add(Component comp) {
        this.add(comp,gbc);
        return comp;
    }
}
