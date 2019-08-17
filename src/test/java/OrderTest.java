import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.HomePage;

public class OrderTest extends BaseTest {

    @Test
    @Story("User applies filters and orders product")
    @Description("Valid order scenario with valid filtering for department, price and brand")
    public void validOrderScenarioTest() {
        HomePage homePage = page.getPage(HomePage.class);
        homePage.GivenIAmAtHomePage()
                .whenISearchItem("Harry Potter")
                .andIExpandAllDepartments()
                .andIPickDepartment("Video Games")
                .andIFilterPrice("10", "150")
                .andIFilterBrand("Warner Bros")
                .andIOpenDetailsPageForFirstItem()
                .andIPickQuantity("2")
                .andIAddItemToCart()
                .andIVerifyPriceIsCorrect()
                .andWhenIGoToShoppingCart()
                .thenIVerifyPriceIsCorrect();
    }
}
