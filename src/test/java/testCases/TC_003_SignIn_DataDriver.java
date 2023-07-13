package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.SignInPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_SignIn_DataDriver extends BaseClass{
	
	@Test(dataProvider="SignInData", dataProviderClass=DataProviders.class)
	public void dataDriver(String email, String password, String st) {
		
		log.info("********** Started the TC_003_SignIn_DataDriver ***********");
	
		try
		{
		HomePage hp=new HomePage(driver);
		hp.signIn();
		
			log.info("Entering the user data");
			
			Thread.sleep(3000);
		SignInPage signIn=new SignInPage(driver);
		signIn.username(email);
		signIn.password(password);
		signIn.sign_In();
		
			log.info("signed in successfully");
			
			Thread.sleep(3000);
		
		MyAccountPage myap=new MyAccountPage(driver);
		boolean target=myap.verifytitle();
		
		if(st.equals("Valid")) {
			if(target==true) {
				myap.sign_out();
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		}
		
		if(st.equals("Invalid"))
		{
			if(target==false) {
				Assert.assertTrue(true);
			}else {
				myap.sign_out();
				Assert.assertTrue(false);
			}
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		log.info("********** finished the TC_002_SignInTest ***********");
  }

}
