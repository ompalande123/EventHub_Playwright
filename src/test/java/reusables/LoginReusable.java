package reusables;

import com.microsoft.playwright.Page;
import components.LoginComponent;
import utils.ExcelReader;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginReusable {
    Page page;
    ExcelReader excelReader;
    public LoginReusable(Page page){
        this.page =page;
    }

    public void login(){
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
}
