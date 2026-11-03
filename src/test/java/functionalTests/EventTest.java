package functionalTests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import components.SearchEventsComponent;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import components.AddNewEventInformationComponent;
import components.MenubarHomePageComponent;
import components.LoginComponent;
import reusables.LoginReusable;
import utils.ConstantsUtils;
import utils.ExcelReader;
import utils.ExtentManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static utils.ConstantsUtils.TEST_DATA_PATH;

@Listeners(utils.ExtentReportListener.class)
public class EventTest extends BaseTest {

    ExcelReader excelReader;
    private static final Logger logger= LogManager.getLogger(EventTest.class);

    @BeforeMethod
    public void runPrerequisites(){
        LoginReusable loginReusable=new LoginReusable(page);
        loginReusable.login();

    }


    @Test()
    public void addNewEvent(){
        // read data from Excel
        try {
            excelReader=new ExcelReader(TEST_DATA_PATH, "AddNewEvent",1);
        }catch (Exception e)
        {
            logger.error("Sheet name not available - ", e);
        }
        ExtentManager.getTest().info("Excel file read successfully");
        logger.info("Excel file read successfully...");
        ExtentManager.getTest().info("Login done successfully");
        logger.info("Login with valid credentials done successfully...");

        // Actual test
        MenubarHomePageComponent menubarOptions=new MenubarHomePageComponent(page);
        menubarOptions.selectEventFromMenu().click();
        menubarOptions.addNewEventButton().click();
        ExtentManager.getTest().info("Clicked on Events link successfully");
        logger.info("Clicked on Events link successfully...");
        AddNewEventInformationComponent addNewEventInformationComponent =new AddNewEventInformationComponent(page);

        addNewEventInformationComponent.eventTitle().fill(excelReader.getCellData("Title"));

        addNewEventInformationComponent.eventDescription().fill(excelReader.getCellData("Description"));
        addNewEventInformationComponent.eventCategory().selectOption(excelReader.getCellData("Category"));
        addNewEventInformationComponent.eventDateAndTime().fill(excelReader.getCellData("Event Date and Time")); //should be in format yyyy/mm/ddTHH:MM
        addNewEventInformationComponent.eventCity().fill(excelReader.getCellData("City"));
        addNewEventInformationComponent.eventPrice().fill(excelReader.getCellData("Price"));
        addNewEventInformationComponent.eventSeats().fill(excelReader.getCellData("Total Seats"));
        addNewEventInformationComponent.eventVenue().fill(excelReader.getCellData("Venue"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Event")).click();
        ExtentManager.getTest().info("Event details added successfully");
        logger.info("Event details added successfully...");

    }

    @Test(groups = {"sanity"})
    public void validateAddedEvent(){
        // read data from Excel
        excelReader=new ExcelReader(TEST_DATA_PATH, "FindEvent",1);

        // Actual test
        MenubarHomePageComponent menubarOptions=new MenubarHomePageComponent(page);
        menubarOptions.selectEventFromMenu().click();
        SearchEventsComponent searchEventsComponent=new SearchEventsComponent(page);
        assertThat(searchEventsComponent.allEvents(excelReader.getCellData("EventName"))).isVisible();
    }


    @Test
    public void bookEvent(){
        //read data from Excel
        excelReader=new ExcelReader(TEST_DATA_PATH, "BookEvent",1);
        // Actual test
        MenubarHomePageComponent menubarOptions=new MenubarHomePageComponent(page);
        menubarOptions.selectEventFromMenu().click();
        Locator book = page.getByTestId("event-card")
                .filter(new Locator.FilterOptions()
                        .setHasText(excelReader.getCellData("Event Name")))
                .getByTestId("book-now-btn");
        book.click();
        int numberOfTickets= Integer.parseInt(excelReader.getCellData("No. of tickets"));
        for(int i=1;i<numberOfTickets;i++){
            page.getByText("+").click();
        }


    }



}
