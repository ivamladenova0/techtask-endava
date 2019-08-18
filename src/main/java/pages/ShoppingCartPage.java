package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) { super(driver); }

    @FindBy(how = How.CSS, using = ".a-size-medium.a-color-price.sc-price.sc-white-space-nowrap.sc-product-price.sc-price-sign.a-text-bold")
    static WebElement priceForSingleProductElement;

    @FindBy(how = How.CSS, using = ".a-size-medium.a-color-price.sc-price.sc-white-space-nowrap.sc-price-sign")
    static WebElement priceForAllProductsElement;

    public static Double priceForSingleProduct;
    private static Double priceForAllProductsInCart;

    @Step
    public void thenIVerifyPriceIsCorrect(){
        waitForVisibilityOf(priceForAllProductsElement);
        priceForSingleProduct = convertToDoubleValue(priceForSingleProductElement);
        priceForAllProductsInCart = convertToDoubleValue(priceForAllProductsElement);
        Assert.assertEquals(ProductDetailsPage.priceInDetailsForSingleProduct, priceForSingleProduct, "Price for single product differs, expected: " + ProductDetailsPage.priceInDetailsForSingleProduct + " actual: " + priceForSingleProduct);
        Assert.assertEquals(CartSubtotalPage.priceForAllProductsInCartSubtotal, priceForAllProductsInCart, "Price for quantity of products differs, expected: " + CartSubtotalPage.priceForAllProductsInCartSubtotal + " actual: " + priceForAllProductsInCart);
    }
}
