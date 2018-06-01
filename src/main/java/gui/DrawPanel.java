package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Xiangxi on 2018/6/1.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class DrawPanel extends JPanel {
    public final int WIDTH;
    public final int HEIGHT;
    private Color[][] colors;

    public DrawPanel(int width, int height) {
        super();
        WIDTH = width;
        HEIGHT = height;
        setBackground(Color.WHITE);
        setSize(WIDTH, HEIGHT);
        colors = new Color[WIDTH][HEIGHT];
    }
    public void drawMandelbrot() {
        calculateMandelbrotColors();
        drawColors();
    }
    private void calculateMandelbrotColors() {
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j <HEIGHT; j++) {
                colors[i][j] = getMandelbrotColorAt(i, j);
            }
        }
    }
    private Color getMandelbrotColorAt(int i, int j) {
        // TODO: put real generation function here
        int iPosition = i / 4;
        int jPosition = j / 4;
        if ((iPosition + jPosition) % 2 == 0)
            return Color.BLUE;
        else
            return Color.RED;
    }
    private void drawColors() {
        for(int i = 0; i < WIDTH; i++)
            for(int j = 0; j < HEIGHT; j++)
                drawPointAt(i, j, colors[i][j]);
    }
    private void drawPointAt(int i, int j, Color color) {
        Graphics graphics = this.getGraphics();
        graphics.setColor(color);
        graphics.drawLine(i, j, i, j+1);
    }
}
