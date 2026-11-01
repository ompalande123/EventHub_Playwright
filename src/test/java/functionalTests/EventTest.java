package functionalTests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddNewEventInformation;
import pages.MenubarHomePage;
import pages.LoginComponent;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EventTest extends BaseTest {

    @BeforeMethod
    public void login(){
        LoginComponent loginComponent=new LoginComponent(page);
        loginComponent.navigateTo();
        loginComponent.enterEmailAddress().fill("ompalande123@gmail.com");
        loginComponent.enterPassword().fill("Playwright#2026");
        loginComponent.cilckOnSignInButton().click();
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
    }


    @Test()
    public void addNewEvent(){
        MenubarHomePage menubarOptions=new MenubarHomePage(page);
        menubarOptions.selectEventFromMenu().click();
        menubarOptions.addNewEventButton().click();

        AddNewEventInformation addNewEventInformation=new AddNewEventInformation(page);
        addNewEventInformation.eventTitle().fill("Trampoline Park");
        addNewEventInformation.eventDescription().fill("This park is located in Gandharv Nagari!");

    }

}
