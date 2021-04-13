package com.midTrans.pages;

import com.midTrans.Common.CommonActions;
import com.midTrans.pageobjects.OrderSummaryObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class OrderSummaryScreen extends CommonActions {

    public OrderSummaryObject orderSummaryObject = new OrderSummaryObject();

    public OrderSummaryScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),
                orderSummaryObject);
    }

    @Step("Verify Order Summary Currency....")
    public void verifyCurrency() {
        orderSummaryObject.lblCurrency.isDisplayed();
    }

    @Step("Verify Order Summary Amount....")
    public void verifyAmount() {
        orderSummaryObject.txtAmount.isDisplayed();
    }

    @Step("Verify shipping details....")
    public void verifyShippingDetails() {
        waitForElementToAppear(orderSummaryObject.lnkShippingDetails);
    }

    @Step("Verify shipping details:Name....")
    public String verifyShippingCustNameDetails() {
        return orderSummaryObject.shippingCustDetails.get(0).getText();
    }

    @Step("Verify shipping details:PhNo....")
    public String verifyShippingCustPhNoDetails() {
        return orderSummaryObject.shippingCustDetails.get(1).getText();
    }

    @Step("Verify shipping details:Email....")
    public String verifyShippingCustEmailDetails() {
        return orderSummaryObject.shippingCustDetails.get(2).getText();
    }

    @Step("Verify shipping details:Address....")
    public String verifyShippingCustAddressDetails() {
        return orderSummaryObject.shippingCustDetails.get(3).getText();
    }

    @Step("Verify shipping details:Address {7} ....")
    public void clickOrderDetails() {
        orderSummaryObject.lnkOrderDetails.click();
    }

    @Step("Verify shipping details:Address....")
    public void clickShippingDetails() {
        orderSummaryObject.lnkShippingDetails.click();
    }

    @Step("Verify order details:Item Name....")
    public String getlblItemName() {
        return orderSummaryObject.lblItemName.getText();
    }

    @Step("Verify order details:Amount....")
    public String getAmount() {
        return orderSummaryObject.lblAmount.getText();
    }

    @Step("Verify order details:Amount....")
    public void clickContinue() {
        orderSummaryObject.btnContinue.click();
    }
}
