package com.example.selenium.pages;

import com.example.selenium.util.RandomNumberGenerator;
import com.example.selenium.util.StringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class ResendPage extends PageObject {

    private WebElement surveyForm;

    private WebElement otherInput;

    @FindBy(xpath = Constants.submitResultButton)
    private WebElement submitResultButton;

    private WebElement resendEmailButton;

    public ResendPage(WebDriver driver) {
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        surveyForm = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(Constants.surveyForm)
                )
        );
    }

    public boolean isInitialized() {
        return surveyForm.isDisplayed();
    }

    public boolean isSurveySubmitted() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement surveySuccess = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("survey-success"))
        );
        return surveySuccess.isDisplayed();
    }

    public boolean isResendEmailSubmitted() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath(Constants.resendEmailButton)
                )
        );
    }

    public void selectButtonsInSurveyForm() {
        List<List<WebElement>> buttonBlocks = surveyButtonsBlocks();
        for(List<WebElement> block : buttonBlocks) {
            int randomIndex = RandomNumberGenerator.generateNumberInRange(0, block.size()-1);
            WebElement button = block.get(randomIndex);
            button.click();
            if (button.getText().contains("Other")) {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                otherInput = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(Constants.otherInput)
                        )
                );
                otherInput.sendKeys(StringGenerator.generateString(35));
            }
        }
    }

    public void submitSurveyForm() {
        submitResultButton.click();
    }

    public void submitResendEmail() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        resendEmailButton =  wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(Constants.resendEmailButton)
                )
        );
        resendEmailButton.click();
    }

    private List<List<WebElement>> surveyButtonsBlocks() {
        List<WebElement> blocks = surveyForm.findElements(By.className("radio"));
        List<List<WebElement>> buttonBlocks = new ArrayList<List<WebElement>>();
        for (WebElement block : blocks) {
            List<WebElement> buttons = new ArrayList<WebElement>();
            List<WebElement> labels = block.findElements(By.tagName("label"));
            for (WebElement label : labels) {
                WebElement button = label.findElement(By.tagName("button"));
                buttons.add(button);
            }
            buttonBlocks.add(buttons);
        }
        return buttonBlocks;
    }

    private static class Constants {
        static final String surveyForm = "//form[@name='survey-form']";
        static final String otherInput = "//input[@class='switch__input']";
        static final String submitResultButton = "//form[@name='survey-form']/button[@type='submit']";
        static final String resendEmailButton = "//input[@name='cEmail']/following-sibling::p/button";

    }

}
