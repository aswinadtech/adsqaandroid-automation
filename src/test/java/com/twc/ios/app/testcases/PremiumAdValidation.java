package com.twc.ios.app.testcases;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.twc.ios.app.charlesfunctions.CharlesProxy;
import com.twc.ios.app.functions.Functions;
import com.twc.ios.app.general.ParseForVideoOrderedList;
import com.twc.ios.app.general.TestBase;
import com.twc.ios.app.general.TwcIosBaseTest;
import com.twc.ios.app.general.Utils;
import com.twc.ios.app.pages.AddressScreen;
import com.twc.ios.app.pages.AirQualityCardScreen;
import com.twc.ios.app.pages.DailyNavTab;
import com.twc.ios.app.pages.FTLScreens;
import com.twc.ios.app.pages.FeedOneCardScreen;
import com.twc.ios.app.pages.HomeNavTab;
import com.twc.ios.app.pages.HourlyNavTab;
import com.twc.ios.app.pages.LogInScreen;
import com.twc.ios.app.pages.PlanningCardScreen;
import com.twc.ios.app.pages.RadarNavTab;
import com.twc.ios.app.pages.SeasonalHubCardScreen;
import com.twc.ios.app.pages.SettingsScreen;
import com.twc.ios.app.pages.VideoNavTab;



//---Main method for execute the test

/**
 * @author Naresh
 *
 */
public class PremiumAdValidation extends TwcIosBaseTest {

	//private static final String CONFIG_FILE_PATH = "charles_common.config";
	private static final String CONFIG_FILE_PATH = "enableUSA.config";

	// public static CharlesProxy proxy;
	public File configFile;
	HourlyNavTab hrTab;
	DailyNavTab dTab;
	HomeNavTab hmTab;
	RadarNavTab rTab;
	VideoNavTab vTab;
	AddressScreen addrScreen;
	PlanningCardScreen pScreen;
	SeasonalHubCardScreen sScreen;
	SettingsScreen stScreen;
	LogInScreen loginScreen;
	FTLScreens ftlScreens;
	AirQualityCardScreen aqCardScreen;
	FeedOneCardScreen fOneCardScreen;
	
	By bySettingsButton = MobileBy.AccessibilityId("settingsButton");
	
	MobileElement settingsButton = null;
	HashMap<Integer, Float> dTime = new HashMap<Integer, Float>();
	
	@BeforeClass(alwaysRun = true)
	@Description("BeforeClass")
	public void beforeClass() {
		System.out.println("****** Smoke Test Started");
		logStep("****** Smoke Test Started");
		//this.configFile = this.charlesGeneralConfigFile(CONFIG_FILE_PATH);
		//this.configFile = this.rewriteRuleToEnableUSA(CONFIG_FILE_PATH);
		this.configFile = this.rewriteRuleToEnableUSAWhenNoTunnelBear(CONFIG_FILE_PATH, "usa", "US", "WA");
		proxy = new CharlesProxy("localhost", 8111, CONFIG_FILE_PATH);
		proxy.startCharlesProxyWithUI();
		proxy.disableRewriting();
		proxy.stopRecording();
		proxy.disableMapLocal();
	}

	@AfterClass(alwaysRun = true)
	@Description("AfterClass")
	public void afterClass() throws Exception {
		System.out.println("****** After Class Started");
		logStep("****** After Class Started");
		for (Map.Entry m : dTime.entrySet()) {
			System.out.println("Iteration No: " + m.getKey() + " " + " Ad display Time is: " + m.getValue());
			//System.out.println(m.getValue());
		}
		Ad.terminateApp("com.weather.TWC");
		proxy.quitCharlesProxy();
		Ad.quit();
	}

	@Test(priority = 0)
	@Description("Updating Device Proxy Details and Launch the App")
	public void beforeTest() throws Exception {
		System.out.println("==============================================");
		System.out.println("****** Updating Device Proxy Details and Launch the App test case Started");
		logStep("****** Updating Device Proxy Details and Launch the App test case Started");
		// Preconditions
		Utils.getiPhoneUDID();
		Utils.getiOSVersion();
		Functions.capabilities();
		Functions.Appium_Autostart();
//		Utils.getCurrentMacIPAddressAndSetiPhoneProxy(true, true);
		// Enable rewriting to USA on Charles because Tunnel Bear not working in India
		proxy.enableRewriting();
		proxy.startRecording();
		proxy.clearCharlesSession();
		Functions.archive_folder("Charles");
		Functions.launchtheApp("true");
		System.out.println("App launched ");
		logStep("App launched ");
		Functions.archive_folder("Charles");
	//	proxy.getXml();
	//	Utils.createXMLFileForCharlesSessionFile();
	//	proxy.clearCharlesSession();
		
				
	}
	
	@Test(priority = 1, enabled = false)
	@Description("Validating HomeScreen IMAd")
	public void Validating_Home_page_IMAd() throws Exception {
		System.out.println("================= Premium Ad Test Started =========================");
		logStep("Validating HomeScreen IMAd ");
		/*
		 * try{ Ad.closeApp(); Ad.launchApp(); }catch(Exception e){
		 * Functions.close_launchApp(); }
		 */

		// Functions.launchtheProductionApp("true");
	//	Ad.launchApp();
		Ad.terminateApp("com.weather.TWC");
		// readExcelValues.excelValues("Smoke","Zipcode");
		// Functions.enterAddress(readExcelValues.data[1][Functions.Cap]);
		// Thread.sleep(10000);
		// Ad.launchApp();
		
		for (int i = 1; i <= 200; i++) {
			System.out.println("Iteration Number: " + i);
			Ad.launchApp();
			
			TestBase.waitForVisibilityOfElementLocated(Ad, 30, bySettingsButton);
			//settingsButton = Ad.findElement(bySettingsButton);
			// finding the time before the operation is executed
			System.out.println("Start time");
			long start = System.currentTimeMillis();

			// MobileElement adElement= (MobileElement)
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.weather.Weather:id/ad_view_holder")));
			// MobileElem//MobileElement adElement= (MobileElement)
			// Functions.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name=\"Integrated
			// Marquee\"]")));
			// MobileElement adElement= (MobileElement)
			// Functions.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name=\"nextgen-integrated-marquee-card-containerView\"]")));
			try {
				MobileElement adElement = (MobileElement) new WebDriverWait(Ad, 15).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name='nextgen-integrated-marquee-card-containerView']//XCUIElementTypeOther[@name='an ad']")));
			} catch (Exception e) {
				System.out.println("Ad not loaded in iteration: "+i);
			}
			
			//XCUIElementTypeOther[@name='nextgen-integrated-marquee-card-containerView']//XCUIElementTypeOther[@name='an ad']

// finding the time after the operation is executed
			long end = System.currentTimeMillis();
			System.out.println("End time");
//finding the time difference and converting it into seconds
			float sec = (end - start) / 1000F;
			dTime.put(i, sec);
			System.out.println(sec + " seconds");
			logStep(sec + " seconds");
			//Ad.closeApp();
			Ad.terminateApp("com.weather.TWC");
		}
		

		System.out.println("================= Premium Ad Test Ended =========================");
	}
	
	@Test(priority = 2, enabled = true)
	@Description("Validating HomeScreen StickyAd")
	public void Validating_Home_page_StickyAd() throws Exception {
		System.out.println("================= Sticky Ad Test Started =========================");
		logStep("Validating HomeScreen Sticky Ad ");
		/*
		 * try{ Ad.closeApp(); Ad.launchApp(); }catch(Exception e){
		 * Functions.close_launchApp(); }
		 */

		// Functions.launchtheProductionApp("true");
	//	Ad.launchApp();
		Ad.terminateApp("com.weather.TWC");
		// readExcelValues.excelValues("Smoke","Zipcode");
		// Functions.enterAddress(readExcelValues.data[1][Functions.Cap]);
		// Thread.sleep(10000);
		// Ad.launchApp();
		
		for (int i = 1; i <= 200; i++) {
			System.out.println("Iteration Number: " + i);
			Ad.launchApp();
			
			TestBase.waitForVisibilityOfElementLocated(Ad, 30, bySettingsButton);
			//settingsButton = Ad.findElement(bySettingsButton);
			// finding the time before the operation is executed
			System.out.println("Start time");
			long start = System.currentTimeMillis();
			

			// MobileElement adElement= (MobileElement)
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.weather.Weather:id/ad_view_holder")));
			// MobileElem//MobileElement adElement= (MobileElement)
			// Functions.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name=\"Integrated
			// Marquee\"]")));
			// MobileElement adElement= (MobileElement)
			// Functions.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name=\"nextgen-integrated-marquee-card-containerView\"]")));
			try {
				MobileElement adElement = (MobileElement) new WebDriverWait(Ad, 15).until(ExpectedConditions
						.visibilityOfElementLocated(By.name("an ad")));
			} catch (Exception e) {
				System.out.println("Ad not loaded in iteration: "+i);
			}
					
			

// finding the time after the operation is executed
			long end = System.currentTimeMillis();
			System.out.println("End time");
//finding the time difference and converting it into seconds
			float sec = (end - start) / 1000F;
			dTime.put(i, sec);
			System.out.println(sec + " seconds");
			logStep(sec + " seconds");
			//Ad.closeApp();
			Ad.terminateApp("com.weather.TWC");
		}
		

		System.out.println("================= Sticky Ad Test Ended =========================");
	}
	
	

}
