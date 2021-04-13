package com.midTrans.pageobjects;

import io.appium.java_client.pagefactory.HowToUseLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

public class HomeScreenObject {

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//a[@class='btn buy']")
    public WebElement btnBuyNow;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='cart-head']/span[contains(text(),'Shopping Cart')]")
    public WebElement lblShoppingCart;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='title']")
    //@FindBy(xpath = "//div[text()='Midtrans Pillow']")
    public WebElement lblMidTransPillow;

}
