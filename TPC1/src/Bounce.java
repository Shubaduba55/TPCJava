import javax.swing.*;
import java.util.Scanner;

public class Bounce {
    public static void main(String[] args) {
        System.out.print("Choose task demonstration (2-4): ");

        Scanner myScanner = new Scanner(System.in);

        int taskNumber = myScanner.nextInt();

        Task t = switch (taskNumber) {
            case 3 -> Task.TASK3;
            case 4 -> Task.TASK4;
            default -> Task.TASK2;
        };

        BounceFrame frame = new BounceFrame(t);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        System.out.println("Thread name = " +
                Thread.currentThread().getName());

    }
}