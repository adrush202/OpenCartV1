package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement txtfirstname;

	@FindBy(xpath = "//input[@name='lastname']")
	WebElement txtlastname;

	@FindBy(xpath = "//input[@name='email']")
	WebElement txtemail;

	@FindBy(xpath = "//input[@name='telephone']")
	WebElement txtphone;

	@FindBy(xpath = "//input[@name='password']")
	WebElement txtpwd;

	@FindBy(xpath = "//input[@name='confirm']")
	WebElement txtcnfrm;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkagree;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msg;

	public void setFirstName(String fname) {
		txtfirstname.sendKeys(fname);

	}

	public void setLastName(String lname) {
		txtlastname.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtemail.sendKeys(email);
	}

	public void setTelephone(String tel) {
		txtphone.sendKeys(tel);

	}

	public void setPassword(String pwd) {
		txtpwd.sendKeys(pwd);
	}

	public void setCnfPassword(String pwd) {
		txtcnfrm.sendKeys(pwd);
	}

	public void setAgree() {
		chkagree.click();
	}
	
	public void setButton() {
		btnContinue.click();
	}
	
	public String confirmMessage() {
		
		return msg.getText();
	}
	

}
