package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups= {"Regression","Master"})
	public void test_account_Registration() throws InterruptedException
	{
		log.debug("...........Debugging is started..........");
		log.info("**********TC_001_AccountRegistrationTest**********");
		try
		{
			
		HomePage hp=new HomePage(driver);
		hp.createAnAccount();
		log.info(".......clicked of create an account.......");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		log.info(".......providing customers data.......");
		
		regpage.setFirstName(randomeString().toUpperCase());
		
		regpage.setLastName(randomeString().toUpperCase());
		
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		
//		regpage.setTelephone(randomeNumber());

		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
//		regpage.setPrivacyPolicy();
		
//		regpage.newsLetter();
		
		regpage.clickContinue();
		
		Thread.sleep(5000);
		
		log.info(".......data provided.......");
		
		String confmsg=regpage.getConfirmationMsg();
		
		log.info(".......getting confirmation message.......");
		
		Assert.assertEquals(confmsg, "Thank you for registering with Main Website Store.");
		
		log.info(".......Account created successfully.......");
		}
		catch(Exception e)
		{
			Assert.fail();
			log.info(".......Problem in creating Account.......");
		}
		log.info("**********Finished TC_001_AccountRegistrationTest**********");
	}
	
	
}
