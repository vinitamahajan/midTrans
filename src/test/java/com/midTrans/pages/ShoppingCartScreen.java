package com.midTrans.pages;

import com.midTrans.Common.CommonActions;
import com.midTrans.pageobjects.ShoppingCartObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartScreen extends CommonActions {

    public ShoppingCartObject shoppingCartObject = new ShoppingCartObject();

    public ShoppingCartScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),
                shoppingCartObject);
    }

    @Step("Getting the customer details: name ....")
    public String getName() {
        return shoppingCartObject.shoppingcartCustomerDetails.get(0).getAttribute("value");
    }

    @Step("Getting the customer details: PhoneNo ....")
    public String getPhNo() {
        return shoppingCartObject.shoppingcartCustomerDetails.get(1).getAttribute("value");
    }

    @Step("Getting the customer details: City ....")
    public String getCity() {
        return shoppingCartObject.shoppingcartCustomerDetails.get(2).getAttribute("value");
    }

    @Step("Getting the customer details: Postal Code ....")
    public String getPostalCode() {
        return shoppingCartObject.shoppingcartCustomerDetails.get(3).getAttribute("value");
    }

    @Step("Getting the customer details: Address ....")
    public String getAddress() {
        return shoppingCartObject.shoppingcartCustomerDetailsAddress.getText();
    }

    @Step("Getting the customer details: Postal Code  ....")
    public String getEmail() {
        return shoppingCartObject.shoppingcartCustomerDetailsEmail.getText();
    }

    @Step("Clicking Checkout ....")
    public void clickCheckout() {
        waitForElementToAppear(shoppingCartObject.btnCheckout).click();
    }
}
