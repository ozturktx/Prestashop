package com.prestashop.utilities;


import junit.framework.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;
    static {
        //path to location of file
        String path = "configuration.properties";
        try {
            // java needs inputstream to read a file.
            //inoutstream takes the location of the file as a constructor

            FileInputStream fileInputStream = new FileInputStream(path);

            //properties is used to read specifically properties file, files that has a key value pairs
            properties = new Properties();
            //file contents are load to properties from inputstream
            properties.load(fileInputStream);
            //streams must be closed
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //returns the value of specific file
    public static String getProperty(String property){
        return properties.getProperty(property);
    }

}
