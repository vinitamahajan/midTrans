package com.midTrans.Common;

import com.google.common.collect.ImmutableMap;
import com.midTrans.Utilities.DiskCleanUp;
import com.midTrans.Utilities.PropertiesFileUtils;
import com.midTrans.Utilities.TestListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.qameta.allure.Allure;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Listeners({TestListener.class})
public class BaseTest {

    DesiredCapabilities caps = new DesiredCapabilities();
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);
    public AppiumDriver<MobileElement> driver;
    public static PropertiesConfiguration config;
    public InputStream input = null;
    public static AppiumDriverLocalService appiumService;
    public static String appiumServiceUrl;

    @Parameters({"platform","platformVersion","deviceName"})
    @BeforeTest(groups = {"Smoke", "Regression"})
    public void initEnvProperties(String platform,String platformVersion,String deviceName) {
        try {
            System.out.println("I will be executing before each class");
            DiskCleanUp.cleanUpDisk();
            File propertiesFile = new File("src/main/resources/env.properties");
            config = new PropertiesConfiguration(propertiesFile);
            config.setProperty("platform", platform);
            config.save();
            setAllureEnvironment(platform);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Parameters({"platform","platformVersion","deviceName"})
    @BeforeClass(groups = {"Smoke", "Regression"})
    public void setDriver(String platform,String platformVersion,String deviceName) throws InterruptedException, MalformedURLException, ConfigurationException {
        startServer();
        if (config.getProperty("platform").equals("android")) {
            androidSetup(platform,platformVersion,deviceName);
        } else {
            if (config.getProperty("platform").equals(("ios"))) {
                iosSetup(platform,platformVersion,deviceName);
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(4000);
    }

    @AfterMethod(groups = {"Smoke", "Regression"})
    public void captureScreenshot(ITestResult iTestResult)
    {
        Object iTestResultInstance = iTestResult.getInstance();
        AppiumDriver appiumDriver = ((BaseTest) iTestResultInstance).driver;
        if((appiumDriver instanceof AndroidDriver) || (appiumDriver instanceof IOSDriver))
        {
            Allure.addAttachment(UUID.randomUUID().toString(), new
                    ByteArrayInputStream(((TakesScreenshot)
            this.driver).getScreenshotAs(OutputType.BYTES)));
        }
    }

    @AfterClass(groups = {"Smoke", "Regression"})
    public void tearDown()
    {
        if(driver !=null)
        {
            try
            {
                driver.quit();
            }
            catch (NoSuchMethodError | NoSuchSessionException | SessionNotCreatedException nsme)
            {
                LOG.error(nsme.getMessage());
            }
            driver = null;
        }
        stopServer();
    }

    public static void startServer()
    {
        int port = 4723;
        appiumService = AppiumDriverLocalService.buildDefaultService();
        if(!checkIfServerIsRunning(4723)) {
            appiumService.start();
            if(appiumService == null || !appiumService.isRunning())
            {
                throw new AppiumServerHasNotBeenStartedLocallyException("Appium Server Node has not been started !!");
            }
        }
        else
        {
            System.out.println("Appium server is already running on port : " + port);
        }
        System.out.println(appiumService.getUrl());
        appiumServiceUrl = appiumService.getUrl().toString();
        System.out.println("Appium Server address : " + appiumServiceUrl);
    }

    public static void stopServer(){
        if(appiumService!=null)
        {
            appiumService.stop();
        }
        appiumService = null;
    }

    public static boolean checkIfServerIsRunning(int port)
    {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try
        {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        }
        catch (IOException e)
        {
            isServerRunning = true;
        }
        finally {
            serverSocket = null;
        }

        return isServerRunning;
    }

    public AppiumDriver<MobileElement> getDriver()
    {
        return driver;
    }

    public void androidSetup(String platform,String platformVersion,String deviceName) throws MalformedURLException, ConfigurationException {
       DesiredCapabilities capabilities = new DesiredCapabilities();
        File propertiesFile = new File("src/main/resources/android.properties");
        config = new PropertiesConfiguration(propertiesFile);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,System.getProperty("platform"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, System.getProperty(platformVersion));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("skipUnlock", false);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, System.getProperty("deviceName"));
        capabilities.setCapability("showChromedriverLog","true");
        //capabilities.setCapability("uiautomator2ServerInstallTimeout",40000);
        capabilities.setCapability("chromedriver_autodownload",true);
        //capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        this.driver = new AndroidDriver<MobileElement>(new URL(appiumServiceUrl), capabilities);
    }

    public void iosSetup(String platform,String platformVersion,String deviceName) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, System.getProperty("platform"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, System.getProperty(platformVersion));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, System.getProperty(deviceName));
        capabilities.setCapability("autoAcceptAlerts", true);
        driver = new IOSDriver<MobileElement>(new URL(appiumServiceUrl), capabilities);
    }

    public  static void setAllureEnvironment(String platform){
        if(platform.toLowerCase().equals("ios"))
        {
            PropertiesFileUtils.loadRunConfigProps("/src/main/resources/commonconfig.properties",
                    "ios.properties");
            allureEnvironmentWriter(
                    ImmutableMap.<String,String>builder()
                            .put("Platform", "iOS")
                            .put("Device", PropertiesFileUtils.getProfileProps("ios.device.name"))
                            .put("Platform.Version", PropertiesFileUtils.getProfileProps("ios.platform.version"))
                            .put("Device.Udid", PropertiesFileUtils.getProfileProps("ios.udid"))
                            .put("URL", appiumServiceUrl)
                            .build());
        } else if (platform.toLowerCase().equals("android")) {
            PropertiesFileUtils.loadRunConfigProps("/src/main/resources/commonconfig.properties", "android.property");
            allureEnvironmentWriter(
                    ImmutableMap.<String, String>builder()
                            .put("Platform", "Android")
                            .put("Device", PropertiesFileUtils.getProfileProps("android.device.name"))
                            .put("Platform.Version", PropertiesFileUtils.getProfileProps("android.platform.version"))
                            .put("Device.Udid", PropertiesFileUtils.getProfileProps("android.udid"))
                            .put("URL", appiumServiceUrl)
                            .build());

        }
    }
}
