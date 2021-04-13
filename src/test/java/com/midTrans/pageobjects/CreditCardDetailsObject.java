package com.midTrans.pageobjects;

import io.appium.java_client.pagefactory.HowToUseLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

public class CreditCardDetailsObject {

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//input[@name='cardnumber']")
    public WebElement txtCardNumber;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//input[@placeholder='MM / YY']")
    public WebElement txtExpiryDate;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//label[text()='CVV']")
    public WebElement lblTel;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//input[@type='email']")
    public WebElement lblEmail;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//input[@inputmode='numeric']")
    public WebElement txtCVV;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='content']//span[text()='Invalid card number']")
    public WebElement msgInvalidCard;


    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//label[text()='Expiry date']")
    public WebElement msgInvalidexpiry;


    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//span[text()='Pay Now']")
    public WebElement btnPayNow;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//a[@class='header-back']")
    public WebElement btnBack;

}
