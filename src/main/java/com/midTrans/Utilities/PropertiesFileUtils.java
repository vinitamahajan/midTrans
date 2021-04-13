package com.midTrans.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesFileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesFileUtils.class);
    private static Properties environmentProps;
    private static Properties profileProps;

    public static String getMessage(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return
                    ResourceBundle.getBundle("src/main/resources/message/messages").getString(key);
        }
    }

    public static String getEnvProps(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return environmentProps.getProperty(key);
        }

    }

    public static String getProfileProps(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return profileProps.getProperty(key);
        }
    }

    public static void loadRunConfigProps(String commonConfigPropertyFileLocation, String profilePathKey) {
        environmentProps = new Properties();
        try {
            String projectDir = System.getProperty("user.dir");
            FileInputStream commonFis = new FileInputStream(projectDir + "/" + commonConfigPropertyFileLocation);
            environmentProps.load(commonFis);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        profileProps = new Properties();
        try {
            String projectDir = System.getProperty("user.dir");
            FileInputStream profileFis = new FileInputStream(projectDir + "/" + environmentProps.getProperty(profilePathKey));
            profileProps.load(profileFis);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public static void loadRunConfigProps(String commonConfigPropertyFileLocation)
    {
        environmentProps = new Properties();
        try
        {
            InputStream inputStream = new FileInputStream(commonConfigPropertyFileLocation);
            environmentProps.load(inputStream);
        }
        catch (IOException e)
        {
            LOG.error(e.getMessage());
        }
    }

}