package ThreadsTesting;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Second extends Thread {
    private Thread thread;

    public Second(Thread t) {
        this.thread = t;
    }

    @Override
    public void run() {
        try {
            System.out.println("isAlive = " + thread.isAlive() + ", state = " + thread.getState());

            thread.start();
            Thread.sleep(200);

            System.out.println("isAlive = " + thread.isAlive() + ", state = " + thread.getState());

            thread.interrupt();
            System.out.println("isInterrupted = " + thread.isInterrupted());
            System.out.println("isAlive = " + thread.isAlive() + ", state = " + thread.getState());

            thread.join();
            System.out.println("isInterrupted = " + thread.isInterrupted());
        } catch (InterruptedException ex) {
            Logger.getLogger(Second.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
