public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball){
        b = ball;
    }
    @Override
    public void run(){
        try{
            // Interrupt if Ball is in Basket
            for(int i=1; i<10000 && !b.isInBasket(); i++){
                b.move();

                System.out.println("Thread name = "
                        + Thread.currentThread().getName());

                // Previously, with if (b.isInBasket()) this.interrupt()
                // We set interrupt flag as true. But the Thread was not
                // terminated immediately. Instead, we still printed text
                // and started sleep() of the thread. In the method sleep
                // JVM? checks whether this thread is interrupted. And if so
                // it throws InterruptedException.

                Thread.sleep(5);
            }
        } catch(InterruptedException ignored){
            System.out.println("INTERRUPTED EXCEPTION!!!");
        }
    }
}