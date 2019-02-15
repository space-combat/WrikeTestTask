package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterPage extends PageObject {

    @FindBy(xpath = "//a[@href='" + Constants.twitterUrl + "']")
    private WebElement twitterButton;

    public FooterPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return twitterButton.isDisplayed();
    }

    private static class Constants {
        static final String twitterUrl = "https://twitter.com/wrike";
    }

}
