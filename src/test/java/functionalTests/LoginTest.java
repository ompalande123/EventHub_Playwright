package functionalTests;

import base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginComponent;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {





    @Test(priority = 1)
    public void loginWithValidCreds(){
        LoginComponent loginComponent=new LoginComponent(page);
        loginComponent.navigateTo();
        loginComponent.enterEmailAddress().fill("ompalande123@gmail.com");
        loginComponent.enterPassword().fill("Playwright#2026");
        loginComponent.cilckOnSignInButton().click();
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
    }

    @Test(priority = 2)
    public void loginWithInvalidCreds(){
        LoginComponent loginComponent=new LoginComponent(page);
        loginComponent.navigateTo();
        loginComponent.enterEmailAddress().fill("ompalande123@gmail.com");
        loginComponent.enterPassword().fill("Wrong#2026");
        loginComponent.cilckOnSignInButton().click();
    }


}
