package com.prestashop.tests.smoke_tests;

import com.prestashop.tests.functional_tests.cart.ErrorMessageValidation;
import com.prestashop.utilities.TestBase;

import cucumber.api.java.eo.Do;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.List;

public class ProductInformation extends TestBase {

    //ErrorMessageValidation errorMessageValidation=new ErrorMessageValidation();
    @Test
    public void SameNamePriceTest() throws InterruptedException {
        String itemName="Blouse";
        homePage.item(itemName).click();
       // System.out.println(homePage.priceforBlouse.getAttribute("innerHTML"));
        double priceHomePage= Double.parseDouble(homePage.priceforBlouse.getAttribute("innerHTML").trim().substring(1));
        //items.item.click();
        driver.switchTo().frame(items.iframe);
        String actualName=items.name.getText();
        double actualPrice =Double.parseDouble(items.price.getText().substring(1));
        items.sizes().selectByIndex(1);


        //String price=driver.findElement(By.xpath("(//li[2]//span[@class='price product-price'])[2]")).getText();
        System.out.println(itemName);
        //System.out.println(itemPrice);


       // String actualPrice=driver.findElement(By.id("our_price_display")).getText();
        Assert.assertEquals(actualName,itemName);
        Assert.assertEquals(actualPrice,priceHomePage, "Found price is "+actualPrice);
        /////////////////////////////////////////////////////////////


        String quantity=items.count.getAttribute("value");
       // select=new Select(driver.findElement(By.id("group_1")));
        String size=items.sizes().getOptions().get(0).getText();//getFirstSelectedOption().getText();
        List<WebElement> Sizeoptions=new ArrayList<>(items.sizes().getOptions());
        System.out.println(Sizeoptions.get(0).getText());

        Assert.assertTrue(quantity.equals("1"));
        Assert.assertEquals(size,"S", "Default size "+size);
        boolean options=true;
        for(int i=0;i<Sizeoptions.size();i++) {
            if(Sizeoptions.get(i).getText().equals("S")|| Sizeoptions.get(i).getText().equals("M")|| Sizeoptions.get(i).getText().equals("L"))
            {
                System.out.println(Sizeoptions.get(i).getText());
                continue;
            }
            else
            {options=false;
                break;}
        }
        Assert.assertTrue(options,"Size options do not match");
        items.addtoCart.click();
       // driver.findElement(By.id("add_to_cart")).click();
        Thread.sleep(3000);
        String addedItemName=items.AddeditemName.getText();
        //System.out.println("itemName "+addedItemName);
        String addedItemSize=items.AddedItemSize.getText();
        addedItemSize=addedItemSize.substring(addedItemSize.length()-1);
        //System.out.println("Size "+addedItemSize);

        String addedItemQuantity=items.AddedItemCount.getText();
        // System.out.println("Quantity "+addedItemQuantity);
        double addedItemPrice=Double.parseDouble(items.AddedItemPrice.getText().substring(1));
        //System.out.println("Price "+addedItemPrice);
        String message=items.AddedItemMessage.getText();
        String expectedConfirmation="Product successfully added to your shopping cart";


        Assert.assertEquals(message,expectedConfirmation, "Confirmation title does not match");
        Assert.assertEquals(addedItemQuantity,"1", "Quantity is not one");
        softAssert.assertEquals(addedItemSize,"S", "Default size is not S");
        Assert.assertEquals(addedItemName,itemName, "Confirmation page itemName and homepage itemName does not match");
        Assert.assertEquals(addedItemPrice,actualPrice, "Confirmation page price and homepage price does not match");


    }

}
