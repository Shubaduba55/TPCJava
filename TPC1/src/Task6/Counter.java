package Task6;

public class Counter {
    final Object sync = new Object();
    final Object sync1 = new Object();
    private int value = 0;
    private int asyncValue = 0;

    public void increment(){
        value++;
    }
    public void decrement(){
        value--;
    }

    public synchronized void syncMethodIncrement(){
        value++;
    }
    public synchronized void syncMethodDecrement(){
        value--;
    }


    public void syncBlockIncrement(){
        synchronized (this){
            value++;
        }
        asyncValue++;
    }
    public void syncBlockDecrement(){
        synchronized (this){
            value--;
        }
        asyncValue--;
    }

    public void syncBlockObjectIncrement(){
        synchronized (sync){
            value++;
        }
        asyncValue++;
    }
    public void syncBlockObjectDecrement(){
        synchronized (sync){
            value--;
        }
        asyncValue--;
    }

    public void printValue(){
        System.out.printf("Counter value is %d%n", value);
    }
    public void printAsyncValue(){
        System.out.printf("Counter async value is %d%n", asyncValue);
    }
}
