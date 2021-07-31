package org.example.tests;


import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
   // HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests()  {
       //homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
        log4j.endMethod("LoginTests - initTests()");

    }


    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginPositive")
    public void positiveLogin(String login, String password) {
       //loginPage.loginAsAttl(LOGIN, PASSWORD);

        loginPage.loginAsAttl(login, password);
       boardsPage.waitUntilPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),"Boards",
                "Name of the button is not 'Boards'");
    }

    @Test
    public void negativeLogin()  {
        loginPage.loginNotAttl("aaaaaaaa", "rererere");
       Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this username",
               "The error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void negativeLoginThirdDataProv(String login, String password)  {
        loginPage.loginNotAttl(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this email",
                "The error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "negativeLogin1")
    public void negativeLogin1(String login, String password, String message)  {
        log4j.startTestCase("negativeLogin1(), parameters: login="
                + login + " password=" + password + " message='" + message + "'");
        log4j.info("Enter login not attl: login=" + login + " password=" + password);
        loginPage.loginNotAttl(login,password);
        log4j.info("Assert: Message has to be - " + message);
        Assert.assertEquals(loginPage.getErrorMessage(), message,
                "The error message is not correct");
        log4j.endTestCase2();
    }
}



