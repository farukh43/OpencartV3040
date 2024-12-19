package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid  - login success  - test pass   - logout
Data is valid -- login failed  - test fail

Data is invalid - login success - test fail   - logout
Data is invalid -- login failed - test pass
*/

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = {"Datadriven"}) // getting data provide from different class
	public void verify_loginDDT(String email, String pwd, String expectedDataType) {
		logger.info("****** Starting TC003_LoginDDT   *********");

try {		
		// HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();

		// Login
		LoginPage lp = new LoginPage(driver);
		lp.setEMailAddress(email);
		lp.setPassword(pwd);
		lp.clickOnLogin();

		// my Account Page
		MyAccountPage mp = new MyAccountPage(driver);
		boolean targetPage = mp.isMyAccountPageExists();
		//Assert.assertTrue(targetPage);

		if (expectedDataType.equalsIgnoreCase("valid")) {
			if (targetPage == true) {
				mp.clickOnLogout();
				Assert.assertTrue(true);

			} else {
				Assert.assertTrue(false);
			}
		}

		if (expectedDataType.equalsIgnoreCase("invalid")) {
			if (targetPage == true) {
				mp.clickOnLogout();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
	} catch (Exception e) {
		Assert.fail();
	}

	logger.info("****** Finished TC003_LoginDDT   *********");
}
}
