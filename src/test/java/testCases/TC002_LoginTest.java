package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	@Test(groups = {"Sanity","Master"})
	public void verifyLogin() {
		try {
		logger.info("*********** Starting TC002_LoginTest *********");
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp = new LoginPage(driver);
		lp.setEMailAddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickOnLogin();
		
		//my Account Page
		MyAccountPage mp= new MyAccountPage(driver);
		boolean targetPage=mp.isMyAccountPageExists();
		Assert.assertTrue(targetPage); 
		//Assert.assertEquals(targetPage, true,"Login Fails");
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*********** Finished TC002_LoginTest *********");
		
	}

}
