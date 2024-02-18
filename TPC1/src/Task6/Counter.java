package Task6;

public class Counter {
    private int value = 0;

    public void increment(){
        value++;
    }
    public void decrement(){
        value--;
    }
    public void print(){
        System.out.printf("Counter value is %d%n", value);
    }
}
