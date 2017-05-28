/**
 * Apr 7, 2017 10:03:18 PM DevFormTest.java Created by Tristan Treboz
 */
package test.quadratic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ttr.math.function.Quadratic;

/**
 * @author Tristan Treboz
 *
 */
public class DevFormTest {

	/**
	 * Test building developed form when f(x)=b.
	 *
	 * @throws Exception
	 */
	@Test
	public void developedConstant() throws Exception {

		// f(x)=0
		Quadratic pln = new Quadratic(0, 0, 0);
		assertEquals("0", pln.developedForm()); //$NON-NLS-1$

		// f(x)=-1
		pln = new Quadratic(0, 0, -1);
		assertEquals("-1", pln.developedForm()); //$NON-NLS-1$

		// f(x)=1
		pln = new Quadratic(0, 0, 1);
		assertEquals("1", pln.developedForm()); //$NON-NLS-1$

	}

	/**
	 * Test building developed form when function is a linear function.
	 *
	 * @throws Exception
	 */
	@Test
	public void developedLinear() throws Exception {

		// f(x)=x-2
		Quadratic pln = new Quadratic(0, 1, -2);
		assertEquals("x-2", pln.developedForm()); //$NON-NLS-1$

		// f(x)=-2x+1
		pln = new Quadratic(0, -2, 1);
		assertEquals("-2x+1", pln.developedForm()); //$NON-NLS-1$

		// f(x)=x
		pln = new Quadratic(0, 1, 0);
		assertEquals("x", pln.developedForm()); //$NON-NLS-1$
	}

	/**
	 * Test building developed form when f is a quadratic.
	 *
	 * @throws Exception
	 */
	@Test
	public void developedQuadratic() throws Exception {

		// f(x)=x²
		Quadratic pln = new Quadratic(1, 0, 0);
		assertEquals("x²", pln.developedForm()); //$NON-NLS-1$

		// f(x)=x²+x+1
		pln = new Quadratic(1, 1, 1);
		assertEquals("x²+x+1", pln.developedForm()); //$NON-NLS-1$

		// f(x)=x²-1
		pln = new Quadratic(1, 0, -1);
		assertEquals("x²-1", pln.developedForm()); //$NON-NLS-1$

		// f(x)=-4x²-4x
		pln = new Quadratic(-4, -4, 0);
		assertEquals("-4x²-4x", pln.developedForm()); //$NON-NLS-1$

		// f(x)=-x²-x+2
		pln = new Quadratic(-1, -1, 2);
		assertEquals("-x²-x+2", pln.developedForm()); //$NON-NLS-1$

		// f(x)=2x²+8x+1
		pln = new Quadratic(2, 8, 1);
		assertEquals("2x²+8x+1", pln.developedForm()); //$NON-NLS-1$
	}

}
