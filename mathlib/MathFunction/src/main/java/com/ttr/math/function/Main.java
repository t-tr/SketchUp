/**
 * Mar 14, 2017 10:04:04 PM Main.java ttr
 */
package com.ttr.math.function;

/**
 *
 * @author Tristan Treboz
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		final Quadratic poly1 = new Quadratic(1, 8, -1);
		poly1.solve();
		System.out.println(poly1.getX0());
		System.out.println(poly1.getX1());
		System.out.println(poly1.canonicalForm());
		System.out.println(poly1.developedForm());
	}

}
