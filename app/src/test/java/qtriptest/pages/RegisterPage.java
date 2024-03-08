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


public class RegisterPage {
    Assertion assertion = new Assertion();
    public String testUserForAll = "";
    RemoteWebDriver driver;
    @FindBy(name = "email")
    private WebElement userNameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-login']")
    private WebElement registerNowElement;

    @FindBy(xpath = "//a[@class='nav-link login register']")
    WebElement goToRegister;

    public RegisterPage(RemoteWebDriver driver) {

        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);

    }

    public void navigateToRegisterPage() throws InterruptedException {
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        goToRegister.click();
        assertion.assertTrue(driver.getCurrentUrl().contains("register"), "Failed to redirect to register page");
        logStatus("Register Test", "navigate to register page", "Success");
        Thread.sleep(3000);
        
    }


    public Boolean registerNewUser(String username, String password, String confirmpassword, Boolean makeUserDynamic) throws InterruptedException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String test_username_email="";
        Boolean status = false;
        if (makeUserDynamic){
            test_username_email = username.split("@")[0]+"_"+String.valueOf(timestamp.getTime())+"@"+username.split("@")[1];
            // System.out.println(test_username_email);
        }
        else{
            test_username_email = username;
        }
            // driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        try {
            userNameInput.sendKeys(test_username_email);
            passwordInput.sendKeys(password);
            confirmPasswordInput.sendKeys(confirmpassword);
            registerNowElement.click();
            Thread.sleep(5000);
            status = driver.getCurrentUrl().endsWith("/login");
            logStatus("Register Test", "navigate to Login page after registration", "Success");
            this.testUserForAll = test_username_email;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("failed,.................... "+ e.getMessage());
        }
        return status;

    }


    public static void logStatus(String type, String message, String status) {

        System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
                message, status));
    }

}
