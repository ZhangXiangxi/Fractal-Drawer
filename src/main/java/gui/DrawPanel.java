package gui;

import algorithms.*;

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
    public ColorSelector[] colorSelectors;
    public FractalKernel[] kernels;
    public int colorSelection = 0;
    public int kernelSelection = 0;

    double escapeRadius = 4.0;
    int maxIterations = 5000;

    public DrawPanel(int width, int height) {
        super();
        WIDTH = width;
        HEIGHT = height;
        graphWindow = new GraphWindow(-0.743030, 0.126433, 0.016110, WIDTH, HEIGHT);
        setBackground(Color.WHITE);
        setSize(WIDTH, HEIGHT);
        colors = new Color[WIDTH][HEIGHT];
        registerColorSelectors();
        registerKernels();
    }
    public void drawGraph() {
        calculateColors();
        drawColors();
    }
    public void setColorSelection(String colorSelection) {
        int selection;
        try {
            selection = Integer.parseInt(colorSelection);
        } catch (NumberFormatException e) {
            selection = 0;
        }
        setColorSelection(selection);
    }
    public void setColorSelection(int colorSelectionIndex) {
        colorSelectionIndex %= colorSelectors.length;
        colorSelection = colorSelectionIndex;
    }
    private void registerColorSelectors() {
        colorSelectors = new ColorSelector[2];
        colorSelectors[0] = new GradualColorSelector(maxIterations);
        colorSelectors[1] = new CrazyColorSelector();
    }
    private void registerKernels() {
        kernels = new FractalKernel[1];
        kernels[0] = new MandelbrotKernel(maxIterations, escapeRadius);
    }
    private void calculateColors() {
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j <HEIGHT; j++) {
                colors[i][j] = getColorAt(i, j);
            }
        }
    }
    private Color selectColor(double color) {
        return colorSelectors[colorSelection].getColor(color);
    }

    private Color getColorAt(int i, int j) {
        return selectColor(kernels[kernelSelection].depthAt(graphWindow.getGraphX(i), graphWindow.getGraphY(j)));
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
