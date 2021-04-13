package com.midTrans.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DiskCleanUp {
    private static final Logger LOG = LoggerFactory.getLogger(DiskCleanUp.class);

    public static void cleanUpDisk()
    {
        try
        {
            PropertiesFileUtils.loadRunConfigProps("src/main/resources/commonconfig.properties",
                    "env.property");
            if((new
                    File(PropertiesFileUtils.getProfileProps("screenshot.dir"))).exists())
                FileUtils.cleanDirectory(new
                        File(PropertiesFileUtils.getProfileProps("screenshot.dir")));
        } catch (IOException e) {
            LOG.warn("Config.properties file is missing !!");
            LOG.error(e.getMessage());
        }
    }
}
