package com.midTrans.pages;

import com.midTrans.Common.CommonActions;
import com.midTrans.pageobjects.HomeScreenObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen extends CommonActions {

    public HomeScreenObject homeScreenObject = new HomeScreenObject();

    public HomeScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),
                homeScreenObject);
    }

    @Step("Clicking BuyNow....")
    public void clickBuyNow() {
        homeScreenObject.btnBuyNow.click();
        //waitForElementToAppear(homeScreenObject.btnBuyNow).click();
    }

    @Step("Verifying Label....")
    public boolean verifyLabel() {
        waitForElementToAppear(homeScreenObject.lblShoppingCart);
        return homeScreenObject.lblShoppingCart.isDisplayed();
    }

    @Step("Checking visibility of Shopping Cart....")
    public String verifyLabelText() {
        waitForElementToAppear(homeScreenObject.lblShoppingCart);
        return homeScreenObject.lblShoppingCart.getText();
    }

}
