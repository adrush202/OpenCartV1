package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;



import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger log;
	public Properties p;

	@BeforeClass(groups = { "Regression", "Sanity", "Master", "DataDriver" })
	@Parameters({"os", "br"})
	public void setUp(String os, String br) throws IOException {

		FileReader file = new FileReader("./src//test//resources/config.properties");
		p = new Properties();
		p.load(file);

		log = LogManager.getLogger(this.getClass());

		if (p.getProperty("exec_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities cap = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}

			else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching os");
				return;
			}

			switch (br.toLowerCase()) {

			case "chrome":
				cap.setBrowserName("chrome");
				break;

			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;

			default:
				System.out.println("No matching browser");
				return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		if (p.getProperty("exec_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {

			case "chrome":
				driver = new ChromeDriver();
				break;

			case "edge":
				driver = new EdgeDriver();
				break;

			case "firefox":
				driver = new FirefoxDriver();
				break;

			default:
				System.out.println("Invalid browser name");
				return;

			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");

	}

	public String randomString() {

		String word = RandomStringUtils.randomAlphabetic(5);
		return word;
	}

	public String randomNumeric() {

		String num = RandomStringUtils.randomNumeric(5);
		return num;
	}

	public String randomAlphanumeric() {
		String alpha = RandomStringUtils.randomAlphabetic(5);
		String beta = RandomStringUtils.randomNumeric(5);
		return (alpha + "@" + beta);

	}

	public String captureScreen(String tname) {

		String timeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		String filepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		File targetFile = new File(filepath);

		src.renameTo(targetFile);
		return filepath;

	}

}
