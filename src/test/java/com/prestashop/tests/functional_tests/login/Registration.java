package com.prestashop.tests.functional_tests.login;

import com.prestashop.utilities.TestBase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Registration extends TestBase {

    /*RegistrationPage registrationPage=new RegistrationPage();
    signinPage signinPage=new signinPage();
    HomePage homepage=new HomePage();
    FakeData faker=new FakeData();*/
   // MyAccountPage accountPage=new MyAccountPage();

    public void fillRegistrationForm()
    {
        pages.registrationPage().title.click();
        pages.registrationPage().firstName.sendKeys(faker.getFname());
        pages.registrationPage().lastName.sendKeys(faker.getLname());
        pages.registrationPage().password.sendKeys("password123");
        pages.registrationPage().dayList().selectByIndex(19);
        pages.registrationPage().monthList().selectByIndex(2);
        pages.registrationPage().yearList().selectByIndex(3);
        pages.registrationPage().address.sendKeys(faker.getAddress());
        pages.registrationPage().city.sendKeys(faker.getCity());
        pages.registrationPage().stateList().selectByValue("43");
        pages.registrationPage().zipCode.sendKeys(faker.getZipcode());
        pages.registrationPage().phone.sendKeys(faker.getPhonenmbr());
    }
    @Test
    public void registrationTest() throws InterruptedException {

        extentLogger=report.createTest("Registration test ");
        pages.homePage().signin.click();
        extentLogger.info("Click on sign in button ");
        pages.signinPage().signupEmail.sendKeys(faker.getEmail());
        pages.signinPage().createAaccount.click();
        extentLogger.info("Click on sign up button ");
        fillRegistrationForm();
        extentLogger.info("Fill in the required fields");
        String actual=pages.registrationPage().email.getAttribute("value");
        Assert.assertEquals(actual,faker.getEmail(),actual);

        pages.registrationPage().register.click();
        String fullname=pages.myAccountPage().fullname.getText();
        extentLogger.info("Click on register button");
        extentLogger.info("Check that name matches with the name put during registration");
        Assert.assertEquals(faker.getFname()+" "+faker.getLname(),fullname);
        pages.myAccountPage().personalInformation.click();
        extentLogger.info("Confirm the address");
        String fllname=pages.myAccountPage().firstNameAddress.getAttribute("value")+" "+pages.myAccountPage().lastNameAddress.getAttribute("value");
        Assert.assertEquals(fllname,fullname);
        pages.myAccountPage().backtoAccount.click();
        pages.myAccountPage().myAddresses.click();
        String expectedaddress=pages.myAccountPage().activeAddress.getText();
        Assert.assertEquals(expectedaddress,faker.getAddress(),expectedaddress);
        pages.homePage().logOut.click();
        extentLogger.info("Click on log out");
        extentLogger.pass("Passed");

    }

    @Test
    public void emptyFirstNameError(){
        extentLogger=report.createTest("Register with empty firstname test ");

        pages.homePage().signin.click();
        extentLogger.info("Fill out required filed except firstname and click on register");
        pages.signinPage().signupEmail.sendKeys(faker.getEmail());
        pages.signinPage().createAaccount.click();
        fillRegistrationForm();
        pages.registrationPage().firstName.clear();
        pages.registrationPage().register.click();
        extentLogger.info("Confirm that browser gives error");
        //String  message=registrationPage.errorMessage.getText();
        Assert.assertTrue(pages.registrationPage().errorMessage().get(0).getText().contains("firstname is required."));
        extentLogger.pass("Passed");
    }
}
