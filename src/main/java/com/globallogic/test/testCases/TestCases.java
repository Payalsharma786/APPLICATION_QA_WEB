package com.globallogic.test.testCases;

import com.globallogic.test.utilities.Driver;
import com.globallogic.test.utilities.ReportGenerator;
import com.globallogic.test.utilities.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

public class TestCases extends TestBase {
    Logger logger = Logger.getLogger(TestCases.class);


    public void test001() {
        try {
            Driver.Instance.findElement(By.xpath("//div[@class='signIn']")).click();
            logger.debug("Clicked on signIn Button");
            Driver.Instance.findElement(By.xpath("//input[@id='phoneNo']")).sendKeys("8462902292");//"payalsharma2711@gmail.com"
            logger.debug("Username is passed");
            Driver.Instance.findElement(By.xpath("//div/input[@id='password']")).sendKeys("12KAfour@");//"password"
            logger.debug("Password is passed");
            Driver.Instance.findElement(By.xpath("//button[@type='submit']")).click();
            logger.debug("Clicked on submit Button");
            logger.debug("UPDATING STATUS PASS");
            ReportGenerator.testResulltDetails.put("1", new Object[]{"test001", "Pass"});
        } catch (Exception e) {
            logger.debug("Exception:: " + e.getMessage());
            logger.debug("UPDATING STATUS FAIL");
            ReportGenerator.testResulltDetails.put("1", new Object[]{"test001", "Fail"});
        }
    }


    public void test002() {
        try {
            Driver.Instance.findElement(By.xpath("//div[@class='search']")).click();
            logger.debug("Clicked on search Button");
            Driver.Instance.findElement(By.xpath("//input[@type='search'][1]")).sendKeys("2 states");//"payalsharma2711@gmail.com"
            logger.debug("2 States is passed");
             Driver.Instance.findElement(By.xpath("//div[@class='title ellipsize'][1]")).click();
            logger.debug("Clicked on search Result");
            String streamingVedioOnline = Driver.Instance.findElement(By.xpath("//div[@class='toptitle clear-both']")).getText();
            logger.debug("Streaming video::" + streamingVedioOnline);
            Assert.assertTrue(streamingVedioOnline.contains("2 States"));
            logger.debug("UPDATING STATUS PASS");
            ReportGenerator.testResulltDetails.put("2", new Object[]{"test002", "Pass"});
        } catch (Exception e) {
            logger.debug("Exeption:: " + e.getMessage());
            logger.debug("UPDATING STATUS FAIL");
            ReportGenerator.testResulltDetails.put("2", new Object[]{"test002", "Fail"});
        }
    }


    public void test003() {
        try {
            Driver.Instance.findElement(By.xpath("//div[@class='search']")).click();
            logger.debug("Clicked on search Button");
            Driver.Instance.findElement(By.xpath("//input[@type='search'][1]")).sendKeys("2 states");//"payalsharma2711@gmail.com"
            logger.debug("2 States is passed");
            Driver.Instance.findElement(By.xpath("//div[@class='title ellipsize'][1]")).click();
            logger.debug("Clicked on search Result");
            String streamingVedioOnline = Driver.Instance.findElement(By.xpath("//div[@class='toptitle clear-both']")).getText();
            logger.debug("Streaming video::" + streamingVedioOnline);
            Assert.assertTrue(streamingVedioOnline.contains("2 States"));
            Driver.Instance.findElement(By.xpath("//div[@class='watch-share']//div[@class='add-to-watchlist']")).click();
            logger.debug("Added to wish list");
            logger.debug("UPDATING STATUS PASS");
            ReportGenerator.testResulltDetails.put("3", new Object[]{"test003", "Pass"});
        } catch (Exception e) {
            logger.debug("Exeption:: " + e.getMessage());
            logger.debug("UPDATING STATUS FAIL");
            ReportGenerator.testResulltDetails.put("3", new Object[]{"test003", "Fail"});
        }
    }

}



