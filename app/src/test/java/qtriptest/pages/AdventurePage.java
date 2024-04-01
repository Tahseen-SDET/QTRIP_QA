package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
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

    @FindBy(xpath = "//*[@id='1773524915']/div[2]/div/div[2]/p")
    private WebElement durationResult;

    @FindBy(xpath = "//div[@class='category-banner'and text()='Cycling']")
    private WebElement categoryResult;

    @FindBy(xpath = "//div/div[2]/div[@class='ms-3']")
    private WebElement clearHrFilter;

    @FindBy(xpath = "//div/div[3]/div[@class='ms-3']")
    private WebElement clearCategoryFilter;

    @FindBy(xpath = "//div[@class='activity-card']")
    private List<WebElement> filterClearedResults;

    @FindBy(id = "search-adventures")
    private WebElement adventureSearch;

    @FindBy(xpath = "//div[@class='activity-card']")
    private WebElement adventureResult;




    public AdventurePage(RemoteWebDriver driver) {

        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    
    }

    public void hrsFilter(String filterByHrs) {
        try {
            Select dropDown1 = new Select(hourFilter);
            dropDown1.selectByVisibleText(filterByHrs);
            Thread.sleep(2000);
            // assertion.assertTrue(durationResult.getText().contains("9 Hours"));
        }
        
        catch (Exception e) {
            System.out.println("hrs filter not working or execution has error "+e.getMessage());
        }

    }

    public void filterCategory(String filterByCategory, String bothFilterResults) {
        try {
            Select dropDown2 = new Select(categoryFilter);
            dropDown2.selectByVisibleText(filterByCategory);
            Thread.sleep(2000);
            // assertion.assertTrue(categoryResult.getText().contains("Cycling"));
            assertion.assertTrue(filterClearedResults.size() == Integer.parseInt(bothFilterResults));
        } catch (Exception e) {
            System.out.println("category filter not working or execution has error "+e.getMessage());
        }
    }

    public void clearFilter(String clearFilterResults) {
        try {
            clearHrFilter.click();
            Thread.sleep(2000);
            clearCategoryFilter.click();
            Thread.sleep(2000);
            assertion.assertTrue(filterClearedResults.size() == Integer.parseInt(clearFilterResults));
            
        } catch (Exception e) {
            System.out.println("Clearing Filters funtionality has some error "+e.getMessage());
        }
        
    }

    public void searchAdventure(String adventurePlace) {
        try {
            adventureSearch.sendKeys(adventurePlace);
            Thread.sleep(2000);
            adventureResult.click();
            Thread.sleep(4000);
        } catch (Exception e) {
            System.out.println("unable to search for adventure "+e.getMessage());
        }
        

    }





}

