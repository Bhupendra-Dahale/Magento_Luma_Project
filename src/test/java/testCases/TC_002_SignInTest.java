package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.SignInPage;
import testBase.BaseClass;

public class TC_002_SignInTest extends BaseClass{
	
	@Test(groups= {"Sanity","Master"})
	public void SignIn_TestCase(){
		
		log.info("********** Started the TC_002_SignInTest ***********");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.signIn();
		
			log.info("Entering the user data");
			
			Thread.sleep(3000);
		SignInPage signIn=new SignInPage(driver);
		signIn.username(rb.getString("Email"));
		signIn.password(rb.getString("Pwd"));
		signIn.sign_In();
		
			log.info("signed in successfully");
			
			Thread.sleep(3000);
		
		MyAccountPage myap=new MyAccountPage(driver);
		boolean target=myap.verifytitle();
		Assert.assertEquals(target, true);
		
		  log.info("Title matched successfully");
		  
		  myap.sign_out();
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		log.info("********** finished the TC_002_SignInTest ***********");
	}
}