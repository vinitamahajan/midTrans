package com.midTrans.pageobjects;

import io.appium.java_client.pagefactory.HowToUseLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

public class OTPObject {

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//input[@name='PaRes']")
    public WebElement txtPassword;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//button[@name='ok']")
    public WebElement btnOK;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//button[@name='cancel']")
    public WebElement btnCancel;


    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//button[@name='resend']")
    public WebElement btnResend;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='final-panel success']")
    public WebElement lblSuccess;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='final-panel failed']")
    public WebElement lblFailure;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//i[@class='icon xmark']")
    public WebElement lbliconXMark;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='text-failed text-bold']//span")
    public WebElement lblfailText1;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='text-failed']//span")
    public WebElement lblfailText2;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='trans-status trans-success']//span")
    public List<WebElement> lblSuccessHome;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='text-button-main']//span[text()='Use Another Payment Options']")
    public WebElement btnAnotherPaymentOption;
}
