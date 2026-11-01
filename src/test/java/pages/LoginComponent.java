package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginComponent {

    private Page page;

    public LoginComponent(Page page){
        this.page=page;
    }



    public void navigateTo(){
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
    }


    public Locator enterEmailAddress(){
        return page.getByLabel("Email");
    }

    public Locator enterPassword(){
        return page.getByLabel("Password");
    }

    public Locator cilckOnSignInButton(){
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In"));
    }

    /*public void login(String email, String password){
        page.getByLabel("Email").fill(email);
        page.getByLabel("Password").fill(password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
    }*/

    public String getTitle(){
        return page.title();
    }


}
