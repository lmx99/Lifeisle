package com.lifeisle;

import com.lifeisle.ui.LoginFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jekton Luo
 * @version 0.01 6/4/2015.
 */
public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new LoginFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(320, 160);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
