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
    public WebElement minPriceElement;

    @FindBy(how = How.ID, using = "high-price")
    public WebElement maxPriceElement;

    @FindBy(how = How.CSS, using = "#priceRefinements ul>li>span * span>input")
    public WebElement priceFilterButton;

    @FindBy(how = How.CSS, using = "div[data-index='0'] h2>a")
    public WebElement firstResultElement;

    @FindAll(@FindBy(how = How.CSS, using = "#brandsRefinements ul li span a"))
    private List<WebElement> brandElements;

    @Step
    public DepartmentPage andIFilterPrice(String expectedMinPrice, String expectedMaxPrice){
        verifyPageisLoaded(driver);
        writeText(minPriceElement, expectedMinPrice);
        writeText(maxPriceElement, expectedMaxPrice);
        click(priceFilterButton);
        return this;
    }

    @Step
    public DepartmentPage andIFilterBrand(String expectedBrand){
        WebElement brand = findElementFromElements(brandElements, expectedBrand);
        click(brand);
        return this;
    }

    @Step
    public ProductDetailsPage andIOpenDetailsPageForFirstItem(){
        click(firstResultElement);
        verifyPageisLoaded(driver);
        return new PageFactory().initElements(driver,ProductDetailsPage.class);
    }

}
