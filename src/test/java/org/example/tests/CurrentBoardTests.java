package org.example.tests;


import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper boardQa9Haifa;


    @BeforeMethod
    public void initTests() {
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        boardQa9Haifa = new CurrentBoardPageHelper(driver, "QA Haifa9");

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
        boardQa9Haifa.waitUntilPageIsLoaded();


    }


    @Test
    public void newListCreatingTest() throws InterruptedException {
        int beginListsQuantity = boardQa9Haifa.getListsQuantity();
        boardQa9Haifa.addNewList("New List");
        int endListsQuantity = boardQa9Haifa.getListsQuantity();
        Assert.assertEquals(endListsQuantity,beginListsQuantity+1,
                "endListsQuantity is not beginListsQuantity+1");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "newListCreating")
    public void newListCreatingTestParam(String nameList) throws InterruptedException {
        int beginListsQuantity = boardQa9Haifa.getListsQuantity();
        boardQa9Haifa.addNewList("New List");
        int endListsQuantity = boardQa9Haifa.getListsQuantity();
        Assert.assertEquals(endListsQuantity,beginListsQuantity+1,
                "endListsQuantity is not beginListsQuantity+1");
    }

    @Test
    public void addNewCardTest() throws InterruptedException {

        int beginList = boardQa9Haifa.getListsQuantity();
        if(beginList==0) {
            boardQa9Haifa.addNewList("New List1");
        }
        int beginCards = boardQa9Haifa.getCardQuantity();
        boardQa9Haifa.addCardToTheList("Card name");
        int endCardsQuantity = boardQa9Haifa.getCardQuantity();
        Assert.assertEquals(endCardsQuantity,beginCards+1,
                "endCardsQuantity is not beginCards+1");
        }


    @Test
    public void archiveList(){
        int beginList = boardQa9Haifa.getListsQuantity();
        if(beginList==0) {
            boardQa9Haifa.addNewList("New List1");
            beginList++;
        }
        boardQa9Haifa.archiveFirstList();
        int endList = boardQa9Haifa.getListsQuantity();
        Assert.assertEquals(beginList-1,endList,
                "beginLists-1 is not endLists");

    }

    @Test
    public void copyList(){

        int beginList = boardQa9Haifa.getListsQuantity();
        if(beginList==0) {

            boardQa9Haifa.addNewList("New List2");
            beginList++;
        }

        boardQa9Haifa.copyList("Copy List");
        int endLists = boardQa9Haifa.getListsQuantity();
        Assert.assertEquals(endLists,beginList+1, "endLists is not beginLists+1");

    }
}









