package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.tests.TestBase.log4j;

public class LoginPageHelper extends PageBase {
    @FindBy(css = ".text-primary")
    WebElement logInIcon;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "user")
    WebElement userField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = "p.error-message")
    WebElement errorMessage;
    @FindBy(xpath = "//input[@value = 'Log in with Atlassian']")
    WebElement loginAsAttlButton;
    @FindBy(id = "login-submit")
    WebElement submitAsAtllButton;

    public LoginPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageHelper openPage() {
        log4j.startMethod("LoginPageHelper - openPage()");
        log4j.info("wait until loginIcon button is clickable and click on it");
        waitUntilElementIsClickable(logInIcon, 15);
        logInIcon.click();
        log4j.endMethod("LoginPageHelper - openPage()");
        return this;
    }

    public LoginPageHelper waitUntilPageIsLoaded() {
        log4j.startMethod("LoginPageHelper - waitUntilPageIsLoaded()");
        log4j.info("Wait until 'login' button is clickable");
        waitUntilElementIsClickable(loginButton, 10);
        log4j.endMethod("LoginPageHelper - waitUntilPageIsLoaded()");
        return this;
    }

    public void loginNotAttl(String login, String password) {
        fillInUserField(login);
        fillInPasswordField(password);
        submitLoginNotAttl();
    }
    public void loginNotAttl1(String login, String password) {
        fillInUserField(login);
        fillInPasswordField(password);
        submitLoginNotAttl();
    }

    public void loginAsAttl(String login, String password) {
        fillInUserField(login);
        pressLoginAsAttlButton();
        fillInPasswordAttl(password);
        submitLoginAttl();

    }

    public String getErrorMessage(){
        log4j.startMethod("LoginPageHelper - getErrorMessage()");
        log4j.info("wait until error message is visible");
        waitUntilElementIsVisible(errorMessage, 10);
        log4j.endMethod("LoginPageHelper - getErrorMessage()");
        log4j.info("return error message");
        return errorMessage.getText();

    }

    public void fillInUserField(String value) {
        editField(userField, value);
    }

    public void fillInPasswordField(String value) {
        editField(passwordField, value);
    }

    public void submitLoginNotAttl() {
       loginButton.click();   }

    public void pressLoginAsAttlButton() {
        waitUntilElementIsClickable(loginAsAttlButton,5);
        loginAsAttlButton.click();  }

    public void fillInPasswordAttl(String value) {
        waitUntilElementIsClickable(passwordField, 5);
        editField(passwordField, value);
    }

    public void submitLoginAttl() {
        waitUntilElementIsClickable(submitAsAtllButton, 5);
        submitAsAtllButton.click();
    }
}
