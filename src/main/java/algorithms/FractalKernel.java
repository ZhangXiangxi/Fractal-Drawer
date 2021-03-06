package algorithms;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public interface FractalKernel {
    public abstract double depthAt(double x, double y);
    public abstract double defaultX();
    public abstract double defaultY();
    public abstract double defaultWidth();
    int getMaxIterations();
}
