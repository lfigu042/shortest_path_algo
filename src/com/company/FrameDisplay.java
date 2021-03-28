package com.company;

import javax.swing.*;

public class FrameDisplay extends JFrame {
    int WIDTH = 600;
    int HEIGHT = 600;

    public FrameDisplay() {
        setTitle("Graph Display");
        setSize(WIDTH, HEIGHT);
        GraphDisplay panel = new GraphDisplay();
        add(panel);
    }
}
