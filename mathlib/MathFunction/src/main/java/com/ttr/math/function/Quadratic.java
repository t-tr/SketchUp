/**
 *
 */
package com.ttr.math.function;

import com.ttr.math.function.msg.Messages;

/**
 * Describe a quadratic function.
 *
 * @author Tristan Treboz
 *
 */
public class Quadratic {

	private final float	a;
	private final float	b;
	private final float	c;

	private float		alpha;
	private float		beta;
	private float		disc;
	private double		x0;
	private double		x1;
	private int			nb_solution;

	/**
	 * Instantiate a quadratic.
	 *
	 * @param a
	 * @param b
	 * @param c
	 */
	public Quadratic(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * @param factor
	 * @return float value as String
	 */
	private static String factorToString(float factor, boolean first) {

		String result = ""; //$NON-NLS-1$

		if (factor == 1) {
			if (!first) {
				result = "+"; //$NON-NLS-1$
			}
		} else if (factor == -1) {
			result = "-"; //$NON-NLS-1$
		} else if (factor > 0) {
			if (first) {
				result = remZero(Math.abs(factor));
			} else {
				result = "+" + remZero(factor);
			}
		} else {
			result = remZero(factor);
		}

		return result;

	}

	/**
	 * @param factor
	 * @return
	 */
	private static String remZero(float factor) {

		String result = ""; //$NON-NLS-1$

		final String temp = String.valueOf(factor);
		// TODO regex to process any number of 0
		if (temp.endsWith(".0")) { //$NON-NLS-1$
			result = String.valueOf((int) factor);
		} else {
			result = temp;
		}

		return result;
	}

	/**
	 *
	 * @param value
	 * @return
	 */
	private static float square(float value) {
		return value * value;
	}

	/**
	 * Return the canonical form as a String f(x)=a(x-alpha)²+beta.
	 *
	 * @return the canonical form of the equation
	 */
	public String canonicalForm() {

		final StringBuffer result = new StringBuffer(20);
		String alpha_c;
		String beta_c;
		char sign_alpha;

		// Simplify alpha
		if (this.alpha < 0) {
			sign_alpha = '+';
		} else {
			sign_alpha = '-';
		}
		alpha_c = remZero(Math.abs(this.alpha));

		// Simplify beta
		if (this.beta == 0) {
			beta_c = ""; //$NON-NLS-1$
		} else if (this.beta > 0) {
			beta_c = "+" + remZero(this.beta); //$NON-NLS-1$
		} else {
			beta_c = String.valueOf(remZero(this.beta));
		}

		// Simplify a
		if (this.a == -1) {
			result.append('-');
		} else if (this.a != 1) {
			result.append(remZero(this.a));
		}

		result.append(Messages.getString("Quadratic.canonical.x.start")).append(sign_alpha).append(alpha_c) //$NON-NLS-1$
				.append(Messages.getString("Quadratic.canonical.x.end")).append("²").append(beta_c); //$NON-NLS-1$ //$NON-NLS-2$

		return result.toString();
	}

	/**
	 * Build developed form of a quadratic function as a String
	 *
	 * @return String
	 */
	public String developedForm() {

		final StringBuffer result = new StringBuffer();
		boolean firstTerm = true;

		// Add x² term
		if (this.a != 0) {
			result.append(factorToString(this.a, firstTerm)).append("x²"); //$NON-NLS-1$
			firstTerm = false;
		}

		// Add x term
		if (this.b != 0) {
			result.append(factorToString(this.b, firstTerm)).append("x"); //$NON-NLS-1$
			firstTerm = false;
		}

		// Add constant term
		if (this.c != 0) {
			if (this.c > 0 && !firstTerm) {
				result.append('+');
			}
			result.append(remZero(this.c));
		} else {
			// All factor have been 0 so far!
			if (result.length() == 0) {
				result.append('0');
			}
		}

		return result.toString();
	}

	/**
	 * @return the disc
	 */
	public float getDisc() {
		return this.disc;
	}

	/**
	 * @return the x0
	 */
	public double getX0() {
		return this.x0;
	}

	/**
	 * @return the x1
	 */
	public double getX1() {
		return this.x1;
	}

	/**
	 * Compute the image for the x value given in parameter.
	 *
	 * @param x_value
	 * @return image
	 */
	public float image(float x_value) {

		return this.a * square(x_value) + this.b * x_value + this.c;
	}

	/**
	 * Solve quadratic.
	 *
	 */
	public void solve() {

		this.alpha = -this.b / (2 * this.a);

		this.disc = square(this.b) - 4 * this.a * this.c;

		if (this.disc < 0) {
			this.nb_solution = 0;

		}
		if (this.disc == 0) {
			this.nb_solution = 1;
			this.x0 = this.alpha;
			this.x1

					= this.alpha;
		}
		if (this.disc > 0) {
			this.x0 = (-this.b - Math.sqrt(this.disc)) / (2 * this.a);
			this.x1 = (-this.b + Math.sqrt(this.disc)) / (2 * this.a);
			this.nb_solution = 2;
		}

		this.beta = image(this.alpha);

	}

}
