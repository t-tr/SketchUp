/**
 * Mar 27, 2017 10:42:52 PM PlotMenu.java ttr
 */
package com.ttr.math.sketchup.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.ttr.math.sketchup.controller.PlotController;

/**
 * Menu of the main window.
 *
 * @author Tristan Treboz
 *
 */
public final class MainMenu {

	/**
	 * Private constructor.
	 */
	private MainMenu() {
	}

	/**
	 * Add menu bar.
	 *
	 */
	public static void addMenuBar() {

		final JMenuBar menubar = new JMenuBar();

		//
		// Main menu
		final JMenu mainMenu = new JMenu("Main");

		menubar.add(mainMenu);

		// New item
		final JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PlotController.getInstance().initialize();
				PlotController.getPlotFrm().repaint();
			}
		});

		mainMenu.add(newItem);
		menubar.add(mainMenu);

		// Exit item
		final JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PlotController.getFunctFrm().dispose();
				PlotController.getPlotFrm().dispose();
			}
		});
		mainMenu.add(exitItem);
		menubar.add(mainMenu);

		//
		// Options menu
		final JMenu optionMenu = new JMenu("Options");
		final JMenu plotColor = new JMenu("Plot color");

		final JMenuItem colorRed = new JMenuItem("RED");
		final JMenuItem colorBlue = new JMenuItem("BLUE");
		final JMenuItem colorGreen = new JMenuItem("GREEN");
		final JMenuItem colorOrange = new JMenuItem("ORANGE");
		final JMenuItem colorYellow = new JMenuItem("YELLOW");
		final JMenuItem colorPink = new JMenuItem("PINK");

		plotColor.add(colorRed);
		plotColor.add(colorBlue);
		plotColor.add(colorGreen);
		plotColor.add(colorOrange);
		plotColor.add(colorYellow);
		plotColor.add(colorPink);

		// Set plot color to BLUE
		colorBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlotController.getInstance().setPlotColor(Color.BLUE);
			}
		});
		// Set plot color to RED
		colorRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlotController.getInstance().setPlotColor(Color.RED);
			}
		});
		// Set plot color to GREEN
		colorGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlotController.getInstance().setPlotColor(Color.GREEN);
			}
		});
		// Set plot color to ORANGE
		colorOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlotController.getInstance().setPlotColor(Color.ORANGE);
			}
		});
		// Set plot color to YELLOW
		colorYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlotController.getInstance().setPlotColor(Color.YELLOW);
			}
		});
		// Set plot color to PINK
		colorPink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlotController.getInstance().setPlotColor(Color.PINK);
			}
		});

		optionMenu.add(plotColor);

		menubar.add(optionMenu); // Help menu final
		final JMenu helpMenu = new JMenu("Help");
		final JMenuItem help = new JMenuItem("Help me");
		helpMenu.add(help);
		// About the application
		final JMenuItem about = new JMenuItem("About Sketch'Up");
		helpMenu.add(about);
		menubar.add(helpMenu);

		PlotController.getFunctFrm().setJMenuBar(menubar);

	}

}
