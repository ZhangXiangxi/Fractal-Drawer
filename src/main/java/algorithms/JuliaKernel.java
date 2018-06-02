package algorithms;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class JuliaKernel implements FractalKernel {
    public final static double DEFAULT_X_CENTER = 0;
    public final static double DEFAULT_Y_CENTER = 0;
    public final static double DEFAULT_GRAPH_WIDTH = 3;

    public final static double DEFAULT_X = -0.8;
    public final static double DEFAULT_Y = 0.156;

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

    public int maxIterations;
    public double escapeRadiusSquare;
    public double cx, cy;

    public JuliaKernel(int maxIterations, double escapeRadius) {
        this.maxIterations = maxIterations;
        this.escapeRadiusSquare = escapeRadius * escapeRadius;
        cx = DEFAULT_X;
        cy = DEFAULT_Y;
    }

    public void setJuliaParameters(double x, double y) {
        cx = x;
        cy = y;
    }

    @Override
    public int getMaxIterations() {
        return maxIterations;
    }

    @Override
    public double depthAt(double x, double y) {
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
            if (smodz >= escapeRadiusSquare) {
                return i;
            }
        }
        return -1.0;
    }
}
