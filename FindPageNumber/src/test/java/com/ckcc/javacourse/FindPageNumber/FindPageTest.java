package com.ckcc.javacourse.FindPageNumber;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class FindPageTest {

	@Test
	public void testFindNearPageNumber_PageIsEven() {
		FindPage objFindPage = new FindPage(10, 6);
		assertEquals(5, objFindPage.findNearPageNumber());
	}
	
	@Test
	public void testFindNearPageNumber_PageIsOdd() {
		FindPage objFindPage = new FindPage(8, 1);
		assertEquals(2, objFindPage.findNearPageNumber());
	}

	@Test
	public void testFindBackPageNumber_PageIsEven() {
		FindPage objFindPage = new FindPage(9, 12);
		assertEquals(25, objFindPage.findBackPageNumber());
	}
	
	@Test
	public void testFindBackPageNumber_PageIsOdd() {
		FindPage objFindPage = new FindPage(3, 11);
		assertEquals(1, objFindPage.findBackPageNumber());
	}

}
