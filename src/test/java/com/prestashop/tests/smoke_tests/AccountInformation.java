package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
;import org.testng.annotations.Test;
public class AccountInformation extends TestBase {
    @Test
    public void LoginMyAccount() throws InterruptedException {
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("oscar6161@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Password61");
        driver.findElement(By.id("SubmitLogin")).click();
        String title=driver.getTitle();
        String userName=driver.findElement(By.xpath("//a[@title='View my customer account']")).getText();
        System.out.println(userName);

        Assert.assertTrue(title.contains("My account"));
        Assert.assertEquals(userName,"Oscar Bono");

        driver.findElement(By.xpath("//a[.='My personal information']")).click();
        Thread.sleep(3000);
        String fullname=driver.findElement(By.id("firstname")).getAttribute("value")+" "+driver.findElement(By.id("lastname")).getAttribute("value");
        Assert.assertEquals(userName,fullname);
        driver.findElement(By.xpath("//span[.='Save']")).click();
        String message=driver.findElement(By.xpath("//div[@class='alert alert-danger']//li")).getText();
        Assert.assertEquals(message,"The password you entered is incorrect.");
        driver.findElement(By.xpath("(//a//span//i)[3]")).click();

        Assert.assertTrue(title.contains("My account"));

        driver.findElement(By.xpath("//a[.='My addresses']")).click();
        driver.findElement(By.xpath("//a[.='Add a new address']")).click();

        Thread.sleep(2000);
        Assert.assertEquals(userName,fullname);
        driver.findElement(By.id("firstname")).clear();

        driver.findElement(By.id("submitAddress")).click();
       message=driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[1]")).getText();
        Assert.assertEquals(message,"firstname is required.");
    }

}
