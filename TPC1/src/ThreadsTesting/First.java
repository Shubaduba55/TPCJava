package ThreadsTesting;

import java.util.logging.Level;
import java.util.logging.Logger;

public class First extends Thread {
    @Override
    public void run(){
        System.out.println("tic….");
        try {
            Thread.sleep(4000);
            System.out.println("Thread First Finished!");
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException occured.");
            Logger.getLogger(First.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}