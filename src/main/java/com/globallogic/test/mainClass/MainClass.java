package com.globallogic.test.mainClass;

import com.globallogic.test.testCases.TestCases;
import com.globallogic.test.utilities.TestBase;

public class MainClass {
   public static void main(String[] args){
       TestBase.setUp();
       TestCases tc = new TestCases();
       tc.test001();
       tc.test002();
       tc.test003();
       TestBase.cleanUp();
   }
}
