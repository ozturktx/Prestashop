package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.HomePage;
import com.prestashop.pages.ItemPage;
import com.prestashop.pages.ShoppingCartPage;
import com.prestashop.pages.signinPage;
import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.TestBase;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorMessageValidation extends TestBase {


    public int val;
   public double itemPrice;
    String item;
    public void AddItemToCart(String itemName)
    {

        pages.homePage().item(itemName).click();

        //items.item.click();
        driver.switchTo().frame(pages.itemPage().iframe);
        pages.itemPage().count.clear();
        val=rndm.nextInt(3)+2;
        pages.itemPage().count.sendKeys(Integer.toString(val));

      itemPrice =Double.parseDouble(pages.itemPage().price.getText().substring(1));
        pages.itemPage().sizes().selectByIndex(1);

        pages.itemPage().addtoCart.click();


    }
    public void login(){

        pages.homePage().signin.click();

        pages.signinPage().email.sendKeys(ConfigurationReader.getProperty("username"));
        pages.signinPage().password.sendKeys(ConfigurationReader.getProperty("password"));
        pages.signinPage().signInButton.click();
    }
    public void verifyCartbyHovering()
    {
        action.moveToElement(pages.homePage().showCart).perform();
        //  System.out.println(homePage.itemOnCart.getAttribute("alt"));
        Assert.assertEquals(item,pages.homePage().itemOnCart.getAttribute("alt"));
    }
    @Test(priority = 1)
    public void CartLogOutTest() throws InterruptedException {
        //Actions action=new Actions(driver);
        extentLogger = report.createTest("Add item to the Cart Test");
        login();
        extentLogger.info("Login to account");
        pages.homePage().logo.click();
        item="Blouse";
        extentLogger.info("Select an item");
        extentLogger.info("Add a random quantity");
        extentLogger.info("Select a size and add it to cart");

        AddItemToCart(item);

        Thread.sleep(2000);
        pages.itemPage().closeWindow.click();
        extentLogger.info("Check that item is on the cart");
        verifyCartbyHovering();
        Thread.sleep(1000);
        pages.homePage().logOut.click();
        extentLogger.info("Log out");
        Assert.assertTrue(pages.homePage().emptyCart.isDisplayed());
    }

    @Test(priority = 2)
    public void CartIconDeleteTest() throws InterruptedException {
        //Actions action=new Actions(driver);
        extentLogger = report.createTest("Remove Item from cart Test");
        item="Blouse";
        AddItemToCart(item);
        extentLogger.info("Select an item");
        extentLogger.info("Add a random quantity");
        extentLogger.info("Select a size and add it to cart");
        Thread.sleep(2000);
        pages.itemPage().continueShopping.click();
        verifyCartbyHovering();
        extentLogger.info("Confirm that item is on the cart");
        extentLogger.info("Click x button to remove the item from the cart");
        pages.homePage().removeItem.click();
        Thread.sleep(1000);
        Assert.assertTrue(pages.homePage().emptyCart.isDisplayed());


    }


    @Test(priority = 3)
    public void CartCheckoutDeleteTest() throws InterruptedException {
        //Actions action=new Actions(driver);
        extentLogger = report.createTest("Cart checkout Delete Test");
        item="Blouse";
        AddItemToCart(item);
        extentLogger.info("Add an item to cart ");

        int first=val;
        Thread.sleep(2000);
        pages.itemPage().continueShopping.click();
        System.out.println("value for first"+val);
        AddItemToCart("Printed Chiffon Dress");
        extentLogger.info("Add another item to cart ");
        int second=val;
        Thread.sleep(2000);
        pages.itemPage().proceed.click();
        Thread.sleep(1000);
        extentLogger.info("Check that all items are added to cart ");
        Assert.assertEquals(pages.shoppingCartPage().getQuantity.getText(),first+second+" Products");
        pages.shoppingCartPage().removeItem.click();
        extentLogger.info("Remove the first item from checkout cart");
        Thread.sleep(1000);
        extentLogger.info("Confirm that only one item left inside the cart");
        Assert.assertEquals(pages.shoppingCartPage().getQuantity.getText(),second+" Products");
        Thread.sleep(1000);
        extentLogger.info("Remove the second item");
        pages.shoppingCartPage().removeItem.click();
        Thread.sleep(1000);
        extentLogger.info("Verify that there is no more item in the cart");
        Assert.assertEquals(pages.shoppingCartPage().emptyCartMessage.getText(),"Your shopping cart is empty.");

    }
    @Test(priority = 4)
    public void CartLoginTest() throws InterruptedException {
        //Actions action=new Actions(driver);
        extentLogger = report.createTest("Verify the item after logging in test");
        extentLogger.info("add an item to cart and conform it by hovering over");
        extentLogger.info("Login to account and confirm that item is still there");
        item="Blouse";
        AddItemToCart(item);
        Thread.sleep(2000);
        pages.itemPage().closeWindow.click();
        verifyCartbyHovering();
        login();
       verifyCartbyHovering();
       extentLogger.pass("Test is passed");
    }
    @Ignore
    @Test(priority = 5)
    public void cartDetails() throws InterruptedException {
        extentLogger = report.createTest("Cart Details Test");


        AddItemToCart("Blouse");
        extentLogger.info("add an item to cart and confirm it by hovering over");
        String Expected="Product successfully added to your shopping cart";
        Assert.assertEquals(pages.itemPage().cartConfirmation.getText(),Expected);
        extentLogger.info("check the shipping price, make sure it is right");
        double shipping=Double.parseDouble(pages.itemPage().shippingPrice.getText().substring(1));
        Assert.assertEquals(shipping,2.0,"Shipping is not right");
        extentLogger.info("check the total price, make sure it is right");

        double total=Double.parseDouble(pages.itemPage().totalPrice.getText().substring(1));
        Assert.assertEquals((itemPrice*val+shipping),total,"total does not match quantity*itemprice+shipping");
        pages.itemPage().closeWindow.click();
        driver.switchTo().defaultContent();
        extentLogger.info("add another item to cart");

        AddItemToCart("Printed Chiffon Dress");
        //String Expected="Product successfully added to your shopping cart";
        Assert.assertEquals(pages.itemPage().cartConfirmation.getText(),Expected);

        total=Double.parseDouble(pages.itemPage().totalPrice.getText().substring(1));
       // Assert.assertEquals((itemPrice*val+shipping),total,"total does not match quantity*itemprice+shipping");
       Thread.sleep(1000);
        extentLogger.info("Make sure that total price increased");

        pages.itemPage().closeWindow.click();

        driver.switchTo().defaultContent();
        pages.homePage().showCart.click();
        extentLogger.info("click on cart details and make sure that cart total adds up with individual item total");

        // ShoppingCartPage shoppingCartPage=new ShoppingCartPage();
        Thread.sleep(1000);
        double cartTotal=Double.parseDouble(pages.shoppingCartPage().cartTotal.getText().substring(1));
        Assert.assertEquals(cartTotal,total);
extentLogger.pass("Test is passed");
    }
}
