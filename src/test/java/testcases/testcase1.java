package testcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helpers.BaseDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import pageObjects.HomePage;
import utils.Constants;
import utils.Utilities;


public class testcase1 extends BaseDriver {
	MobileDriver<MobileElement> driver;
	HomePage homePage;
	Utilities utils = new Utilities();

	String[] locationNames = new String[] {"Hospitals","Colleges","Hotels","Shopping Malls","Temples"};
	int repeatation = locationNames.length;

	List<String> list;
	static ExtentTest test;
	static ExtentReports report;


	@BeforeTest
	public void setup() throws Exception {

		if(Constants.PLATFORM.equalsIgnoreCase("ANDROID")) {
			driver= getAndroidMobileDriver();
		}else if(Constants.PLATFORM.equalsIgnoreCase("IOS")) {
			driver= getIOSDriver();

		}
		report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResults.html", false);
		test = report.startTest(this.getClass().getName());
	}

	@Test(priority=1)
	public void searchPlacesNearBy() throws InterruptedException, FileNotFoundException, IOException {
		homePage = new HomePage(driver);
		SoftAssert softAssert = new SoftAssert();

		for(int i=0;i<repeatation;i++) {

			homePage.searchText(locationNames[i]);
			homePage.clickSearchButton();

			String locationNameOnMapScreen = homePage.getLocationNameDisplayed();

			if(locationNameOnMapScreen!=null) {
				System.out.println("Search successful for : "+locationNames[i]);
			}else {
				System.out.println("Search failed for : "+locationNames[i]);
			}

			softAssert.assertEquals(locationNameOnMapScreen, (locationNames[i]+" near you"));

			((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));

		}

		System.out.println("Extent Report generated at: "+ 
				System.getProperty("user.dir")+"/ExtentReportResults.html");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, "Test Method name: "+ result.getName());
			test.log(LogStatus.FAIL, "Test Method status: "+ "Test "+LogStatus.FAIL.toString().toLowerCase()+"ed");
			String screenShotPath = Utilities.capture(driver, "Fail-screenShotName");
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
		}

		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS, "Test Method name: "+ result.getName());
			test.log(LogStatus.PASS, "Test Method status: "+ "Test "+LogStatus.PASS.toString().toLowerCase()+"ed");
			String screenShotPath = Utilities.capture(driver, "Pass-screenShotName");
			test.log(LogStatus.PASS, "Snapshot below: " + test.addScreenCapture(screenShotPath));
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
	}


	@AfterTest
	public  void endTest(){
		report.endTest(test);
		report.flush();
		report.close();
		driver.quit();
	}

}
