package qtriptest.pages;
import java.sql.Timestamp;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.asserts.Assertion;


public class LoginPage {
    RemoteWebDriver driver;
    @FindBy(name = "email")
    private WebElement userNameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-login']")
    private WebElement registerNowElement;

    @FindBy(xpath = "//div[@class='nav-link login register']")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[@href='/']")
    private WebElement homeButton;

    @FindBy(xpath = "//a[text()='Login Here']")
    private WebElement loginHereButton;

    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);

    }

    public Boolean performLogin(String username, String password) {
        Boolean status = false;
        try {
            userNameInput.clear();
            userNameInput.sendKeys(username);
            passwordInput.clear();
            passwordInput.sendKeys(password);
            registerNowElement.click();
            Thread.sleep(4000);
            status = logoutButton.isDisplayed();
        } catch (Exception e) {
            System.out.println("something failed while performing login"+ e.getMessage());
        }
        return status;

    }


    public Boolean performLogout() throws InterruptedException {
        Boolean status = false;
        try {
            logoutButton.click();
            Thread.sleep(3000);
            loginHereButton.isDisplayed();
            status = true;
        } catch (Exception e) {
            //TODO: handle exception
            status = false;
        }
        return status;

    }
}
