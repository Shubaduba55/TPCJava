import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BounceFrame extends JFrame implements BallInBasketListener {
    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    JTextArea textBallsInBaskets;
    private final Task task;
    public BounceFrame(Task task){
        this.task = task;

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();

        if (task == Task.TASK2)
            constructorSetupCodeForTask2();


        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");


        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (task){
                    case TASK2 ->
                        createBallAndStartThread();

                    case TASK3 ->
                        createRedAndBlueBalls(0, 0, 100);
                    case TASK4 ->{
                        ArrayList<Ball> balls = new ArrayList<>();
                        int iterations = 300;
                        balls.add(new Ball(canvas, 0, 0, Color.red, iterations));
                        balls.add(new Ball(canvas, 100, 0, Color.green, iterations));
                        balls.add(new Ball(canvas, 0, 100, Color.blue, iterations));
                        joinBalls(balls);
                    }

                }
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
        if (task == Task.TASK2)
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

    private void constructorSetupCodeForTask2(){
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
    }
    private void createBallAndStartThread(){
        Ball b = new Ball(canvas);
        canvas.addBall(b);

        BallThread thread = new BallThread(b);
        thread.start();
        System.out.println("Thread name = " +
                thread.getName());
    }
    private void createRedAndBlueBalls(int x, int y, int numberOfLowPriorityBalls){

        ArrayList<BallThread> threads = new ArrayList<>();
        // Create many blue Low priority Balls
        Ball blueBall;
        BallThread threadLP;
        for (int i = 0; i < numberOfLowPriorityBalls; i++){
            blueBall = new Ball(canvas, x, y, Color.blue);
            canvas.addBall(blueBall);

            threadLP = new BallThread(blueBall);
            threadLP.setPriority(Thread.MIN_PRIORITY);

            threads.add(threadLP);
        }

        // Create 1 red High priority Ball
        Ball redBall = new Ball(canvas, x, y, Color.red);
        canvas.addBall(redBall);

        BallThread threadHP = new BallThread(redBall);
        threadHP.setPriority(Thread.MAX_PRIORITY);
        threads.add(threadHP);

        // Start all Threads starting with the Red Ball (High Priority)
        for (int i = threads.size() - 1; i >= 0; i-- ){
            threads.get(i).start();
            System.out.println("Thread name = " +
                    threads.get(i).getName());
        }
    }
    private void joinBalls(ArrayList<Ball> balls){
        ArrayList<BallThreadWithJoin> threads = new ArrayList<>();

        for (Ball ball : balls) canvas.addBall(ball);

        for (int i = 0; i < balls.size() - 1; i++){
            threads.add(new BallThreadWithJoin(balls.get(i)));
        }

        int nThreads = threads.size();

        // Connect threads
        for (int i = 0; i < nThreads - 1; i++){
            threads.get(i).setThread(threads.get(i+1));
        }

        // Create BallThread for last ball and connect BallThread to the last BallThreadWithJoin
        BallThread threadLast = new BallThread(balls.get(balls.size()-1));
        threads.get(nThreads-1).setThread(threadLast);

        //Start threads
//        for (BallThreadWithJoin thread : threads)
//            thread.start();
        threads.get(0).start();
    }
}