package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.Assertion;

public class AdventurePage {
    RemoteWebDriver driver;
    Assertion assertion = new Assertion();

    @FindBy(xpath = "//select[@id='duration-select']")
    private WebElement hourFilter;

    @FindBy(xpath = "//select[@id='category-select']")
    private WebElement categoryFilter;


    public AdventurePage(RemoteWebDriver driver) {

        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    
    }

    public void hrsFilter() {
        try {
            Select dropDown = new Select(hourFilter);
            List<WebElement> hrs =  dropDown.getOptions();
            for(WebElement elem : hrs) {
                assertion.assertTrue(elem.isDisplayed());
            }
            System.out.println("Appropriate data is displayed correctly");
            dropDown.selectByVisibleText("2-6 Hours");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("dropdown data is missing "+e.getMessage());
        }

    }

    public void filterCategory() {
        try {
            Select dropDown = new Select(categoryFilter);
            List<WebElement> hrs =  dropDown.getOptions();
            for(WebElement elem : hrs) {
                assertion.assertTrue(elem.isDisplayed());
            }
            System.out.println("Appropriate data is displayed correctly");
            dropDown.selectByVisibleText("Cycling Routes");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("dropdown data is missing "+e.getMessage());
        }

    }





}

