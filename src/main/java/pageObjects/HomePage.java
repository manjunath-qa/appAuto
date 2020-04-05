package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.Utilities;

public class HomePage {

	MobileDriver<MobileElement> driver;
	Utilities utils = new Utilities();

	public HomePage(MobileDriver<MobileElement> driver2){
		this.driver=driver2;
		PageFactory.initElements(new AppiumFieldDecorator(driver2), this);
	}

	@iOSXCUITFindBy(id="")
	@AndroidFindBy(id="geekboy.placefinder:id/etSearch")
	private MobileElement searchBox;

	@iOSXCUITFindBy(id="")
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.Button")
	private MobileElement searchButton;

	@iOSXCUITFindBy(id="")
	@AndroidFindBy(id="geekboy.placefinder:id/tvPlaceNearby")
	private MobileElement locationNameDisplayed;


	public void searchText(String text){
		if(utils.isElementPresent(driver, searchBox, 10)) {
			searchBox.click();
			searchBox.sendKeys(text);
		}
	}

	public void clickSearchButton(){
		if(utils.isElementPresent(driver, searchButton, 10)) {
			searchButton.click();
		}
	}


	public String getLocationNameDisplayed(){
		if(utils.isElementPresent(driver, locationNameDisplayed, 20)) {
			return 	locationNameDisplayed.getText();
		}else {
			return null;
		}

	}


}
