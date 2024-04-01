package qtriptest.pages;

import java.rmi.Remote;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.asserts.Assertion;

public class HomePage {

    RemoteWebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Search a City ']")
    private WebElement citySearch;

    @FindBy(xpath = "//ul[@id='results']/h5")
    private WebElement noResults;

    @FindBy(xpath = "//ul[@id='results']/a/li")
    private WebElement cityResults;

    public HomePage(RemoteWebDriver driver) {

        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);

    }

    public void navigateToHomePage() throws InterruptedException {
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        Thread.sleep(3000);
    }

    // public void searchCityInvalid(String cityName) throws InterruptedException {
        
    // }

    public void searchCity(String cityName) throws InterruptedException {
        try {
            Assertion assertion = new Assertion();
            citySearch.sendKeys(cityName);
            Thread.sleep(2000);
            assertion.assertTrue(cityResults.getText().contains(cityName));
            cityResults.click();
            Thread.sleep(3000);
        } catch (Exception e) {
            //TODO: handle exception
            Assertion assertion = new Assertion();
            // citySearch.sendKeys(cityName);
            Thread.sleep(2000);
            if(noResults.isDisplayed()) {
            assertion.assertTrue(noResults.getText().contains("No City found"));
            citySearch.clear();
        }
        }
        
    }
}
