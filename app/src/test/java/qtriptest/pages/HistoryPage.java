
package qtriptest.pages;
import java.rmi.Remote;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.asserts.Assertion;


public class HistoryPage {

    RemoteWebDriver driver;

    @FindBy(xpath = "//a[text()='Reservations']")
    private WebElement reservationButton;

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> totalReservations;



    public HistoryPage(RemoteWebDriver driver) {

        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);

    }


    public void goToReservations() {
        try {
            reservationButton.click();
            Thread.sleep(3000);

        } catch (Exception e) {

        }
    }


    public Boolean verifyReservationCount() {
        Boolean status = false;
        if(totalReservations.size() == 3) {
            status = true;
        }
        else{
            status = false;
        }
        return status;
    }
}