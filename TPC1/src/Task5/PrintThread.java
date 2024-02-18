package Task5;

public class PrintThread extends Thread {
    private char symbol;
    private int iterations;
    PrintThread(char symbol, int iterations){
        this.symbol = symbol;
        this.iterations = iterations;
    }
    @Override
    public void run(){
        for (int i = 0; i < iterations; i++){
            System.out.print(symbol);
            if((i + 1) % 100 == 0) System.out.print("\n");
        }
    }
}
