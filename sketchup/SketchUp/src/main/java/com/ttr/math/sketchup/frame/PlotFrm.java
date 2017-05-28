/**
 * Apr 9, 2017 11:12:31 PM PlotFrm.java Created by Tristan Treboz
 */
package com.ttr.math.sketchup.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import com.ttr.math.sketchup.Activator;
import com.ttr.math.sketchup.controller.PlotController;
import com.ttr.math.sketchup.listener.PlotListener;
import com.ttr.math.sketchup.menu.PlotMenu;

/**
 * Window displaying the plot.
 *
 * @author Tristan Treboz
 *
 */
public class PlotFrm extends JFrame {

	private static final long serialVersionUID = -4265443457393708133L;

	/**
	 * Constructor.
	 *
	 * @throws HeadlessException
	 */
	public PlotFrm() throws HeadlessException {
	}

	/**
	 * Constructor.
	 *
	 * @param gc
	 */
	public PlotFrm(GraphicsConfiguration gc) {
		super(gc);
	}

	/**
	 * Constructor.
	 *
	 * @param title
	 * @throws HeadlessException
	 */
	public PlotFrm(String title) throws HeadlessException {
		super(title);
	}

	/**
	 * Constructor.
	 *
	 * @param title
	 * @param gc
	 */
	public PlotFrm(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	/**
	 * Create the plot frame.
	 *
	 * @param screenHeight
	 * @param screenWidth
	 */
	public void createGraphicsPlot(int screenWidth, int screenHeight) {

		this.setSize(2 * screenWidth / 3, screenHeight);
		this.setBackground(Color.WHITE);
		this.setLocation(screenWidth / 3, 0);

		// Add listener in the Activator frame
		PlotListener.addListener(this);

		// Add the menu bar
		PlotMenu.addMenuBar(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Draw graph axis.
	 *
	 * @param graphic
	 * @param frm_width
	 * @param frm_height
	 */
	public void drawAxis(Graphics graphic, int frm_width, int frm_height) {

		int scale_factor = 0;

		if (PlotController.getInstance().direction) {
			scale_factor = 10 / (int) PlotController.getInstance().scale;
		} else {
			scale_factor = 10 * (int) PlotController.getInstance().scale;
		}

		// Set axis color to black
		graphic.setColor(Color.BLACK);

		// Draw X axis
		graphic.fillRect(0, frm_height / 2 - 1 - PlotController.getInstance().y_offset, frm_width, 3);
		// Unit marker on X axis
		graphic.fillRect(frm_width / 2 + scale_factor / 10 - PlotController.getInstance().x_offset - 1,
				frm_height / 2 - 7 - PlotController.getInstance().y_offset, 3, 14);

		// Draw Y axis
		graphic.fillRect(frm_width / 2 - 1 - PlotController.getInstance().x_offset, 0, 3, frm_height);
		// Unit marker on Y axis
		graphic.fillRect(frm_width / 2 - 7 - PlotController.getInstance().x_offset,
				frm_height / 2 - scale_factor / 10 - PlotController.getInstance().y_offset - 1, 14, 3);
	}

	/**
	 * Plot the function.
	 *
	 * @param graphic
	 * @param sketch
	 * @param frmWidth
	 * @param frmHeight
	 */
	public void plotFunction(Graphics graphic, Activator sketch, int frmWidth, int frmHeight) {

		// Initialize local variables used as coordinates
		float x_scaled = 0;
		float y_scaled = 0;
		int x_coord = 0;
		int y_coord = 0;
		int x_coord_prev = 0;
		int y_coord_prev = 0;
		boolean isFirstPoint = true;
		boolean isSelected = true;

		try {
			// --- Solve equation
			sketch.getQuadratic().solve();
		} catch (final Exception e) {
			e.printStackTrace();
		}

		float image = 0;

		// Activator each point
		for (int i = -frmWidth / 2 + PlotController.getInstance().x_offset; i < frmWidth / 2
				+ PlotController.getInstance().x_offset; i++) {

			// Scale x
			if (PlotController.getInstance().direction) {
				x_scaled = i * PlotController.getInstance().scale;
			} else {
				x_scaled = i / PlotController.getInstance().scale;
			}

			// Compute image of x
			image = sketch.getQuadratic().image(x_scaled);

			// Scale image
			if (PlotController.getInstance().direction) {
				y_scaled = image / PlotController.getInstance().scale;
			} else {
				y_scaled = image * PlotController.getInstance().scale;
			}

			// Translate x and y in the graphic system
			x_coord = frmWidth / 2 - PlotController.getInstance().x_offset + i;
			y_coord = (int) (frmHeight / 2 - PlotController.getInstance().y_offset + -1 * y_scaled);

			// Set color to user-defined color
			graphic.setColor(sketch.getPlotColor());

			// Draw line
			if (!isFirstPoint) {
				graphic.drawLine(x_coord_prev, y_coord_prev, x_coord, y_coord);
			}

			// Save coordinates from last point
			x_coord_prev = x_coord;
			y_coord_prev = y_coord;

			// Display point
			if (isSelected) {
				graphic.setColor(new Color(255, 0, 0));
				graphic.drawOval(x_coord - 1, y_coord - 1, 3, 3);
				isSelected = false;
			} else {
				isSelected = true;
			}

			graphic.setColor(new Color(0, 0, 0));
			/*
			 * graphic.drawString("x offs:" + PlotController.getInstance().x_offset, 50, 50);
			 * graphic.drawString("y offs:" + PlotController.getInstance().y_offset, 50, 65);
			 * graphic.drawString("scale :" + PlotController.getInstance().scale, 50, 80);
			 * graphic.drawString("direct:" + PlotController.getInstance().direction, 50, 95);
			 * graphic.drawString("x orig:" + PlotController.getInstance().x_orig, 50, 110);
			 * graphic.drawString("y orig:" + PlotController.getInstance().y_orig, 50, 125);
			 * graphic.drawString("scalef:" + PlotController.getInstance().scale_factor, 50, 140);
			 * graphic.drawString("adjust:" + PlotController.getInstance().adjust, 50, 155);
			 */

			isFirstPoint = false;
		}
	}
}
