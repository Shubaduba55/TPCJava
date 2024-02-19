package Task5;

public class SyncPrintThread extends Thread {
    private final char symbol;
    private final SyncedPrinter printer;
    private final boolean accessValue;
    SyncPrintThread(SyncedPrinter printer, char symbol, boolean accessValue){
        this.symbol = symbol;
        this.printer = printer;
        this.accessValue = accessValue;

    }
    @Override
    public void run(){

        while(true){

            printer.waitAndChange(accessValue, symbol);

            if(printer.isStop())
                return;
        }
    }
}
