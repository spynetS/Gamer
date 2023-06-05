package com.game.engine.editor.components;

import com.game.engine.components.Component;
import com.game.engine.editor.Editor;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class GeneretedComponentUI extends ComponentUi{
    public GeneretedComponentUI(String name, Component component) {
        super(name, component);
        setBackground(Color.GRAY);

        updateValues(true);

    }

    /**
     * @param name
     * @return the component with name
     */
    public VariableUi getVariable(String name){
        for(java.awt.Component c : getComponents()){
            if(c instanceof VariableUi as){
                if(as.title.getText() == name)  return (VariableUi) c;
            }
        }
        return null;
    }

    /**
     * updates all values with the right component values
     */
    @Override
    public void updateValues(boolean add){
        try {
            for(Field f : component.getClass().getDeclaredFields()){
                f.setAccessible(true);

                if(f.getType() == float.class){
                    FloatUi floatUi = (FloatUi) getVariable(f.getName());
                    float fs = (float) f.get(component);
                    floatUi.value.setValue(fs);

                    if(add)
                        add(floatUi);
                }

                if(f.getType() == Vector2.class){
                    Vector2Ui vec2 = (Vector2Ui) getVariable(f.getName());
                    if(Editor.editor.getFocusOwner() != vec2){
                        Vector2 c = (Vector2) f.get(component);
                        vec2.x.setValue(c.getX());
                        vec2.y.setValue(c.getY());
                    }
                    if(add) add(vec2);
                }
                Debug.log(f.getType());
                if(f.getType() == Color.class){
                    ColorUi colorUi = new ColorUi(f.getName());

                    colorUi.colorChooser.setColor((Color) f.get(component));

                    if(add) add(colorUi);
                }
            }

        }
        catch (Exception e){}
    }

}
