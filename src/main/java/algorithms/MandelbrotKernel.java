package algorithms;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class MandelbrotKernel implements FractalKernel{
    public int maxIterations;
    public double escapeRadius;

    public MandelbrotKernel(int maxIterations, double escapeRadius) {
        this.maxIterations = maxIterations;
        this.escapeRadius = escapeRadius;
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
            if (smodz >= escapeRadius) {
                return i;
            }
        }
        return -1.0;
    }
}
