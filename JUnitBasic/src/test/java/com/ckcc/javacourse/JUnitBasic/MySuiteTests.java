package com.ckcc.javacourse.JUnitBasic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MessageUtilTest.class, MessageUtilTest1.class })
public class MySuiteTests {

}
