package com.game.engine.editor.components;

import com.game.engine.components.Component;
import com.game.engine.editor.Editor;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.time.temporal.ValueRange;

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
    public <T extends VariableUi> T getVariable(String name, VariableUi i){
        for(java.awt.Component c : getComponents()){
            if(c instanceof VariableUi as){
                if(as.title.getText() == name)  return (T) c;
            }
        }
        return (T) i;
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
                    FloatUi floatUi = getVariable(f.getName(), new FloatUi(f.getName()));
                    float fs = (float) f.get(component);
                    floatUi.value.setValue(fs);

                    if(add)
                        add(floatUi);
                }

                if(f.getType() == Vector2.class){
                    Vector2Ui vec2 = (Vector2Ui) getVariable(f.getName(), new Vector2Ui(f.getName()));
                    if(Editor.editor.getFocusOwner() != vec2){
                        Vector2 c = (Vector2) f.get(component);
                        vec2.x.setValue(c.getX());
                        vec2.y.setValue(c.getY());
                    }
                    if(add) add(vec2);
                }
                if(f.getType() == Color.class){
                    ColorUi colorUi = new ColorUi(f.getName());

                    colorUi.color.setBackground((Color) f.get(component));

                    if(add) add(colorUi);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
