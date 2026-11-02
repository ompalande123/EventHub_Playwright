package components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SearchEventsComponent {

    private Page page;

    public SearchEventsComponent(Page page){
        this.page=page;
    }

    public Locator allEvents(String eventName){
        return page.getByTestId("event-card").filter().getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(eventName));
    }



}
