package Task6;

public class Main {
    public static void main(String[] args){
        syncThreadsDemo(4, 10000);
        // twoAsyncThreads();
    }

    private static void  twoAsyncThreads(){
        int iterations = 100000;
        Counter counter = new Counter();

        Thread incThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < iterations ;i++){
                     counter.increment();
                }
            }
        });

        Thread decThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < iterations ;i++){
                    counter.decrement();
                }
            }
        });

        incThread.start();
        decThread.start();

        try{
            incThread.join();
            decThread.join();
        }catch (InterruptedException ignored){}

        counter.printValue();
    }

    private static void syncThreadsDemo(int numberOfThreads, int iterations){
        Counter counter = new Counter();

        Thread[] threads = new Thread[numberOfThreads];
        int half = numberOfThreads / 2;
        for(int i = 0; i < half; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < iterations; i++)
                        //counter.syncMethodIncrement();
                        // counter.syncBlockIncrement();
                        counter.syncBlockObjectIncrement();
                }
            });
        }

        for(int i = half; i < numberOfThreads; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < iterations; i++)
                        // counter.syncMethodDecrement();
                        counter.syncBlockDecrement();
                        // counter.syncBlockObjectDecrement();
                }
            });
        }

        for(Thread thread : threads)
            thread.start();

       try{
           for(Thread thread : threads)
               thread.join();
       }catch (InterruptedException ignored){}

        counter.printValue();
        counter.printAsyncValue();
    }
}
