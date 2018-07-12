package logistic;

import static org.junit.Assert.*;
import org.junit.Test;

public class InitPriceTest {

	@Test
	public void testInitPrice_LocationA() {
		Sending sendingObj = new Sending(120, "A", false);
		double actualValue = sendingObj.initPrice();
		assertEquals((55.0/75.0), actualValue,0.0);
	}
	
	@Test
	public void testInitPrice_LocationB() {
		Sending sendingObj = new Sending(120, "B", false);
		double actualValue = sendingObj.initPrice();
		assertEquals((75.0/50.0), actualValue,0.0);
	}
	
	@Test
	public void testInitPrice_LocationC() {
		Sending sendingObj = new Sending(120, "C", false);
		double actualValue = sendingObj.initPrice();
		assertEquals((27.0/30.0), actualValue,0.0);
	}
	
	@Test
	public void testInitPrice_LocationD() {
		Sending sendingObj = new Sending(120, "D", false);
		double actualValue = sendingObj.initPrice();
		assertEquals((50.0/80.0), actualValue,0.0);
	}
	
	@Test
	public void testInitPrice_LocationOther() {
		Sending sendingObj = new Sending(120, "E", false);
		double actualValue = sendingObj.initPrice();
		assertEquals(0, actualValue,0.0);
	}

}
