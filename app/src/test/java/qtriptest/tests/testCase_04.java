package qtriptest.tests;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.*;
// import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import qtriptest.pages.HomePage;

public class testCase_04 {

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


    @Test(description = "Test Case Id 4", dataProvider = "data-provider", dataProviderClass = DP.class, enabled = true, priority = 4, groups = "Reliability Flow")
    public void TestCase04(String NewUserName, String Password, String dataset1, String dataset2, String dataset3) throws InterruptedException {
        String [] set1Arr = dataset1.split(";");
        String [] set2Arr = dataset2.split(";");
        String [] set3Arr = dataset3.split(";");
        logStatus("Booking History flow and Reliability", "Verify that Booking history can be viewed", "STARTED");
        HomePage homepage = new HomePage(driver);
        homepage.navigateToHomePage();
        RegisterPage registerpage = new RegisterPage(driver);
        registerpage.navigateToRegisterPage();
        registerpage.registerNewUser(NewUserName, Password, Password, true);
        testUserForAll = registerpage.testUserForAll;
        LoginPage loginpage = new LoginPage(driver);
        loginpage.performLogin(testUserForAll, Password);
        int index = 0;
        homepage.navigateToHomePage();
        homepage.searchCity(set1Arr[index]);
        AdventurePage adventurepage = new AdventurePage(driver);
        adventurepage.searchAdventure(set1Arr[index+1]);
        AdventureDetailsPage adventuredetailspage = new AdventureDetailsPage(driver);
        assertion.assertTrue(adventuredetailspage.enterDetails(set1Arr[index+2], set1Arr[index+3], set1Arr[index+4]));
        logStatus("Booking Functionality", "Verify that the adventure booking process", "Success");

        homepage.navigateToHomePage();
        homepage.searchCity(set2Arr[index]);
        adventurepage.searchAdventure(set2Arr[index+1]);
        assertion.assertTrue(adventuredetailspage.enterDetails(set2Arr[index+2], set2Arr[index+3], set2Arr[index+4]));
        logStatus("Booking Functionality", "Verify that the adventure booking process", "Success");

        homepage.navigateToHomePage();
        homepage.searchCity(set3Arr[index]);
        adventurepage.searchAdventure(set3Arr[index+1]);
        assertion.assertTrue(adventuredetailspage.enterDetails(set3Arr[index+2], set3Arr[index+3], set3Arr[index+4]));
        logStatus("Booking Functionality", "Verify that the adventure booking process", "Success");
    
        HistoryPage history = new HistoryPage(driver);
        history.goToReservations();
        assertion.assertTrue(history.verifyReservationCount());
        logStatus("Reservation History", "Check if all the bookings are displayed on the Reservation page", "Success");
        loginpage.performLogout();

            
    }





}






