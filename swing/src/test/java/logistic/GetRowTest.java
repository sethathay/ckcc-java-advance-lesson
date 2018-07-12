package logistic;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetRowTest {

	@Test
	public void testGetRow_ExpressYes() {
		Sending sendingObj = new Sending(220, "B", true);
		Object[] actualValue = sendingObj.getRow();
		double price = 220 * (75.0/50.0);
		Object[] expectValue = new Object[] {220.0, "B", "YES", (price + (price * 0.15))};
		assertArrayEquals(expectValue, actualValue);
	}
	
	@Test
	public void testGetRow_ExpressNo() {
		Sending sendingObj = new Sending(220, "B", false);
		Object[] actualValue = sendingObj.getRow();
		double price = 220 * (75.0/50.0);
		Object[] expectValue = new Object[] {220.0, "B", "YES", price};
		assertArrayEquals(expectValue, actualValue);
	}

}
