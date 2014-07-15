package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ClockFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	//UI elems
	JPanel panel = new JPanel();
	JPanel rightPanel, centerPanel, leftPanel;
	JLabel countdownLabel, openTimeLabel, openCountLabel, closedTimeLabel, closedCountLabel;
	JLabel closedLabel = new JLabel("CLOSED ARM");
	JLabel openLabel = new JLabel("OPEN ARM");
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem resetMenuItem = new JMenuItem("Reset"); //TODO: Wire up
	Color TEXT_COLOR = new Color(85,5,85);
	Experiment experiment;
	
	public ClockFrame(Experiment e){
		experiment = e;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 150);
		///
		fileMenu.add(resetMenuItem);
		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);
		//////
		rightPanel= new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		centerPanel= new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setAlignmentX(CENTER_ALIGNMENT);
		leftPanel= new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		panel.setLayout(new BorderLayout());
		//
		openTimeLabel = new JLabel("0.00");
		openCountLabel = new JLabel("0");
		openCountLabel.setAlignmentX(CENTER_ALIGNMENT);
		openTimeLabel.setAlignmentX(CENTER_ALIGNMENT);
		openLabel.setAlignmentX(CENTER_ALIGNMENT);
		rightPanel.add(openLabel);
		rightPanel.add(Box.createVerticalGlue());
		rightPanel.add(openCountLabel);
		rightPanel.add(openTimeLabel);
		rightPanel.add(Box.createVerticalGlue());
		//
		countdownLabel = new JLabel(Experiment.toMinSec(experiment.duration()));
		countdownLabel.setAlignmentX(CENTER_ALIGNMENT);
		countdownLabel.setHorizontalTextPosition(JLabel.CENTER);
		countdownLabel.setHorizontalAlignment(JLabel.CENTER);
		centerPanel.add(countdownLabel,BorderLayout.CENTER);
		//
		closedTimeLabel = new JLabel("0.00");
		closedCountLabel = new JLabel("0");
		closedCountLabel.setAlignmentX(CENTER_ALIGNMENT);
		closedTimeLabel.setAlignmentX(CENTER_ALIGNMENT);
		closedLabel.setAlignmentX(CENTER_ALIGNMENT);
		leftPanel.add(closedLabel);
		leftPanel.add(Box.createVerticalGlue());
		leftPanel.add(closedCountLabel);
		leftPanel.add(closedTimeLabel);
		leftPanel.add(Box.createVerticalGlue());
		//
		panel.add(rightPanel,BorderLayout.EAST);
		panel.add(centerPanel,BorderLayout.CENTER);
		panel.add(leftPanel,BorderLayout.WEST);
		//////
		this.add(panel);
		//
		colorizeLabels();
	}
	
	private void colorizeLabels() {
		closedLabel.setForeground(TEXT_COLOR);
		openLabel.setForeground(TEXT_COLOR);
		countdownLabel.setForeground(TEXT_COLOR);
		openTimeLabel.setForeground(TEXT_COLOR);
		openCountLabel.setForeground(TEXT_COLOR);
		closedTimeLabel.setForeground(TEXT_COLOR);
		closedCountLabel.setForeground(TEXT_COLOR);
	}

	public void paint(Graphics g){
		countdownLabel.setText(Experiment.toMinSec(experiment.duration));
		openCountLabel.setText(""+experiment.openEntries);
		closedCountLabel.setText(""+experiment.closedEntries);
		openTimeLabel.setText(Experiment.toSec(experiment.openTime()));
		closedTimeLabel.setText(Experiment.toSec(experiment.closedTime()));
		//System.out.println(countdownLabel.getText());
		super.paint(g);
	}
	

}
