package com.github.ajstri.echoedcalculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI extends JFrame {
    /* Variables */
    private JTextField userInputField;
    private JButton ParseExpression;

    public CalculatorGUI () {
        initialize();
        setCenterScreen();
    }

    public void initialize() {
        initializeComponents();
    }

    private void initializeComponents() {
        userInputField = new javax.swing.JTextField();
    }

    private void setCenterScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int locX = dim.width / 2 - this.getWidth() / 2;
        int locY = dim.height/2 - this.getHeight() / 2;
        this.setLocation(locX, locY);
    }

}
