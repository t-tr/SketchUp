/**
 * Apr 9, 2017 11:01:04 PM FunctionFrm.java Created by Tristan Treboz
 */
package com.ttr.math.sketchup.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.ttr.math.function.Quadratic;
import com.ttr.math.sketchup.Activator;
import com.ttr.math.sketchup.controller.PlotController;
import com.ttr.math.sketchup.listener.PlotListener;
import com.ttr.math.sketchup.menu.MainMenu;

/**
 * Window managing list of functions and displaying solutions and information.
 *
 * @author Tristan Treboz
 *
 */
public class FunctionFrm extends JFrame {

	/**
	 *
	 */
	private static final long	serialVersionUID	= -315768756850500219L;

	private JLabel				jLabela;
	private JLabel				jLabelb;
	private JTextField			aText;
	private JTextField			bText;
	private JTextField			cText;
	private JButton				plotBtn;
	private JButton				deleteBtn;
	private JTextField			message;
	private JList<String>		list;
	private boolean				isValid				= true;

	/**
	 * @throws HeadlessException
	 */
	public FunctionFrm() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gc
	 */
	public FunctionFrm(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor.
	 *
	 * @param title
	 * @throws HeadlessException
	 */
	public FunctionFrm(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor.
	 *
	 * @param title
	 * @param gc
	 */
	public FunctionFrm(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create the graphics of the function frame.
	 *
	 * @param screenWidth
	 * @param screenHeight
	 */
	public void createGraphicsFunction(int screenWidth, int screenHeight) {

		// Add listener in the Activator frame
		PlotListener.addListener(this);

		// Add the menu bar
		MainMenu.addMenuBar();

		// Create graphic components
		createGraphics(screenWidth, screenHeight);

		// Plot button
		plotBtn.addActionListener(new ActionListener() {

			// Listener for press Plot! button
			public void actionPerformed(ActionEvent e) {

				pressPlot();
			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Check the input value.
	 *
	 * @param input
	 * @return verified value
	 */
	private float checkInput(JTextField input) {

		float value = 0;

		if (!input.getText().isEmpty()) {
			try {
				value = Float.parseFloat(input.getText());
			} catch (final NumberFormatException nbex) {
				message.setEnabled(true);
				message.setForeground(Color.RED);
				message.setText(input.getName() + " value is invalid:" + nbex.getLocalizedMessage());
				isValid = false;
			}
		}

		return value;
	}

	/**
	 * Create the graphics.
	 *
	 * @param screenHeight
	 * @param screenWidth
	 *
	 */
	private void createGraphics(int screenWidth, int screenHeight) {

		this.setSize(screenWidth / 3, screenHeight);
		this.setLocation(0, 0);
		// this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

		// Initialize the items
		jLabela = new JLabel("x²");
		jLabelb = new JLabel("x");
		aText = new JTextField("");
		aText.setPreferredSize(new Dimension(screenWidth / 15, 30));
		aText.setCaretColor(Color.BLUE);
		bText = new JTextField("");
		bText.setPreferredSize(new Dimension(screenWidth / 15, 30));
		bText.setCaretColor(Color.BLUE);
		cText = new JTextField("");
		cText.setPreferredSize(new Dimension(screenWidth / 15, 30));
		aText.setName("a");
		bText.setName("b");
		cText.setName("c");
		plotBtn = new JButton("Plot!");

		message = new JTextField("");
		message.setEnabled(false);
		message.setEditable(false);
		list = new JList<String>(PlotController.getInstance().getModel());

		// Create list of plots
		final JScrollPane scrollPane = new JScrollPane(list);

		final JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

		inputPanel.add(aText);
		inputPanel.add(jLabela);
		inputPanel.add(bText);
		inputPanel.add(jLabelb);
		inputPanel.add(cText);
		inputPanel.add(plotBtn);

		inputPanel.setBackground(Color.GRAY);

		final JPanel controlPanel = new JPanel();
		final GridLayout controlLayout = new GridLayout(4, 1, 20, 10);
		controlPanel.setLayout(controlLayout);
		final JLabel title = new JLabel("Input function:", JLabel.LEFT);
		title.setPreferredSize(new Dimension(screenWidth / 3, 20));
		controlPanel.add(title);
		inputPanel.setPreferredSize(getMinimumSize());
		controlPanel.add(inputPanel);
		controlPanel.add(message);

		final JPanel panePanel = new JPanel(new GridLayout(1, 2));
		panePanel.add(scrollPane);
		deleteBtn = new JButton("Delete");
		panePanel.add(deleteBtn);

		deleteBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				pressDelete();
			}

		});

		controlPanel.add(panePanel);

		controlPanel.setVisible(true);
		this.add(controlPanel);
		// this.add(scrollPane);
		this.setVisible(true);
		this.pack();

	}

	/**
	 * Press the Delete button.
	 */
	private void pressDelete() {

		// Check an item has been selected in the list
		if (!list.isSelectionEmpty()) {
			PlotController.getInstance().removePlot(list.getSelectedIndex());
		}

	}

	/**
	 * Press the Plot! button. This method checks the input and plot the function if input are
	 * correct, display an error message otherwise.
	 *
	 * @param plotFrm
	 */
	private void pressPlot() {

		float a = 0;
		float b = 0;
		float c = 0;

		isValid = true;

		// Test input
		a = checkInput(aText);
		if (isValid) {
			b = checkInput(bText);
		}
		if (isValid) {
			c = checkInput(cText);
		}

		// Display the function if input is valid
		if (isValid) {

			final Quadratic qd = new Quadratic(a, b, c);
			final String func = qd.developedForm();

			// Check if the function is not in the list
			if (!PlotController.getInstance().getModel().contains(func)) {

				// Get the developed form of the function
				final String newElement = qd.developedForm();
				PlotController.getInstance().addElement(newElement);

				final Activator plot = new Activator(qd, PlotController.getInstance().getPlotColor());
				System.out.println("nb component:" + PlotController.getFunctFrm().getComponentCount());
				PlotController.getPlotFrm().add(plot);
				PlotController.getPlotFrm().repaint();
				PlotController.getPlotFrm().setVisible(true);

				// Increment number of plots
				PlotController.getInstance().nb_plot++;
			} else {
				message.setText("This function has been added already");
			}
		}
	}
}
