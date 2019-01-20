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
        homePage.signin.click();
        signinPage.email.sendKeys(ConfigurationReader.getProperty("username"));
        signinPage.password.sendKeys(ConfigurationReader.getProperty("password"));
        signinPage.signInButton.click();
        String title= Driver.getDriver().getTitle();
        accountName=myAccountPage.fullname.getText();


        Assert.assertTrue(title.contains("My account"));
        Assert.assertEquals(accountName,"Oscar Bono");

        myAccountPage.personalInformation.click();
        Thread.sleep(3000);
        fullname= myAccountPage.firstNameAddress.getAttribute("value")+" "+myAccountPage.lastNameAddress.getAttribute("value");
        Assert.assertEquals(accountName,fullname);
        myAccountPage.save.click();
        message=myAccountPage.alertMessage.getText();
        Assert.assertEquals(message,"The password you entered is incorrect.");
        myAccountPage.backtoAccount.click();
        title=Driver.getDriver().getTitle();
        Assert.assertTrue(title.contains("My account"));
//clicks on my address tab
        myAccountPage.myAddresses.click();
       //clicks on add a address
        myAccountPage.addAddress.click();

        Thread.sleep(2000);
        Assert.assertEquals(accountName,fullname);
        myAccountPage.firstNameAddress.clear();
        myAccountPage.submitAddress.click();

        List<WebElement>errors=driver.findElements(By.xpath("//div[@class='alert alert-danger']//li"));
        Assert.assertTrue(errors.get(0).getText().contains("firstname is required."));
    }

}
