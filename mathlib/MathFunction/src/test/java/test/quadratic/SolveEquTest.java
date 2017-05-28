/**
 * Mar 14, 2017 11:41:25 PM PolynomeTestsTest.java Tristan Treboz
 */
package test.quadratic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ttr.math.function.Quadratic;

/**
 * @author Tristan Treboz
 *
 */
public class SolveEquTest {

	/**
	 * Function is not a second degree polynomial.
	 */
	@Test
	public void a2EqualZero() {

		final Quadratic pln = new Quadratic(0, 1, 1);
		assertNotNull(pln);
	}

	/**
	 * Quadratic is not null.
	 */
	@Test
	public void quadraticNotNull() {

		final Quadratic pln = new Quadratic(1, 1, 1);
		assertNotNull(pln);
	}

	/**
	 * Solve quadratic. 2 rational solutions.
	 *
	 * @throws Exception
	 */
	@Test
	public void solve2Rational() throws Exception {

		final Quadratic pln = new Quadratic(-1, 1, 2);
		pln.solve();

		assertEquals(2, pln.image(0), 0);
		assertEquals(2, pln.getX0(), 0);
		assertEquals(-1, pln.getX1(), 0);
		assertEquals(9, pln.getDisc(), 0);
	}

	/**
	 * Solve quadratic. Double solution.
	 *
	 * @throws Exception
	 */
	@Test
	public void solveDoubleRational() throws Exception {

		final Quadratic pln = new Quadratic(1, 0, 0);
		pln.solve();

		assertEquals(0, pln.image(0), 0);
		assertEquals(0, pln.getX0(), 0);
		assertEquals(pln.image(4), pln.image(-4), 0);

		assertEquals(0, pln.getDisc(), 0);
	}

	/**
	 * Solve quadratic. 0 rational solutions.
	 *
	 * @throws Exception
	 */
	@Test
	public void solveNoRational() throws Exception {

		final Quadratic pln = new Quadratic(-1, 1, -2);
		pln.solve();

		assertEquals(-2, pln.image(0), 0);
		assertEquals(-2, pln.image(1), 0);
		assertEquals(-7, pln.getDisc(), 0);
	}

}
