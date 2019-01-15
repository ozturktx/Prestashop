package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.HomePage;
import com.prestashop.pages.ItemPage;
import com.prestashop.pages.ShoppingCartPage;
import com.prestashop.pages.signinPage;
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
    double itemPrice;
    String item;
    public void AddItemToCart(String itemName)
    {

        homePage.item(itemName).click();
        //items.item.click();
        driver.switchTo().frame(items.iframe);
        items.count.clear();
        val=rndm.nextInt(3)+2;
        items.count.sendKeys(Integer.toString(val));

      itemPrice =Double.parseDouble(items.price.getText().substring(1));
        items.sizes().selectByIndex(1);
        items.addtoCart.click();

    }
    public void login(){

        homePage.signin.click();
        signinPage.email.sendKeys("oscar6161@gmail.com");
        signinPage.password.sendKeys("Password61");
        signinPage.signInButton.click();
    }
    public void verifyCartbyHovering()
    {
        action.moveToElement(homePage.showCart).perform();
        //  System.out.println(homePage.itemOnCart.getAttribute("alt"));
        Assert.assertEquals(item,homePage.itemOnCart.getAttribute("alt"));
    }
    @Test(priority = 1)
    public void CartLogOutTest() throws InterruptedException {
        //Actions action=new Actions(driver);
        login();
        homePage.logo.click();
        item="Blouse";
        AddItemToCart(item);
        Thread.sleep(1000);
        items.closeWindow.click();
        verifyCartbyHovering();
        Thread.sleep(1000);
        homePage.logOut.click();
        Assert.assertTrue(homePage.emptyCart.isDisplayed());
    }

    @Test(priority = 2)
    public void CartIconDeleteTest() throws InterruptedException {
        //Actions action=new Actions(driver);

        item="Blouse";
        AddItemToCart(item);
        Thread.sleep(1000);
        items.continueShopping.click();
        verifyCartbyHovering();
        homePage.removeItem.click();
        Thread.sleep(1000);
        Assert.assertTrue(homePage.emptyCart.isDisplayed());


    }


    @Test(priority = 3)
    public void CartCheckoutDeleteTest() throws InterruptedException {
        //Actions action=new Actions(driver);

        item="Blouse";
        AddItemToCart(item);
        int first=val;
        Thread.sleep(2000);
        items.continueShopping.click();
        System.out.println("value for first"+val);
        AddItemToCart("Printed Chiffon Dress");
        int second=val;
        Thread.sleep(1000);
        items.proceed.click();
        Thread.sleep(1000);
        Assert.assertEquals(cartPage.getQuantity.getText(),first+second+" Products");
        cartPage.removeItem.click();
        Thread.sleep(1000);
        Assert.assertEquals(cartPage.getQuantity.getText(),second+" Products");
        Thread.sleep(1000);
        cartPage.removeItem.click();
        Thread.sleep(1000);
        Assert.assertEquals(cartPage.emptyCartMessage.getText(),"Your shopping cart is empty.");

    }
    @Test(priority = 4)
    public void CartLoginTest() throws InterruptedException {
        //Actions action=new Actions(driver);
        item="Blouse";
        AddItemToCart(item);
        Thread.sleep(2000);
        items.closeWindow.click();
        verifyCartbyHovering();
        login();
       verifyCartbyHovering();
    }
    @Ignore
    @Test(priority = 5)
    public void cartDetails() throws InterruptedException {

        AddItemToCart("Blouse");

        String Expected="Product successfully added to your shopping cart";
        Assert.assertEquals(items.cartConfirmation.getText(),Expected);
        double shipping=Double.parseDouble(items.shippingPrice.getText().substring(1));
        Assert.assertEquals(shipping,2.0,"Shipping is not right");
        double total=Double.parseDouble(items.totalPrice.getText().substring(1));
        Assert.assertEquals((itemPrice*val+shipping),total,"total does not match quantity*itemprice+shipping");
        items.closeWindow.click();
        driver.switchTo().defaultContent();

        AddItemToCart("Printed Chiffon Dress");
        //String Expected="Product successfully added to your shopping cart";
        Assert.assertEquals(items.cartConfirmation.getText(),Expected);

        total=Double.parseDouble(items.totalPrice.getText().substring(1));
       // Assert.assertEquals((itemPrice*val+shipping),total,"total does not match quantity*itemprice+shipping");
       Thread.sleep(1000);
        items.closeWindow.click();

        driver.switchTo().defaultContent();
        homePage.showCart.click();
        ShoppingCartPage shoppingCartPage=new ShoppingCartPage();
        Thread.sleep(1000);
        double cartTotal=Double.parseDouble(shoppingCartPage.cartTotal.getText().substring(1));
        Assert.assertEquals(cartTotal,total);

    }
}
