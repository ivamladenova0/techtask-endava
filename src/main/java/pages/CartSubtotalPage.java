package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartSubtotalPage extends BasePage {
    public CartSubtotalPage(WebDriver driver) { super(driver);}

    @FindBy(how = How.ID, using = "hlb-view-cart-announce")
    private WebElement cartButton;

    @FindBy(how = How.CSS, using = "#hlb-subcart span.a-color-price.hlb-price.a-inline-block.a-text-bold")
    private WebElement priceSubtotalForAllProductsInCartElement;

    public static Double priceForAllProductsInCartSubtotal;

    @Step
    public CartSubtotalPage andIVerifyPriceIsCorrect(){
        waitForVisibilityOf(cartButton);
        priceForAllProductsInCartSubtotal = convertToDoubleValue(priceSubtotalForAllProductsInCartElement);
        Assert.assertEquals(ProductDetailsPage.actualPriceForQuantityOfProduct, priceForAllProductsInCartSubtotal);
        return this;
    }

    @Step
    public ShoppingCartPage andWhenIGoToShoppingCart(){
        waitForVisibilityOf(cartButton);
        click(cartButton);
        return new PageFactory().initElements(driver, ShoppingCartPage.class);
    }

}
