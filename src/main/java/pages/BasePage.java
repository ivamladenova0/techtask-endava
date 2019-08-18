package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage extends PageGenerator {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    //Click Method by using JAVA Generics (You can use both By or Webelement)
    public <T> void click(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    //Write Text by using JAVA Generics (You can use both By or Webelement)
    public <T> void writeText(T elementAttr, String text) {
        if (elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    //Read Text by using JAVA Generics (You can use both By or Webelement)
    public <T> String readText(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }

    public WebElement findElementFromElements(List<WebElement> elements, String departmentName) {
        WebElement element;
        for (int i = 0; i <= elements.size(); i++) {
            element = elements.get(i);
            if (element.getText().equals(departmentName)) {
                return element;
            }
        }
        return null;
    }

    public Double convertToDoubleValue(WebElement element){
       Double value = Double.parseDouble(element.getText()
               .replace("USD ", "")
               .replace("$", "")
               .replace("$ ", ""));
       return value;
    }

    public void waitForVisibilityOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
