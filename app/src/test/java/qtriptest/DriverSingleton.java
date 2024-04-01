package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {
    RemoteWebDriver driver;
    private static DriverSingleton instanceOfSingleton = null;

    private DriverSingleton() throws MalformedURLException {
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		driver.manage().window().maximize();
        System.out.println("Driver Initialised");
    }

    public static DriverSingleton getInstanceOfWebdriver() throws MalformedURLException {
        if (instanceOfSingleton == null) {
            instanceOfSingleton = new DriverSingleton();
        }
        return instanceOfSingleton;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }
}