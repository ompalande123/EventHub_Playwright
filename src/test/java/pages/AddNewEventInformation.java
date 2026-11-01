package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddNewEventInformation {

    private Page page;

    public AddNewEventInformation(Page page){
        this.page=page;
    }

    public Locator eventTitle(){
        return page.getByLabel("Title");
    }

    public Locator eventDescription(){
        return page.getByPlaceholder("Describe the event");
    }


}
