import io.github.bonigarcia.wdm.*;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.PageGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    }

    @AfterMethod
    public void teardown (ITestResult result) throws IOException {
        if(!result.isSuccess()) {
            takeScreenShot(result.getMethod());
        }
        driver.quit();
    }

    private void takeScreenShot(String name) throws IOException {
        String path = getRelativePath(name);
        File screenShot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File(path));
        String filename = makeScreenShotFileName(name);
        System.out.println("Taking Screenshot! " + filename);
        Reporter.log("<a href=" + path + " target='_blank' >" + filename
                + "</a>");
    }
    private void takeScreenShot(ITestNGMethod testMethod) throws IOException {
        String nameScreenShot = testMethod.getTestClass().getRealClass()
                .getSimpleName()
                + "_" + testMethod.getMethodName();
        takeScreenShot(nameScreenShot);
    }
    private String makeScreenShotFileName(String name) {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
        Date date = new Date();
        return dateFormat.format(date) + "_" + name + ".png";
    }
    private String getRelativePath(String name) throws IOException {
        Path path = Paths.get(".", "target", "surefire-reports", "screenShots",
                makeScreenShotFileName(name));
        File directory = new File(path.toString());
        return directory.getCanonicalPath();
    }

    @Attachment(value = "Web Page Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        // Take a screenshot as byte array and return
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}

