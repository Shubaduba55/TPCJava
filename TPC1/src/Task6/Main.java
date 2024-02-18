package Task6;

public class Main {
    public static void main(String[] args){
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
        }catch (InterruptedException e){}

        counter.print();
    }
}
