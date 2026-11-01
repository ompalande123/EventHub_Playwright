package base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.BrowserFactory;


public class BaseTest {

    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setupBrowser(){
        BrowserFactory.initializeBrowser();
    }

    @BeforeMethod
    public void setupContextPage(){
        context=BrowserFactory.browser.newContext();
        page=context.newPage();
    }

    @AfterMethod
    public void tearDown(){
        if(context!=null){
            context.close();
        }
    }


    @AfterClass
    public void closeBrowser(){
        BrowserFactory.closeBrowser();
    }

}
