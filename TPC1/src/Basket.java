import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Basket {
    private Component canvas;
    private int signature;
    private double x;
    private double y;
    private static double radius;
    private static double [] xCoordinates;
    private static double [] yCoordinates;

    public Basket(Component c, int signature){
        canvas = c;

        if (xCoordinates == null ||
                yCoordinates == null){
            throw new IllegalStateException(("xCoordinates or/and yCoordinates is/are not initialized"));
        }

        int maxOptions = xCoordinates.length * yCoordinates.length;

        if(signature > maxOptions - 1 || signature < 0){
            throw new IllegalArgumentException(
                    String.format(
                            "Invalid signature value, passed %d, when maximum signature value is %d",
                            signature,
                            maxOptions)
            );
        }
        this.signature = signature;
        updateCoordinates();
    }

    /**
     * Updates coordinates for Basket with new coordinate values from arrays
     */
    public void updateCoordinates(){
        int xId = signature / xCoordinates.length;
        int yId = signature % yCoordinates.length;

        this.x = xCoordinates[xId];
        this.y = yCoordinates[yId];
    }

    public boolean isInBasket(double x, double y){
        double x_2 = Math.pow(x - this.x, 2);
        double y_2 = Math.pow(y - this.y, 2);
        return (x_2 + y_2) <= Math.pow(radius, 2);
    }

    public void draw (Graphics2D g2){
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(x,y,2*radius,2*radius));

    }

    public static void setRadius(double radius){
        Basket.radius = radius;
    }
    public static double getRadius(double radius){
        return Basket.radius;
    }

    public static void setXCoordinates(double[] xCoordinates){
        Basket.xCoordinates = xCoordinates;
    }
    public static void setYCoordinates(double[] yCoordinates){
        Basket.yCoordinates = yCoordinates;
    }

    private static void updateCoordinates(double[] coordinates, double windowParameter){
        if (xCoordinates == null ||
                yCoordinates == null){
            throw new IllegalStateException(("xCoordinates or/and yCoordinates is/are not initialized"));
        }

        int n = coordinates.length;
        double step = windowParameter/(n-1);
        for (int i = 0; i < n; i++){
            coordinates[i] = step*i;
        }
    }

    /**
     * Update coordinates for static variable xCoordinates of the class Basket.
     * @param windowWidth
     */
    public static void updateXCoordinates(double windowWidth){
        updateCoordinates(xCoordinates, windowWidth);
    }

    /**
     * Update coordinates for static variable yCoordinates of the class Basket.
     * @param windowHeight
     */
    public static void updateYCoordinates(double windowHeight){
        updateCoordinates(yCoordinates, windowHeight);
    }
}
