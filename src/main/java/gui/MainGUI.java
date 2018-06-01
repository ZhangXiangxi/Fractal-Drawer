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
    private DrawPanel drawPanel;
    public MainGUI() throws HeadlessException {
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialControlPanel();
        initialMainPanel();
        this.setContentPane(mainPanel);
        this.setTitle("HIT计算机图形学实验 张翔熙");
    }
    // TODO: reformat the function structures here
    private void initialMainPanel() {
        mainPanel.setLayout(null);
        drawPanel = new DrawPanel(360, 340);
        drawPanel.setBounds(0, 0, 360, 340);
        mainPanel.add(drawPanel);
        controlPanel.setBounds(360, 0, 90, 340);
        mainPanel.add(controlPanel);
    }
    private void initialControlPanel() {
        controlPanel.setSize(90, 340);
        // TODO: size adjustment

        controlPanel.setBackground(Color.GRAY);
        controlPanel.setLayout(new GridLayout(1,1));

        JPanel buttonPanel = new JPanel();
        JButton drawMBButton = new JButton("Mandelbrot");
        drawMBButton.addActionListener(e->drawPanel.drawMandelbrot());
        buttonPanel.add(drawMBButton);
        controlPanel.add(buttonPanel);
    }
    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
        mainGUI.setSize(450, 340);
        mainGUI.setResizable(false);
        // TODO: size adjustment
    }
}
