package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class MyAccountPage {

    public MyAccountPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(css=".account>span")
    public WebElement fullname;

    @FindBy(xpath="//a[.='My personal information']")
    public WebElement personalInformation;

    @FindBy(id="firstname")
    public WebElement firstNameAddress;

    @FindBy(id="lastname")
    public WebElement lastNameAddress;

    @FindBy(xpath = "(//a//span//i)[3]")
    public WebElement backtoAccount;

    @FindBy(xpath = "//a[.='My addresses']")
    public WebElement myAddresses;

    @FindBy(xpath = "//span[@class='address_address1']")
    public WebElement activeAddress;
}
