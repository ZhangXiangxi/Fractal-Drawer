package gui;

import algorithms.JuliaKernel;
import algorithms.MandelbrotKernel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xiangxi on 2018/6/2.
 * Contact him on xiangxi.zhang.cs@gmail.com
 */
public class ControlPanel extends JPanel {
    public DrawPanel drawPanel;
    public JTextField xField;
    public JTextField yField;
    public JTextField widthField;
    public JTextField juliaX;
    public JTextField juliaY;

    public ControlPanel(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        initialize();
    }
    private void initialize() {
        setSize(120, 800);

        //setBackground(Color.GRAY);
        setLayout(null);

        JPanel colorSelectorPanel = new JPanel();
        colorSelectorPanel.setLayout(null);
        colorSelectorPanel.setBounds(0, 35, 120, 100);
        JLabel colorSelectionLabel = new JLabel("配色方案:");
        colorSelectionLabel.setBounds(0, 0, 120, 50);
        colorSelectorPanel.add(colorSelectionLabel);
        JTextField colorSelectorField = new JTextField("0");
        colorSelectorField.setBounds(0, 50, 120, 50);
        colorSelectorPanel.add(colorSelectorField);
        add(colorSelectorPanel);


        JPanel graphSelectionPanel = new JPanel();
        graphSelectionPanel.setLayout(null);
        graphSelectionPanel.setBounds(0, 155, 120, 150);

        JLabel graphSelectionHeadPanel = new JLabel("选择图象：");
        graphSelectionHeadPanel.setBounds(0, 0, 120, 50);
        graphSelectionPanel.add(graphSelectionHeadPanel);

        JPanel graphSelectionLabels = new JPanel();
        graphSelectionLabels.setLayout(new GridLayout(2,1));
        graphSelectionLabels.setBounds(0, 50, 80, 100);
        graphSelectionLabels.add(new JLabel("Mandelbrot"));
        graphSelectionLabels.add(new JLabel("Julia"));
        graphSelectionPanel.add(graphSelectionLabels);

        JPanel graphSelectionButtonPanel = new JPanel();
        graphSelectionButtonPanel.setLayout(new GridLayout(2, 1));
        graphSelectionButtonPanel.setBounds(80, 50, 40, 100);
        ButtonGroup graphSelectionGroups = new ButtonGroup();
        JRadioButton mandelbrotButton = new JRadioButton();
        JRadioButton juliaButton = new JRadioButton();
        graphSelectionGroups.add(mandelbrotButton);
        graphSelectionGroups.add(juliaButton);
        graphSelectionButtonPanel.add(mandelbrotButton);
        graphSelectionButtonPanel.add(juliaButton);
        graphSelectionPanel.add(graphSelectionButtonPanel);
        mandelbrotButton.addChangeListener(e->{if(mandelbrotButton.isSelected()) changeGraphSelection(0);});
        juliaButton.addChangeListener(e->{if (juliaButton.isSelected()) changeGraphSelection(1);});

        add(graphSelectionPanel);


        JPanel juliaParameterPanel = new JPanel();
        juliaParameterPanel.setLayout(null);
        juliaParameterPanel.setBounds(0, 325, 120, 150);

        JLabel juliaParameterHeadLabel = new JLabel("Julia参数");
        juliaParameterHeadLabel.setBounds(0, 0, 120, 50);
        juliaParameterPanel.add(juliaParameterHeadLabel);

        JPanel juliaParameterLabelPanel = new JPanel();
        juliaParameterLabelPanel.setBounds(0, 50, 40, 100);
        juliaParameterLabelPanel.setLayout(new GridLayout(2, 1));
        juliaParameterLabelPanel.add(new JLabel("x"));
        juliaParameterLabelPanel.add(new JLabel("y"));
        juliaParameterPanel.add(juliaParameterLabelPanel);

        JPanel juliaParameterTextPanel = new JPanel();
        juliaParameterTextPanel.setLayout(new GridLayout(2, 1));
        juliaParameterTextPanel.setBounds(40, 50, 80, 100);
        juliaX = new JTextField(JuliaKernel.DEFAULT_X + "");
        juliaY = new JTextField(JuliaKernel.DEFAULT_Y + "");
        juliaParameterTextPanel.add(juliaX);
        juliaParameterTextPanel.add(juliaY);
        juliaParameterPanel.add(juliaParameterTextPanel);

        add(juliaParameterPanel);


        JPanel centerSelectionPanel = new JPanel();
        centerSelectionPanel.setLayout(null);
        centerSelectionPanel.setBounds(0, 495, 120, 200);

        JLabel centerSelectionHeadPanel = new JLabel("视图中心：");
        centerSelectionHeadPanel.setBounds(0, 0, 120, 50);
        centerSelectionPanel.add(centerSelectionHeadPanel);

        JPanel centerLabelPanel = new JPanel();
        centerLabelPanel.setBounds(0,50,30,150);
        centerLabelPanel.setLayout(new GridLayout(3,1));
        centerLabelPanel.add(new JLabel("x"));
        centerLabelPanel.add(new JLabel("y"));
        centerLabelPanel.add(new JLabel("宽"));
        centerSelectionPanel.add(centerLabelPanel);

        JPanel centerTextPanel = new JPanel();
        centerTextPanel.setBounds(30, 50, 90, 150);
        centerTextPanel.setLayout(new GridLayout(3,1));
        xField = new JTextField(MandelbrotKernel.DEFAULT_X_CENTER+"");
        yField = new JTextField(MandelbrotKernel.DEFAULT_Y_CENTER+"");
        widthField = new JTextField(MandelbrotKernel.DEFAULT_GRAPH_WIDTH + "");
        centerTextPanel.add(xField);
        centerTextPanel.add(yField);
        centerTextPanel.add(widthField);
        centerSelectionPanel.add(centerTextPanel);

        add(centerSelectionPanel);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 715, 120, 50);
        JButton drawMBButton = new JButton("画图");
        drawMBButton.addActionListener(e-> {
            drawPanel.setColorSelection(colorSelectorField.getText());
            drawPanel.setCenter(xField.getText(), yField.getText());
            drawPanel.setGraphWidth(widthField.getText());
            drawPanel.drawGraph();
        });
        buttonPanel.add(drawMBButton);
        add(buttonPanel);

        mandelbrotButton.setSelected(true);
        setJuliaParameterFields(false);
    }
    public void setJuliaParameterFields(boolean toActive) {
        if (toActive) {
            juliaX.setEnabled(true);
            juliaX.setText(JuliaKernel.DEFAULT_X + "");
            juliaY.setEnabled(true);
            juliaY.setText(JuliaKernel.DEFAULT_Y + "");
        } else {
            juliaX.setEnabled(false);
            juliaX.setText("无效");
            juliaY.setEnabled(false);
            juliaY.setText("无效");
        }
    }
    public void changeGraphSelection(int selection) {
        drawPanel.setKernelSelection(selection);
        setJuliaParameterFields(selection == 1);
        xField.setText(drawPanel.kernels[selection].defaultX() + "");
        yField.setText(drawPanel.kernels[selection].defaultY() + "");
        widthField.setText(drawPanel.kernels[selection].defaultWidth() + "");
    }
}
