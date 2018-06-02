package algorithms;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class MandelbrotKernel implements FractalKernel{
    public int maxIterations;
    public double escapeRaduisSquare;
    public final static double DEFAULT_X_CENTER = -0.5271824;
    public final static double DEFAULT_Y_CENTER = -0.6124885999999998;
    public final static double DEFAULT_GRAPH_WIDTH = 1e-5;

    public MandelbrotKernel(int maxIterations, double escapeRadius) {
        this.maxIterations = maxIterations;
        this.escapeRaduisSquare = escapeRadius * escapeRadius;
    }

    @Override
    public double defaultX() {
        return DEFAULT_X_CENTER;
    }

    @Override
    public double defaultY() {
        return DEFAULT_Y_CENTER;
    }

    @Override
    public double defaultWidth() {
        return DEFAULT_GRAPH_WIDTH;
    }

    @Override
    public int getMaxIterations() {
        return maxIterations;
    }

    @Override
    public double depthAt(double cx, double cy) {
        double x = 0;
        double y = 0;
        double newX;
        double newY;

        double smodz = 0;
        int i = 0;
        while (i < maxIterations) {
            newX = x * x - y * y + cx;
            newY = 2 * x * y + cy;
            x = newX;
            y = newY;
            i++;

            smodz = x * x + y * y;
            if (smodz >= escapeRaduisSquare) {
                return i;
            }
        }
        return -1.0;
    }
}
