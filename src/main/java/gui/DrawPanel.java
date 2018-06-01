package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xiangxi on 2018/6/1.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class DrawPanel extends JPanel {
    public final int WIDTH;
    public final int HEIGHT;
    public DrawPanel(int width, int height) {
        super();
        WIDTH = width;
        HEIGHT = height;
        setBackground(Color.WHITE);
        setSize(WIDTH, HEIGHT);
    }
    public void drawMandelbrot() {
        // TODO:
    }
}
