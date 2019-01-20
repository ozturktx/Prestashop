package com.prestashop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {
    private static WebDriver driver;

    private Driver(){}
    public static WebDriver getDriver(){
       // WebDriver driver;
        // if driver is not created then create  a driver object and return it
        if (driver==null){
            String browser=ConfigurationReader.getProperty("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver=new EdgeDriver();
                    break;

                case "safari":
                    try {
                        throw new WebDriverException();
                    }catch (WebDriverException e)
                    {
                        System.out.println("this browser is not supported by Windows OS");
                    }
                        break;


            }
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
