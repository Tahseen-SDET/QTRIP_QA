package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
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

import qtriptest.pages.HomePage;

public class testCase_03 {


    RemoteWebDriver driver;
    public String testUserForAll = "";

    Assertion assertion = new Assertion();

    public static void logStatus(String type, String message, String status) {

        System.out.println(String.format("%s |  %s  |  %s | %s",
                String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }


    @BeforeClass(alwaysRun = true, enabled = true)
    public void createDriver() throws MalformedURLException {
        DriverSingleton singleton = DriverSingleton.getInstanceOfWebdriver();
        driver = singleton.getDriver();
    }

    @Test(description = "Test Case Id 3", dataProvider = "data-provider", dataProviderClass = DP.class, enabled = true, priority = 3, groups = "Booking and Cancellation Flow")
    public void TestCase03(String NewUserName, String Password, String SearchCity,
            String AdventureName, String GuestName, String Date, String count) throws InterruptedException {
        logStatus("Booking and Cancellation Flow", "Verify that adventure booking and cancellation works fine", "STARTED");
        HomePage homepage = new HomePage(driver);
        homepage.navigateToHomePage();
        RegisterPage registerpage = new RegisterPage(driver);
        registerpage.navigateToRegisterPage();
        registerpage.registerNewUser(NewUserName, Password, Password, true);
        testUserForAll = registerpage.testUserForAll;
        LoginPage loginpage = new LoginPage(driver);
        loginpage.performLogin(testUserForAll, Password);
        homepage.searchCity(SearchCity);
        AdventurePage adventurepage = new AdventurePage(driver);
        adventurepage.searchAdventure(AdventureName);
        AdventureDetailsPage adventuredetailspage = new AdventureDetailsPage(driver);
        assertion.assertTrue(adventuredetailspage.enterDetails(GuestName, Date, count));
        logStatus("Booking Functionality", "Verify that the adventure booking process", "Success");
        assertion.assertTrue(adventuredetailspage.editReservation());
        loginpage.performLogout();
        logStatus("Cancellation Functionality", "Verify that the adventure booking is Cancelled", "Success");
    }


}
