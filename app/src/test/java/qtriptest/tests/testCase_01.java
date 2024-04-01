package qtriptest.tests;
import qtriptest.DP;
import qtriptest.DriverSingleton;
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


public class testCase_01 {
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
		// logStatus("driver", "Initializing driver", "Started");
		// final DesiredCapabilities capabilities = new DesiredCapabilities();
		// capabilities.setBrowserName(BrowserType.CHROME);
		// driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		// driver.manage().window().maximize();
		// logStatus("driver", "Initializing driver", "Success");
	}
	

	@Test(description = "Test Case Id 1",dataProvider = "data-provider", dataProviderClass = DP.class, enabled = true, priority = 1, groups = "Login Flow")
	public void TestCase01(String username, String password) throws InterruptedException {
		try {
			logStatus("Register & Login Test", "New User Registration", "STARTED");
			RegisterPage register = new RegisterPage(driver);
			register.navigateToRegisterPage();
			Boolean status = register.registerNewUser(username,password,password,true);
			assertion.assertTrue(status, "New User Registration: PASSED");
			logStatus("Register Test", "New User Registration", "PASSED");
			testUserForAll = register.testUserForAll;
		} catch (Exception e) {
			logStatus("Register Test", "New User Registration", "FAILED");
		}
		logStatus("Login Test", "Login with Registered user", "STARTED");
		LoginPage login = new LoginPage(driver);
		Boolean status = login.performLogin(testUserForAll, password);
		assertion.assertTrue(status,"Success");
		if(status){
			logStatus("Login Test", "Login with Registered user", "PASSED");
		}
		else{
			logStatus("Login Test", "Login with Registered user", "FAILED");
		}
		assertion.assertTrue(login.performLogout());
		logStatus("Logout Test", "Logout Registered user", "PASSED");
	}



}



