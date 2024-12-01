package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement pwd;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement login;

	public void setEmail(String Email) {
		email.sendKeys(Email);
	}

	public void setPassword(String Pwd) {
		pwd.sendKeys(Pwd);
	}

	public void clickButton() {
		login.click();
	}
}
