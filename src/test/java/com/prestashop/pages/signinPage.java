package com.prestashop.pages;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class signinPage {

    public signinPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "email_create")
    public WebElement signupEmail;

    @FindBy(id="SubmitCreate")
    public WebElement createAaccount;

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(name = "passwd")
    public WebElement password;

    @FindBy(id = "SubmitLogin")
    public WebElement signInButton;

    @FindBy(id = "create_account_error")
    public WebElement errorMessage;

}
