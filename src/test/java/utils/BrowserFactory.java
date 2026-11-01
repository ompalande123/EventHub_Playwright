package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.util.Locale;

public class BrowserFactory {

    public static Playwright playwright;
    public static Browser browser;

    public static void initializeBrowser(){
            playwright=Playwright.create();

            String browserName=ConfigReader.getProperty("browser");
            boolean headless=Boolean.parseBoolean(ConfigReader.getProperty("setHeadless"));

            switch(browserName.toLowerCase()){
                case "chromium":
                    browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(1000));
                    break;

                case "firefox":
                    browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                    break;

                case "webkit":
                    browser=playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browserName provided: "+browserName);
            }
    }

    public static void closeBrowser() {

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }

}
