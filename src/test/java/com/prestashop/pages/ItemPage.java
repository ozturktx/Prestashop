package com.prestashop.pages;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ItemPage {

    public ItemPage()
    {
        PageFactory.initElements(Driver.getDriver(), this);
    }
//
//    @FindBy(tagName="h1")
//    public WebElement itemName;

    @FindBy(xpath = "(//img[@alt='Blouse'])[1]")
    public WebElement item;

    @FindBy(xpath = "//iframe[@class='fancybox-iframe']")
    public WebElement iframe;


    @FindBy(id="quantity_wanted")
    public WebElement count;


    @FindBy(className="icon-plus")
    public WebElement plus;


    @FindBy(className="icon-minus")
    public WebElement minus;

    //condition
    @FindBy(css="#product_condition>.editable")
    public WebElement condition;

    //price
    @FindBy(className="our_price_display")
    public WebElement price;

// add to cart
    @FindBy(name="Submit")
    public WebElement addtoCart;

    @FindBy(xpath = "(//h2)[1]")
    public WebElement cartConfirmation;

    @FindBy(xpath = "//span[@class='ajax_cart_shipping_cost']")
    public WebElement shippingPrice;

    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    public WebElement totalPrice;

    @FindBy(xpath = "//span[@title='Close window']")
    public WebElement closeWindow;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    public WebElement continueShopping;


    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    public WebElement proceed;

    public Select sizes() {
        return new Select(Driver.getDriver().findElement(By.id("group_1")));
    }
}
