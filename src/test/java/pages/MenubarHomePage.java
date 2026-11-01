package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MenubarHomePage {

    private Page page;

    public MenubarHomePage(Page page){
        this.page=page;
    }

    public Locator clickOnEventsFromMenu(){
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Events"));
    }

    public Locator selectEventFromMenu(){
        return page.getByTestId("nav-events");
    }

    public Locator addNewEventButton(){
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add New Event"));
    }


}
