package com.ckcc.javacourse.JUnitBasic;

import static org.junit.Assert.*;
import org.junit.Test;

public class MessageUtilTest {
	
	String message = "Hello World";
	MessageUtil messageUtil = new MessageUtil(message);

	@Test
	public void testPrintMessage() {
		assertEquals(message, messageUtil.printMessage());
	}

}
