package com.RevDeBug;

import CarsEconomy.Cars;

import javax.swing.*;
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
    private JButton executeButton;
    private JButton exceptionExecuteButton;


    public MainWindow()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();
        frame.setTitle("RevDeBug Demo");
        frame.setPreferredSize(new Dimension(700, 300));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};

        mainPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        demoJPanel = new JPanel();
        helloJPanel = new JPanel(gridBagLayout);
        ExceptionsJPanel = new JPanel(gridBagLayout);
        ThreadsJPanel = new JPanel();
        LoopsJPanel = new JPanel();
        executeButton = new JButton();
        exceptionExecuteButton = new JButton();

        demoPanelContent = new JLabel();
        helloPanelContent = new JLabel();
        exceptionsPanelContent = new JLabel();

        tabbedPane1.setPreferredSize(new Dimension(690, 290));
        UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
        UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);

        tabbedPane1.add("Demo application", demoJPanel);
        tabbedPane1.add("Hello, RevDeBug", helloJPanel);
        tabbedPane1.add("Exceptions", ExceptionsJPanel);
        tabbedPane1.add("Threads", ThreadsJPanel);
        tabbedPane1.add("Loops", LoopsJPanel);

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
                "Click on Execute button and stop debugging. Your recording will be loaded automatically.<br>" +
                "Follow instructions in comments.</html>");

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
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
}
