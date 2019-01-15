package com.prestashop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    private static WebDriver driver;

    private Driver(){}
    public static WebDriver getDriver(){
       // WebDriver driver;
        // if driver is not created then create  a driver object and return it
        if (driver==null){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        return driver;
    }

    public static void closeDriver()
    {
        if(driver!=null){
            driver.quit();
            driver=null;
        }

    }

}
