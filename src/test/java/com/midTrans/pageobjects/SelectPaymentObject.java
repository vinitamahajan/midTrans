package com.midTrans.pageobjects;

import io.appium.java_client.pagefactory.HowToUseLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

public class SelectPaymentObject {

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='list-title text-actionable-bold']")
    //@FindBy(xpath = "//div[text()='Credit/Debit Card']")
    public WebElement lblCreditCard;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='promo-text']/span[text()='promo']")
    public WebElement lblPromo;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//a[@class='header-back']")
    public WebElement btnBack;
}
