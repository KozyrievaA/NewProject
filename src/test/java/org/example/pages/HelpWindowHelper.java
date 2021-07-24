package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpWindowHelper extends PageBase {
    @FindBy(xpath = "//span[contains(text(),'Help')]")
    WebElement helpButton;
    @FindBy(xpath = "//span[contains(text(),'Go to your boards')]")
    WebElement goToYourBoardsButton;
    @FindBy(xpath = "//h1[contains(text(),'Get help with Trello')]")
    WebElement getHelpWithTrello;

    public static String FIRSTWINDOWHANDLE = "";
    public static String SECONDWINDOWHANDLE = "";

    public HelpWindowHelper(WebDriver driver) {
        this.driver = driver;
    }


    public void waitUntilHelpButtonIsLoaded() {
        waitUntilElementIsClickable(helpButton, 5);
    }

    public void openHelpMenu() {
        helpButton.click();
    }

    public void waitUntilHelpIsVisible() {
        waitUntilElementIsVisible(getHelpWithTrello, 5);
    }

    public void goToYourBoardsButton() {
        goToYourBoardsButton.click();
    }

    public void switchToSecondWindow() {
        FIRSTWINDOWHANDLE = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            System.out.println("Handle: " + handle);
            if (!handle.equals(FIRSTWINDOWHANDLE)) {
                SECONDWINDOWHANDLE = handle;
            }
            System.out.println("First active window handle: " + driver.getWindowHandle());
            driver.switchTo().window(SECONDWINDOWHANDLE);
            System.out.println("Help window handle is active: " + driver.getWindowHandle());
        }
    }
    public void closeWindow() {
        driver.close();
        driver.switchTo().window(FIRSTWINDOWHANDLE);
        System.out.println("First active window handle: " + driver.getWindowHandle());
    }

}

