package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

    public ShoppingCartPage(){
        PageFactory.initElements(Driver.getDriver(),this);

    }
    @FindBy(xpath ="//span[@id='total_price']")
    public WebElement cartTotal;

    @FindBy(id="summary_products_quantity")
    public WebElement getQuantity;

    @FindBy(xpath="//td[@class='cart_delete text-center']/div/a")
    public WebElement removeItem;

    @FindBy(xpath = "//p[@class='alert alert-warning']")
    public WebElement emptyCartMessage;



}
