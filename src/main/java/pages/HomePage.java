package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.PropertyReader;

public class HomePage extends BasePage{

    public HomePage (WebDriver driver) {
        super(driver);
    }

    String baseURL = PropertyReader.baseURL;

    @FindBy(how = How.ID, using = "twotabsearchtextbox")
    private WebElement searchFieldElement;

    @FindBy(how = How.CSS, using = ".nav-search-submit.nav-sprite")
    private WebElement searchButton;

    public double priceInResults;
    public double quantityValue;

    @Step
    public HomePage GivenIAmAtHomePage(){
        driver.get(baseURL);
        return this;
    }

    @Step
    public DepartmentsPage whenISearchItem(String searchText){
        waitForVisibilityOf(searchFieldElement);
        writeText(searchFieldElement, searchText);
        click(searchButton);

        return new PageFactory().initElements(driver, DepartmentsPage.class);
    }
}
