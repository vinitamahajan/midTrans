package com.midTrans.pageobjects;

import io.appium.java_client.pagefactory.HowToUseLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

public class OrderSummaryObject {

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//span[@class='text-amount-rp']")
    public WebElement lblCurrency;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//span[@class='text-amount-amount']")
    public WebElement txtAmount;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//a[@class='text-actionable']/span[text()='shipping details']")
    public WebElement lnkShippingDetails;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//a[@class='text-actionable']/span[text()='order details']")
    public WebElement lnkOrderDetails;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='text-button-main']/span[text()='Continue']")
    public WebElement btnContinue;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='text-body']")
    public List<WebElement> shippingCustDetails;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//span[@class='item-name']")
    public WebElement lblItemName;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//td[@class='table-amount text-body']")
    public WebElement lblAmount;
}
