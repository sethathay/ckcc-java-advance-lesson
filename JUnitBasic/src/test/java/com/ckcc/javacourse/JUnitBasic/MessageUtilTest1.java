package com.ckcc.javacourse.JUnitBasic;

import static org.junit.Assert.*;

import org.junit.Test;

public class MessageUtilTest1 {

	String message = "Robert";	
	MessageUtil messageUtil = new MessageUtil(message);
	   
	@Test
	public void testSalutationMessage() {
		message = "Hi!" + "Robert";
		assertEquals(message,messageUtil.salutationMessage());
	}

}
