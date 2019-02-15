package com.example.selenium;

import com.example.selenium.steps.HomePageSteps;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;


public class HomePageTest extends FunctionalTest {

    @Before
    public void gotoHomePage() {
        driver.get("https://wrike.com/");
    }

    @Test
    public void testCreateAccount() {
        HomePageSteps steps = new HomePageSteps(driver);
        assertTrue(steps.createAccount());
    }

    @Test
    public void testSubmitSurveyForm() {
        HomePageSteps steps = new HomePageSteps(driver);
        assertTrue(steps.submitSurveyForm());
    }

    @Test
    public void testResendEmail() {
        HomePageSteps steps = new HomePageSteps(driver);
        assertTrue(steps.resendEmail());
    }

    @Test
    public void testTwitterButton() {
        HomePageSteps steps = new HomePageSteps(driver);
        assertTrue(steps.checkTwitterButton());
    }

}
