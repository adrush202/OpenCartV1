package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.RegistrationPage;

public class TC001_AccountRegister extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account() {

		try {

			log.info("******* Starting C001_AccountRegister *******");

			Homepage hp = new Homepage(driver);
			hp.clickAccount();
			hp.clickRegister();

			RegistrationPage rp = new RegistrationPage(driver);
			rp.setFirstName(randomString().toUpperCase());
			rp.setLastName(randomString().toUpperCase());
			rp.setEmail(randomString() + "@gmail.com");
			rp.setTelephone(randomNumeric());

			String password = randomAlphanumeric();

			rp.setPassword(password);
			rp.setCnfPassword(password);

			rp.setAgree();
			rp.setButton();

			String cnf = rp.confirmMessage();

			Assert.assertEquals(cnf, "Your Account Has Been Created!");
			
			
			
		} catch (Exception e) {
 
			log.error("Failed");
			log.debug("Debug logs");
			Assert.fail();
		}
		
		log.info("******* C001_AccountRegister Finished *******");
		
		

	}

}
