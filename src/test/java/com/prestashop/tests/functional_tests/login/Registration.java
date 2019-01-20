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
        registrationPage.title.click();
        registrationPage.firstName.sendKeys(faker.getFname());
        registrationPage.lastName.sendKeys(faker.getLname());
        registrationPage.password.sendKeys("password123");
        registrationPage.dayList().selectByIndex(19);
        registrationPage.monthList().selectByIndex(2);
        registrationPage.yearList().selectByIndex(3);
        registrationPage.address.sendKeys(faker.getAddress());
        registrationPage.city.sendKeys(faker.getCity());
        registrationPage.stateList().selectByValue("43");
        registrationPage.zipCode.sendKeys(faker.getZipcode());
        registrationPage.phone.sendKeys(faker.getPhonenmbr());
    }
    @Test
    public void registrationTest() throws InterruptedException {


        homePage.signin.click();

        signinPage.signupEmail.sendKeys(faker.getEmail());
        signinPage.createAaccount.click();
        fillRegistrationForm();
        String actual=registrationPage.email.getAttribute("value");
        Assert.assertEquals(actual,faker.getEmail(),actual);

        registrationPage.register.click();
        String fullname=myAccountPage.fullname.getText();
        Assert.assertEquals(faker.getFname()+" "+faker.getLname(),fullname);
        myAccountPage.personalInformation.click();
        String fllname=myAccountPage.firstNameAddress.getAttribute("value")+" "+myAccountPage.lastNameAddress.getAttribute("value");
        Assert.assertEquals(fllname,fullname);
        myAccountPage.backtoAccount.click();
        myAccountPage.myAddresses.click();
        String expectedaddress=myAccountPage.activeAddress.getText();
        Assert.assertEquals(expectedaddress,faker.getAddress(),expectedaddress);
        homePage.logOut.click();

    }

    @Test
    public void emptyFirstNameError(){

        homePage.signin.click();

        signinPage.signupEmail.sendKeys(faker.getEmail());
        signinPage.createAaccount.click();
        fillRegistrationForm();
        registrationPage.firstName.clear();
        registrationPage.register.click();

        //String  message=registrationPage.errorMessage.getText();
        Assert.assertEquals(registrationPage.errorMessage.getText(),"firstname is required.");
    }
}
