/**
 * Apr 7, 2017 10:05:49 PM CanonicalFormTest.java Created by Tristan Treboz
 */
package test.quadratic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ttr.math.function.Quadratic;

/**
 * @author Tristan Treboz
 *
 */
public class CanonicalFormTest {

	/**
	 * Test the method which builds canonical form.
	 *
	 * @throws Exception
	 */
	@Test
	public void canonicalQuadratic() throws Exception {

		// a=1
		// f(x)=x²-x
		Quadratic pln = new Quadratic(1, -1, 0);
		pln.solve();
		assertEquals("(x-0.5)²-0.25", pln.canonicalForm()); //$NON-NLS-1$

		// beta equals 0
		// f(x)=x²+2x+1
		pln = new Quadratic(1, 2, 1);
		pln.solve();
		assertEquals("(x+1)²", pln.canonicalForm()); //$NON-NLS-1$

		// alpha and beta are integers
		// f(x)=x²+2x+2
		pln = new Quadratic(1, 2, 2);
		pln.solve();
		assertEquals("(x+1)²+1", pln.canonicalForm()); //$NON-NLS-1$

		// a=-1
		// f(x)=-x²+0,5x+0,0075
		pln = new Quadratic(-1, 0.5f, 0.0075f);
		pln.solve();
		assertEquals("-(x-0.25)²+0.07", pln.canonicalForm()); //$NON-NLS-1$

		// f(x)=5x²-x+2
		pln = new Quadratic(5, -1, 2);
		pln.solve();
		assertEquals("5(x-0.1)²+1.95", pln.canonicalForm()); //$NON-NLS-1$

		// f(x)=-2x²+x+3
		final Quadratic pln2 = new Quadratic(-2, 1, 3);
		pln2.solve();
		assertEquals("-2(x-0.25)²+3.125", pln2.canonicalForm()); //$NON-NLS-1$
	}
}
