package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage {

	public MyAccount(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//h2[text()='My Account']")

	WebElement header;
	
	@FindBy(xpath="(//a[text()='Logout'])[2]")
	WebElement logout;

	public boolean MyAccount() {

		try {
			return (header.isDisplayed());
		}

		catch (Exception e) {
			return false;
		}
	}
	
	public void logout() {
		
		logout.click();
	}

}
