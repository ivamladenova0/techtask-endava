import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.PageGenerator;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public PageGenerator page;

    @BeforeMethod
    public void classLevelSetup() {
        String browserType = System.getProperty("browserType");
        switch (browserType) {
            case "Chrome":
                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;

            case "Firefox":
                FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;

            case "Safari":
                driver = new SafariDriver();
                driver.manage().window().maximize();
                break;

            case "Opera":
                OperaDriverManager.getInstance(DriverManagerType.OPERA).setup();
                driver.manage().window().maximize();
                driver = new OperaDriver();
                break;

            case "Edge":
                EdgeDriverManager.getInstance(DriverManagerType.EDGE).setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;

        }
        wait = new WebDriverWait(driver,20);
        page = new PageGenerator(driver);
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

