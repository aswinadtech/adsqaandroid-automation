package com.twc.ios.app.pages;

import org.openqa.selenium.By;

import com.twc.ios.app.charlesfunctions.CharlesProxy;
import com.twc.ios.app.functions.Functions;
import com.twc.ios.app.general.Driver;
import com.twc.ios.app.general.TestBase;
import com.twc.ios.app.general.Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

public class AndroidNewsCardScreen extends Utils {
	AppiumDriver<MobileElement> Ad;
	String newsGridItem0_Id = "com.weather.Weather:id/news_grid_item_0";
	
	By byNewsCard = MobileBy.id("com.weather.Weather:id/card_news_view");
	By byNewsGridItem0 = MobileBy.id(newsGridItem0_Id);
	
	
	MobileElement newsCard = null;
	MobileElement newsGridItem0 = null;
	

	public AndroidNewsCardScreen(AppiumDriver<MobileElement> Ad) {
		this.Ad = Ad;
	}

	@Step("Navigate To News Card Content Page")
	public void navigateToNewsCardContentPage() throws Exception {
		newsGridItem0 = Ad.findElement(byNewsGridItem0);
		TestBase.clickOnElement(byNewsGridItem0, newsGridItem0, "News Card Details");
		TestBase.waitForMilliSeconds(6000);
		attachScreen();
		navigateBackToFeedCardAndroid();
	/*	CharlesProxy.proxy.stopRecording();
		Functions.archive_folder("Charles");
		TestBase.waitForMilliSeconds(5000);
		CharlesProxy.proxy.getXml();
		Utils.createXMLFileForCharlesSessionFile();
		if (Utils.isInterStitialAdCalExists("Smoke", "News(details)")) {

			if (Utils.isInterstitialCall_hasResponse("Smoke", "News(details)")) {
				if (unlimitedInterstitial) {
					handle_Interstitial_Ad();
				} else {
					if (!interStitialDisplayed) {
						
						handle_Interstitial_Ad();
					} else {
						System.out.println("Interstitial Ad is already handled, hence not handling again");
						logStep("Interstitial Ad is already handled, hence not handling again");

					}
				}
			}
		}
		Functions.delete_folder("Charles");	
		CharlesProxy.proxy.startRecording();*/
	}
	
	@Step("Navigate To News Card Content Page And Not to Handle Interstitials")
	public void navigateToNewsCardContentPageAndDontHandleInterstitials() throws Exception {
		newsGridItem0 = Ad.findElement(byNewsGridItem0);
		TestBase.clickOnElement(byNewsGridItem0, newsGridItem0, "News Card Details");
		TestBase.waitForMilliSeconds(6000);
		attachScreen();
	}

	@Step("Navigate To News Details")
	public void navigateToNewsDetails() throws Exception {
		try {
			newsGridItem0 = Ad.findElement(byNewsGridItem0);
			TestBase.clickOnElement(byNewsGridItem0, newsGridItem0, "News Card Details");
			TestBase.waitForMilliSeconds(6000);
			attachScreen();
			System.out.println("Navigated to News Details ");
			logStep("Navigated to News Details ");
		} catch (Exception e) {
			System.out.println("News Details not displayed");
			logStep("News Details not displayed");
			// attachScreen();
		}
		
	}
	
	public  void scrollToNewsCard() throws Exception {
		//byNewsCard = MobileBy.AccessibilityId("news-card-containerView");
		//aQCard = Ad.findElement(byAirQualityCard);
		Functions.genericScroll(byNewsCard, true, true, getOffsetYTop(), TOLERANCE_FROM_TOP);
		//Functions.genericScrollTWC(byNewsCard, true, true, getOffsetYTop(), TOLERANCE_FROM_TOP, false, false);
	}
}
