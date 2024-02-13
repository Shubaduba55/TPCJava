import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;


public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Basket> baskets = new ArrayList<>();
    private int nBallsInBaskets = 0;
    public void addBall(Ball b){
        this.balls.add(b);
    }
    public void addBasket(Basket basket) {
        baskets.add(basket);
    }
    public ArrayList<Basket> getBaskets(){return baskets;}

    private ArrayList<BallInBasketListener> listeners = new ArrayList<>();
    public int getBallsInBaskets(){
        return nBallsInBaskets;
    }

    public void addBallInBasketListener(BallInBasketListener listener) {
        listeners.add(listener);
    }

    // Method to be called when a ball gets into a basket
    private void fireBallInBasketEvent() {
        BallInBasketEvent event = new BallInBasketEvent();
        for (BallInBasketListener listener : listeners) {
            listener.ballInBasketOccurred(event);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for (Basket basket : baskets) {
            basket.draw(g2);
        }

        for(int i=0; i<balls.size();i++){
            Ball b = balls.get(i);

            if (!b.isInBasket())
                b.draw(g2);
            else{
                balls.remove(i);
                nBallsInBaskets += 1;
                fireBallInBasketEvent();
                System.out.printf("Ball got into basket. Overall number of such balls is %d%n", nBallsInBaskets);
            }

        }
    }
}