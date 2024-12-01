package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {

	public Homepage(WebDriver driver) {

		super(driver);
	}

	@FindBy(linkText = "Login")
	WebElement log;

	public void clickAccount() {
		WebElement Account = driver.findElement(By.xpath("//span[text()='My Account']"));

		Actions act = new Actions(driver);
		act.click(Account).build().perform();
	}

	public void clickRegister() {

		WebElement register = driver.findElement(By.xpath("//a[text()='Register']"));
		Actions act = new Actions(driver);
		act.click(register).build().perform();

	}

	public void clickLogin() {
		WebElement log=driver.findElement(By.linkText("Login"));
		Actions act=new Actions(driver);
		act.click(log).build().perform();
	}

}
