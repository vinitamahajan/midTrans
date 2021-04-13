package com.midTrans.Common;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;

public class CommonActions {

    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;
    public Logger LOG = LoggerFactory.getLogger(CommonActions.class);
    private static final long DRIVER_WAIT_TIME = 20;
    private JsonParser parser = new JsonParser();

    public CommonActions(AppiumDriver<MobileElement> driver)
    {
        this.driver = driver;
    }

    public void hideKeypad() throws InterruptedException {
        try {
            Thread.sleep(2000);
            driver.hideKeyboard();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        }
    }

    public void waitForframeToLoad(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void waitForPageToLoad(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void waitForElementToDisAppear(String id) {
        WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
    }

    public void waitForElementsToAppear(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public WebElement waitForElement(WebElement element) {
        waitForPageToLoad(element);
        WebElement el = element;
        return el;
    }

    public void swipeLeftUntilTextExists(String expected) {
        do {
            swipeLeft();
        } while (!driver.getPageSource().contains(expected));
    }

    public void swipeRight() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.9);
        int endx = (int) (size.width * 0.20);
        int starty = size.height / 2;
        new TouchAction(driver).press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(endx, starty)).release().perform();
    }

    public void swipeLeft() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.8);
        int endx = (int) (size.width * 0.20);
        int starty = size.height / 2;
        new TouchAction(driver).press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(endx, starty)).release();
    }

    public void clickBackButton() {
        driver.navigate().back(); //Closes keyboard
    }

    protected WebElement waitForExpectedElement(final By by) {
        return wait.until(visibilityOfElementLocated(by));
    }

    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
            LOG.info(e.getMessage());
            return null;
        } catch (TimeoutException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }
    public boolean textToBePresentInElementLocated(final By by, final String text) {
        return new WebDriverWait(driver, DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public boolean textToBePresentInElementValue(final WebElement element, final String text) {
        return new WebDriverWait(driver, DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    public boolean textToBePresentInElementValue(final By by, final String text) {
        return new WebDriverWait(driver, DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementValue(by, text));
    }

    public boolean invisibilityOfElementLocated(By by) {
        return (new WebDriverWait(driver, DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean invisibilityOfElementWithText(final By by, final String text) {
        return (new WebDriverWait(driver, DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementWithText(by, text));
    }

    public WebElement elementToBeClickable(By by) {
        return (new WebDriverWait(driver, DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(by));
    }

    private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return driver -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
            }
            WebElement element = driver.findElement(by);
            return element.isDisplayed() ? element : null;
        };
    }

    public boolean isElementPresent(final By by) {
        try {
            new WebDriverWait(driver, DRIVER_WAIT_TIME).until(ExpectedConditions.presenceOfElementLocated(by));

        } catch (TimeoutException exception) {
            LOG.info(exception.getMessage());
            return false;
        }
        return true;
    }

    public <T> T readJSONHierarchy(String fileName, String hierarchy, Class<T> T) throws Exception {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int callerMethodIndex = 0;
        for (int i = 0; !(stackTrace[i].toString().startsWith(this.getClass().getName())); callerMethodIndex = i+++1) {
            continue;
        }

        String[] trace = stackTrace[callerMethodIndex].getClassName().toString().split("\\.");
        String resourcePath = "src/main"+ File.separator+"resources"+File.separator+"TestCaseData"
                +File.separator+fileName;

        JsonElement data = this.parser.parse(new FileReader(resourcePath));
        DocumentContext context = JsonPath.parse(data.getAsJsonObject().toString());

        return context.read(hierarchy,T);
    }

}

