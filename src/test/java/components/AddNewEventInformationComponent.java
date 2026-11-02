package components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddNewEventInformationComponent {

    private Page page;

    public AddNewEventInformationComponent(Page page){
        this.page=page;
    }

    public Locator eventTitle(){
        return page.getByLabel("Title");
    }

    public Locator eventDescription(){
        return page.getByPlaceholder("Describe the event");
    }

    public Locator eventCategory(){
        return page.getByLabel("Category");
    }

    public Locator eventDateAndTime(){
        return  page.getByLabel("Event Date & Time");
    }

    public Locator eventCity(){
        return page.getByLabel("City");
    }

    public Locator eventPrice(){
        return page.getByLabel("Price");
    }

    public Locator eventSeats(){
        return page.getByLabel("Total Seats");
    }

    public Locator eventVenue(){
        return page.getByLabel("Venue");
    }

}
