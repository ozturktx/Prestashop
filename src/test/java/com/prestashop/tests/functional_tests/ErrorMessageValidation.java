package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorMessageValidation extends TestBase {

    @Test
    public void emptyFirstNameError(){
        String fname=faker.name().firstName();
        String lname=faker.name().lastName();
        String phonenmbr=faker.phoneNumber().cellPhone();
        String email=faker.name().lastName()+faker.number().digit()+"@gmail.com";
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys(email);
        driver.findElement(By.id("SubmitCreate")).click();

        driver.findElement(By.id("id_gender1")).click();
       // driver.findElement(By.id("customer_firstname")).sendKeys(fname);
        driver.findElement(By.id("customer_lastname")).sendKeys(lname);
        String actual=driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertEquals(actual,email,actual);
        //driver.findElement(By.id("email")).sendKeys("oscar6141@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Password6141");
        select=new Select(driver.findElement(By.id("days")));
        select.selectByIndex(19);
        select=new Select(driver.findElement(By.id("months")));
        select.selectByIndex(2);
        select=new Select(driver.findElement(By.id("years")));
        select.selectByIndex(3);
        driver.findElement(By.id("address1")).sendKeys("TEXAS");
        driver.findElement(By.id("city")).sendKeys("TEXAS");
        select=new Select(driver.findElement(By.id("id_state")));
        select.selectByValue("43");
        driver.findElement(By.id("postcode")).sendKeys("77611");
        driver.findElement(By.id("phone_mobile")).sendKeys(phonenmbr);
        driver.findElement(By.id("submitAccount")).click();
        String  message=driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[1]")).getText();
        Assert.assertEquals(message,"firstname is required.");

    }
    @Test
    public void cartDetails() throws InterruptedException {
        driver.findElement(By.xpath("(//img[@alt='Blouse'])[1]")).click();
       WebElement frame=driver.findElement(By.xpath("//iframe[@class='fancybox-iframe']"));
       driver.switchTo().frame(frame);

        driver.findElement(By.id("quantity_wanted")).clear();
        int val=rndm.nextInt(3)+2;
        driver.findElement(By.id("quantity_wanted")).sendKeys(Integer.toString(val));
        String SitemPrice=driver.findElement(By.id("our_price_display")).getText();
        double itemPrice=Double.parseDouble(SitemPrice.substring(1));
        select=new Select(driver.findElement(By.id("group_1")));
        select.selectByIndex(1);
        driver.findElement(By.name("Submit")).click();
        String message=driver.findElement(By.xpath("(//h2)[1]")).getText();
        String Expected="Product successfully added to your shopping cart";

        String Sshipping=driver.findElement(By.xpath("//span[@class='ajax_cart_shipping_cost']")).getText();
        double shipping=Double.parseDouble(Sshipping.substring(1));
        String Stotal=driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']")).getText();
        double total=Double.parseDouble(Stotal.substring(1));
        Assert.assertEquals(shipping,2.0,"Shipping is not right");

        Assert.assertEquals((itemPrice*val+shipping),total,"total does not match quantity*itemprice+shipping");
        Assert.assertEquals(message,Expected,"Confirmation does not match");
        driver.findElement(By.xpath("//span[@title='Close window']")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@title='My Store']")).click();
        driver.findElement(By.xpath("(//img[@alt='Printed Dress'])[2]")).click();
        Thread.sleep(2000);
        frame=driver.findElement(By.xpath("//iframe[@class='fancybox-iframe']"));
        driver.switchTo().frame(frame);

        driver.findElement(By.id("quantity_wanted")).clear();
        val=rndm.nextInt(3)+2;
        driver.findElement(By.id("quantity_wanted")).sendKeys(Integer.toString(val));

        select=new Select(driver.findElement(By.id("group_1")));
        select.selectByIndex(1);
        driver.findElement(By.name("Submit")).click();
        message=driver.findElement(By.xpath("(//h2)[1]")).getText();
        Assert.assertEquals(message,Expected);
        Stotal=driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']")).getText();
        System.out.println("Total for second "+Stotal);
        System.out.println("Total before second"+total);
        total=Double.parseDouble(Stotal.substring(1));
        System.out.println("Total after second "+total);
Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@title='Close window']")).click();


        driver.switchTo().defaultContent();
        WebElement cart=driver.findElement(By.xpath("//*[@title='View my shopping cart']"));
        action.moveToElement(cart).perform();
        Thread.sleep(1000);

        String SCartTotal=driver.findElement(By.xpath("//*[@class='price cart_block_total ajax_block_cart_total']")).getText();
        double cartTotal=Double.parseDouble(SCartTotal.substring(1));
        Assert.assertEquals(cartTotal,total);

    }
}
