package com.midTrans.pages;

import com.midTrans.Common.CommonActions;
import com.midTrans.pageobjects.OTPObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class OTPScreen extends CommonActions {

    public OTPObject otpObject = new OTPObject();

    public OTPScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),
                otpObject);
    }

    @Step("Enter OTP: {0} ....")
    public void enterOTP(String otp) {
        otpObject.txtPassword.click();
        otpObject.txtPassword.sendKeys(otp);
    }

    @Step("Clear OTP textbox....")
    public void enterOTPclear() {
        otpObject.txtPassword.click();
        otpObject.txtPassword.clear();
    }

    @Step("Click OK....")
    public void clickOK() {
        otpObject.btnOK.click();
    }

    @Step("get icon....")
    public boolean getFailureIcon() {
        return otpObject.lbliconXMark.isDisplayed();
    }

    @Step("get message1....")
    public String getFailuremsg1() {
        return otpObject.lblfailText1.getText();
    }

    @Step("get message1....")
    public String getFailuremsg2() {
        return otpObject.lblfailText2.getText();
    }

    @Step("click pay another method....")
    public void clickpayanother() {
        otpObject.btnAnotherPaymentOption.click();
    }

    @Step("get success....")
    public boolean getSuccess() {
        return waitForElementToAppear(otpObject.lblSuccess).isDisplayed();
    }

    @Step("get success home....")
    public String getSuccessHome1() {
        return otpObject.lblSuccessHome.get(0).getText();
    }

    @Step("get success home....")
    public String getSuccessHome2() {
        return otpObject.lblSuccessHome.get(1).getText();
    }
}
