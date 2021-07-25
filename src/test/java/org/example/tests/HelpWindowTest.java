package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HelpWindowTest extends TestBase {

    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper boardQa9Haifa;
    MenuPageHelper menuPage;
    HelpWindowHelper helpPage;


    @BeforeMethod
    public void initTests() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        boardQa9Haifa = new CurrentBoardPageHelper(driver, "QA Haifa9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        helpPage = PageFactory.initElements(driver, HelpWindowHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilPageIsLoaded()
                .loginAsAttl(LOGIN, PASSWORD);
        boardsPage
                .waitUntilPageIsLoaded()
                .openBoardsMenu();
        boardQa9Haifa
                .openPage()
                .waitUntilPageIsLoaded();
        menuPage.openPage();

        helpPage.waitUntilHelpButtonIsLoaded();
        helpPage.openHelpMenu();
        helpPage.waitUntilHelpIsVisible();

    }

    @Test
    public void windowHandleTest() throws InterruptedException {
        String firstWindow = driver.getWindowHandle();
        String secondWindow = driver.getWindowHandle();
        helpPage.switchToSecondWindow();
        helpPage.closeWindow();
        Assert.assertEquals(firstWindow,secondWindow);
    }

    @Test
    public void windowHandleTest2() throws InterruptedException {
        helpPage.switchToSecondWindow();
        String firstWindow=driver.getWindowHandle();
        helpPage.goToYourBoardsButton();
        String secondWindow =driver.getWindowHandle();
        //System.out.println("Active 'Go to your boards' window handle: " + driver.getWindowHandle());
        Assert.assertEquals(firstWindow,secondWindow);

    }
}


