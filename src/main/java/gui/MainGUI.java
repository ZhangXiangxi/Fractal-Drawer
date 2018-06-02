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
        drawPanel = new DrawPanel(1000, 800);
        drawPanel.setBounds(0, 0, 1000, 800);
        mainPanel.add(drawPanel);
        controlPanel.setBounds(1000, 0, 120, 800);
        mainPanel.add(controlPanel);
    }
    private void initialControlPanel() {
        controlPanel.setSize(120, 800);

        controlPanel.setBackground(Color.GRAY);
        controlPanel.setLayout(new GridLayout(3, 1));

        JPanel colorSelectorPanel = new JPanel();
        colorSelectorPanel.setLayout(new GridLayout(2, 1));
        colorSelectorPanel.add(new JLabel("配色方案:"));
        JTextField colorSelectorField = new JTextField("0");
        colorSelectorPanel.add(colorSelectorField);
        controlPanel.add(colorSelectorPanel);


        JPanel centerSelectionPanel = new JPanel();
        centerSelectionPanel.setLayout(null);
        centerSelectionPanel.setSize(120, 100);

        JPanel centerLabelPanel = new JPanel();
        centerLabelPanel.setBounds(0,0,30,100);
        centerLabelPanel.setLayout(new GridLayout(2,1));
        centerLabelPanel.add(new JLabel("x"));
        centerLabelPanel.add(new JLabel("y"));
        centerSelectionPanel.add(centerLabelPanel);

        JPanel centerTextPanel = new JPanel();
        centerTextPanel.setBounds(30, 0, 90, 100);
        centerTextPanel.setLayout(new GridLayout(2,1));
        JTextField xField = new JTextField(DrawPanel.DEFAULT_X_CENTER+"");
        JTextField yField = new JTextField(DrawPanel.DEFAULT_Y_CENTER+"");
        centerTextPanel.add(xField);
        centerTextPanel.add(yField);
        centerSelectionPanel.add(centerTextPanel);

        controlPanel.add(centerSelectionPanel);

        JPanel buttonPanel = new JPanel();
        JButton drawMBButton = new JButton("画图");
        drawMBButton.addActionListener(e-> {
            drawPanel.setColorSelection(colorSelectorField.getText());
            drawPanel.setCenter(xField.getText(), yField.getText());
            drawPanel.drawGraph();
        });
        buttonPanel.add(drawMBButton);
        controlPanel.add(buttonPanel);
    }
    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
        mainGUI.setSize(1120, 800);
        mainGUI.setResizable(false);
    }
}
