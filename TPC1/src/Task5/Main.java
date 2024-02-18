package Task5;

public class Main {
    public static void main(String[] args){
        int iterations = 5000;
        Thread A = new PrintThread('-', iterations);
        Thread B = new PrintThread('|', iterations);

        A.start();
        B.start();
    }
}
