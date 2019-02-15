package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {

    @FindBy(xpath = Constants.getStartedForFreeButton)
    private WebElement getStartedForFreeButton;

    @FindBy(xpath = Constants.enterYourBusinessEmailInput)
    private WebElement enterYourBusinessEmailInput;

    @FindBy(xpath = Constants.createAccountButton)
    private WebElement createAccountButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return getStartedForFreeButton.isDisplayed();
    }

    public void clickGetStartedForFree(){
        getStartedForFreeButton.click();
    }

    public void enterEmail(String email) {
        enterYourBusinessEmailInput.clear();
        enterYourBusinessEmailInput.sendKeys(email);
    }

    public ResendPage clickCreateAccount(){
        createAccountButton.click();
        return new ResendPage(driver);
    }

    private static class Constants {
        // Есть два корректных варианта XPath для 'getStartedForFreeButton'
        /* //a[text()='Login']/preceding-sibling::button */
        /* //form[@data-hash='sticky_header']/following::button[@type='submit'] */
        // оба работают в браузере с помощью JS,
        // но ни один не работет здесь
        // падают по таймауту (в Chrome).
        // Поэтому XPath скопирован из браузера.
        // Проверялось в Chrome с помощью этого кода:
        // var button = document.evaluate("<XPath>", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);
        static final String getStartedForFreeButton =
                "/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button";
        static final String enterYourBusinessEmailInput =
                "//div[text()='Start Free trial']/following::input[@name='email']";
        static final String createAccountButton =
                "//div[text()='Start Free trial']/following::button[@type='submit']";
    }

}
