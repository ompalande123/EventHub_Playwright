package utils;

import com.microsoft.playwright.Page;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtils {

    public static String captureScreenshot(Page page, String testName){
        try {

            Path folder= Path.of("test-output", "failedScreenshots");
            Files.createDirectories(folder);

            String timestamp=DateUtils.getCurrentDateTime();
            String fileName=testName+"_"+timestamp+".png";
            Path screenshotPath=folder.resolve(fileName);
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(screenshotPath)
                    .setFullPage(true));

            return screenshotPath.toAbsolutePath().toString();

        } catch (IOException e) {
            System.out.println("Screenshot capture failed - "+e.getMessage());
            return null;
        }


    }

}
