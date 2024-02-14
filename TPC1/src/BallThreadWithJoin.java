import java.awt.*;

public class BallThreadWithJoin extends Thread{
    private Ball ball;
    private Thread thread;
    public BallThreadWithJoin(Ball ball) {
        this.ball = ball;
    }

    public void setThread(Thread thread){
        this.thread = thread;
    }

    @Override
    public void run(){
        if (thread == null)
            throw new IllegalStateException(("Thread is not initialized."));

        try{
            Color initialColor = ball.getColor();
            for(int i=1; i < ball.getIterationsNumber(); i++){
                ball.move();

                System.out.println("Thread name = "
                        + Thread.currentThread().getName());

                if( i == (ball.getIterationsNumber() / 2)){
                    if (thread.getState() == State.NEW)
                        thread.start();
                    ball.setColor(Color.lightGray);
                    thread.join();
                    ball.setColor(initialColor);
                }

                Thread.sleep(5);
                if (i + 2 == ball.getIterationsNumber()){
                    ball.setColor(Color.darkGray);
                }
            }

        } catch(InterruptedException ignored){
            System.out.println("Interrupted exception!");
        }
    }
}
