/**
 * Mar 22, 2017 10:53:59 PM PlotController.java ttr
 */
package com.ttr.math.sketchup.controller;

import java.awt.Color;

import javax.swing.DefaultListModel;

import com.ttr.math.sketchup.frame.FunctionFrm;
import com.ttr.math.sketchup.frame.PlotFrm;

/**
 * Controller class.
 *
 * @author Tristan Treboz
 *
 */
public class PlotController {

	private static PlotController			instance		= null;

	private static DefaultListModel<String>	listModel;

	/**
	 * The plot frame.
	 */
	public static PlotFrm					plotFrm;

	/**
	 * The function frame.
	 */
	public static FunctionFrm				functFrm;

	/** Number of plots. */
	public int								nb_plot			= 0;

	/** Number of refreshes. */
	public int								nb_refresh		= 0;

	/** X offset. */
	public int								x_offset		= 0;

	/** Y offset. */
	public int								y_offset		= 0;

	/** X origin. */
	public int								x_orig			= 0;

	/** Y origin. */
	public int								y_orig			= 0;

	/** Adjust. */
	public double							adjust			= 1;

	/** Scale factor. */
	public double							scale_factor	= 1;

	/** Scale. */
	public float							scale			= 3;

	/** Direction. Gives the type of zoom. */
	public boolean							direction		= false;

	/** True if mouse is pressed. */
	public boolean							isPressed;

	/** Color selected in the menu of the main window. */
	private Color							plotSelectedColor;

	/** ID of the plot. */
	private int								plotId			= 0;

	/**
	 * Constructor.
	 */
	protected PlotController() {
		this.setPlotColor(Color.GRAY);
	}

	/**
	 * @return the functFrm
	 */
	public static FunctionFrm getFunctFrm() {
		return functFrm;
	}

	/**
	 * getInstance method.
	 *
	 * @return instance
	 *
	 */
	public static PlotController getInstance() {

		if (instance == null) {
			instance = new PlotController();
			listModel = new DefaultListModel<String>();
		}

		return instance;
	}

	/**
	 * @return the plotFrm
	 */
	public static PlotFrm getPlotFrm() {
		return plotFrm;
	}

	/**
	 * @param functFrm
	 *            the functFrm to set
	 */
	public static void setFunctFrm(FunctionFrm functFrm) {
		PlotController.functFrm = functFrm;
	}

	/**
	 * @param plotFrm
	 *            the plotFrm to set
	 */
	public static void setPlotFrm(PlotFrm plotFrm) {
		PlotController.plotFrm = plotFrm;
	}

	/**
	 *
	 * @param element
	 */
	public void addElement(String element) {
		listModel.addElement(element);
	}

	/**
	 * Decrement plotID.
	 */
	public void decPlotId() {
		this.plotId--;
	}

	/**
	 * Return the listModel.
	 *
	 * @return the listModel
	 */
	public DefaultListModel<String> getModel() {
		return listModel;
	}

	/**
	 * @return the plotColor
	 */
	public Color getPlotColor() {
		return plotSelectedColor;
	}

	/**
	 * @return the plotId.
	 */
	public int getPlotId() {
		return this.plotId;
	}

	/**
	 * Increment plotID.
	 */
	public void incPlotId() {
		this.plotId++;
	}

	/**
	 * Initialize graph.
	 */
	public void initialize() {

		x_offset = 0;
		y_offset = 0;
		x_orig = 0;
		y_orig = 0;
		adjust = 1;
		scale_factor = 1;
		scale = 3;
		direction = false;

	}

	/**
	 * Remove a plot from the list and from the plot window.
	 *
	 * @param index
	 */
	public void removePlot(int index) {

		if (!listModel.isEmpty()) {
			listModel.remove(index);
		}

		this.nb_plot--;

	}

	/**
	 * Set the listModel.
	 *
	 * @param model
	 *            the listModel to set
	 */
	public void setModel(DefaultListModel<String> model) {
		PlotController.listModel = model;
	}

	/**
	 * @param plotColor
	 *            the plotColor to set
	 */
	public void setPlotColor(Color plotColor) {
		this.plotSelectedColor = plotColor;
	}

	/**
	 * zoomIn method.
	 */
	public void zoomIn() {

		if (!direction) {
			scale++;
		} else {
			scale--;
		}

		// Change direction
		if (scale == 0) {
			direction = false;
			scale = 2;
		}
	}

	/**
	 * zoomOut method.
	 */
	public void zoomOut() {

		if (direction) {
			scale++;
		} else {
			scale--;
		}

		// Change direction
		if (scale == 0) {
			direction = true;
			scale = 2;
		}
	}

}
