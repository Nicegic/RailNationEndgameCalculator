package gui;

import cal.Controller;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private Controller controller;
    private FinishCalGUI finishCalGUI;
    private JTabbedPane tabs;


    public Window(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel title = new JLabel("Endspiel Hilfs-Tool");
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);
        controller = new Controller(this);
        setLocationRelativeTo(null);
        tabs = new JTabbedPane();
        finishCalGUI = new FinishCalGUI(controller);
        tabs.addTab("Zeitkalkulation gesamt",null,finishCalGUI, "Hier kannst du die Übersicht für alle Werke ansehen");
        add(tabs);
        pack();
        setVisible(true);
    }

    public void calculationDone(Object [][] durations, String window){
        switch(window){
            case "FinishCalGUI":
                finishCalGUI.calculationDone(durations);
                break;
            default:

        }
    }

    public JProgressBar getProgressBar(String tab){
        switch(tab) {
            case "FinishCalGUI":
                return finishCalGUI.enableProgressUpdate();
            default:
                return null;
        }
    }
}
