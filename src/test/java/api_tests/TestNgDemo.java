package api_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SauceDemoPage;
import utilities.Driver;

public class TestNgDemo {
	
	SauceDemoPage page;
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test Method.");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("After Test Method.");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method.");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method.");
	}
	
	@Test (priority=-3, description="UI test demo with TestNG", groups={"smoketest", "regression"})
	public void uiTestDemo() {
		page = new SauceDemoPage();
		//Test case 1
		//go to https://saucedemo.com
		Driver.getDriver().get("https://saucedemo.com");
		//log in with valid username = standard_user   password = secret_sauce
		page.username.sendKeys("standard_user");
		page.password.sendKeys("secret_sauce");
		page.loginBtn.click();
		//Verify that you are logged in successfully.
		Assert.assertEquals(page.inventoryItems.size(), 6);
	}
	
	@Test (priority=10, description="Hard Assert Demo", groups="smoketest")
	public void assertionDemo() {
		// Hard assert.  if the condition fails,  it stops the execution of the code.
		Assert.assertEquals(true, true);
		System.out.println("// Hard assert.  if the condition fails,  it stops the execution of the code.");
		
	}
	
	@Test (priority=4, description="SoftAssert demo", dependsOnMethods="assertionDemo")
	public void softAssertDemo() {
		SoftAssert softassert = new SoftAssert();
		// soft assert.  if the condition fails,  it DOES NOT stop the execution of the code.
		// but it marks the test as fail.
		softassert.assertEquals(true, true);
		System.out.println("// soft assert.  if the condition fails,  it DOES NOT stop the execution of the code.");
		
		
		// in order for soft assert to mark the test fail with exception/errors, we need to have this line
		softassert.assertAll();
	}

}
