package com.prestashop.utilities;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.tests.functional_tests.cart.ErrorMessageValidation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    public WebDriver driver;
    protected Actions action;
    String url="http://automationpractice.com/index.php";
    public Select select;
    public SoftAssert softAssert;
    public Random rndm;
    public ItemPage items;
   public HomePage homePage;
    public signinPage signinPage;
    public ShoppingCartPage cartPage;
    public MyAccountPage myAccountPage;
    public FakeData faker;
    public RegistrationPage registrationPage;//=new RegistrationPage()
    @BeforeMethod
    public void setUp()
    {
        faker=new FakeData();
        registrationPage=new RegistrationPage();
        items=new ItemPage();
        homePage=new HomePage();
        signinPage=new signinPage();
        cartPage=new ShoppingCartPage();
        myAccountPage=new MyAccountPage();
//        errorMessageValidation=new ErrorMessageValidation();
  softAssert=new SoftAssert();
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
