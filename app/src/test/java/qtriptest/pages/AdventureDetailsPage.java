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


public class AdventureDetailsPage {
    RemoteWebDriver driver;

    @FindBy(name = "name")
    private WebElement nameTextBox;

    @FindBy(name = "date")
    private WebElement dateTextBox;

    @FindBy(name = "person")
    private WebElement personTextBox;

    @FindBy(className = "reserve-button")
    private WebElement reserveButton;

    @FindBy(id = "reserved-banner")
    private WebElement reservationStatus;

    @FindBy(xpath = "//a[text()='Reservations']")
    private WebElement reservationButton;

    @FindBy(xpath = "//tbody[@id='reservation-table']/tr/th")
    private WebElement tranID;

    @FindBy(className = "cancel-button")
    private WebElement cancelButton;


    public AdventureDetailsPage(RemoteWebDriver driver) {

        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    
    }


    public Boolean enterDetails(String name, String date, String count) {
        Boolean status = false;
        try {
            nameTextBox.sendKeys(name);
            dateTextBox.sendKeys(date);
            personTextBox.clear();
            personTextBox.sendKeys(count);
            Thread.sleep(2000);
            reserveButton.click();
            Thread.sleep(3000);
            status = reservationStatus.isDisplayed();

        } catch (Exception e) {
            System.out.println("unable to enter details "+e.getMessage());
            status = false;       
        }
        return status;
        
    }

    public Boolean editReservation() throws InterruptedException {
        // Boolean status = false;
        reservationButton.click();
        Thread.sleep(3000);
        System.out.println("TransactionID is: "+ tranID.getText());
        cancelButton.click();
        System.out.println("Booking Cancelled..");
        // Thread.sleep(3000);
        // driver.navigate().refresh();
        Thread.sleep(2000);
        return true;
        // if(!tranID.isDisplayed()) {
        //     status = true;
        // }
        // else{
        //     status = false;
        // }
        // return status;
        
    }







}