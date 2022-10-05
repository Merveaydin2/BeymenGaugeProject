package driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSuite;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static  WebDriverWait webWait;
    public static WebDriver driver;
    public static JavascriptExecutor jsDriver;

    @BeforeScenario
    public void setUp() throws Exception {

        String url = "https://www.beymen.com/";
        DesiredCapabilities capabilities;
        if (StringUtils.isEmpty(System.getenv("key"))) {
            capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-popup-blocking");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver(capabilities);
        } else {
            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("key", System.getenv("key"));
            driver = new RemoteWebDriver(new URL("http://hub.testinium.io/wd/hub"), capabilities);
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        }

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS).implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

    }

    @AfterScenario
    public void tearDown() {

        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
