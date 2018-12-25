package com.prestashop.tests.smoke_tests;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ProductInformation {

    String url="http://automationpractice.com/index.php";
    WebDriver driver;
    @BeforeMethod
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void SameNamePriceTest()
    {
        String name=driver.findElement(By.xpath("(//h5//a[@title='Blouse'])[1]")).getText();
        String price=driver.findElement(By.xpath("(//li[2]//span[@class='price product-price'])[2]")).getText();
        System.out.println(name);
        System.out.println(price);
        driver.findElement(By.xpath("(//img[@title='Blouse'])[1]")).click();
        String actualName=driver.findElement(By.xpath("//h1")).getText();
        String actualPrice=driver.findElement(By.id("our_price_display")).getText();
        Assert.assertEquals(actualName,name);
        Assert.assertEquals(actualPrice,price, "Found price is "+actualPrice);

    }
    @Test
    public void ProdcutInfoTest()
    {
        driver.findElement(By.xpath("(//img[@title='Blouse'])[1]")).click();
        String quantity=driver.findElement(By.id("quantity_wanted")).getAttribute("value");
        Select select=new Select(driver.findElement(By.id("group_1")));
        String size=select.getFirstSelectedOption().getText();
        List<WebElement> Sizeoptions=new ArrayList<>(select.getOptions());
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
    }
    @Test
    public void AddToCartTest() throws InterruptedException {
        String name=driver.findElement(By.xpath("(//h5//a[@title='Blouse'])[1]")).getText();
        String price=driver.findElement(By.xpath("(//li[2]//span[@class='price product-price'])[2]")).getText();
        driver.findElement(By.xpath("(//img[@title='Blouse'])[1]")).click();

        driver.findElement(By.id("add_to_cart")).click();
        Thread.sleep(3000);
        String addedItemName=driver.findElement(By.id("layer_cart_product_title")).getText();
        //System.out.println("name "+addedItemName);
        String addedItemSize=driver.findElement(By.id("layer_cart_product_attributes")).getText();
       addedItemSize=addedItemSize.substring(addedItemSize.length()-1);
        //System.out.println("Size "+addedItemSize);

        String addedItemQuantity=driver.findElement(By.id("layer_cart_product_quantity")).getText();
       // System.out.println("Quantity "+addedItemQuantity);
        String addedItemPrice=driver.findElement(By.id("layer_cart_product_price")).getText();
        //System.out.println("Price "+addedItemPrice);
        String message=driver.findElement(By.xpath("(//h2)[1]")).getText();
        //System.out.println("Message "+message);
        String expectedConfirmation="Product successfully added to your shopping cart";


        Assert.assertEquals(message,expectedConfirmation, "Confirmation title does not match");
        Assert.assertEquals(addedItemQuantity,"1", "Quantity is not one");
        Assert.assertEquals(addedItemSize,"S", "Default size is not S");
        Assert.assertEquals(addedItemName,name, "Confirmation page name and homepage name does not match");
        Assert.assertEquals(addedItemPrice,price, "Confirmation page price and homepage price does not match");


    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
