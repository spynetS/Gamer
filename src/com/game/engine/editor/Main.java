package com.game.engine.editor;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args){

        //FlatDarculaLaf.setup();
        //FlatDarkLaf.setup();

        EditorWindow window = new EditorWindow();

        window.add(new Editor().getRrom());

        window.setSize(1920/2,1080/2);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

}
