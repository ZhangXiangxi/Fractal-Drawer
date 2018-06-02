package algorithms;

import java.awt.*;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class CrazyColorSelector implements ColorSelector{
    @Override
    public Color getColor(double iterCount) {
        if (iterCount >= 0) {
            int sign = (int)iterCount % 3;
            if (sign == 0)
                return Color.RED;
            if (sign == 1)
                return Color.GREEN;
            if (sign == 2)
                return Color.BLUE;
            return Color.BLACK;
        }
        else
            return Color.RED;
    }
}
