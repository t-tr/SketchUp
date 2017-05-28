
package com.ttr.math.sketchup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;

import com.ttr.math.function.Quadratic;
import com.ttr.math.sketchup.controller.PlotController;
import com.ttr.math.sketchup.frame.FunctionFrm;
import com.ttr.math.sketchup.frame.PlotFrm;
import com.ttr.math.sketchup.grid.Grid;

/**
 * SketchUp is an application, which solves equation and plot the corresponding function. It aims at
 * helping students understanding mathematics and solving problems.
 *
 * Activator class starts the application.
 *
 * @author Tristan Treboz
 *
 */
public class Activator extends JPanel {

	/**
	 * Screen width.
	 */
	public static int			screenWidth;

	/**
	 * Screen height.
	 */
	public static int			screenHeight;

	/**
	 * Serial UID.
	 */
	private static final long	serialVersionUID	= 462129449187954328L;

	/**
	 * Quadratic function.
	 */
	private final Quadratic		quadratic;

	/**
	 * Color of the plot.
	 */
	private final Color			plotColor;

	/**
	 * ID of the plot.
	 */
	private final int			plotId;

	/**
	 * Constructor.
	 *
	 * @param quad
	 * @param color
	 */
	public Activator(Quadratic quad, Color color) {
		this.quadratic = quad;
		this.plotColor = color;
		PlotController.getInstance().incPlotId();
		this.plotId = PlotController.getInstance().getPlotId();
	}

	/**
	 * Start the application. Create two windows based on screen size.
	 *
	 * @param args
	 */
	public static void main(String... args) {

		// Get graphics environment information
		getEnvInfo();

		PlotController.getInstance();
		// Instantiate Plot window
		PlotController.setPlotFrm(new PlotFrm());
		PlotController.getPlotFrm().createGraphicsPlot(screenWidth, screenHeight);

		Grid.drawGrid(PlotController.getPlotFrm().getGraphics(), screenWidth, screenHeight);

		// Instantiate Function window
		PlotController.setFunctFrm(new FunctionFrm());
		PlotController.getFunctFrm().createGraphicsFunction(screenWidth, screenHeight);
	}

	/**
	 * Get graphics environment information like screen size.
	 */
	private static void getEnvInfo() {

		final GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		screenWidth = (int) genv.getDefaultScreenDevice().getDefaultConfiguration().getBounds().getWidth();
		screenHeight = (int) genv.getDefaultScreenDevice().getDefaultConfiguration().getBounds().getHeight();
	}

	/**
	 * @return the plotColor
	 */
	public Color getPlotColor() {
		return this.plotColor;
	}

	/**
	 * @return the plotId
	 */
	public int getPlotId() {
		return plotId;
	}

	/**
	 * @return the quadratic
	 */
	public Quadratic getQuadratic() {
		return this.quadratic;
	}

	@Override
	public void paint(Graphics graphic) {

		System.out.println("paint graphic:" + this.getPlotId());

		final int frm_width = PlotController.getPlotFrm().getSize().width;
		final int frm_height = PlotController.getPlotFrm().getSize().height;

		if (PlotController.getInstance().nb_refresh == 0) {

			// Display grid
			Grid.drawGrid(graphic, frm_width, frm_height);

			// Draw axis
			PlotController.getPlotFrm().drawAxis(graphic, frm_width, frm_height);
		}

		// Activator function
		PlotController.getPlotFrm().plotFunction(graphic, this, frm_width, frm_height);

		// System.out.println("refresh plot:" + PlotController.getInstance().nb_refresh);
		PlotController.getInstance().nb_refresh++;

		if (PlotController.getInstance().nb_refresh >= PlotController.getInstance().nb_plot) {
			PlotController.getInstance().nb_refresh = 0;
		}
	}

}
