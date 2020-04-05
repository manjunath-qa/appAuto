package utils;

public interface Constants {

	String projectDir = System.getProperty("user.dir");
	public static final String PLATFORM="ANDROID";    // Change this to IOS if want to run for ios automation
	
	public static final String androidAppPath=projectDir+"/apps/testingApp.apk";
	public static final String iOSAppPath=projectDir+"/apps/iostestingApp.app";
	public static final String androidpropertyFilePath=projectDir+"/androidproperties";
	public static final String iospropertyFilePath=projectDir+"/iosproperties";
}
