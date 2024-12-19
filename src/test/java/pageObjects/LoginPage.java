package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "email")
	WebElement txtEmailAddress;

	@FindBy(id = "input-password")
	WebElement txtPassword;

	@FindBy(xpath = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']")
	WebElement linkForgottenPassword;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;

	public void setEMailAddress(String email) {
		txtEmailAddress.sendKeys(email);
	}

	public void setPassword(String pswd)

	{
		txtPassword.sendKeys(pswd);
	}

	public void clickOnLogin()

	{
		btnLogin.click();
	}
}
