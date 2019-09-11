package com.RevDeBug;

import CarsEconomy.Cars;
import Loops.Looper;
import MultiThread.MyThread;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialDarkTheme;
import mdlaf.themes.MaterialLiteTheme;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class MainWindow {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel helloJPanel;
    private JPanel ExceptionsJPanel;
    private JPanel ThreadsJPanel;
    private JPanel LoopsJPanel;
    private JLabel helloPanelContent;
    private JPanel demoJPanel;
    private JLabel demoPanelContent;
    private JLabel exceptionsPanelContent;
    private JLabel threadPanelContent;
    private JButton executeButton;
    private JButton exceptionExecuteButton;
    private JButton threadExecuteButton;
    private JList threadMessageList;
    private JScrollPane threadMessageScrollPane;
    private JLabel loopsPanelContent;
    private JList loopsCarsList;
    private JScrollPane loopsCarsScrollPane;
    private JButton loopsExecuteButton;


    public MainWindow()
    {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();
        frame.setTitle("RevDeBug Demo");
        frame.setPreferredSize(new Dimension(700, 350));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};

        DefaultListModel<String> messages = new DefaultListModel<String>();
        DefaultListModel<String> cars = new DefaultListModel<String>();

        mainPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        demoJPanel = new JPanel();
        helloJPanel = new JPanel(gridBagLayout);
        ExceptionsJPanel = new JPanel(gridBagLayout);
        ThreadsJPanel = new JPanel(gridBagLayout);
        LoopsJPanel = new JPanel(gridBagLayout);
        executeButton = new JButton();
        exceptionExecuteButton = new JButton();
        threadExecuteButton = new JButton();

        demoPanelContent = new JLabel();
        helloPanelContent = new JLabel();
        exceptionsPanelContent = new JLabel();
        threadPanelContent = new JLabel();

        threadMessageList = new JList(messages);
        threadMessageScrollPane = new JScrollPane(threadMessageList);
        threadMessageScrollPane.setPreferredSize(new Dimension(350, 150));

        loopsCarsList = new JList(cars);
        loopsCarsScrollPane = new JScrollPane(loopsCarsList);
        loopsCarsScrollPane.setPreferredSize(new Dimension(350, 100));
        loopsPanelContent = new JLabel();
        loopsExecuteButton = new JButton();

        tabbedPane1.setPreferredSize(new Dimension(690, 310));
        UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
        UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);

        tabbedPane1.add("Demo application", demoJPanel);
        tabbedPane1.add("Hello, RevDeBug", helloJPanel);
        tabbedPane1.add("Exceptions", ExceptionsJPanel);
        tabbedPane1.add("Threads", ThreadsJPanel);
        tabbedPane1.add("Loops", LoopsJPanel);

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                messages.clear();
                cars.clear();
            }
        });

        demoJPanel.add(demoPanelContent);
        demoPanelContent.setText("<html>RevDeBug is a solution that improves the effectiveness of root cause detection and elimination of bugs.<br>" +
                "Provided examples will help you learn how to use our Reverse debugger as efficient as possible.<br>" +
                "Click on one of the buttons and you will get further instructions.</html>");

        GridBagConstraints helloGridBagConst = new GridBagConstraints();
        helloGridBagConst.gridx = 0;
        helloGridBagConst.gridy = 0;

        helloJPanel.add(helloPanelContent, helloGridBagConst);
        helloGridBagConst.gridy = 1;
        helloJPanel.add(executeButton, helloGridBagConst);
        executeButton.setText("Execute");
        helloPanelContent.setText("<html>This example will introduce you to basic ideas of reverse debbuging.<br>" +
                "Click on Execute button and stop debugging.</html>");

        GridBagConstraints exceptionsGridBagConst = new GridBagConstraints();
        exceptionsGridBagConst.gridx = 0;
        exceptionsGridBagConst.gridy = 0;

        ExceptionsJPanel.add(exceptionsPanelContent, exceptionsGridBagConst);
        exceptionsGridBagConst.gridy = 1;
        ExceptionsJPanel.add(exceptionExecuteButton, exceptionsGridBagConst);
        exceptionExecuteButton.setText("Execute");
        exceptionsPanelContent.setText("<html>This example will throw an exception.<br>" +
                "No worries, it is expected.<br>" +
                "Click on execute and find out how to fix bugs with RevDeBug.</html>");

        GridBagConstraints threadGridBagConst = new GridBagConstraints();
        threadGridBagConst.gridx = 0;
        threadGridBagConst.gridy = 0;

        ThreadsJPanel.add(threadPanelContent, threadGridBagConst);
        threadGridBagConst.gridy = 1;
        ThreadsJPanel.add(threadMessageScrollPane, threadGridBagConst);
        threadGridBagConst.gridy = 2;
        ThreadsJPanel.add(threadExecuteButton, threadGridBagConst);
        threadExecuteButton.setText("Execute");
        threadPanelContent.setText("<html>Following sample will run 30 threads parallel.</html>");

        GridBagConstraints loopsGridBagConst = new GridBagConstraints();
        loopsGridBagConst.gridx = 0;
        loopsGridBagConst.gridy = 0;

        LoopsJPanel.add(loopsPanelContent, loopsGridBagConst);
        loopsGridBagConst.gridy = 1;
        LoopsJPanel.add(loopsCarsScrollPane, loopsGridBagConst);
        loopsGridBagConst.gridy = 2;
        LoopsJPanel.add(loopsExecuteButton, loopsGridBagConst);
        loopsExecuteButton.setText("Execute");
        loopsPanelContent.setText("<html>Learn how you can easily jump through next iterations of loops with RevDeBug.<br>" +
                "Hit Execute, stop debugging and find out how RevDeBugs helps with loops.</html>");

        mainPanel.add(tabbedPane1);

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(executeButton.getText().equals("Close"))
                {
                    System.exit(0);
                }
                else
                {
                    executeButton.setText("Close");
                }

                String who = System.getProperty("user.name");
                int currentHour = LocalDateTime.now().getHour();

                String when = (currentHour >= 12 && currentHour < 18) ? "Afternoon" :
                        (currentHour >= 18 && currentHour < 22) ? "Evening" :
                                (currentHour >= 22 && currentHour < 6) ? "Night" : "Morning";

                helloPanelContent.setText("Good " + when + " " + who + " !");
            }
        });

        exceptionExecuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cars cars = new Cars();
                cars.Calculate();
            }
        });

        threadExecuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messages.clear();
                int threadCount = 30;
                for(int threadNo = 0; threadNo < threadCount; threadNo++)
                {
                    Thread newThread = new MyThread(messages);
                    newThread.setName(threadNo + 1 + "");
                    newThread.start();
                }
            }
        });

        loopsExecuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cars.clear();
                Looper looper = new Looper();
                looper.CollectCars().forEach((item) ->
                {
                    cars.addElement(item);
                });
            }
        });
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
}
