package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;


public class Utilities {

	public Properties readyProperty() throws FileNotFoundException, IOException{

		Properties prop = new Properties();
		FileInputStream fis;
		if(Constants.PLATFORM.equalsIgnoreCase("ANDROID")) {
			fis = new FileInputStream(Constants.androidpropertyFilePath);
			prop.load(fis);
		}else if(Constants.PLATFORM.equalsIgnoreCase("IOS")){
			fis = new FileInputStream(Constants.iospropertyFilePath);
			prop.load(fis);
		}

		return prop;
	}

	public static String capture(MobileDriver<MobileElement> driver,String screenShotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") +"/Screenshots/"+System.currentTimeMillis()+
				screenShotName+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);        

		return dest;
	}

	public boolean isElementPresent(MobileDriver<MobileElement> driver, MobileElement elementName, int timeout) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(elementName));
			return true;
		}catch(Exception e){
			return false;
		}

	}


}
