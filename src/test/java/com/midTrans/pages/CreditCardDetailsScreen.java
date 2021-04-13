package com.midTrans.pages;

import com.midTrans.Common.CommonActions;
import com.midTrans.pageobjects.CreditCardDetailsObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class CreditCardDetailsScreen extends CommonActions {

    public CreditCardDetailsObject creditCardDetailsObject = new CreditCardDetailsObject();

    public CreditCardDetailsScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),
                creditCardDetailsObject);
    }

    @Step("Entering the card number {0} ....")
    public void enterCard(String cardno) {
        creditCardDetailsObject.txtCardNumber.click();
        creditCardDetailsObject.txtCardNumber.sendKeys(cardno);
    }

    @Step("Entering the expiry date {0} ....")
    public void enterExpiryDate(String expiry) {
        creditCardDetailsObject.txtExpiryDate.click();
        creditCardDetailsObject.txtExpiryDate.sendKeys(expiry);
    }

    @Step("Entering the CVV {0} ....")
    public void entercvv(String cvv) {
        creditCardDetailsObject.txtCVV.click();
        creditCardDetailsObject.txtCVV.sendKeys(cvv);
    }

    @Step("Verifying Email ....")
    public String getEmail() {
        return creditCardDetailsObject.lblEmail.getText();
    }

    @Step("Verifying PhoneNumber....")
    public String getTel() {
        return creditCardDetailsObject.lblTel.getText();
    }

    @Step("Verifying Invalid Message....")
    public String getInvalidMsg() {
        return creditCardDetailsObject.msgInvalidCard.getText();
    }

    @Step("Verifying Invalid Message....")
    public String getcssvalue() {
        return creditCardDetailsObject.msgInvalidexpiry.getCssValue("color");
    }

    @Step("Verifying Invalid Message....")
    public void clearCard() {
        creditCardDetailsObject.txtCardNumber.click();
        creditCardDetailsObject.txtCardNumber.clear();
        creditCardDetailsObject.txtCardNumber.click();
    }

    @Step("Verifying Invalid Message....")
    public void clearExpiry() {
        creditCardDetailsObject.txtExpiryDate.clear();
    }

    @Step("Click Pay Now....")
    public void clickPayNow() {
        creditCardDetailsObject.btnPayNow.click();
    }

    @Step("Click Pay Now....")
    public void clickBack() {
        creditCardDetailsObject.btnBack.click();
    }

}
