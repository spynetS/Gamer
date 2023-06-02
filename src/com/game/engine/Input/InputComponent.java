package com.game.engine.Input;

import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;

public class InputComponent extends Component {

    @Getter
    @Setter
    LinkedList<Integer> leftKeys = new LinkedList<>();

    @Getter
    @Setter
    private String context;

    @Override
    public void update() {
        super.update();
        LinkedList<Integer> keyBuffer = new LinkedList<>();
        for(int key : leftKeys){
            if(!Input.isKeyDown(key)){
                keyBuffer.add(key);
            }
        }
        leftKeys.removeAll(keyBuffer);
    }

    public boolean isKeyDown(int key){
        return (Input.isKeyDown(key) && Input.getActiveContext().contains(context));
    }
    public boolean isKeyPressed(int keycode){
        boolean pressed = Input.getKeyDowns().contains(keycode) &&
                !leftKeys.contains(keycode) &&
                Input.getActiveContext().contains(context);
        if(pressed)
            leftKeys.add(keycode);
        return pressed;
    }




}
