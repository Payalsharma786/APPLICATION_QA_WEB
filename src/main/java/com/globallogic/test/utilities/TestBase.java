package com.globallogic.test.utilities;


import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TestBase {


    public static void setUp() {
        Driver.Initialize();
        Driver.addLogToFile();
        Driver.Instance.navigate().to(PropertiesUtils.getProperty("application.url"));
    }


    public static void cleanUp() {
        ReportGenerator reportGeneratorObject = new ReportGenerator();
        Driver.close();
        Driver.sendMail(reportGeneratorObject.reportGeneration());
    }

}
