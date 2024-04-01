package qtriptest.tests;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.*;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.*;
// import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;


public class testCase_02 {
    RemoteWebDriver driver;
	public String testUserForAll="";
	
	Assertion assertion = new Assertion();

	public static void logStatus(String type, String message, String status) {

        System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
                message, status));
    }


    @BeforeClass(alwaysRun = true, enabled = true)
	public void createDriver() throws MalformedURLException {
        DriverSingleton singleton = DriverSingleton.getInstanceOfWebdriver();
		driver = singleton.getDriver();
	}

    @Test(description = "Test Case Id 2", dataProvider = "data-provider", dataProviderClass = DP.class, enabled = true, priority = 2, groups = "Search and Filter flow")
    public void TestCase02(String CityName, String Category_Filter, String DurationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws InterruptedException {
        logStatus("Search and Filter Test", "Verify that Search and filters work fine", "STARTED");
        HomePage homepage = new HomePage(driver);
        homepage.navigateToHomePage();
        // homepage.searchCityInvalid(CityName);
        // logStatus("Search for an invalid city", "Verify if the no matches found message is displayed", "Success");
        homepage.searchCity(CityName);
        logStatus("Search for a city", "verify that the city or error message is displayed on auto complete", "Success");
        AdventurePage adventurepage = new AdventurePage(driver);
        adventurepage.hrsFilter(DurationFilter);
        logStatus("Filter Functionality", "Verify that appropriate data is displayed using hrs filter", "Success");
        adventurepage.filterCategory(Category_Filter, ExpectedFilteredResults);
        logStatus("Filter Functionality", "Verify that appropriate data is displayed using category filter", "Success");
        adventurepage.clearFilter(ExpectedUnFilteredResults);
        logStatus("Clear Filters", "Verify that all the records are displayed", "Success");

    }







}
