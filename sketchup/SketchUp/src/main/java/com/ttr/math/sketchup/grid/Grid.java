/**
 * Mar 22, 2017 11:07:51 PM Grid.java ttr
 */
package com.ttr.math.sketchup.grid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ttr.math.sketchup.constant.Constant;
import com.ttr.math.sketchup.controller.PlotController;

/**
 * @author Tristan Treboz
 *
 */
public class Grid {

	/**
	 * Constructor.
	 */
	public Grid() {
	}

	/**
	 * Draw the grid.
	 *
	 * @param graphic
	 * @param frm_width
	 * @param frm_height
	 */
	public static void drawGrid(Graphics graphic, int frm_width, int frm_height) {

		if (PlotController.getInstance().direction) {
			PlotController.getInstance().scale_factor = Constant.SCALE_BASIS / PlotController.getInstance().scale;
		} else {
			PlotController.getInstance().scale_factor = Constant.SCALE_BASIS * PlotController.getInstance().scale;
		}

		if (PlotController.getInstance().scale_factor * PlotController.getInstance().adjust > 100) {
			PlotController.getInstance().adjust /= 5;
		}
		if (PlotController.getInstance().scale_factor * PlotController.getInstance().adjust < 20) {
			PlotController.getInstance().adjust *= 5;
		}
		final double adj = PlotController.getInstance().adjust;
		final double grid_gap = PlotController.getInstance().scale_factor * adj;

		/*
		 * if (scale_factor * PlotController.getInstance().adjust < 20) {
		 * PlotController.getInstance().adjust *= 5; } if (!PlotController.getInstance().direction)
		 * { scale_factor *= PlotController.getInstance().adjust; } if
		 * (PlotController.getInstance().direction) { scale_factor /=
		 * PlotController.getInstance().adjust; }
		 */

		/*
		 * System.out.println( "scale_factor:" + PlotController.getInstance().scale_factor +
		 * ", adjust=" + PlotController.getInstance().adjust + ", direction=" +
		 * PlotController.getInstance().direction + ", =" + grid_gap);
		 */

		graphic.setFont(new Font("Arial", Font.PLAIN, 10));

		// X axis
		for (int i = 0; i <= frm_height / 2; i++) {

			setGridLineColor(graphic, grid_gap, i % 3);

			final int y_up = (int) (frm_height / 2 - i * grid_gap - PlotController.getInstance().y_offset);
			final int y_down = (int) (frm_height / 2 + i * grid_gap - PlotController.getInstance().y_offset);

			graphic.drawLine(0, y_up, frm_width, y_up);
			graphic.drawLine(0, y_down, frm_width, y_down);

			// Set axis color to black
			graphic.setColor(Color.BLACK);

			// Display unit on X axis
			if (grid_gap >= 40 || grid_gap < 40 && i % 3 == 0) {
				final float unit = i * (float) PlotController.getInstance().adjust * Constant.SCALE_BASIS;
				graphic.drawString(String.format(Constant.FLOAT_SCALE, unit), 2, y_up - 2);
				graphic.drawString(String.format(Constant.FLOAT_SCALE, -unit), 2, y_down - 2);
			}
		}

		// Y axis
		for (int i = 0; i < frm_width / 2; i++) {

			setGridLineColor(graphic, grid_gap, i % 3);

			final int x_up = (int) (frm_width / 2 - i * grid_gap - PlotController.getInstance().x_offset);
			final int x_down = (int) (frm_width / 2 + i * grid_gap - PlotController.getInstance().x_offset);

			graphic.drawLine(x_up, 0, x_up, frm_height);
			graphic.drawLine(x_down, 0, x_down, frm_height);

			if (i < frm_width / 2 - 5) {
				// Set axis color to black
				graphic.setColor(Color.BLACK);

				// Display unit on Y axis
				if (grid_gap >= 40 || grid_gap < 40 && i % 3 == 0) {

					final float unit = i * (float) PlotController.getInstance().adjust * Constant.SCALE_BASIS;
					graphic.drawString(String.format(Constant.FLOAT_SCALE, unit), x_down + 2, frm_height - 80);
					graphic.drawString(String.format(Constant.FLOAT_SCALE, -unit), x_up + 2, frm_height - 80);
				}
			}
		}

	}

	/**
	 * @param graphic
	 * @param grid_gap
	 * @param
	 */
	private static void setGridLineColor(Graphics graphic, double grid_gap, int color) {

		if (grid_gap >= 40 || grid_gap < 40 && color == 0) {
			// --- Set grid color to dark grey
			graphic.setColor(Color.DARK_GRAY);
		} else {
			// --- Set grid color to light grey
			graphic.setColor(Color.LIGHT_GRAY);
		}

	}

}
