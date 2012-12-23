package customdrawing;

import java.io.IOException;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JFrame {

    private AndroidPanel droidPanel;

    public MainPanel() throws FontFormatException, IOException {
        droidPanel = new AndroidPanel();
        droidPanel.setPreferredSize(new Dimension(600, 700));
        /*Control buttons*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        JButton cycleColors = new JButton("Cycle Colors");
        cycleColors.addActionListener(droidPanel);
        JButton eatApple = new JButton("Eat Apple");
        eatApple.addActionListener(droidPanel);
        buttonPanel.add(cycleColors);
        buttonPanel.add(eatApple);
        /*----FINALIZE-----*/
        this.setLayout(new BorderLayout());
        this.add(droidPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        try {
            new MainPanel();
        } catch (Exception e) {
            System.err.println("font file not loaded, please make sure the droid font is in project folder.");
        }
    }
}
