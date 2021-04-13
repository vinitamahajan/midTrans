package com.midTrans.Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Base64Screenshot {

    public static String getBase64Screenshot(WebDriver driver) throws IOException{
        String screenshotdir = "screenshotdir/";
        String Base64StringofScreenshots = "";
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        Date oDate = new Date();
        SimpleDateFormat oSDF = new SimpleDateFormat("ddMMYYYY_HHmmss");
        String sDate = oSDF.format(oDate);
        FileUtils.copyFile(src, new File(screenshotdir + "Screenshot_" + sDate +
                ".png"));

        byte[] fileContent = FileUtils.readFileToByteArray(src);
        Base64StringofScreenshots = "data:image/png;base64," +
                Base64.getEncoder().encodeToString(fileContent);
        return Base64StringofScreenshots;
     }

}
