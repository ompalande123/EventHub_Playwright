package InterviewPrograms;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
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

        context.tracing().start(new Tracing.StartOptions().setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        try {
            page.onRequest(request -> {
                System.out.println("Request URL is: " + request.url());
                System.out.println("Request method is: " + request.method());
                System.out.println("Request headers is: " + request.headers());
            });

            page.onResponse(response -> {
                System.out.println("Response URL is: " + response.url());
                System.out.println("Response status is: " + response.status());
            });

            page.navigate("https://eventhub.rahulshettyacademy.com/login");

            page.screenshot(new Page.ScreenshotOptions().setFullPage(true)
                    .setPath(Paths.get("screenshots123.png")));

            page.getByLabel("Email").fill("ompalande123@gmail.com");
            page.getByLabel("Password").fill("Playwright#2026");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign123 In")).click();
            System.out.println(page.title());

        }finally {
            context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("tracePractice.zip")));
        }

        context.close();
        browser.close();


    }

}
