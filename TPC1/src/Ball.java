import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {
    private BallCanvas canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;

    private int x = 0;
    private int y= 0;
    private int dx = 2;
    private int dy = 2;
    private boolean in_basket = false;

    public Ball(BallCanvas c){
        this.canvas = c;


        if(Math.random()<0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

    public static void f(){
        int a = 0;
    }

    public void draw (Graphics2D g2){
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));

    }

    public void move(){
        // if(in_basket) return;

        x+=dx;
        y+=dy;
        if(x<0){
            x = 0;
            dx = -dx;
        }
        else if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        else if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }

        for (int i = 0; i < canvas.getBaskets().size() && !in_basket; i++){
            if (canvas.getBaskets().get(i).isInBasket(this.x, this.y))
                in_basket = true;
        }

        this.canvas.repaint();

    }

    public boolean isInBasket(){
        return in_basket;
    }
}
