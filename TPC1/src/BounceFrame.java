import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BounceFrame extends JFrame implements BallInBasketListener {
    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    JTextArea textBallsInBaskets;
    public BounceFrame(){
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();
        // Add listener for Ball getting into Basket
        canvas.addBallInBasketListener(this);


        double [] xCoords = new double[3];
        double [] yCoords = new double[3];

        // Set overall data for Baskets
        Basket.setRadius(10);
        Basket.setXCoordinates(xCoords);
        Basket.setYCoordinates(yCoords);

        // Create Basket objects and add them to the canvas
        for(int i = 0; i < xCoords.length * yCoords.length; i++){
            if( (i-1) % 3 != 0){
                canvas.addBasket(new Basket(canvas, i));
            }
        }

        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        // Set Text Value and Background Colour
        textBallsInBaskets =
                new JTextArea(String.format("Balls in baskets: %d", canvas.getBallsInBaskets()));
        textBallsInBaskets.setBackground(Color.lightGray);

        // Listener for resizing of the frame. Created to move Baskets.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Basket.updateXCoordinates(canvas.getWidth() - 20);
                Basket.updateYCoordinates(canvas.getHeight() - 20);
                for(Basket basket : canvas.getBaskets()){
                    basket.updateCoordinates();
                }
            }
        });

        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas);
                canvas.addBall(b);

                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(textBallsInBaskets);
        content.add(buttonPanel, BorderLayout.SOUTH);

    }

    // Method of Interface BallInBasketListener
    @Override
    public void ballInBasketOccurred(BallInBasketEvent event) {
        textBallsInBaskets.setText(
                String.format("Balls in baskets: %d", canvas.getBallsInBaskets())
        );
    }
}