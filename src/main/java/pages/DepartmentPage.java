package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DepartmentPage extends BasePage {

    public DepartmentPage(WebDriver driver) { super(driver);
    verifyPageisLoaded(driver);}

    @FindBy(how = How.ID, using = "low-price")
    private WebElement minPriceElement;

    @FindBy(how = How.ID, using = "high-price")
    private WebElement maxPriceElement;

    @FindBy(how = How.CSS, using = "#priceRefinements ul>li>span * span>input")
    private WebElement priceFilterButton;

    @FindBy(how = How.CSS, using = "div[data-index='0'] h2>a")
    private WebElement firstResultElement;

    @FindAll(@FindBy(how = How.CSS, using = "#brandsRefinements ul li span a"))
    private List<WebElement> brandElements;

    @Step
    public DepartmentPage andIFilterPrice(String expectedMinPrice, String expectedMaxPrice){
        waitForVisibilityOf(minPriceElement);
        verifyPageisLoaded(driver);
        writeText(minPriceElement, expectedMinPrice);
        writeText(maxPriceElement, expectedMaxPrice);
        click(priceFilterButton);
        return this;
    }

    @Step
    public DepartmentPage andIFilterBrand(String expectedBrand){
        waitForVisibilityOf(priceFilterButton);
        WebElement brand = findElementFromElements(brandElements, expectedBrand);
        click(brand);
        return this;
    }

    @Step
    public ProductDetailsPage andIOpenDetailsPageForFirstItem(){
        waitForVisibilityOf(firstResultElement);
        click(firstResultElement);
        verifyPageisLoaded(driver);
        return new PageFactory().initElements(driver,ProductDetailsPage.class);
    }

}
