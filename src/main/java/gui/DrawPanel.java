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

    private Color[][] colors;
    public final GraphWindow graphWindow;
    public ColorSelector[][] colorSelectors;
    public FractalKernel[] kernels;
    public int colorSelection = 0;
    public int kernelSelection = 0;
    public ControlPanel controlPanel;

    public DrawPanel(int width, int height) {
        super();
        WIDTH = width;
        HEIGHT = height;
        graphWindow = new GraphWindow(MandelbrotKernel.DEFAULT_X_CENTER, MandelbrotKernel.DEFAULT_Y_CENTER,
                MandelbrotKernel.DEFAULT_GRAPH_WIDTH, WIDTH, HEIGHT);
        setBackground(Color.WHITE);
        setSize(WIDTH, HEIGHT);
        colors = new Color[WIDTH][HEIGHT];
        registerKernels();
        registerColorSelectors();
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
        colorSelectionIndex %= colorSelectors[0].length;
        colorSelection = colorSelectionIndex;
    }
    public void setKernelSelection(int kernelSelection) {
        this.kernelSelection = kernelSelection;
    }
    public void setJuliaParameters(String xString, String yString) {
        double x, y;
        try {
            x = Double.parseDouble(xString);
        } catch (NumberFormatException e) {
            x = JuliaKernel.DEFAULT_X;
        }
        try {
            y = Double.parseDouble(yString);
        } catch (NumberFormatException e) {
            y = JuliaKernel.DEFAULT_Y;
        }
        ((JuliaKernel)kernels[1]).setJuliaParameters(x, y);
    }
    public void setCenter(String xString, String yString) {
        double xCenter, yCenter;
        try {
            xCenter = Double.parseDouble(xString);
        } catch (NumberFormatException e) {
            xCenter = kernels[kernelSelection].defaultX();
        }
        try {
            yCenter = Double.parseDouble(yString);
        } catch (NumberFormatException e) {
            yCenter = kernels[kernelSelection].defaultY();
        }
        graphWindow.recenter(xCenter, yCenter);
    }
    public void setGraphWidth(String widthString) {
        double graphWidth;
        try {
            graphWidth = Double.parseDouble(widthString);
        } catch (NumberFormatException e) {
            graphWidth = kernels[kernelSelection].defaultWidth();
        }
        graphWindow.rescale(graphWidth);
    }
    private void registerColorSelectors() {
        colorSelectors = new ColorSelector[kernels.length][3];
        for(int i = 0; i < kernels.length; i++) {
            colorSelectors[i][0] = new GradualColorSelector(kernels[i].getMaxIterations());
            colorSelectors[i][1] = new CrazyColorSelector();
            colorSelectors[i][2] = new HsvColorSelector(kernels[i].getMaxIterations());
        }
    }
    private void registerKernels() {
        kernels = new FractalKernel[2];
        kernels[0] = new MandelbrotKernel(5000, 2.0);
        kernels[1] = new JuliaKernel(500, 2.0);
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
        return colorSelectors[kernelSelection][colorSelection].getColor(color);
    }
    private void drawPointAt(int i, int j, Color color) {
        Graphics graphics = this.getGraphics();
        graphics.setColor(color);
        graphics.drawLine(i, j, i, j+1);
    }
    private class CatchMouseClick implements MouseListener {
        private Point startPoint;
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
            startPoint = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Point endPoint = e.getPoint();
            double xStart = graphWindow.getGraphX(startPoint.x);
            double xEnd = graphWindow.getGraphX(endPoint.x);
            double yStart = graphWindow.getGraphY(startPoint.y);
            double yEnd = graphWindow.getGraphY(endPoint.y);
            double newX = (xStart + xEnd) / 2.0;
            double newY = (yStart + yEnd) / 2.0;
            double newWidth = xStart - xEnd;
            newWidth = newWidth < 0 ? -1.0 * newWidth : newWidth;
            controlPanel.xField.setText(newX + "");
            controlPanel.yField.setText(newY + "");
            controlPanel.widthField.setText(newWidth + "");
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
