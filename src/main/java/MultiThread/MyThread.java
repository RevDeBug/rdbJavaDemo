package MultiThread;

import javax.swing.*;
import java.beans.EventHandler;
import java.util.EventObject;
import java.util.List;
import java.util.Random;

public class MyThread extends Thread{
    private int _number;
    private Random _random;
    private static int _sharedNumber;
    private DefaultListModel<String> messages;

    public MyThread(DefaultListModel<String> messages){
        this.messages = messages;
    }

    public void run()
    {
        _random = new Random();
        int charHeight = 0;
        int threadID = Integer.parseInt(Thread.currentThread().getName());
        int max = 1000 + 20 * threadID;
        messages.addElement("Thread " + Thread.currentThread().getName() + " will process loop " + max + "times");

        while (_number < max)
        {
            ++_number;
            --_sharedNumber;
            if((threadID % 3) == 0 && (_sharedNumber % 20) == 0)
            {
                charHeight = getCharHeight();
            }
        }
        messages.addElement("Thread " + Thread.currentThread().getName() + " has ended.");
    }

    private int getCharHeight()
    {
        // just a tribute to MS Word ;)
        return 8;
    }
}
