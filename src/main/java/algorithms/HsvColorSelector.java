package algorithms;

import java.awt.*;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class HsvColorSelector implements ColorSelector {
    public final int maxIteration;
    public float multiplier = 0.01f;

    public HsvColorSelector(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    @Override
    public Color getColor(double colorIndicator) {

        if (colorIndicator < 0)
            return Color.BLACK;

        float ratio = (float)colorIndicator * multiplier;
        return Color.getHSBColor(ratio, 1.0f, 1.0f);
    }
}
