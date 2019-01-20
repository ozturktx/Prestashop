package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;

public class HomePage {

    public HomePage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="header_logo")
    public WebElement logo;

    @FindBy(id="search_query_top")
    public WebElement search;

    @FindBy(className="login")
    public WebElement signin;

    @FindBy(className="logout")
    public WebElement logOut;

    @FindBy(xpath = "//*[@title='View my shopping cart']")
    public WebElement showCart;

    @FindBy(xpath = "//dl[@class='products']//dt//img")
    public WebElement itemOnCart;

    @FindBy(xpath = "//*[@class='ajax_cart_no_product']")
    public WebElement emptyCart;

    @FindBy(xpath = "//span[@class='remove_link']")
    public WebElement removeItem;

    @FindBy(xpath = "(//ul[@id='homefeatured']/li[2]//span)[2]")
    public WebElement priceforBlouse;



/*
public WebElement itemOnCart(String cartItem){
    String xpth="//*[@class='first_item']/*[@title='"+cartItem+"']";
    return Driver.getDriver().findElement(By.xpath(xpth));
}*/
    public WebElement item(String item) {
        String xpth="(//img[@alt='"+item+"'])[1]";
        String css="#center_column a.product-name[title='"+item+"']";
        return Driver.getDriver().findElement(By.xpath(xpth));
    }
}
