package algorithms;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class JuliaKernel implements FractalKernel {
    public final static double DEFAULT_X_CENTER = -0.5271824;
    public final static double DEFAULT_Y_CENTER = -0.6124885999999998;
    public final static double DEFAULT_GRAPH_WIDTH = 1e-5;

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
    public double escapeRadius;

    public JuliaKernel(int maxIterations, double escapeRadius) {
        this.maxIterations = maxIterations;
        this.escapeRadius = escapeRadius;
    }

    @Override
    public double depthAt(double x, double y) {
        return 0; //TODO:
    }
}
