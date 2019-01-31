package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
;import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AccountInformation extends TestBase {

   private String fullname;
    String accountName;
    String message;
    @Test
    public void LoginMyAccount() throws InterruptedException {
        extentLogger=report.createTest("Login Test ");

        pages.homePage().signin.click();
        pages.signinPage().email.sendKeys(ConfigurationReader.getProperty("username"));
        pages.signinPage().password.sendKeys(ConfigurationReader.getProperty("password"));
        pages.signinPage().signInButton.click();
        String title= Driver.getDriver().getTitle();
        accountName=pages.myAccountPage().fullname.getText();

extentLogger.info("enter credentials and click log in");
        Assert.assertTrue(title.contains("My account"));
        Assert.assertEquals(accountName,"Oscar Bono");

        pages.myAccountPage().personalInformation.click();
        Thread.sleep(3000);
        fullname= pages.myAccountPage().firstNameAddress.getAttribute("value")+" "+pages.myAccountPage().lastNameAddress.getAttribute("value");
        Assert.assertEquals(accountName,fullname);
        pages.myAccountPage().save.click();
        extentLogger.info("Click on personal info and confirm that user name");
        message=pages.myAccountPage().alertMessage.getText();
        Assert.assertEquals(message,"The password you entered is incorrect.");
        extentLogger.info("Click save button and confirm that there is a error");

        pages.myAccountPage().backtoAccount.click();
        title=Driver.getDriver().getTitle();
        Assert.assertTrue(title.contains("My account"));
//clicks on my address tab
        extentLogger.info("confirm the address");
        pages.myAccountPage().myAddresses.click();
       //clicks on add a address
        pages.myAccountPage().addAddress.click();
        extentLogger.info("confirm the address");
        Thread.sleep(2000);
        Assert.assertEquals(accountName,fullname);

        pages.myAccountPage().firstNameAddress.clear();
        pages.myAccountPage().submitAddress.click();

       // List<WebElement>errors=driver.findElements(By.xpath("//div[@class='alert alert-danger']//li"));
        Assert.assertTrue(pages.registrationPage().errorMessage().get(0).getText().contains("firstname is required."));

        extentLogger.info("Confirm that firstname is required message displayed");
    }

}
