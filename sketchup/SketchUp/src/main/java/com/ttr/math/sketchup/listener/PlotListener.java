/**
 * Mar 22, 2017 10:45:02 PM PlotListener.java ttr
 */
package com.ttr.math.sketchup.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import com.ttr.math.sketchup.controller.PlotController;

/**
 * @author Tristan Treboz
 *
 */
public class PlotListener {

	/**
	 * Add listener.
	 *
	 * @param frm
	 */
	public static void addListener(final JFrame frm) {

		// Listen mouse wheel events
		frm.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getWheelRotation() < 0) {
					PlotController.getInstance().zoomIn();
					frm.repaint();
				} else {
					PlotController.getInstance().zoomOut();
					frm.repaint();
				}
			}
		});

		// Listen keyboard events
		frm.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {

				// Press home key
				if (e.getKeyCode() == KeyEvent.VK_HOME) {
					PlotController.getInstance().initialize();
					frm.repaint();
				}

				// Press LEFT key
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					PlotController.getInstance().x_offset -= 50;
					frm.repaint();
				}

				// Press RIGHT key
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					PlotController.getInstance().x_offset += 50;
					frm.repaint();
				}

				// Press UP key
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					PlotController.getInstance().y_offset -= 50;
					frm.repaint();
				}
				// Press DOWN key
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					PlotController.getInstance().y_offset += 50;
					frm.repaint();
				}

				// Press + key
				if (e.getKeyCode() == KeyEvent.VK_ADD) {
					PlotController.getInstance().zoomIn();
					frm.repaint();
				}

				// Press - key
				if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
					PlotController.getInstance().zoomOut();
					frm.repaint();
				}

			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}
		});

		// Drag event
		frm.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {
				if (PlotController.getInstance().isPressed) {
					PlotController.getInstance().x_offset = PlotController.getInstance().x_orig - e.getX();
					PlotController.getInstance().y_offset = PlotController.getInstance().y_orig - e.getY();
					frm.repaint();
				}
			}

			public void mouseMoved(MouseEvent e) {
			}

		});

		// Mouse listener
		frm.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				PlotController.getInstance().isPressed = true;
				PlotController.getInstance().x_orig = e.getX() + PlotController.getInstance().x_offset;
				PlotController.getInstance().y_orig = e.getY() + PlotController.getInstance().y_offset;
			}

			public void mouseReleased(MouseEvent e) {
				PlotController.getInstance().isPressed = false;
			}
		});

	}

}
