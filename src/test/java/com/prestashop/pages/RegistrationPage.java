package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

    public RegistrationPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id="id_gender1")
    public WebElement title;
    @FindBy(id="customer_firstname")
    public WebElement firstName;

    @FindBy(id="customer_lastname")
    public WebElement lastName;

    @FindBy(id="email")
    public WebElement email;

    @FindBy(id="passwd")
    public WebElement password;

    @FindBy(id="days")
    public WebElement day;

    @FindBy(id="months")
    public WebElement month;

    @FindBy(id="years")
    public WebElement year;

    @FindBy(id="address1")
    public WebElement address;

    @FindBy(id="city")
    public WebElement city;

    @FindBy(id="id_state")
    public WebElement state;

    @FindBy(id="postcode")
    public WebElement zipCode;

    @FindBy(id="phone_mobile")
    public WebElement phone;

    @FindBy(id="submitAccount")
    public WebElement register;

    @FindBy(xpath="//div[@class='alert alert-danger']//li[1]")
    public WebElement errorMessage;


    public Select stateList()
    {
        return new Select(state);
    }

    public Select dayList()
    {
        return new Select(day);
    }
    public Select monthList()
    {
        return new Select(month);
    }

    public Select yearList()
    {
        return new Select(year);
    }
}
