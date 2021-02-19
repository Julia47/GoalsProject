package Tools;

import java.awt.*;

public class ConvertorColor {
    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

//    public String toHexString(Color value) {
//        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
//                .toUpperCase();
//    }

    public String toHexString(javafx.scene.paint.Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) )
                .toUpperCase();
    }
}
