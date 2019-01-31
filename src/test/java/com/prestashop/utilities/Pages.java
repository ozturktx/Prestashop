package com.prestashop.utilities;

import com.prestashop.pages.*;
import com.prestashop.tests.functional_tests.login.Registration;

public class Pages {

    private HomePage homePage;
    private ItemPage itemPage;
    private MyAccountPage myAccountPage;
    private RegistrationPage registrationPage;
    private ShoppingCartPage shoppingCartPage;
    private signinPage signinPage;

    public HomePage homePage()
    {
        if(homePage==null){
            homePage=new HomePage();
        }
        return homePage;
    }
    public ItemPage itemPage()
    {
        if(itemPage==null){
            itemPage=new ItemPage();
        }
        return itemPage;
    }
    public MyAccountPage myAccountPage()
    {
        if(myAccountPage==null){
            myAccountPage=new MyAccountPage();
        }
        return myAccountPage;
    }
    public RegistrationPage registrationPage()
    {
        if(registrationPage==null){
            registrationPage=new RegistrationPage();
        }
        return registrationPage;
    }
    public ShoppingCartPage shoppingCartPage()
    {
        if(shoppingCartPage==null){
            shoppingCartPage=new ShoppingCartPage();
        }
        return shoppingCartPage;
    }
    public signinPage signinPage()
    {
        if(signinPage==null){
            signinPage=new signinPage();
        }
        return signinPage;
    }
}
