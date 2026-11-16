package InterviewPrograms;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class PracticeMockAPIs {

    @Test
    public void testRouting(){
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch();
        BrowserContext context=browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/")));
        Page page=context.newPage();

        page.onRequest(request -> {
            System.out.println("Request URL is: "+request.url());
            System.out.println("Request method is: "+request.method());
            System.out.println("Request headers is: "+request.headers());
        });

        page.onResponse(response -> {
            System.out.println("Response URL is: "+response.url());
            System.out.println("Response status is: "+response.status());
        });

        page.navigate("https://eventhub.rahulshettyacademy.com/login");

        page.screenshot(new Page.ScreenshotOptions().setFullPage(true)
                .setPath(Paths.get("screenshots123.png")));

        context.close();
        browser.close();


    }

}
