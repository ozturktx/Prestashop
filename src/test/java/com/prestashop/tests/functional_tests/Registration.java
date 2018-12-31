package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Registration extends TestBase {

    @Test
    public void registrationTest() throws InterruptedException {

        String fname=faker.name().firstName();
        String lname=faker.name().lastName();
        String phonenmbr=faker.phoneNumber().cellPhone();
        String email=faker.name().lastName()+faker.number().digit()+"@gmail.com";
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys(email);
        driver.findElement(By.id("SubmitCreate")).click();

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(fname);
        driver.findElement(By.id("customer_lastname")).sendKeys(lname);
        String actual=driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertEquals(actual,email,actual);
        //driver.findElement(By.id("email")).sendKeys("oscar6141@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Password6141");
        select=new Select(driver.findElement(By.id("days")));
        select.selectByIndex(19);
        select=new Select(driver.findElement(By.id("months")));
        select.selectByIndex(2);
        select=new Select(driver.findElement(By.id("years")));
        select.selectByIndex(3);
        driver.findElement(By.id("address1")).sendKeys("TEXAS");
        driver.findElement(By.id("city")).sendKeys("TEXAS");
        select=new Select(driver.findElement(By.id("id_state")));
        select.selectByValue("43");
        driver.findElement(By.id("postcode")).sendKeys("77611");
        driver.findElement(By.id("phone_mobile")).sendKeys(phonenmbr);
        driver.findElement(By.id("submitAccount")).click();

        String fullname=driver.findElement(By.xpath("//a[@title='View my customer account']")).getText();

        System.out.println(fullname);
        Assert.assertEquals(fname+" "+lname,fullname);
        driver.findElement(By.xpath("//a[.='My personal information']")).click();
        Thread.sleep(3000);
        String fllname=driver.findElement(By.id("firstname")).getAttribute("value")+" "+driver.findElement(By.id("lastname")).getAttribute("value");
        Assert.assertEquals(fllname,fullname);
        driver.findElement(By.xpath("(//a//span//i)[3]")).click();

        driver.findElement(By.xpath("//a[.='My addresses']")).click();
        String address=driver.findElement(By.xpath("//span[@class='address_address1']")).getText();
        Assert.assertEquals(address,"TEXAS",address);

        driver.findElement(By.xpath("//a[@title='Log me out']")).click();
    }
}
