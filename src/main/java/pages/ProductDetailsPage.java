package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "add-to-cart-button")
    public WebElement addToCartButton;

    @FindBy(how = How.ID, using = "quantity")
    public WebElement quantityElement;

    @FindBy(how = How.ID, using = "newPriceRow")
    private WebElement priceBigForSingleItemElement;

    public static Double quantityValue;
    public static Double priceInDetailsForSingleProduct;
    public static Double actualPriceForQuantityOfProduct;

    public ProductDetailsPage andIPickQuantity(String expectedSelectValue) {
        verifyPageisLoaded(driver);
        Select quantitySelect = new Select(quantityElement);
        quantitySelect.selectByVisibleText(expectedSelectValue);

        priceInDetailsForSingleProduct = Double.parseDouble(priceBigForSingleItemElement.getText().replace("$ ", "").replace(" ", "."));
        quantityValue = Double.parseDouble(expectedSelectValue);
        actualPriceForQuantityOfProduct = quantityValue * priceInDetailsForSingleProduct;
        return this;
    }

    @Step
    public CartSubtotalPage andIAddItemToCart() {
        verifyPageisLoaded(driver);
        click(addToCartButton);
        return new PageFactory().initElements(driver, CartSubtotalPage.class);
    }
}
