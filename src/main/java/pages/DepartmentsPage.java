package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DepartmentsPage extends BasePage {

public DepartmentsPage(WebDriver driver) { super(driver); }

    @FindBy(how = How.XPATH, using = "//a[contains(@data-a-expander-toggle,'Departments')]")
    private WebElement expandAllDepartmentsElement;

    @FindAll(@FindBy(how = How.CSS, using = "#departments span>a>span"))
    private List<WebElement> deparmentElements;

    @Step
    public DepartmentsPage andIExpandAllDepartments(){
        click(expandAllDepartmentsElement);
        return this;
    }

    @Step
    public DepartmentPage andIPickDepartment(String expectedDepartment) {
        waitForVisibilityOf(expandAllDepartmentsElement);
       WebElement department = findElementFromElements(deparmentElements, expectedDepartment);
       click(department);
       return new PageFactory().initElements(driver,DepartmentPage.class);
    }
}
