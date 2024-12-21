package pageobjects;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(name ="firstname")
	WebElement txtFirstName;
	
	
	@FindBy(name ="lastname")
	WebElement txtLastName;
	
	
	@FindBy(name ="email")
	WebElement txtEmail;
	
	
	@FindBy(id ="input-telephone")
	WebElement txtTelePhone;
	
	@FindBy(css = "#input-password")
	WebElement txt_Password;
	
	@FindBy(xpath ="//input[@id='input-confirm']")
	WebElement txtPasswordConfirm;
	
	@FindBy(xpath ="//input[@name='agree']")
	WebElement chkPolicyname;
	
	@FindBy(xpath ="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath ="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	@FindBy(xpath ="//*[@class='list-group']//a")
	WebElement linkNavigationalLinks;
	
	public void setFirstName (String fname)
	{
		txtFirstName.sendKeys(fname);
	}

	public void setLastName (String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tele)
	{
		txtTelePhone.sendKeys(tele);
	}
	
	public void setPassword(String pswd)
	{
		txt_Password.sendKeys(pswd);
	}
	
	public void setConfirmPassword(String pswd)
	{
		txtPasswordConfirm.sendKeys(pswd);
	}
	
	public void setPrivacyPolicy()
	{
		chkPolicyname.click();
	}
	
	public void clickContinue() {
	    //sol1
	    btnContinue.click();

	    //sol2
	    //btnContinue.submit();

	    //sol3
	    //Actions act=new Actions(driver);
	    //act.moveToElement(btnContinue).click().perform();

	    //sol4
	    //JavascriptExecutor js=(JavascriptExecutor)driver;
	    //js.executeScript("arguments[0].click();", btnContinue);

	    //Sol 5
	    //btnContinue.sendKeys(Keys.RETURN);

	    //Sol6
	    //WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    //mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}

	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
	
	public int getNavigationalLinksCount() {
	    try {
	        // Ensure linkNavigationalLinks is a list of WebElements
	        List<WebElement> linkNavigational = (List<WebElement>) linkNavigationalLinks;
	        int linksCount = linkNavigational.size();
	        System.out.println(linksCount);
	        return linksCount;
	    } catch (Exception e) {
	        // Handle the exception appropriately, maybe log it
	        System.out.println("Error occurred: " + e.getMessage());
	        return 0; // Return 0 if there is an exception
	    }
	}

	
	
}
