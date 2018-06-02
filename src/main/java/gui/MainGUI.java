package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xiangxi on 2018/6/1.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class MainGUI extends JFrame {
    private JPanel mainPanel = new JPanel();
    private ControlPanel controlPanel;
    private DrawPanel drawPanel;
    public MainGUI() throws HeadlessException {
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialMainPanel();
        this.setContentPane(mainPanel);
        this.setTitle("HIT计算机图形学实验 张翔熙");
    }
    // TODO: reformat the function structures here
    private void initialMainPanel() {
        mainPanel.setLayout(null);
        drawPanel = new DrawPanel(1000, 800);
        drawPanel.setBounds(0, 0, 1000, 800);
        mainPanel.add(drawPanel);
        JPanel emptyPanelLeft = new JPanel();
        emptyPanelLeft.setBounds(1000, 0, 25, 800);
        mainPanel.add(emptyPanelLeft);
        controlPanel = new ControlPanel(drawPanel);
        controlPanel.setBounds(1025, 0, 120, 800);
        drawPanel.linkToControl(controlPanel);
        mainPanel.add(controlPanel);
        JPanel emptyPanelRight = new JPanel();
        emptyPanelRight.setBounds(1145, 0, 25, 800);
        mainPanel.add(emptyPanelRight);
    }
    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
        mainGUI.setSize(1170, 800);
        mainGUI.setResizable(false);
    }
}
