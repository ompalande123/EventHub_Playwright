package functionalTests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import components.LoginComponent;
import utils.ExcelReader;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    ExcelReader excelReader;



    @Test(priority = 1)
    public void loginWithValidCreds(){
        excelReader=new ExcelReader("src/test/resources/testData/eventsTestData.xlsx",
                "LoginDetails",
                1);

        LoginComponent loginComponent=new LoginComponent(page);
        loginComponent.navigateTo();
        loginComponent.enterEmailAddress().fill(excelReader.getCellData("UserName"));
        loginComponent.enterPassword().fill(excelReader.getCellData("Password"));
        loginComponent.cilckOnSignInButton().click();
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
    }

    @Test(priority = 2)
    public void loginWithInvalidCreds(){
        excelReader=new ExcelReader("src/test/resources/testData/eventsTestData.xlsx",
                "LoginDetails",
                2);

        LoginComponent loginComponent=new LoginComponent(page);
        loginComponent.navigateTo();
        loginComponent.enterEmailAddress().fill(excelReader.getCellData("UserName"));
        loginComponent.enterPassword().fill(excelReader.getCellData("Password"));
        loginComponent.cilckOnSignInButton().click();
    }


}
