package gui;

import algorithms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Xiangxi on 2018/6/1.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class DrawPanel extends JPanel {
    public final int WIDTH;
    public final int HEIGHT;
    public final static double DEFAULT_X_CENTER = -0.5271824;
    public final static double DEFAULT_Y_CENTER = -0.6124885999999998;
    public final static double DEFAULT_GRAPH_WIDTH = 1e-5;
    private Color[][] colors;
    public final GraphWindow graphWindow;
    public ColorSelector[] colorSelectors;
    public FractalKernel[] kernels;
    public int colorSelection = 0;
    public int kernelSelection = 0;
    public ControlPanel controlPanel;

    double escapeRadius = 4.0;
    int maxIterations = 5000;

    public DrawPanel(int width, int height) {
        super();
        WIDTH = width;
        HEIGHT = height;
        graphWindow = new GraphWindow(DEFAULT_X_CENTER, DEFAULT_Y_CENTER, DEFAULT_GRAPH_WIDTH, WIDTH, HEIGHT);
        setBackground(Color.WHITE);
        setSize(WIDTH, HEIGHT);
        colors = new Color[WIDTH][HEIGHT];
        registerColorSelectors();
        registerKernels();
        addMouseListener(new CatchMouseClick());
    }
    public void linkToControl(ControlPanel controlPanel) {
        assert(controlPanel != null);
        this.controlPanel = controlPanel;
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
    public void setCenter(String xString, String yString) {
        double xCenter, yCenter;
        try {
            xCenter = Double.parseDouble(xString);
        } catch (NumberFormatException e) {
            xCenter = DEFAULT_X_CENTER;
        }
        try {
            yCenter = Double.parseDouble(yString);
        } catch (NumberFormatException e) {
            yCenter = DEFAULT_Y_CENTER;
        }
        graphWindow.recenter(xCenter, yCenter);
    }
    public void setGraphWidth(String widthString) {
        double graphWidth;
        try {
            graphWidth = Double.parseDouble(widthString);
        } catch (NumberFormatException e) {
            graphWidth = DEFAULT_GRAPH_WIDTH;
        }
        graphWindow.rescale(graphWidth);
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
    public void drawGraph() {
        calculateColors();
        drawColors();
    }
    private void calculateColors() {
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j <HEIGHT; j++) {
                colors[i][j] = getColorAt(i, j);
            }
        }
    }
    private void drawColors() {
        for(int i = 0; i < WIDTH; i++)
            for(int j = 0; j < HEIGHT; j++)
                drawPointAt(i, j, colors[i][j]);
    }
    private Color getColorAt(int i, int j) {
        return selectColor(kernels[kernelSelection].depthAt(graphWindow.getGraphX(i), graphWindow.getGraphY(j)));
    }
    private Color selectColor(double color) {
        return colorSelectors[colorSelection].getColor(color);
    }
    private void drawPointAt(int i, int j, Color color) {
        Graphics graphics = this.getGraphics();
        graphics.setColor(color);
        graphics.drawLine(i, j, i, j+1);
    }
    private class CatchMouseClick implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            Point clickAt = e.getPoint();
            double newX = graphWindow.getGraphX(clickAt.x);
            double newY = graphWindow.getGraphY(clickAt.y);
            controlPanel.xField.setText(newX + "");
            controlPanel.yField.setText(newY + "");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // pass
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
