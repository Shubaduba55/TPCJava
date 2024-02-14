package ThreadsTesting;

public class interruptedExample {
    public static void throwsException() throws InterruptedException {
        // Main thread, because this method is called from the main method
        Thread.currentThread().interrupt();

        // Will be in the status Interrupted. However, it doesn't stop the Thread.
        // It will be in the State Runnable.
        System.out.println("interrupted = " + Thread.currentThread().isInterrupted());
        System.out.println("state = " + Thread.currentThread().getState());

        // Check, whether Thread has been interrupted, if so, set flag as false.
        if (Thread.interrupted()) {

            System.out.println("is interrupted = " + Thread.currentThread().isInterrupted());
            System.out.println("is alive = " + Thread.currentThread().isAlive());
            // Handle interrupt by throwing an exception
            throw new InterruptedException();
        }

        for (int j = 0; j < 100; j++) {
            System.out.println("continue performance...");
        }
    }
}
