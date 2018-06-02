package algorithms;

/**
 * Created by Xiangxi on 2018/6/1.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */ /*
// -0.743030 + 0.126433i @ 0.016110 /0.75

double graphHeight = HEIGHT / WIDTH * graphWidth;


double RATIO = 0.75;
int IMAGEWIDTH = WIDTH;
int IMAGEHEIGHT = HEIGHT;
double PIXELSIZE = graphWidth / IMAGEWIDTH;
double COFFSET = IMAGEWIDTH % 2 == 0 ? (IMAGEWIDTH / 2) - 0.5 : (IMAGEWIDTH / 2);
double ROFFSET = IMAGEHEIGHT % 2 == 0 ? (IMAGEHEIGHT / 2) - 0.5 : (IMAGEHEIGHT / 2);
*/
public class GraphWindow {
    private double graphCenterX;
    private double graphCenterY;
    private int windowCenterX;
    private int windowCenterY;
    private double ratio;
    private double windowWidth;

    public GraphWindow(double graphCenterX, double graphCenterY, double graphWidth, int windowWidth, int windowHeight) {
        this.graphCenterX = graphCenterX;
        this.graphCenterY = graphCenterY;
        windowCenterX = windowWidth / 2;
        windowCenterY = windowHeight / 2;
        ratio = graphWidth / (double) windowWidth;
        this.windowWidth = (double)windowWidth;
    }
    public void recenter(double graphCenterX, double graphCenterY) {
        this.graphCenterX = graphCenterX;
        this.graphCenterY = graphCenterY;
    }
    public void rescale(double newGraphWidth) {
        ratio = newGraphWidth / windowWidth;
    }

    public double getGraphX(int windowX) {
        return (double) (windowX - windowCenterX) * ratio + graphCenterX;
    }

    public double getGraphY(int windowY) {
        return (double) (windowY - windowCenterY) * ratio + graphCenterY;
    }
}
