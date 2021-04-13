package com.midTrans.Utilities;

import com.midTrans.Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class RetryListener implements IRetryAnalyzer {
    private static int count = 0;
    private static int maxTry = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(!iTestResult.isSuccess()){
            if(count < maxTry)
            {
                count ++;
                iTestResult.setStatus(ITestResult.FAILURE);
                embedScreenshot(iTestResult);
                return true;
            }
        }
        else
        {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    public void embedScreenshot(ITestResult iTestResult)
    {
        AppiumDriver driver = ((BaseTest) iTestResult.getInstance()).getDriver();
        Allure.addAttachment(UUID.randomUUID().toString(),
                new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
    }
}
