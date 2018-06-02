package algorithms;

import java.awt.*;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class GradualColorSelector implements ColorSelector {
    public final int MAXITERNUMBER;

    public GradualColorSelector(int MAXITERNUMBER) {
        this.MAXITERNUMBER = MAXITERNUMBER;
    }

    @Override
    public Color getColor(double iterCount) {
        Color colorLeft = Color.BLACK;
        Color colorRight = Color.RED;
        float[] leftComponent = colorLeft.getRGBColorComponents(null);
        float[] rightComponent = colorRight.getRGBColorComponents(null);
        if (iterCount > 0) {
            float ratio = (float)iterCount / (float)MAXITERNUMBER;
            float[] finalColor = new float[3];
            for(int i = 0; i < 3; i++)
                finalColor[i] = leftComponent[i] + ratio * (rightComponent[i] - leftComponent[i]);
            return new Color(finalColor[0], finalColor[1], finalColor[2]);
        }
        else
            return colorRight;
    }
}
