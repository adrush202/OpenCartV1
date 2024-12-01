package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import utilities.DataProviders;

public class TC003_LoginData extends BaseClass {

	@Test(dataProvider = "LoginData",dataProviderClass=DataProviders.class,groups={"DataDriver","Master"})
	public void verify_login(String email, String pwd, String exp) {

		try {

			Homepage hp = new Homepage(driver);
			hp.clickAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);

			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickButton();

			MyAccount ac = new MyAccount(driver);

			boolean result = ac.MyAccount();

			if (exp.equalsIgnoreCase("Valid")) {
				if (result == true) {
					ac.logout();
					Assert.assertTrue(true);

				}

				else {
					Assert.assertFalse(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {

				if (result == true) {
					ac.logout();
					Assert.assertTrue(false);

				}

				else {
					Assert.assertTrue(true);
				}
			}
		}

		catch (Exception e) {
			Assert.fail();
		}

	}

}
