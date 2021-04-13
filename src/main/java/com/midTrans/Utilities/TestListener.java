package com.midTrans.Utilities;

import com.midTrans.Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends BaseTest implements ITestListener {

    private static final Logger LOG = LoggerFactory.getLogger(TestListener.class);

    @Attachment(value= "Failure Screenshot", type = "image/png")
    public static byte[] embedScreenshot(AppiumDriver driver)
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Failure Log", type = "text/plain")
    public static String embedTextLog(String log)
    {
        return log;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOG.info(iTestResult.getMethod().getConstructorOrMethod().getName() +
        "Starting the execution .....");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOG.info(iTestResult.getMethod().getConstructorOrMethod().getName() +
                "Completed execution .....");
        LOG.info("Screenshot embedded for TC : " +
                iTestResult.getMethod().getConstructorOrMethod().getName());
        Object testClass = iTestResult.getInstance();

        AppiumDriver appDriver = ((BaseTest)testClass).driver;

        if((appDriver instanceof AndroidDriver) || appDriver instanceof IOSDriver){
            LOG.info("Screenshot embedded for TC : " +
                    iTestResult.getTestName());
            embedScreenshot(appDriver);
            embedTextLog(iTestResult.getMethod().getConstructorOrMethod().getName() +
                    "has failed & screenshot has been embedded");
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOG.info(iTestResult.getMethod().getConstructorOrMethod().getName() +
               "Completed without any error ....." );
        LOG.info("Screenshot embedded for TC : " +
                iTestResult.getMethod().getConstructorOrMethod().getName());
        Object testClass = iTestResult.getInstance();

        AppiumDriver appDriver = ((BaseTest)testClass).driver;

        if((appDriver instanceof AndroidDriver) || appDriver instanceof IOSDriver)
        {
            LOG.info("Screenshot embedded for TC : " +
                    iTestResult.getTestName());
            embedScreenshot(appDriver);
            embedTextLog(iTestResult.getMethod().getConstructorOrMethod().getName() +
                    "has failed & screenshot has been embedded");
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOG.info(iTestResult.getMethod().getConstructorOrMethod().getName() +
                "skipped ....");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
