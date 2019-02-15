package com.example.selenium.steps;

import com.example.selenium.pages.FooterPage;
import com.example.selenium.pages.HomePage;
import com.example.selenium.pages.ResendPage;
import org.openqa.selenium.WebDriver;

import static com.example.selenium.util.EmailGenerator.generateEmail;
import static junit.framework.TestCase.assertTrue;

public class HomePageSteps {

    private WebDriver driver;

    public HomePageSteps(WebDriver driver) {
        this.driver = driver;
    }

    public boolean createAccount() {
        ResendPage resendPage = moveToResendPage();

        return resendPage.isInitialized();
    }

    private ResendPage moveToResendPage() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isInitialized());
        homePage.clickGetStartedForFree();
        homePage.enterEmail(generateEmail());

        return homePage.clickCreateAccount();
    }

    public boolean submitSurveyForm() {
        ResendPage resendPage = moveToResendPage();
        resendPage.selectButtonsInSurveyForm();
        resendPage.submitSurveyForm();

        return resendPage.isSurveySubmitted();
    }

    public boolean resendEmail() {
        ResendPage resendPage = moveToResendPage();
        resendPage.submitResendEmail();

        return resendPage.isResendEmailSubmitted();
    }

    public boolean checkTwitterButton() {
        FooterPage footerPage = new FooterPage(driver);

        return footerPage.isInitialized();
    }

}
