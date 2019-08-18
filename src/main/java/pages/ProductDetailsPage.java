package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(how = How.ID, using = "quantity")
    private WebElement quantityElement;

    @FindBy(how = How.ID, using = "newPriceRow")
    private WebElement priceBigForSingleItemElement;

    public static Double quantityValue;
    public static Double priceInDetailsForSingleProduct;
    public static Double actualPriceForQuantityOfProduct;

    public void selectFromDropDown(Select dropDown){

      if (dropDown.isMultiple()){
          dropDown.selectByVisibleText("2");
          quantityValue = 2.0;
      }

     else {
           System.out.println("..Selecting available option..");
           dropDown.selectByIndex(0);
           quantityValue = 1.0;
       }

    }

    public ProductDetailsPage andIPickQuantity() {
        waitForVisibilityOf(quantityElement);
        verifyPageisLoaded(driver);
        Select quantitySelect = new Select(quantityElement);
        selectFromDropDown(quantitySelect);
        priceInDetailsForSingleProduct = Double.parseDouble(priceBigForSingleItemElement.getText().replace("$ ", "").replace(" ", "."));
        actualPriceForQuantityOfProduct = quantityValue * priceInDetailsForSingleProduct;
        return this;
    }

    @Step
    public CartSubtotalPage andIAddItemToCart() {
        waitForVisibilityOf(quantityElement);
        verifyPageisLoaded(driver);
        click(addToCartButton);
        return new PageFactory().initElements(driver, CartSubtotalPage.class);
    }
}
