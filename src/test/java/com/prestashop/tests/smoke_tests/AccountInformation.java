package com.prestashop.tests.smoke_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AccountInformation {

    String url="http://automationpractice.com/index.php";
    WebDriver driver;
    @BeforeMethod
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void LoginMyAccount(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("oscar6161@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Password61");
        driver.findElement(By.id("SubmitLogin")).click();
        String title=driver.getTitle();
        String userName=driver.findElement(By.xpath("//a[@title='View my customer account']")).getText();
        System.out.println(userName);

        Assert.assertTrue(title.contains("My account"));
        Assert.assertEquals(userName,"Oscar Bono");
    }

    @Test
    public void LoginMyPersonalInfo() throws InterruptedException {
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("oscar6161@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Password61");
        driver.findElement(By.id("SubmitLogin")).click();
        driver.findElement(By.xpath("//a[.='My personal information']")).click();
        String userName=driver.findElement(By.xpath("//a[@title='View my customer account']")).getText();
        Thread.sleep(3000);
        String fullname=driver.findElement(By.id("firstname")).getAttribute("value")+" "+driver.findElement(By.id("lastname")).getAttribute("value");
        Assert.assertEquals(userName,fullname);
        driver.findElement(By.xpath("//span[.='Save']")).click();
        String message=driver.findElement(By.xpath("//div[@class='alert alert-danger']//li")).getText();
        Assert.assertEquals(message,"The password you entered is incorrect.");
        driver.findElement(By.xpath("(//a//span//i)[3]")).click();
        String title=driver.getTitle();
        Assert.assertTrue(title.contains("My account"));
    }
    @Test
    public void MyAddresses() throws InterruptedException {
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("oscar6161@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Password61");
        driver.findElement(By.id("SubmitLogin")).click();
        driver.findElement(By.xpath("//a[.='My addresses']")).click();
        driver.findElement(By.xpath("//a[.='Add a new address']")).click();

        String userName=driver.findElement(By.xpath("//a[@title='View my customer account']")).getText();
        Thread.sleep(2000);
        String fullname=driver.findElement(By.id("firstname")).getAttribute("value")+" "+driver.findElement(By.id("lastname")).getAttribute("value");
        Assert.assertEquals(userName,fullname);
        driver.findElement(By.id("firstname")).clear();

        driver.findElement(By.id("submitAddress")).click();
        String message=driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[1]")).getText();
        Assert.assertEquals(message,"firstname is required.");


    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();


    }
}
