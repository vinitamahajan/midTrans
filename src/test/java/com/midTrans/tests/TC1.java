package com.midTrans.tests;

import com.midTrans.Common.BaseTest;
import com.midTrans.Common.CommonActions;
import com.midTrans.Utilities.TestListener;
import com.midTrans.pages.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Listeners({TestListener.class})
public class TC1 extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(TC1.class);
    public HomeScreen homeScreen;
    public OrderSummaryScreen orderSummaryScreen;
    public CreditCardDetailsScreen creditCardDetailsScreen;
    public OTPScreen otpScreen;
    public SelectPaymentScreen selectPaymentScreen;
    public ShoppingCartScreen shoppingCartScreen;

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(groups = {"Smoke","Regression"})
    public void init()
    {
        homeScreen = new HomeScreen(driver);
        orderSummaryScreen = new OrderSummaryScreen(driver);
        creditCardDetailsScreen = new CreditCardDetailsScreen(driver);
        otpScreen = new OTPScreen(driver);
        selectPaymentScreen = new SelectPaymentScreen(driver);
        shoppingCartScreen = new ShoppingCartScreen(driver);
    }

    @Description("Test Description : This test case helps to verify Buy Now without login")
    @Test(priority = 1,description = "Home Screen: Verify uy Now without login", groups = {"Regression", "Smoke"}, alwaysRun = true)
    @Severity(SeverityLevel.CRITICAL)
    public void initSetUp() throws InterruptedException {
        driver.get("https://demo.midtrans.com");
        boolean flag = false;
        homeScreen.clickBuyNow();
        flag = homeScreen.verifyLabel();

        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES)));
        softAssert.assertTrue(flag,"Shopping cart is displayed !!");
        Assert.assertTrue(homeScreen.verifyLabelText().contains("Shopping Cart"));
    }

    @Description("Test Description : This test case helps to verify Shopping cart fields")
    @Test(priority = 2,description = "Shopping Cart Screen: Verify the Shopping cart fields", groups = {"Regression", "Smoke"}, alwaysRun = true)
    @Severity(SeverityLevel.CRITICAL)
    public void shoppingCart() throws Exception {
        boolean flag = false;
        CommonActions commonActions = new CommonActions(driver);
        Map<String, String> carddata = commonActions.readJSONHierarchy("CreditCard.json", "$['shoppinCart']", HashMap.class);
        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES)));

        softAssert.assertEquals(shoppingCartScreen.getName(), carddata.get("NAME"),
                "Shopping Cart Screen: Name is displayed");

        softAssert.assertEquals(shoppingCartScreen.getEmail(), carddata.get("EMAIL"),
                "Shopping Cart Screen: Email is displayed");

        softAssert.assertEquals(shoppingCartScreen.getPhNo(), carddata.get("PHONE_NUMBER"),
                "Shopping Cart Screen: Email is displayed");

        softAssert.assertEquals(shoppingCartScreen.getCity(), carddata.get("CITY"),
                "Shopping Cart Screen: City is displayed");

        softAssert.assertEquals(shoppingCartScreen.getAddress(), carddata.get("ADDRESS"),
                "Shopping Cart Screen: Address is displayed");

        softAssert.assertEquals(shoppingCartScreen.getPostalCode(), carddata.get("POSTAL_CODE"),
                "Shopping Cart Screen: Postal Code is displayed");

        softAssert.assertTrue(flag,"Shopping cart is displayed !!");
        shoppingCartScreen.clickCheckout();
    }

    @Description("Test Description : This test case helps to verify Order Details Screen")
    @Test(priority = 3,description = "Order Details Screen: Verify the Shopping cart fields", groups = {"Regression", "Smoke"}, alwaysRun = true)
    @Severity(SeverityLevel.CRITICAL)
    public void verifyOrderDetails() throws Exception {
        boolean flag = false;
        CommonActions commonActions = new CommonActions(driver);
        Map<String, String> carddata = commonActions.readJSONHierarchy("CreditCard.json", "$['shoppinCart']", HashMap.class);
        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES)));

        int noofframes=driver.findElements(By.tagName("iframe")).size();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'snap-midtrans')]")));

        orderSummaryScreen.verifyCurrency();
        orderSummaryScreen.verifyAmount();
        orderSummaryScreen.clickShippingDetails();


        softAssert.assertEquals(orderSummaryScreen.verifyShippingCustNameDetails(), carddata.get("NAME"),
                "Shipping Details Screen: Name is displayed");

        softAssert.assertEquals(orderSummaryScreen.verifyShippingCustPhNoDetails(), carddata.get("PHONE_NO"),
                "Shipping Details Screen: Phone No is displayed");

        softAssert.assertEquals(orderSummaryScreen.verifyShippingCustEmailDetails(), carddata.get("EMAIL"),
                "Shipping Details Screen: Email is displayed");

        softAssert.assertEquals(orderSummaryScreen.verifyShippingCustAddressDetails(), carddata.get("ADDRESS"),
                "Shipping Details Screen: Address is displayed");

        orderSummaryScreen.clickOrderDetails();

        softAssert.assertEquals(orderSummaryScreen.getlblItemName(), "MidTrans Pillow",
                "Order Details Screen: Item Name is displayed");
        softAssert.assertEquals(orderSummaryScreen.getlblItemName(), "20,000",
                "Order Details Screen: Amount is displayed");

        orderSummaryScreen.clickContinue();
    }

    @Description("Test Description : This test case helps to verify Payment via Credit Card")
    @Test(priority = 4,description = "Order Details Screen: Verify the Payment via credit card", groups = {"Regression", "Smoke"}, alwaysRun = true)
    @Severity(SeverityLevel.CRITICAL)
    public void verifyPaymentviaCreditCard() throws Exception {
        boolean flag = false;
        CommonActions commonActions = new CommonActions(driver);
        Map<String, String> carddata = commonActions.readJSONHierarchy("CreditCard.json", "$['creditCardSuccess']", HashMap.class);
        Map<String, String> invalidcarddata = commonActions.readJSONHierarchy("CreditCard.json", "$['creditCardFailure']", HashMap.class);
        Map<String, String> shippingcart = commonActions.readJSONHierarchy("CreditCard.json", "$['shoppinCart']", HashMap.class);
        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES)));

        selectPaymentScreen.selectViaCredit();

        creditCardDetailsScreen.enterCard(invalidcarddata.get("INVALID_CARD_NO"));
        softAssert.assertEquals(creditCardDetailsScreen.getInvalidMsg(), invalidcarddata.get("INVALID_CARD"),
                "Invalid Card message displayed");

        creditCardDetailsScreen.enterExpiryDate(invalidcarddata.get("EXPIRY_DATE"));
        softAssert.assertEquals(creditCardDetailsScreen.getcssvalue(), invalidcarddata.get("INVALID_EXPIRY"),
                "Invalid Expiry Date color changed to Red");

       creditCardDetailsScreen.clickBack();
       Thread.sleep(1000);
       selectPaymentScreen.selectViaCredit();

        creditCardDetailsScreen.enterCard(carddata.get("CREDIT_CARD"));
        creditCardDetailsScreen.enterExpiryDate(carddata.get("EXPIRY_DATE"));
        creditCardDetailsScreen.entercvv(carddata.get("CVV"));

        softAssert.assertEquals(creditCardDetailsScreen.getEmail(), shippingcart.get("EMAIL"),
                "Correct Email");

        softAssert.assertEquals(creditCardDetailsScreen.getTel(), shippingcart.get("PHONE_NO"),
                "Correct Email");

        creditCardDetailsScreen.clickPayNow();
    }

    @Description("Test Description : This test case helps to verify OTP")
    @Test(priority = 5,description = "Order Details Screen: Verify the OTP", groups = {"Regression", "Smoke"}, alwaysRun = true)
    @Severity(SeverityLevel.CRITICAL)
    public void verifyOTPInvalid() throws Exception {
        boolean flag = false;
        CommonActions commonActions = new CommonActions(driver);
        Map<String, String> carddata = commonActions.readJSONHierarchy("CreditCard.json", "$['creditCardSuccess']", HashMap.class);
        Map<String, String> invalidcarddata = commonActions.readJSONHierarchy("CreditCard.json", "$['creditCardFailure']", HashMap.class);
        Map<String, String> shippingcart = commonActions.readJSONHierarchy("CreditCard.json", "$['shoppinCart']", HashMap.class);
        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES)));


        int noofframes = driver.findElements(By.tagName("iframe")).size();
        System.out.println(noofframes);
        driver.switchTo().frame(0);

        otpScreen.enterOTP(invalidcarddata.get("BANK_OTP"));
        otpScreen.clickOK();

        Thread.sleep(5000);
        int noofframes1 = driver.findElements(By.tagName("iframe")).size();
        System.out.println(noofframes1);
        driver.switchTo().frame(0);

        softAssert.assertEquals(otpScreen.getFailureIcon(), true,
                "Failure Icon is displayed");

        softAssert.assertEquals(otpScreen.getFailuremsg1(), invalidcarddata.get("FAILURE_LABEL1"),
                "Failure Message is displayed");

        softAssert.assertEquals(otpScreen.getFailuremsg2(), invalidcarddata.get("FAILURE_LABEL2"),
                "Failure Message is displayed");
    }

    @Description("Test Description : This test case helps to verify OTP valid")
    @Test(priority = 6,description = "Order Details Screen: Verify the OTP valid", groups = {"Regression", "Smoke"}, alwaysRun = true)
    @Severity(SeverityLevel.CRITICAL)
    public void verifyOTPvalid() throws Exception {
        boolean flag = false;
        CommonActions commonActions = new CommonActions(driver);
        Map<String, String> carddata = commonActions.readJSONHierarchy("CreditCard.json", "$['creditCardSuccess']", HashMap.class);
        Map<String, String> invalidcarddata = commonActions.readJSONHierarchy("CreditCard.json", "$['creditCardFailure']", HashMap.class);
        Map<String, String> shippingcart = commonActions.readJSONHierarchy("CreditCard.json", "$['shoppinCart']", HashMap.class);
        Map<String, String> labels = commonActions.readJSONHierarchy("CreditCard.json", "$['labels']", HashMap.class);
        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES)));

        otpScreen.clickpayanother();

        selectPaymentScreen.selectViaCredit();

        creditCardDetailsScreen.enterCard(carddata.get("CREDIT_CARD"));
        creditCardDetailsScreen.enterExpiryDate(carddata.get("EXPIRY_DATE"));
        creditCardDetailsScreen.entercvv(carddata.get("CVV"));

        creditCardDetailsScreen.clickPayNow();

        int noofframes = driver.findElements(By.tagName("iframe")).size();
        System.out.println(noofframes);
        driver.switchTo().frame(0);

        otpScreen.enterOTP(carddata.get("BANK_OTP"));
        otpScreen.clickOK();
    }

    @Description("Test Description : This test case helps to verify Success message")
    @Test(priority = 7,description = "Order Details Screen: Verify the Success message", groups = {"Regression", "Smoke"}, alwaysRun = true)
    @Severity(SeverityLevel.CRITICAL)
    public void verifySuccessMessage() throws Exception {
        CommonActions commonActions = new CommonActions(driver);
        Map<String, String> carddata = commonActions.readJSONHierarchy("CreditCard.json", "$['creditCardSuccess']", HashMap.class);
         Map<String, String> labels = commonActions.readJSONHierarchy("CreditCard.json", "$['labels']", HashMap.class);
        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES)));

        softAssert.assertEquals(otpScreen.getSuccessHome1(), labels.get("SUCCESS_LABEL1"), "Success Message is displayed");
        softAssert.assertEquals(otpScreen.getSuccessHome2(), labels.get("SUCCESS_LABEL2"), "Success Message is displayed");

    }
}
