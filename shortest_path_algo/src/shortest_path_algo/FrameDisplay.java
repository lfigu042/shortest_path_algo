package shortest_path_algo;

import javax.swing.*;

public class FrameDisplay extends JFrame {
    int WIDTH = 450;
    int HEIGHT = 450;

    public FrameDisplay() {
        setTitle("Graph Display");
        setSize(WIDTH, HEIGHT);
        GraphDisplay panel = new GraphDisplay();
        add(panel);
    }
}
