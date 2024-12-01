package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;

public class TC002_Login extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verifyLogin() {

		try {
			Homepage hp = new Homepage(driver);
			hp.clickAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);

			lp.setEmail(p.getProperty("username"));
			lp.setPassword(p.getProperty("password"));
			lp.clickButton();

			MyAccount ac = new MyAccount(driver);

			boolean result = ac.MyAccount();

			Assert.assertTrue(result);
		}

		catch (Exception e) {
			Assert.fail();
		}

	}

}
