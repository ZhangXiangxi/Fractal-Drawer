package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class ControlPanel extends JPanel {
    public DrawPanel drawPanel;

    public ControlPanel(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        initialize();
    }
    private void initialize() {
        setSize(120, 800);

        setBackground(Color.GRAY);
        setLayout(new GridLayout(3, 1));

        JPanel colorSelectorPanel = new JPanel();
        colorSelectorPanel.setLayout(new GridLayout(2, 1));
        colorSelectorPanel.add(new JLabel("配色方案:"));
        JTextField colorSelectorField = new JTextField("0");
        colorSelectorPanel.add(colorSelectorField);
        add(colorSelectorPanel);


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

        add(centerSelectionPanel);

        JPanel buttonPanel = new JPanel();
        JButton drawMBButton = new JButton("画图");
        drawMBButton.addActionListener(e-> {
            drawPanel.setColorSelection(colorSelectorField.getText());
            drawPanel.setCenter(xField.getText(), yField.getText());
            drawPanel.drawGraph();
        });
        buttonPanel.add(drawMBButton);
        add(buttonPanel);
    }
}
