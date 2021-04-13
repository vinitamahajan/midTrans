package com.midTrans.pages;

import com.midTrans.Common.CommonActions;
import com.midTrans.pageobjects.SelectPaymentObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class SelectPaymentScreen extends CommonActions {

    public SelectPaymentObject selectPaymentObject = new SelectPaymentObject();

    public SelectPaymentScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),
                selectPaymentObject);
    }

    @Step("Selecting via Credit Card....")
    public void selectViaCredit() {
        selectPaymentObject.lblCreditCard.click();
    }


}
