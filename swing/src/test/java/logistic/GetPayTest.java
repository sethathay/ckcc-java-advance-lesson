package logistic;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetPayTest {

	@Test
	public void testGetPay_ExpressYes() {
		Sending sendingObj = new Sending(120, "A", true);
		double actualValue = sendingObj.getPay();
		double expectValue = 120 * (55.0/75.0);
		expectValue = expectValue + (expectValue * 0.15);
		assertEquals(expectValue, actualValue, 0.0);
	}
	
	@Test
	public void testGetPay_ExpressNo() {
		Sending sendingObj = new Sending(120, "A", false);
		double actualValue = sendingObj.getPay();
		double expectValue = 120 * (55.0/75.0);
		assertEquals(expectValue, actualValue, 0.0);
	}

}
