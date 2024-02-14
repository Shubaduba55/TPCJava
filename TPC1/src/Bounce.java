import javax.swing.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Bounce {
    public static void main(String[] args) {
        System.out.print("Choose task demonstration (2-4): ");

        Scanner myScanner = new Scanner(System.in);

        int taskNumber = myScanner.nextInt();

        Task t;

        switch (taskNumber){
            case 2:
                t = Task.TASK2;
                break;
            case 3:
                t = Task.TASK3;
                break;
            default:
                t = Task.TASK2;
        }
        BounceFrame frame = new BounceFrame(t);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        System.out.println("Thread name = " +
                Thread.currentThread().getName());

    }
}