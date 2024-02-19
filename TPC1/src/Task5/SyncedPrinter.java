package Task5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SyncedPrinter {
    private boolean access;
    private int printedSymbols;
    private boolean stop;

    public SyncedPrinter(){
        access = true;
        printedSymbols = 0;
        stop = false;
    }

    public synchronized boolean getAccess(){
        return access;
    }

    public synchronized boolean isStop(){
        return  stop;
    }

    public synchronized void waitAndChange(boolean threadsPermissionValue, char symbol){
        while(getAccess()!= threadsPermissionValue){ // checks is access from SyncPrintThread can be given
            try{
                wait(); // if not, wait
            } catch (InterruptedException ex){
                Logger.getLogger(SyncedPrinter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.print(symbol);
        access = !access; // inverse value of access
        printedSymbols++;
        if(printedSymbols % 100 == 0)
            System.out.println();
        if(printedSymbols + 1 == 10000)
            stop = true;
        notifyAll(); // notifies all waiting threads

    }
}
