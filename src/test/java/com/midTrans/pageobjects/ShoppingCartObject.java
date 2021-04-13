package com.midTrans.pageobjects;

import io.appium.java_client.pagefactory.HowToUseLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

public class ShoppingCartObject {

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//input[@type='text']")
    public List<WebElement> shoppingcartCustomerDetails;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//input[@type='email']")
    public WebElement shoppingcartCustomerDetailsEmail;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//textarea")
    public WebElement shoppingcartCustomerDetailsAddress;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @FindBy(xpath = "//div[@class='cart-checkout']")
    public WebElement btnCheckout;


}
