package ThreadsTesting;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadsTesting {
    public static void main(String[] args){
        First first = new First();
        Second second = new Second(first);
        second.start();
    }
}

