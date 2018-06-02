package gui;

import algorithms.ColorSelector;
import algorithms.CrazyColorSelector;
import algorithms.GradualColorSelector;
import algorithms.GraphWindow;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xiangxi on 2018/6/1.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class DrawPanel extends JPanel {
    public final int WIDTH;
    public final int HEIGHT;
    private Color[][] colors;
    public final GraphWindow graphWindow;
    public ColorSelector colorSelector;

    double ESCAPERADIUS = 4.0;
    int MAXITERNUMBER = 5000;

    public DrawPanel(int width, int height) {
        super();
        WIDTH = width;
        HEIGHT = height;
        graphWindow = new GraphWindow(-0.743030, 0.126433, 0.016110, WIDTH, HEIGHT);
        setBackground(Color.WHITE);
        setSize(WIDTH, HEIGHT);
        colors = new Color[WIDTH][HEIGHT];
        colorSelector = new GradualColorSelector(MAXITERNUMBER);
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
    private double iter(double cx, double cy) {
        double x = 0;
        double y = 0;
        double newx;
        double newy;

        double smodz = 0;
        int i = 0;
        while (i < MAXITERNUMBER) {
            newx = x * x - y * y + cx;
            newy = 2 * x * y + cy;
            x = newx;
            y = newy;
            i++;

            smodz = x * x + y * y;
            if (smodz >= ESCAPERADIUS) {
                return i;
            }
        }
        return -1.0;
    }
    private Color getColor(double color) {
        return colorSelector.getColor(color);
    }

    private Color getMandelbrotColorAt(int i, int j) {
        return getColor(iter(graphWindow.getGraphX(i), graphWindow.getGraphY(j)));
    }
    private Color getTestColorAt(int i, int j) {
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
