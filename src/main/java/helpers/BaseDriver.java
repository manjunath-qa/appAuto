package helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.Constants;
import utils.Utilities;

public class BaseDriver {

	Utilities utils = new Utilities();	
	public AndroidDriver<MobileElement> driver = null;
	public IOSDriver<MobileElement> driver1 = null;

	public AndroidDriver<MobileElement> getAndroidMobileDriver() throws Exception{

		DesiredCapabilities cap = new DesiredCapabilities();
		String url = "http:/0.0.0.0:4723/wd/hub";

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, utils.readyProperty().getProperty("deviceName")); 
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, utils.readyProperty().getProperty("platformName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, utils.readyProperty().getProperty("platformVersion"));
		cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, Constants.androidAppPath);
		cap.setCapability(MobileCapabilityType.NO_RESET, false);

		cap.setCapability("appPackage", utils.readyProperty().getProperty("appPackage")); 
		cap.setCapability("appActivity", utils.readyProperty().getProperty("appActivity")); 

		driver =  new AndroidDriver<MobileElement>(new URL(url), cap);	
		System.out.println("Driver inside setupCapabilities is : "+ driver);

		return driver;
	}


	public IOSDriver<MobileElement> getIOSDriver() throws FileNotFoundException, IOException{

		String url = "http:/0.0.0.0:4723/wd/hub";
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(IOSMobileCapabilityType.BUNDLE_ID, utils.readyProperty().getProperty("udid"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, utils.readyProperty().getProperty("deviceName")); 
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, utils.readyProperty().getProperty("platformName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, utils.readyProperty().getProperty("platformVersion"));

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		
		cap.setCapability(MobileCapabilityType.APP, Constants.iOSAppPath);
		cap.setCapability(MobileCapabilityType.NO_RESET, false);

		cap.setCapability("appPackage", utils.readyProperty().getProperty("appPackage")); 
		cap.setCapability("appActivity", utils.readyProperty().getProperty("appActivity")); 

		driver1 =  new IOSDriver<MobileElement>(new URL(url), cap);	
		System.out.println("Driver inside setupCapabilities is : "+ driver);
		
		return driver1;

	}

}
