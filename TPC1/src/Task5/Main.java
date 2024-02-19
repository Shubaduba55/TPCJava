package Task5;

public class Main {
    public static void main(String[] args){
        // printAsync();
        printSync();
    }

    private static void printAsync(){
        int iterations = 5000;
        Thread A = new PrintThread('-', iterations);
        Thread B = new PrintThread('|', iterations);

        A.start();
        B.start();
    }

    private static void printSync(){
        SyncedPrinter printer = new SyncedPrinter();
        Thread A = new SyncPrintThread(printer,'-', true);
        Thread B = new SyncPrintThread(printer,'|', false);

        A.start();
        B.start();
    }
}
