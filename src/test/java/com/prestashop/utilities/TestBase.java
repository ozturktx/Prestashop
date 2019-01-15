package com.prestashop.utilities;

import com.github.javafaker.Faker;
import com.prestashop.pages.HomePage;
import com.prestashop.pages.ItemPage;
import com.prestashop.pages.ShoppingCartPage;
import com.prestashop.pages.signinPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    public WebDriver driver;
    protected Actions action;
    String url="http://automationpractice.com/index.php";
    public Select select;
    public Random rndm;
    public ItemPage items;//=new ItemPage();
   public HomePage homePage;//=new HomePage();
    public signinPage signinPage;//=new signinPage();
    public ShoppingCartPage cartPage;//=new ShoppingCartPage();
   /* @BeforeClass
    public void setUpClass()
    {
        WebDriverManager.chromedriver().setup();
    }
*/
    @BeforeMethod
    public void setUp()
    {
        items=new ItemPage();
        homePage=new HomePage();
        signinPage=new signinPage();
        cartPage=new ShoppingCartPage();
        driver=Driver.getDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        action= new Actions(driver);
        rndm=new Random();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        Driver.closeDriver();

    }

}
