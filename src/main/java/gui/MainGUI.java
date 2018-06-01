package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xiangxi on 2018/6/1.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class MainGUI extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    private DrawPanel drawPanel = new DrawPanel();
    public MainGUI() throws HeadlessException {
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialControlPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(drawPanel);
        mainPanel.add(controlPanel);
        this.setContentPane(mainPanel);
        this.setTitle("HIT计算机图形学实验 张翔熙");
    }
    private void initialControlPanel() {
        controlPanel.setSize(90, 300);
        // TODO: size adjustment
        controlPanel.setBackground(Color.GRAY);
        controlPanel.setLayout(new GridLayout(1,1));

        JPanel buttonPanel = new JPanel();
        JButton drawMBButton = new JButton("Mandelbrot");
        drawMBButton.addActionListener(e->drawPanel.drawMandelBrot());
        buttonPanel.add(drawMBButton);
        controlPanel.add(buttonPanel);
    }
    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
        mainGUI.setSize(450, 340);
        // TODO: size adjustment
    }
}
